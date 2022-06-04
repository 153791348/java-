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
	private List<BackGround> allBg = new ArrayList<>(); //�������б���
	private BackGround nowBg = new BackGround(); //���浱ǰ����
	private Image offScreenImage = null; //˫����
	private Mario mario = new Mario(); //����¶���
	private Thread thread = new Thread(this);
	
	public MyFrame() {
		this.setSize(800,600); //���ô��ڴ�С
		this.setLocationRelativeTo(null); //���ô��ھ���
		this.setVisible(true); //���ڿɼ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���õ���رռ�����ֹ����
		this.setResizable(false); //���ô��ڴ�С���ɱ�
		this.addKeyListener(this); //��Ӽ��̼�����
		this.setTitle("SaGa SuperMario"); //���ô�������
		StaticValue.init(); //��ʼ��ͼƬ
		mario = new Mario(10,375); //��ʼ�������
		
		for (int i = 1; i <= 3; i++) {
			allBg.add(new BackGround(i,i == 3 ? true :false)); //�������б���
		}
		
		nowBg = allBg.get(0); //����һ����������Ϊ��ǰ����
		mario.setBackGround(nowBg); //����һ�������Ķ��󴫵ݸ�background
		repaint();
		thread.start(); //�����߳�
	}
	
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
	}
	
@Override
public void paint(Graphics g) {
	// TODO �Զ����ɵķ������
	if (offScreenImage == null) {
		offScreenImage = createImage(800, 600);
	}
	
	Graphics graphics = offScreenImage.getGraphics();
	graphics.fillRect(0, 0, 800, 600);
	graphics.drawImage(nowBg.getBgImage(), 0, 0, this); //���Ʊ���
	
	for (Enemy enemy : nowBg.getEnemyList()) {
		graphics.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), this); //���Ƶ���
	}
	
	for (Obstacle ob : nowBg.getObstacleList()) { //�����ϰ���
		graphics.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
	}
	
	graphics.drawImage(nowBg.getTowerImage() , 620, 270, this); //���ƳǱ�
	graphics.drawImage(nowBg.getGanImage(), 500, 220, this); //�������
	graphics.drawImage(mario.getShowImage(), mario.getX(), mario.getY(), this); //�������
	
	g.drawImage(offScreenImage, 0, 0, this);
}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyPressed(KeyEvent e) { //���¼��̵���
		// TODO �Զ����ɵķ������
		if (e.getKeyCode() == 39) { //�����ƶ�
			mario.rightMove();
		}
		
		if (e.getKeyCode() == 37) { //�����ƶ�
			mario.leftMove();
		}
		
		if (e.getKeyCode() == 38) { //��Ծ
			mario.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { //�ɿ����̵���
		// TODO �Զ����ɵķ������
		if (e.getKeyCode() == 39) { //����ֹͣ
			mario.rightStop();
		}
		
		if (e.getKeyCode() == 37) { //����ֹͣ
			mario.leftStop();
		}
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
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
					JOptionPane.showMessageDialog(this, "һ��ͨ��");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
		

}
