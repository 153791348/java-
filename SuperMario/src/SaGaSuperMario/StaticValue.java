package SaGaSuperMario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	public static BufferedImage bg = null; //背景
	public static BufferedImage bg2 = null; //背景2
	public static BufferedImage jump_L = null; //向左跳跃
	public static BufferedImage jump_R = null; //向右跳跃
	public static BufferedImage stand_L = null; //向左站立
	public static BufferedImage stand_R = null; //向右站立
	public static BufferedImage tower = null; //城堡
	public static BufferedImage gan = null; //旗杆
	public static List<BufferedImage> obstacle = new ArrayList<>(); //障碍物
	public static List<BufferedImage> run_L = new ArrayList<>(); //向左跑
	public static List<BufferedImage> run_R = new ArrayList<>(); //向右跑
	public static List<BufferedImage> mogu = new ArrayList<>(); //蘑菇形敌人
	public static List<BufferedImage> flower = new ArrayList<>(); //食人花敌人
	public static String path =System.getProperty("user.dir") + "/src/images/"; //路径前缀，方便后续调用
	
	public static void init() { //初始化方法
		try {
			bg = ImageIO.read(new File(path + "bg.png")); //加载背景图
			bg2 = ImageIO.read(new File(path + "bg2.png")); //加载背景图2
			stand_L = ImageIO.read(new File(path + "s_mario_stand_L.png")); //加载马里奥向左站立
			stand_R = ImageIO.read(new File(path + "s_mario_stand_R.png")); //加载马里奥向右站立
			tower = ImageIO.read(new File(path + "tower.png")); //加载城堡
			gan = ImageIO.read(new File(path + "gan.png")); //加载旗杆
			jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png")); //加载马里奥向左跳
			jump_R = ImageIO.read(new File(path + "s_mario_jump1_R.png")); //加载马里奥向右跳
								
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		for (int i = 1; i <= 2; i++) {
			try {
				run_L.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_L.png")));
				//加载马里奥向左跑
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
		for (int i = 1; i <= 2; i++) {
			try {
				run_R.add(ImageIO.read(new File(path + "s_mario_run"+ i +"_R.png")));
				//加载马里奥向右跑
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		try {
			obstacle.add(ImageIO.read(new File(path + "brick.png"))); //加载砖块
			obstacle.add(ImageIO.read(new File(path + "soil_up.png"))); //加载地面砖块
			obstacle.add(ImageIO.read(new File(path + "soil_base.png"))); //加载地下砖块
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		for (int i = 1; i <= 4; i++) {
			try {
				obstacle.add(ImageIO.read(new File(path + "pipe" + i +".png"))); //加载水管
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
		try {
			obstacle.add(ImageIO.read(new File(path + "brick2.png"))); //加载不可破坏砖块
			obstacle.add(ImageIO.read(new File(path + "flag.png"))); //加载旗子
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		for (int i = 1; i <= 3 ; i++) {
			try {
				mogu.add(ImageIO.read(new File(path + "fungus" + i + ".png"))); //加载蘑菇形敌人
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
		for (int i = 1; i <= 2; i++) {
			try {
				flower.add(ImageIO.read(new File(path + "flower" + i + ".png"))); //加载食人花敌人
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
