package ui.adventuregame;

import image.ImgAdventureGame;
import image.ImgClassicGame;

import java.awt.Graphics;
import java.awt.Image;

import media.Player;
import system.Controller;
import ui.common.Layer;

/**
 * GameOver界面
 */
public class GameOver extends Layer {
	
	private Image img;
	
	public GameOver(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	/**
	 * 改变界面并播放音效
	 */
	public void setImg() {
		Player.stopMusic();
		int score0 = Controller.getController().getScores()[0];
		int score1 = Controller.getController().getScores()[1];
		int score2 = Controller.getController().getScores()[2];
		if (score0 > score1 && score0 > score2) {
			img = ImgAdventureGame.winner;
			Controller.getController().setWin(true);
			Player.playSound("agamewin");
		} else {
			img = ImgAdventureGame.loser;
			Player.playSound("agamelose");
		}
	}
	
}
