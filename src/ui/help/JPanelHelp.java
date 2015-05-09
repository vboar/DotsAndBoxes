package ui.help;

import image.ImgHelp;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import media.Player;
import system.AdventureDao;
import system.JFrameGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.classic.JButtonStart;
import ui.classicgame.JPanelClassicGame;
import ui.common.JPanelCycleMovie;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 帮助Panel类，实现鼠标监听接口
 */
public class JPanelHelp extends JPanel implements MouseListener {

	private JFrameGame game;
	private Layer[] layers = null;
	private LayerBackground bg = null;
	private LayerBackground middle = null;
	private Image[] middleImg = new Image[55];
	Image guideImg = new ImageIcon("image/jpanels/jpanelhelp/bg/guide.jpg").getImage();
	Image adventureImg = new ImageIcon("image/jpanels/jpanelhelp/bg/adventure.jpg").getImage();
	Image aboutUsImg = new ImageIcon("image/jpanels/jpanelhelp/bg/aboutus.jpg").getImage();
	private int picNum = 0;
	/**
	 * 当前显示，0为指南，1为剧情，2为关于，有无必要？
	 */
	private int type = 0;
	
	public JPanelHelp(JFrameGame game) {
		this.game = game;
		this.setLayout(null);
		for (int i = 0; i < middleImg.length; i++) {
			middleImg[i] = new ImageIcon("image/jpanels/jpanelhelp/middle/" + (i + 1) + ".png").getImage();
		}
		bg = new LayerBackground(0, 0, 800, 600, guideImg);
		middle = new LayerBackground(119, 123, 460, 360, middleImg[0]);
		layers = new Layer[] {
				bg,
				middle
		};
		this.add(new JButtonBack(game, this));
		this.add(new JButtonUp(game, this));
		this.add(new JButtonDown(game, this));
		this.addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}

	/**
	 * 鼠标监听事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 根据点击左侧按钮改变标签即改变背景图片
		if (e.getX() > 590 && e.getX() < 657 && 
				e.getY() > 116 && e.getY() < 212) {
			Player.playSound("helpright");
			this.bg.setImg(guideImg);
			picNum = 0;
			this.middle.setImg(middleImg[picNum]);
		} else if (e.getX() > 590 && e.getX() < 657 && 
				e.getY() > 216 && e.getY() < 312) {
			Player.playSound("helpright");
			this.bg.setImg(adventureImg);
			picNum = 41;
			this.middle.setImg(middleImg[picNum]);
		} else if (e.getX() > 590 && e.getX() < 657 && 
				e.getY() > 316 && e.getY() < 412) {
			Player.playSound("helpright");
			this.bg.setImg(aboutUsImg);
			picNum = 42;
			this.middle.setImg(middleImg[picNum]);
		} else if (e.getX() > 229 && e.getX() < 314 && 
				e.getY() > 193 && e.getY() < 208 && picNum == 5) {
			Player.playSound("lineclick");
			picNum++;
			this.middle.setImg(middleImg[picNum]);
		} else if (e.getX() > 461 && e.getX() < 570 && 
				e.getY() > 412 && e.getY() < 458 && picNum == 40) {
        	Player.playSound("homegame");
        	game.setContentPane(new JPanelClassicGame(game, 0));
        	game.remove(JPanelHelp.this);
        	Player.stopMusic();
        	Player.playMusic("classic");
        	game.revalidate();
		} else if (e.getX() > 232 + 119 && e.getX() < 421 + 119 && 
				e.getY() > 270 + 123 && e.getY() < 324 + 123 && picNum == 41) {
        	Player.playSound("homegame");
        	AdventureDao.getAdventureDao().init();
        	game.setContentPane(new JPanelAdventureMap(game, true));
        	Player.stopMusic();
        	game.remove(this);
        	game.revalidate();
		}
		this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}

	public int getPicNum() {
		return picNum;
	}

	public LayerBackground getMiddle() {
		return middle;
	}

	public Image[] getMiddleImg() {
		return middleImg;
	}

	public void setMiddleImg(Image[] middleImg) {
		this.middleImg = middleImg;
	}
	
}
