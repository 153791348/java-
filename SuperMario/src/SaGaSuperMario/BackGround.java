package SaGaSuperMario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	private BufferedImage bgImage = null; //��ǰ������ʾ��ͼ��
	private int sort; //��¼��ǰΪ�ڼ�������
	private boolean flag; //�ж��Ƿ�Ϊ���һ������
	private List<Obstacle> obstacleList = new ArrayList<>(); //�½�һ���б����ϰ���
	private List<Enemy> enemyList = new ArrayList<>(); //�½�һ���б��ŵ���
	private BufferedImage ganImage = null; //��ʾ���
	private BufferedImage towerImage = null; //��ʾ�Ǳ�
	private boolean isReach = false; //�ж��Ƿ񵽴����
	private boolean isBase = false; //�ж������Ƿ����
	
	public BackGround() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	
	public BackGround(int sort,boolean flag) {
		// TODO �Զ����ɵĹ��캯�����
		this.sort = sort;
		this.flag = flag;
		
		if (flag) { 
			bgImage = StaticValue.bg2; //flag=true,��ǰ����Ϊ���һ������
		}else {
			bgImage = StaticValue.bg; //flag=false,��ǰ������Ϊ���ĳ���
		}
		
		if (sort == 1) { //�ж��Ƿ�Ϊ��һ��
			for (int i = 0; i < 27; i++) { 
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //����forѭ�����Ƶ���
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //˫��forѭ�����Ƶ���
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
			
			enemyList.add(new Enemy(580, 385, true, 1, this)); //����Ģ��
			enemyList.add(new Enemy(635, 420, true, 2, 328, 428, this)); //����ʳ�˻�
			
		}
		
		if (sort == 2) { //�ж��Ƿ�Ϊ�ڶ���
			for (int i = 0; i < 27; i++) {
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //����forѭ�����Ƶ���
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //˫��forѭ�����Ƶ���
				}
			}
			
			for (int i = 360; i <= 600; i += 25) { //���Ƶ�һ��ˮ��
				if (i == 360) {
					obstacleList.add(new Obstacle(60, i, 3, this));
					obstacleList.add(new Obstacle(85, i, 4, this));
				}else {
					obstacleList.add(new Obstacle(60, i, 5, this));
					obstacleList.add(new Obstacle(85, i, 6, this));
				}
			}
			
			for (int i = 330; i <= 600; i += 25) { //���Ƶڶ���ˮ��
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
			
			enemyList.add(new Enemy(200, 385, true, 2, this)); //����Ģ��1
			enemyList.add(new Enemy(500, 385, true, 2, this)); //����ʳ�˻�2
			enemyList.add(new Enemy(75, 420, true, 2, 328, 418, this)); //����ʳ�˻�1
			enemyList.add(new Enemy(635, 420, true, 2, 298, 388, this)); //����ʳ�˻�2
		}
		
		if (sort == 3) { 
			for (int i = 0; i < 27; i++) {
				obstacleList.add(new Obstacle(i*30, 420, 1, this)); //����forѭ�����Ƶ���
			}
			
			for (int j = 0; j <= 120; j += 30) {
				for (int i = 0; i < 27; i++) {
					obstacleList.add(new Obstacle(i*30, 570-j, 2, this)); //˫��forѭ�����Ƶ���
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
			
			ganImage = StaticValue.gan; //�������
			towerImage = StaticValue.tower; //���ƳǱ�	
			
			obstacleList.add(new Obstacle(515, 220, 8, this)); //�����ӹ��������
			
			enemyList.add(new Enemy(150, 385, true, 1, this)); //����Ģ��
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
