package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
	private int x; //x����
	private int y; //y����
	private int type; //����ϰ�������
	private BufferedImage showImage = null; //��ʾͼ��
	private BackGround bg = null; //���嵱ǰ��������
	private Thread thread = new Thread(this); //����һ���̶߳���
	
	public Obstacle(int x,int y,int type,BackGround bg) {
		// TODO �Զ����ɵĹ��캯�����
		this.x = x;
		this.y = y;
		this.type = type;
		this.bg = bg;
		showImage = StaticValue.obstacle.get(type);
		
		if (type == 8) { //��������������߳�
			thread.start();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while (true) {
			if (this.bg.isReach()) { //�ж�������Ƿ񵽴�����λ��
				if (this.y < 374) { //�ж������Ƿ����
					this.y += 5;
				}else {
					this.bg.setBase(true);
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
