package SaGaSuperMario;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
	private int x; //x坐标
	private int y; //y坐标
	private int type; //标记障碍物类型
	private BufferedImage showImage = null; //显示图像
	private BackGround bg = null; //定义当前场景对象
	private Thread thread = new Thread(this); //定义一个线程对象
	
	public Obstacle(int x,int y,int type,BackGround bg) {
		// TODO 自动生成的构造函数存根
		this.x = x;
		this.y = y;
		this.type = type;
		this.bg = bg;
		showImage = StaticValue.obstacle.get(type);
		
		if (type == 8) { //如果是旗子启动线程
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
		// TODO 自动生成的方法存根
		while (true) {
			if (this.bg.isReach()) { //判断马里奥是否到达旗子位置
				if (this.y < 374) { //判断旗子是否落地
					this.y += 5;
				}else {
					this.bg.setBase(true);
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
