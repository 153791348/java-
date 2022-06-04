package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Mario implements Runnable{
	private int x; //马里奥的横坐标
	private int y; //马里奥的纵坐标
	private String status; //表示马里奥当前状态
	private BufferedImage showImage = null; //当前状态的图像
	private BackGround backGround = new BackGround(); //获取障碍物信息
	private Thread thread = null; //马里奥的动作实现
	private int xSpeed; //马里奥的移动速度
	private int ySpeed; //马里奥的跳跃速度
	private int index; //定义一个索引
	private int upTime; //马里奥跳跃滞空时间
	private boolean isEnd; //判断马里奥是否到达城堡门口
	private boolean isDeath = false; //判断马里奥是否死亡
	
	public Mario() {
		// TODO 自动生成的构造函数存根
	}
	
	public Mario(int x,int y) {
		// TODO 自动生成的构造函数存根
		this.setX(x);
		this.setY(y);
		setShowImage(StaticValue.stand_R);
		this.status = "stand_R";
		thread = new Thread(this);
		thread.start();
	}
	
	public void death() { //马里奥死亡方法
		isDeath = true;
	}
	
	public void leftMove() { //马里奥向左移动的方法
		xSpeed = -5;
		
		if (backGround.isReach()) { //判断马里奥是否碰到旗子
			xSpeed = 0;
		}	
		
		if (status.indexOf("jump") != -1) {
			status = "jump_L";
		}else {
			status = "move_L";
		}		
	}
	
	public void rightMove() { //马里奥向右移动的方法
		xSpeed = 5;

		if (backGround.isReach()) { //判断马里奥是否碰到旗子
			xSpeed = 0;
		}	
		
		if (status.indexOf("jump") != -1) {
			status = "jump_R";
		}else {
			status = "move_R";
		}	
	}
	
	public void leftStop() { //马里奥向左停止的方法
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump_L";
		}else {
			status = "stop_L";
		}		
	}
	
	public void rightStop() { //马里奥向右停止的方法
		xSpeed = 0;
		if (status.indexOf("jump") != -1) {
			status = "jump_R";
		}else {
			status = "stop_R";
		}		
	}
	
	public void jump() { //马里奥跳跃方法
		if (status.indexOf("jump") == -1) {
			if (status.indexOf("left") != -1) {
				status = "jump_L";
			}else {
				status = "jump_R";
			}
			ySpeed = -10;
			upTime = 7;
		}
		

		if (backGround.isReach()) { //判断马里奥是否碰到旗子
			ySpeed = 0;
		}	
	}
	
	public void fall() { //马里奥下落方法
		if (status.indexOf("left") != -1) {
			status = "jump_L";
		}else {
			status = "jump_R";
		}
		ySpeed = 10;
		
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while (true) {
			boolean onObstacle = false; //判断是否在障碍物上
			boolean can_R = true; //判断是否可以往右走
			boolean can_L = true; //判断是否可以往左走
			
			if (backGround.isFlag() && this.x >= 500) { //判断是否到达旗杆
				this.backGround.setReach(true);
				
				if (this.backGround.isBase()) { //判断旗子是否下落完成
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
				Obstacle obstacle = backGround.getObstacleList().get(i); //利用for循环遍历障碍物
				if (obstacle.getY() == this.y + 25 && (obstacle.getX() > this.x - 30 && obstacle.getX() < this.x + 25)) { //判断马里奥是否位于障碍物上
					onObstacle = true;
				}
				
				if ((obstacle.getY() >= this.y - 30 && obstacle.getY() <= this.y - 20) && (obstacle.getX() >this.x - 30 && obstacle.getX() < this.x +25)) { //判断跳跃能否顶到砖块
					if (obstacle.getType() == 0) {
						backGround.getObstacleList().remove(obstacle);
					}
					upTime = 0;
				}
				
				if (obstacle.getX() == this.x + 25 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) { //判断是否可以往右走
					can_R = false;
				}
				
				if (obstacle.getX() == this.x - 30 && (obstacle.getY() > this.y - 30 && obstacle.getY() < this.y + 25)) { //判断是否可以往左走
					can_L = false;
				}
				
			}
			
			for (int i = 0; i < backGround.getEnemyList().size(); i++) { //for循环遍历敌人，判断马里奥碰到敌人死亡或踩死敌人
				Enemy enemy = backGround.getEnemyList().get(i);
				
				if (enemy.getY() == this.y + 20 && (enemy.getX() - 25 <= this.x && enemy.getX() + 35 >= this.x)) {
					if (enemy.getType() == 1) { //判断是否为蘑菇
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
			
			if (onObstacle && upTime == 0) { //跳跃操作
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
				if (x < 0) { //判断马里奥是否在最左边
					x = 0;
				}
			}
			
			if (status.contains("move")) { //判断是否为移动状态
				index = index == 0 ? 1 : 0;
			}
			
			if ("move_L".equals(status)) { //判断是否向左移动
				showImage = StaticValue.run_L.get(index);
			}
			
			if ("move_R".equals(status)) { //判断是否向右移动
				showImage = StaticValue.run_R.get(index);
			}
			
			if ("stop_L".equals(status)) { //判断是否向左停止
				showImage = StaticValue.stand_L;
			}
			
			if ("stop_R".equals(status)) { //判断是否向右停止
				showImage = StaticValue.stand_R;
			}
			
			if ("jump_L".equals(status)) { //判断是否向左跳跃
				showImage = StaticValue.jump_L;
			}
			
			if ("jump_R".equals(status)) { //判断是否向右跳跃
				showImage = StaticValue.jump_R;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
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
