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
 * һ����movie Panel
 */
public class JPanelOnceMovie extends JPanel{

	/**
	 * ��ǰͼƬ֡
	 */
	int step = 1;
	/**
	 * ͼƬ��Ŀ
	 */
	int allNum;
	/**
	 * �����ٶ�
	 */
	int speed;
	/**
	 * ͼƬ·�������������
	 */
	String path;
	/**
	 * �Ƿ�png
	 */
	boolean png;
	/**
	 * ����
	 */
	boolean over;
	/**
	 * Frame
	 */
	private JFrameGame game;
	
	/**
	 * ����������ø�������
	 */
	public JPanelOnceMovie(JFrameGame game, int allNum, int x, int y, 
			int width, int height, String path, boolean png, int speed) {
		this.game = game;
		this.allNum = allNum;
		this.path = path;
		this.png = png;
		this.speed = speed;
		// ���ô�С��λ��
		this.setBounds(x, y, width, height);
		// ��Ϊ͸��
		this.setOpaque(false);
		// ����movie�߳�
		new Thread(new createMovie()).start();
	}
	
	/**
	 * movie�߳��ڲ���
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
	 * movie��ǰͼƬ֡����
	 */
	public void paint(Graphics g) {
		g.drawImage(getImage(step), 0, 0, null);
	}
	
	/**
	 * ��ͼƬ��ȡ���ڴ���ȥ
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
