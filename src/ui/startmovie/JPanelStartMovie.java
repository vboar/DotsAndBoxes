package ui.startmovie;

import image.ImgAdventureGame;
import image.ImgHelp;
import image.ImgMovie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.help.JPanelHelp;
import ui.home.JPanelHome;

/**
 * ��ͷ������Panel����Ӽ��̼���
 */
public class JPanelStartMovie extends JPanel implements KeyListener {

	/**
	 * ��ǰ����ͼƬ���
	 */
	int step = 1;
	/**
	 * Frame
	 */
	private JFrameGame game;
	/**
	 * ������panel
	 */
	private JPanelHome jPanelHome;
	private boolean homeMusic;
	
	/**
	 * �����������Frame
	 */
	public JPanelStartMovie(JFrameGame game) {
		homeMusic = false;
		this.game = game;
		// ���ý���
		this.setFocusable(true);
		this.addKeyListener(JPanelStartMovie.this);
		// �������
		Controller.getController().changeCursor(3);
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
				if (step > 1 && step <= 41 || step >= 54) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (step == 1) {
					// ��movie�߳̿���ʱ����newһ��������panel�������ӳ�
					jPanelHome = new JPanelHome(game);
					Player.playSound("startmovie");
				}
				if (step == 15) {
					Image[] img = ImgAdventureGame.bg;
					new JPanelHelp(game);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (step == 52) {
					// ��������������
					if (!homeMusic) {
						Player.playMusic("home");
						homeMusic = true;
					}
				}
				if (step > 69) {
					// ȡ������
					JPanelStartMovie.this.setFocusable(false);
					// ����Frame��Ĭ��panel
					game.setContentPane(jPanelHome);
					// ����Ĭ�����
					Controller.getController().changeCursor(2);
					// Ϊ������panel���ý���
					jPanelHome.setFocusable(true);
					// Frame�Ƴ���panel
					game.remove(JPanelStartMovie.this);
					// ������panel���󽹵�
					jPanelHome.requestFocus();
					// Frame�ػ�
					game.revalidate();
					// �����߳�
					break;
				}
				step++;
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
			img = ImageIO.read(new File("image/jpanels/jpanelstartmovie/OP" + n + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * �����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		// �ո�skip
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			step = 69;
			if (!homeMusic) Player.playMusic("home");
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
