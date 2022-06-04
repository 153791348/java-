package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Mario implements Runnable{
	private int x; //����µĺ�����
	private int y; //����µ�������
	private String status; //��ʾ����µ�ǰ״̬
	private BufferedImage showImage = null; //��ǰ״̬��ͼ��
	private BackGround backGround = new BackGround(); //��ȡ�ϰ�����Ϣ
	private Thread thread = null; //����µĶ���ʵ��
	private int xSpeed; //����µ��ƶ��ٶ�
	private int ySpeed; //����µ���Ծ�ٶ�
	private int index; //����һ������
	private int upTime; //�������Ծ�Ϳ�ʱ��
	private boolean isEnd; //�ж�������Ƿ񵽴�Ǳ��ſ�
	private boolean isDeath = false; //�ж�������Ƿ�����
	
	public Mario() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public Mario(int x,int y) {
		// TODO �Զ����ɵĹ��캯�����
		this.setX(x);
		this.setY(y);
		setShowImage(StaticValue.stand_R);
		this.status = "stand_R";
		thread = new Thread(this);
		thread.start();
	}
	
	public void death() { //�������������
		isDeath = true;
	}
	
	public void leftMove() { //����������ƶ��ķ���
		xSpeed = -5;
		
		if (backGround.isReach()) { //�ж�������Ƿ���������
			xSpeed = 0;
		}	
		
		if (status.indexOf("jump") != -1) {
			status = "jump_L";
		}else {
			status = "move_L";
		}		
	}
	
	public void rightMove() { //����������ƶ��ķ���
		xSpeed = 5;

		if (backGround.isReach()) { //�ж�������Ƿ���������
			xSpeed = 0;
		}	
		
		if (status.indexOf("jump") != -1) {
			status = "jump_R";
		}else {
			status = "move_R";
		}	
	}
	
	public void leftStop() { //���������ֹͣ�ķ���
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump_L";
		}else {
			status = "stop_L";
		}		
	}
	
	public void rightStop() { //���������ֹͣ�ķ���
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump_R";
		}else {
			status = "stop_R";
		}		
	}
	
	public void jump() { //�������Ծ����
		if (status.indexOf("jump") == -1) {
			if (status.indexOf("left") != -1) {
				status = "jump_L";
			}else {
				status = "jump_R";
			}
			ySpeed = -10;
			upTime = 7;
		}
		

		if (backGround.isReach()) { //�ж�������Ƿ���������
			ySpeed = 0;
		}	
	}
	
	public void fall() { //��������䷽��
		if (status.indexOf("left") != -1) {
			status = "jump_L";
		}else {
			status = "jump_R";
		}
		ySpeed = 10;
		
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while (true) {
			boolean onObstacle = false; //�ж��Ƿ����ϰ�����
			boolean can_R = true; //�ж��Ƿ����������
			boolean can_L = true; //�ж��Ƿ����������
			
			if (backGround.isFlag() && this.x >= 500) { //�ж��Ƿ񵽴����
				this.backGround.setReach(true);
				
				if (this.backGround.isBase()) { //�ж������Ƿ��������
					status = "move_L";
					
					if (x < 690) {
						x += 5;
					}else {
						isEnd = true;
					}
				}else {
					if (y <395) {
						xSpeed = 0;
						this.y += 5;
						status = "jump_R";
					}
					
					if (y >395) {
						this.y = 395;
						status = "stop_R";
					}
				}
				
			}else {
				
			
			
			for (int i = 0; i < backGround.getObstacleList().size(); i++) {
				Obstacle obstacle = backGround.getObstacleList().get(i); //����forѭ�������ϰ���
				if (obstacle.getY() == this.y + 25 && (obstacle.getX() > this.x - 30 && obstacle.getX() < this.x + 25)) { //�ж�������Ƿ�λ���ϰ�����
					onObstacle = true;
				}
				
				if ((obstacle.getY() >= this.y - 30 && obstacle.getY() <= this.y - 20) && (obstacle.getX() >this.x - 30 && obstacle.getX() < this.x +25)) { //�ж���Ծ�ܷ񶥵�ש��
					if (obstacle.getType() == 0) {
						backGround.getObstacleList().remove(obstacle);
					}
					upTime = 0;
				}
				
				if (obstacle.getX() == this.x + 25 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) { //�ж��Ƿ����������
					can_R = false;
				}
				
				if (obstacle.getX() == this.x - 30 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) { //�ж��Ƿ����������
					can_L = false;
				}
				
			}
			
			for (int i = 0; i < backGround.getEnemyList().size(); i++) { //forѭ���������ˣ��ж���������������������������
				Enemy enemy = backGround.getEnemyList().get(i);
				
				if (enemy.getY() == this.y + 20 && (enemy.getX() - 25 <= this.x && enemy.getX() + 35 >= this.x)) {
					if (enemy.getType() == 1) { //�ж��Ƿ�ΪĢ��
						enemy.death();
						upTime = 3;
						ySpeed = -10;
					}else if (enemy.getType() == 2) {
						death();
					}
				}
				
				if ((enemy.getX() + 35 > this.x && enemy.getX() - 25 < this.x) && (enemy.getY() + 35 > this.y && enemy.getY() - 20 < this.y)) {
					death();
				}
				
			}
			
			if (onObstacle && upTime == 0) { //��Ծ����
				if (status.indexOf("left") != -1) {
					if (xSpeed != 0) {
						status = "move_L";
					}else {
						status = "stop_L";
					}
				}else {
					if (xSpeed != 0) {
						status = "move_R";
					}else {
						status = "stop_R";
					}
				}
			}else {
				if (upTime != 0) {
					upTime--;
				}else {
					fall();
				}
				y += ySpeed;
			}
			}
			
			if (can_L && xSpeed < 0 ||can_R && xSpeed > 0) { 
				x += xSpeed;
				if (x < 0) { //�ж�������Ƿ��������
					x = 0;
				}
			}
			
			if (status.contains("move")) { //�ж��Ƿ�Ϊ�ƶ�״̬
				index = index == 0 ? 1 : 0;
			}
			
			if ("move_L".equals(status)) { //�ж��Ƿ������ƶ�
				showImage = StaticValue.run_L.get(index);
			}
			
			if ("move_R".equals(status)) { //�ж��Ƿ������ƶ�
				showImage = StaticValue.run_R.get(index);
			}
			
			if ("stop_L".equals(status)) { //�ж��Ƿ�����ֹͣ
				showImage = StaticValue.stand_L;
			}
			
			if ("stop_R".equals(status)) { //�ж��Ƿ�����ֹͣ
				showImage = StaticValue.stand_R;
			}
			
			if ("jump_L".equals(status)) { //�ж��Ƿ�������Ծ
				showImage = StaticValue.jump_L;
			}
			
			if ("jump_R".equals(status)) { //�ж��Ƿ�������Ծ
				showImage = StaticValue.jump_R;
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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getShowImage() {
		return showImage;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public boolean isDeath() {
		return isDeath;
	}
	

}
