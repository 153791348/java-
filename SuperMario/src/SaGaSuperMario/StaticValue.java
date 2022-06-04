package SaGaSuperMario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	public static BufferedImage bg = null; //����
	public static BufferedImage bg2 = null; //����2
	public static BufferedImage jump_L = null; //������Ծ
	public static BufferedImage jump_R = null; //������Ծ
	public static BufferedImage stand_L = null; //����վ��
	public static BufferedImage stand_R = null; //����վ��
	public static BufferedImage tower = null; //�Ǳ�
	public static BufferedImage gan = null; //���
	public static List<BufferedImage> obstacle = new ArrayList<>(); //�ϰ���
	public static List<BufferedImage> run_L = new ArrayList<>(); //������
	public static List<BufferedImage> run_R = new ArrayList<>(); //������
	public static List<BufferedImage> mogu = new ArrayList<>(); //Ģ���ε���
	public static List<BufferedImage> flower = new ArrayList<>(); //ʳ�˻�����
	public static String path =System.getProperty("user.dir") + "/src/images/"; //·��ǰ׺�������������
	
	public static void init() { //��ʼ������
		try {
			bg = ImageIO.read(new File(path + "bg.png")); //���ر���ͼ
			bg2 = ImageIO.read(new File(path + "bg2.png")); //���ر���ͼ2
			stand_L = ImageIO.read(new File(path + "s_mario_stand_L.png")); //�������������վ��
			stand_R = ImageIO.read(new File(path + "s_mario_stand_R.png")); //�������������վ��
			tower = ImageIO.read(new File(path + "tower.png")); //���سǱ�
			gan = ImageIO.read(new File(path + "gan.png")); //�������
			jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png")); //���������������
			jump_R = ImageIO.read(new File(path + "s_mario_jump1_R.png")); //���������������
								
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for (int i = 1; i <= 2; i++) {
			try {
				run_L.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_L.png")));
				//���������������
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
		for (int i = 1; i <= 2; i++) {
			try {
				run_R.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_R.png")));
				//���������������
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		try {
			obstacle.add(ImageIO.read(new File(path + "brick.png"))); //����ש��
			obstacle.add(ImageIO.read(new File(path + "soil_up.png"))); //���ص���ש��
			obstacle.add(ImageIO.read(new File(path + "soil_base.png"))); //���ص���ש��
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		for (int i = 1; i <= 4; i++) {
			try {
				obstacle.add(ImageIO.read(new File(path + "pipe" + i +".png"))); //����ˮ��
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
		try {
			obstacle.add(ImageIO.read(new File(path + "brick2.png"))); //���ز����ƻ�ש��
			obstacle.add(ImageIO.read(new File(path + "flag.png"))); //��������
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
		for (int i = 1; i <= 3 ; i++) {
			try {
				mogu.add(ImageIO.read(new File(path + "fungus" + i + ".png"))); //����Ģ���ε���
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
		
		for (int i = 1; i <= 2; i++) {
			try {
				flower.add(ImageIO.read(new File(path + "flower" + i + ".png"))); //����ʳ�˻�����
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
