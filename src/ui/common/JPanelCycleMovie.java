package ui.common;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import system.JFrameGame;

/**
 * �����涯̬movie panel
 */
public class JPanelCycleMovie extends JPanel {
	
	/**
	 * ��ǰͼƬ֡
	 */
	int step = 0;
	/**
	 * ͼƬ��Ŀ
	 */
	int allNum;
	/**
	 * ͼƬ·�������������
	 */
	String path;
	/**
	 * Frame
	 */
	private JFrameGame game;
	/**
	 * �����ٶ�
	 */
	private int speed;
	/**
	 * �Ƿ�png
	 */
	private boolean png;
	/**
	 * ����������ø�������
	 */
	public JPanelCycleMovie(JFrameGame game, int allNum, int x, int y, 
			int width, int height, String path, int speed, boolean png) {
		this.game = game;
		this.allNum = allNum;
		this.path = path;
		this.speed = speed;
		this.png = png;
		// ��Ϊ͸��
		this.setOpaque(false);
		// ���ô�С��λ��
		this.setBounds(x, y, width, height);
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
	 * ͼƬ����
	 */
	public void paint(Graphics g) {
		g.drawImage(getImage(step), 0, 0, null);
	}
	
	/**
	 * ��ȡ��ǰͼƬ֡���ڴ���
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
