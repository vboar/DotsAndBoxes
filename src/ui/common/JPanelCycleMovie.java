package ui.common;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import system.JFrameGame;

/**
 * 主界面动态movie panel
 */
public class JPanelCycleMovie extends JPanel {
	
	/**
	 * 当前图片帧
	 */
	int step = 0;
	/**
	 * 图片数目
	 */
	int allNum;
	/**
	 * 图片路径，不包括序号
	 */
	String path;
	/**
	 * Frame
	 */
	private JFrameGame game;
	/**
	 * 播放速度
	 */
	private int speed;
	/**
	 * 是否png
	 */
	private boolean png;
	/**
	 * 构造器，获得各种属性
	 */
	public JPanelCycleMovie(JFrameGame game, int allNum, int x, int y, 
			int width, int height, String path, int speed, boolean png) {
		this.game = game;
		this.allNum = allNum;
		this.path = path;
		this.speed = speed;
		this.png = png;
		// 设为透明
		this.setOpaque(false);
		// 设置大小和位置
		this.setBounds(x, y, width, height);
		// 启动movie线程
		new Thread(new createMovie()).start();
		
	}
	
	/**
	 * movie线程内部类
	 */
	private class createMovie implements Runnable {
		@Override
		public void run() {
			while (true) {
				repaint();
				if (step >= allNum) step = 0;
				step++;
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 图片绘制
	 */
	public void paint(Graphics g) {
		g.drawImage(getImage(step), 0, 0, null);
	}
	
	/**
	 * 获取当前图片帧到内存中
	 */
	public Image getImage(int n) {
		Image img = null;
		try {
			if (png) {
				img = ImageIO.read(new File(path + n + ".png"));
			} else {
				img = ImageIO.read(new File(path + n + ".jpg"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
}
