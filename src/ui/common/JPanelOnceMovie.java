package ui.common;

import image.ImgAdventureGame;
import image.ImgAdventureMap;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import system.JFrameGame;

/**
 * 一次性movie Panel
 */
public class JPanelOnceMovie extends JPanel{

	/**
	 * 当前图片帧
	 */
	int step = 1;
	/**
	 * 图片数目
	 */
	int allNum;
	/**
	 * 播放速度
	 */
	int speed;
	/**
	 * 图片路径，不包括序号
	 */
	String path;
	/**
	 * 是否png
	 */
	boolean png;
	/**
	 * 结束
	 */
	boolean over;
	/**
	 * Frame
	 */
	private JFrameGame game;
	
	/**
	 * 构造器，获得各种属性
	 */
	public JPanelOnceMovie(JFrameGame game, int allNum, int x, int y, 
			int width, int height, String path, boolean png, int speed) {
		this.game = game;
		this.allNum = allNum;
		this.path = path;
		this.png = png;
		this.speed = speed;
		// 设置大小和位置
		this.setBounds(x, y, width, height);
		// 设为透明
		this.setOpaque(false);
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
				if (step == allNum - 1) over = true;
				if (step >= allNum) {
					JPanelOnceMovie.this.setVisible(false);
					break;
				}
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
	 * movie当前图片帧绘制
	 */
	public void paint(Graphics g) {
		g.drawImage(getImage(step), 0, 0, null);
	}
	
	/**
	 * 将图片读取到内存中去
	 */
	public Image getImage(int n) {
		Image img = null;
		try {
			if (png) {
				img = ImageIO.read(new File(path + n + ".png"));
			}
			else {
				img = ImageIO.read(new File(path + n + ".jpg"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public boolean isOver() {
		return over;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

}
