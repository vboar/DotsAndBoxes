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
 * 开头动画的Panel，添加键盘监听
 */
public class JPanelStartMovie extends JPanel implements KeyListener {

	/**
	 * 当前播放图片序号
	 */
	int step = 1;
	/**
	 * Frame
	 */
	private JFrameGame game;
	/**
	 * 主界面panel
	 */
	private JPanelHome jPanelHome;
	private boolean homeMusic;
	
	/**
	 * 构造器，获得Frame
	 */
	public JPanelStartMovie(JFrameGame game) {
		homeMusic = false;
		this.game = game;
		// 设置焦点
		this.setFocusable(true);
		this.addKeyListener(JPanelStartMovie.this);
		// 隐藏鼠标
		Controller.getController().changeCursor(3);
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
					// 当movie线程开启时马上new一个主界面panel，避免延迟
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
					// 播放主界面音乐
					if (!homeMusic) {
						Player.playMusic("home");
						homeMusic = true;
					}
				}
				if (step > 69) {
					// 取消焦点
					JPanelStartMovie.this.setFocusable(false);
					// 设置Frame的默认panel
					game.setContentPane(jPanelHome);
					// 设置默认鼠标
					Controller.getController().changeCursor(2);
					// 为主界面panel设置焦点
					jPanelHome.setFocusable(true);
					// Frame移除该panel
					game.remove(JPanelStartMovie.this);
					// 主界面panel请求焦点
					jPanelHome.requestFocus();
					// Frame重绘
					game.revalidate();
					// 跳出线程
					break;
				}
				step++;
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
			img = ImageIO.read(new File("image/jpanels/jpanelstartmovie/OP" + n + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * 键盘事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		// 空格skip
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			step = 69;
			if (!homeMusic) Player.playMusic("home");
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
