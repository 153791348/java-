package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable{
	private int x; //储存敌人当前x坐标
	private int y; //储存敌人当前y坐标
	private int type; //储存敌人类型
	private boolean move_to = true; //判断敌人运动方向
	private BufferedImage showImage; //显示敌人当前图像
	private BackGround bg; //背景对象
	private int up = 0; //食人花向上运动范围
	private int down = 0; //食人花向下运动范围
	private Thread thread =new Thread(this); //定义一个线程
	private int image_type = 0; //当前图片状态
	
	public Enemy(int x,int y,boolean move_to,int type,BackGround bg) {
		// TODO 自动生成的构造函数存根,蘑菇
		this.x = x;
		this.y = y;
		this.move_to = move_to;
		this.type = type;
		this.bg = bg;
		showImage = StaticValue.mogu.get(0); //初始化蘑菇图像
		thread.start();
	}
	
	public Enemy(int x,int y,boolean move_to,int type,int up,int down, BackGround bg) {
		// TODO 自动生成的构造函数存根,食人花
		this.x = x;
		this.y = y;
		this.move_to = move_to;
		this.type = type;
		this.up = up;
		this.down = down;
		this.bg = bg;
		showImage = StaticValue.flower.get(0); //初始化食人花图像
		thread.start();
	}
	
	public void death() { //死亡方法
		showImage = StaticValue.mogu.get(2);
		this.bg.getEnemyList().remove(this);
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while (true) {
			if (getType() == 1) { //判断是否为蘑菇
				if (move_to) { //判断运动方向，T为向左，F向右
					this.x -= 2;
				}else {
					this.x += 2;
				}				
				image_type = image_type == 1 ? 0 :1; //三目运算符判断改变状态		
				
				showImage = StaticValue.mogu.get(image_type);
			}
			
			boolean can_L = true; //判断能否向左移动
			boolean can_R = true; //判断能否向左移动
			
			for (int i = 0; i < bg.getObstacleList().size(); i++) { //利用for循环遍历障碍物
				Obstacle obstacle = bg.getObstacleList().get(i);
				
				if (obstacle.getX() == this.x + 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) { //判断敌人是否可以向右走
					can_R = false;
				}
				
				if (obstacle.getX() == this.x - 36 && (obstacle.getY() + 65 > this.y && obstacle.getY() - 35 < this.y)) { //判断敌人是否可以向左走
					can_L = false;
				}				
			}
			
			if (move_to && !can_L || this.x == 0) { //判断蘑菇是否向左移动且碰到障碍物
				move_to = false;
			}else if ((!move_to) && (!can_R) || this.x == 764) { //判断蘑菇是否向右移动且碰到障碍物
				move_to = true;
			}
			
			if (getType() == 2) { //判断是否为食人花
				if (move_to) { //判断运动方向，T为向上，F向下
					this.y -= 2;
				}else {
					this.y += 2;
				}			
				image_type = image_type == 1 ? 0 :1; //三目运算符判断改变状态
				
				if (move_to && (this.y == up)) { //判断食人花是否到上极限位置
					move_to = false;
				}else if ((!move_to) && (this.y == down)) { //判断食人花是否到下极限位置
					move_to = true;
				}
				
				showImage = StaticValue.flower.get(image_type);			
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
