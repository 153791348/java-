package SaGaSuperMario;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame implements KeyListener,Runnable{
	private List<BackGround> allBg = new ArrayList<>(); //储存所有背景
	private BackGround nowBg = new BackGround(); //储存当前背景
	private Image offScreenImage = null; //双缓存
	private Mario mario = new Mario(); //马里奥对象
	private Thread thread = new Thread(this);
	
	public MyFrame() {
		this.setSize(800,600); //设置窗口大小
		this.setLocationRelativeTo(null); //设置窗口居中
		this.setVisible(true); //窗口可见性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置点击关闭键，终止程序
		this.setResizable(false); //设置窗口大小不可变
		this.addKeyListener(this); //添加键盘监听器
		this.setTitle("SaGa SuperMario"); //设置窗口名称
		StaticValue.init(); //初始化图片
		mario = new Mario(10,375); //初始化马里奥
		
		for (int i = 1; i <= 3; i++) {
			allBg.add(new BackGround(i,i == 3 ? true :false)); //创建所有背景
		}
		
		nowBg = allBg.get(0); //将第一个背景设置为当前场景
		mario.setBackGround(nowBg); //将第一个场景的对象传递给background
		repaint();
		thread.start(); //启动线程
	}
	
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
	}
	
@Override
public void paint(Graphics g) {
	// TODO 自动生成的方法存根
	if (offScreenImage == null) {
		offScreenImage = createImage(800, 600);
	}
	
	Graphics graphics = offScreenImage.getGraphics();
	graphics.fillRect(0, 0, 800, 600);
	graphics.drawImage(nowBg.getBgImage(), 0, 0, this); //绘制背景
	
	for (Enemy enemy : nowBg.getEnemyList()) {
		graphics.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this); //绘制敌人
	}
	
	for (Obstacle ob : nowBg.getObstacleList()) { //绘制障碍物
		graphics.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
	}
	
	graphics.drawImage(nowBg.getTowerImage() , 620, 270, this); //绘制城堡
	graphics.drawImage(nowBg.getGanImage(), 500, 220, this); //绘制旗杆
	graphics.drawImage(mario.getShowImage(), mario.getX(), mario.getY(), this); //绘马里奥
	
	g.drawImage(offScreenImage, 0, 0, this);
}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //按下键盘调用
		// TODO 自动生成的方法存根
		if (e.getKeyCode() == 39) { //向右移动
			mario.rightMove();
		}
		
		if (e.getKeyCode() == 37) { //向左移动
			mario.leftMove();
		}
		
		if (e.getKeyCode() == 38) { //跳跃
			mario.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { //松开键盘调用
		// TODO 自动生成的方法存根
		if (e.getKeyCode() == 39) { //向右停止
			mario.rightStop();
		}
		
		if (e.getKeyCode() == 37) { //向左停止
			mario.leftStop();
		}
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while (true) {
			repaint();
			try {
				Thread.sleep(50);
				
				if (mario.getX() >=775) {
					nowBg = allBg.get(nowBg.getSort());
					mario.setBackGround(nowBg);
					mario.setX(10);
					mario.setY(375);
				}
				
				if (mario.isDeath()) {
					JOptionPane.showMessageDialog(this, "GAME OVER!");
					System.exit(0);
				}
				
				if (mario.isEnd()) {
					JOptionPane.showMessageDialog(this, "一命通关");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
		

}
