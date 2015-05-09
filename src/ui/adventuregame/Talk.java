package ui.adventuregame;

import image.ImgAdventureGame;
import image.ImgAdventureMap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import media.Player;
import saint.media.SimplePlayer;
import system.AdventureDao;
import system.Controller;
import system.JFrameGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.common.JButtonRect;
import ui.common.Layer;
import ui.common.LayerBackground;

public class Talk extends JPanel implements MouseListener {

	private JFrameGame game;
	private Layer[] layers = null;
	private int startPic;
	private int endPic;
	private int picNum = 100;
	private LayerBackground talk = null;
	boolean[] open;
	Image[] talkImg = new Image[360];
	
	public Talk(JFrameGame game, int startPic, int endPic) {
		
		this.game = game;
		this.startPic = startPic;
		this.endPic = endPic;
		this.picNum = startPic;
		open = AdventureDao.getAdventureDao().load();
		for (int i = startPic; i <= endPic; i++) {
			talkImg[i] = new ImageIcon(
					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
		}
		this.setLayout(null);
		talk = new LayerBackground(0, 0, 800, 600, talkImg[picNum]);
		layers = new Layer[] {
				talk
		};
		this.addMouseListener(this);
		

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Controller.getController().init();
		// 开始的>>>区域
		boolean beginArea = e.getX() > 680 && e.getY() > 490 
				&& e.getX() < 740 && e.getY() < 550;
		// 普通的下一页区域
		boolean nextArea = e.getX() > 680 && e.getY() > 490 
				&& e.getX() < 740 && e.getY() < 550;
		// 再次挑战区域
		boolean retryArea = e.getX() > 524 && e.getY() > 520 
				&& e.getX() < 632 && e.getY() < 577;
		// 放弃或离开区域
		boolean exitArea = e.getX() > 651 && e.getY() > 520 
				&& e.getX() < 759 && e.getY() < 577;
		// 开始游戏区域
		boolean gameArea = e.getX() > 493 && e.getY() > 440 
				&& e.getX() < 720 && e.getY() < 515;
		// 开始介绍星球的图片
		boolean beginNum = picNum == 100 || picNum == 130 || picNum == 160
				 || picNum == 190 || picNum == 220 || picNum == 250
				 || picNum == 280;
		// 输了的图片
		boolean loseNum = picNum == 107 || picNum == 111 || picNum == 137
				 || picNum == 142 || picNum == 176 || picNum == 180
				 || picNum == 197 || picNum == 202 || picNum == 228
				 || picNum == 230 || picNum == 258 || picNum == 262
				 || picNum == 289 || picNum == 307 || picNum == 329
				 || picNum == 356;
		// 关卡介绍的图片
		boolean gameNum = picNum == 106 || picNum == 110 || picNum == 135
				 || picNum == 141 || picNum == 175 || picNum == 179
				 || picNum == 196 || picNum == 201 || picNum == 227
				 || picNum == 229 || picNum == 257 || picNum == 261
				 || picNum == 288 || picNum == 306 || picNum == 328
				 || picNum == 355;
		// 关卡介绍前一张
		boolean gameBeforeNum = picNum == 105 || picNum == 109 || picNum == 134
				 || picNum == 140 || picNum == 174 || picNum == 178
				 || picNum == 195 || picNum == 200 || picNum == 226
				 || picNum == 228 || picNum == 256 || picNum == 262
				 || picNum == 287 || picNum == 305 || picNum == 327
				 || picNum == 354;
		if (picNum == 106 && gameArea) {
        	Talk.this.newGame(1);
		} else if (picNum == 107) {
			if (retryArea) Talk.this.newGame(1);
			if (exitArea) Talk.this.saveData(1, false);
		} else if (picNum == 110 && gameArea) {
			Talk.this.newGame(2);
		} else if (picNum == 111) {
			if (retryArea) Talk.this.newGame(2);
			if (exitArea) Talk.this.saveData(1, false);
		} else if (picNum == 113 && exitArea) {
			Talk.this.saveData(2, true);
		} else if (picNum == 135 && gameArea) {
			Talk.this.newGame(3);
		} else if (picNum == 137) {
			if (retryArea) Talk.this.newGame(3);
			if (exitArea) Talk.this.saveData(2, false);
		} else if (picNum == 141 && gameArea) {
			Talk.this.newGame(4);
		} else if (picNum == 142) {
			if (retryArea) Talk.this.newGame(4);
			if (exitArea) Talk.this.saveData(2, false);
		} else if (picNum == 143 && exitArea) {
			Talk.this.saveData(3,true);
		} else if (picNum == 175 && gameArea) {
			Talk.this.newGame(5);
		} else if (picNum == 176) {
			if (retryArea) Talk.this.newGame(5);
			if (exitArea) Talk.this.saveData(3, false);
		} else if (picNum == 179  && gameArea) {
			Talk.this.newGame(6);
		} else if (picNum == 180) {
			if (retryArea) Talk.this.newGame(6);
			if (exitArea) Talk.this.saveData(3, false);
		} else if (picNum == 185 && exitArea) {
			Talk.this.saveData(4, true);
		} else if (picNum == 196 && gameArea) {
			Talk.this.newGame(7);
		} else if (picNum == 197) {
			if (retryArea) Talk.this.newGame(7);
			if (exitArea) Talk.this.saveData(4, false);
		} else if (picNum == 201 && gameArea) {
			Talk.this.newGame(8);
		} else if (picNum == 202) {
			if (retryArea) Talk.this.newGame(8);
			if (exitArea) Talk.this.saveData(4, false);
		} else if (picNum == 204 && exitArea) {
			Talk.this.saveData(5, true);
		} else if (picNum == 227 && gameArea) {
			Talk.this.newGame(9);
		} else if (picNum == 228) {
			if (retryArea) Talk.this.newGame(9);
			if (exitArea) Talk.this.saveData(5, false);
		} else if (picNum == 229 && gameArea) {
			Talk.this.newGame(10);
		} else if (picNum == 230) {
			if (retryArea) Talk.this.newGame(10);
			if (exitArea) Talk.this.saveData(5, false);
		} else if (picNum == 232 && exitArea) {
			Talk.this.saveData(6, true);
		} else if (picNum == 257 && gameArea) {
			Talk.this.newGame(11);
		} else if (picNum == 258) {
			if (retryArea) Talk.this.newGame(11);
			if (exitArea) Talk.this.saveData(6, false);
		} else if (picNum == 261 && gameArea) {
			Talk.this.newGame(12);
		} else if (picNum == 262) {
			if (retryArea) Talk.this.newGame(12);
			if (exitArea) Talk.this.saveData(6, false);
		} else if (picNum == 263 && exitArea) {
			Talk.this.saveData(7, true);
		} else if (picNum == 288 && gameArea) {
			Talk.this.newGame(13);
		} else if (picNum == 289) {
			if (retryArea) Talk.this.newGame(13);
			if (exitArea) Talk.this.saveData(7, false);
		} else if (picNum == 292 && nextArea) {
			picNum = 300;
			this.talk.setImg(talkImg[picNum]);
		} else if (picNum == 306 && gameArea) {
			Talk.this.newGame(14);
		} else if (picNum == 307) {
			if (retryArea) Talk.this.newGame(14);
			if (exitArea) Talk.this.saveData(7, false);
		} else if (picNum == 308 && nextArea) {
			picNum = 320;
			this.talk.setImg(talkImg[picNum]);
		} else if (picNum == 328 && gameArea) {
			Talk.this.newGame(15);
		} else if (picNum == 329) {
			if (retryArea) Talk.this.newGame(15);
			if (exitArea) Talk.this.saveData(7, false);
		} else if (picNum == 331 && nextArea) {
			picNum = 340;
			this.talk.setImg(talkImg[picNum]);
		} else if (picNum == 355 && gameArea) {
			Talk.this.newGame(16);
		} else if (picNum == 356) {
			if (retryArea) Talk.this.newGame(16);
			if (exitArea) Talk.this.saveData(7, false);
		} else if (picNum == 357 && exitArea) {
        	Talk.this.saveData(8, true);
		} else if ((!beginNum && !gameNum && !loseNum) && nextArea) {
			picNum++;
			this.talk.setImg(talkImg[picNum]);
		} else if (beginNum && beginArea) {
			picNum++;
			this.talk.setImg(talkImg[picNum]);
		}


		if (beginNum && beginArea) {
			Player.playSound("talknext");
		}
		else if (gameNum && gameArea) {
			Player.playSound("homeclick");
		}
		else if (loseNum && retryArea) {
			Player.playSound("talkagain");
		}
		else if (loseNum && exitArea) {
			Player.playSound("talkback");
		}
		else if (gameBeforeNum && nextArea) {
			Player.playSound("talknext");
			Player.stopMusic();
			Player.playMusic("agamebefore");
		}
		else if ((!beginNum && !gameNum && !loseNum) && nextArea) {
			Player.playSound("talknext");
		}
		else Player.playSound("talkwarn");
		this.talk.setImg(talkImg[picNum]);
		this.repaint();
//		System.out.println(picNum);
		
		}

	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	// 更新数据并返回地图
	public void saveData(int planet, boolean finish) {
    	Player.stopMusic();
    	open[0] = false;
    	boolean temp = open[planet];
    	open[planet] = true;
    	AdventureDao.getAdventureDao().save(open);
    	JPanelAdventureMap jPanelAdventureMap = new JPanelAdventureMap(game, false);
    	if (planet < 8) {
        	if (finish && !open[planet+1]) {
        		Player.playSound("amapunlockshow");
        		jPanelAdventureMap.getUnlock().setVisible(true);
        	}
    	}
    	if (planet == 8 && !temp) {
    		Player.playSound("amapunlockshow");
    		jPanelAdventureMap.getUnlock().setVisible(true);
    	}
    	game.setContentPane(jPanelAdventureMap);
    	System.out.println("hello");
		Controller.getController().init();
		game.repaint();
		game.revalidate();
		System.gc();
	}
	
	// 开始新的游戏
	public void newGame(int mode) {
    	game.setContentPane(new PanelGame(game, mode, false));
    	game.remove(Talk.this);
    	game.revalidate();
	}
	
	public int getPicNum() {
		return picNum;
	}

	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}

	public int getStartPic() {
		return startPic;
	}

	public int getEndPic() {
		return endPic;
	}

	public LayerBackground getTalk() {
		return talk;
	}
	
}
