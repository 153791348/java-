package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable{
	private int x; //������˵�ǰx����
	private int y; //������˵�ǰy����
	private int type; //�����������
	private boolean move_to = true; //�жϵ����˶�����
	private BufferedImage showImage; //��ʾ���˵�ǰͼ��
	private BackGround bg; //��������
	private int up = 0; //ʳ�˻������˶���Χ
	private int down = 0; //ʳ�˻������˶���Χ
	private Thread thread =new Thread(this); //����һ���߳�
	private int image_type = 0; //��ǰͼƬ״̬
	
	public Enemy(int x,int y,boolean move_to,int type,BackGround bg) {
		// TODO �Զ����ɵĹ��캯�����,Ģ��
		this.x = x;
		this.y = y;
		this.move_to = move_to;
		this.type = type;
		this.bg = bg;
		showImage = StaticValue.mogu.get(0); //��ʼ��Ģ��ͼ��
		thread.start();
	}
	
	public Enemy(int x,int y,boolean move_to,int type,int up,int down, BackGround bg) {
		// TODO �Զ����ɵĹ��캯�����,ʳ�˻�
		this.x = x;
		this.y = y;
		this.move_to = move_to;
		this.type = type;
		this.up = up;
		this.down = down;
		this.bg = bg;
		showImage = StaticValue.flower.get(0); //��ʼ��ʳ�˻�ͼ��
		thread.start();
	}
	
	public void death() { //��������
		showImage = StaticValue.mogu.get(2);
		this.bg.getEnemyList().remove(this);
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while (true) {
			if (getType() == 1) { //�ж��Ƿ�ΪĢ��
				if (move_to) { //�ж��˶�����TΪ����F����
					this.x -= 2;
				}else {
					this.x += 2;
				}				
				image_type = image_type == 1 ? 0 :1; //��Ŀ������жϸı�״̬		
				
				showImage = StaticValue.mogu.get(image_type);
			}
			
			boolean can_L = true; //�ж��ܷ������ƶ�
			boolean can_R = true; //�ж��ܷ������ƶ�
			
			for (int i = 0; i < bg.getObstacleList().size(); i++) { //����forѭ�������ϰ���
				Obstacle obstacle = bg.getObstacleList().get(i);
				
				if (obstacle.getX() == this.x + 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) { //�жϵ����Ƿ����������
					can_R = false;
				}
				
				if (obstacle.getX() == this.x - 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) { //�жϵ����Ƿ����������
					can_L = false;
				}				
			}
			
			if (move_to && !can_L || this.x == 0) { //�ж�Ģ���Ƿ������ƶ��������ϰ���
				move_to = false;
			}else if ((!move_to) && (!can_R) || this.x == 764) { //�ж�Ģ���Ƿ������ƶ��������ϰ���
				move_to = true;
			}
			
			if (getType() == 2) { //�ж��Ƿ�Ϊʳ�˻�
				if (move_to) { //�ж��˶�����TΪ���ϣ�F����
					this.y -= 2;
				}else {
					this.y += 2;
				}			
				image_type = image_type == 1 ? 0 :1; //��Ŀ������жϸı�״̬
				
				if (move_to && (this.y == up)) { //�ж�ʳ�˻��Ƿ��ϼ���λ��
					move_to = false;
				}else if ((!move_to) && (this.y == down)) { //�ж�ʳ�˻��Ƿ��¼���λ��
					move_to = true;
				}
				
				showImage = StaticValue.flower.get(image_type);			
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	
	public BufferedImage getShowImage() {
		return showImage;
	}

	public int getType() {
		return type;
	}
	

}
