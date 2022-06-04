package SaGaSuperMario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	private BufferedImage bgImage = null; //当前场景显示的图像
	private int sort; //记录当前为第几个场景
	private boolean flag; //判断是否为最后一个场景
	private List<Obstacle> obstacleList = new ArrayList<>(); //新建一个列表存放障碍物
	private List<Enemy> enemyList = new ArrayList<>(); //新建一个列表存放敌人
	private BufferedImage ganImage = null; //显示旗杆
	private BufferedImage towerImage = null; //显示城堡
	private boolean isReach = false; //判断是否到达旗杆
	private boolean isBase = false; //判断旗子是否落地
	
	public BackGround() {
		// TODO 自动生成的构造函数存根
	}
	
	
	public BackGround(int sort,boolean flag) {
		// TODO 自动生成的构造函数存根
		this.sort = sort;
		this.flag = flag;
		
		if (flag) { 
			bgImage = StaticValue.bg2; //flag=true,则当前场景为最后一个场景
		}else {
			bgImage = StaticValue.bg; //flag=false,则当前场景不为最后的场景
		}
		
		if (sort == 1) { //判断是否为第一关
			for (int i = 0; i < 27; i++) { 
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //利用for循环绘制地面
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //双重for循环绘制地下
				}
			}
			
			for (int i = 120; i <= 150; i += 30) {
				obstacleList.add(new Obstacle(i, 300, 7, this));
			}
			
			for (int i = 300; i <= 570; i += 30) {
				if (i == 360 || i == 390 || i == 480 || i == 510 || i == 540) {
					obstacleList.add(new Obstacle(i, 300, 7, this));
				}else {
					obstacleList.add(new Obstacle(i, 300, 0, this));
				}
			}
			
			for (int i = 420; i <= 450 ; i += 30) {
				obstacleList.add(new Obstacle(i, 240, 7, this));
			}
			
			for (int i = 360; i <= 600; i += 25) {
				if (i == 360) {
					obstacleList.add(new Obstacle(620, i, 3, this));
					obstacleList.add(new Obstacle(645, i, 4, this));
				}else {
					obstacleList.add(new Obstacle(620, i, 5, this));
					obstacleList.add(new Obstacle(645, i, 6, this));
				}
			}
			
			enemyList.add(new Enemy(580, 385, true, 1, this)); //绘制蘑菇
			enemyList.add(new Enemy(635, 420, true, 2, 328, 428, this)); //绘制食人花
			
		}
		
		if (sort == 2) { //判断是否为第二关
			for (int i = 0; i < 27; i++) {
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //利用for循环绘制地面
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //双重for循环绘制地下
				}
			}
			
			for (int i = 360; i <= 600; i += 25) { //绘制第一根水管
				if (i == 360) {
					obstacleList.add(new Obstacle(60, i, 3, this));
					obstacleList.add(new Obstacle(85, i, 4, this));
				}else {
					obstacleList.add(new Obstacle(60, i, 5, this));
					obstacleList.add(new Obstacle(85, i, 6, this));
				}
			}
			
			for (int i = 330; i <= 600; i += 25) { //绘制第二根水管
				if (i == 330) {
					obstacleList.add(new Obstacle(620, i, 3, this));
					obstacleList.add(new Obstacle(645, i, 4, this));
				}else {
					obstacleList.add(new Obstacle(620, i, 5, this));
					obstacleList.add(new Obstacle(645, i, 6, this));
				}
			}
			
			obstacleList.add(new Obstacle(300, 330, 0, this));
			
			for (int i = 270; i <= 330; i += 30) {
				if (i == 300) {
					obstacleList.add(new Obstacle(i, 360, 7, this));
				}else {
					obstacleList.add(new Obstacle(i, 360, 0, this));
				}
			}
			
			for (int i = 240; i <= 360; i += 30) {
				if (i == 240 || i == 360) {
					obstacleList.add(new Obstacle(i, 390, 0, this));
				}else {
					obstacleList.add(new Obstacle(i, 390, 7, this));
				}
			}
			
			obstacleList.add(new Obstacle(240, 300, 0, this));
			
			for (int i = 360; i <= 540; i += 60) {
				obstacleList.add(new Obstacle(i, 270, 7, this));
			}
			
			enemyList.add(new Enemy(200, 385, true, 2, this)); //绘制蘑菇1
			enemyList.add(new Enemy(500, 385, true, 2, this)); //绘制食人花2
			enemyList.add(new Enemy(75, 420, true, 2, 328, 418, this)); //绘制食人花1
			enemyList.add(new Enemy(635, 420, true, 2, 298, 388, this)); //绘制食人花2
		}
		
		if (sort == 3) { 
			for (int i = 0; i < 27; i++) {
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //利用for循环绘制地面
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //双重for循环绘制地下
				}
			}
			
			int temp = 290;
			for (int i = 390; i >= 270; i -= 30) {
				for (int j = temp; j <= 410; j += 30) {
					obstacleList.add(new Obstacle(j, i, 7, this));
				}
				temp += 30;
			}
			
			temp = 60;
			for (int i = 390; i >= 360; i -= 30) {
				for (int j = temp; j <= 90; j += 30) {
					obstacleList.add(new Obstacle(j, i, 7, this));
				}
				temp += 30;
			}
			
			ganImage = StaticValue.gan; //绘制旗杆
			towerImage = StaticValue.tower; //绘制城堡	
			
			obstacleList.add(new Obstacle(515, 220, 8, this)); //将旗子挂在旗杆上
			
			enemyList.add(new Enemy(150, 385, true, 1, this)); //绘制蘑菇
		}
		
	}

	
	public BufferedImage getBgImage() {
		return bgImage;
	}

	public int getSort() {
		return sort;
	}

	public boolean isFlag() {
		return flag;
	}
	
	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}

	public BufferedImage getGanImage() {
		return ganImage;
	}

	public BufferedImage getTowerImage() {
		return towerImage;
	}


	public boolean isReach() {
		return isReach;
	}


	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}


	public boolean isBase() {
		return isBase;
	}


	public void setBase(boolean isBase) {
		this.isBase = isBase;
	}


	public List<Enemy> getEnemyList() {
		return enemyList;
	}

}
