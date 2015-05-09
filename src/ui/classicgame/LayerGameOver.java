package ui.classicgame;

import image.ImgClassicGame;
import java.awt.Graphics;
import java.awt.Image;
import media.Player;
import system.Controller;
import ui.common.Layer;

/**
 * GameOver界面
 */
public class LayerGameOver extends Layer {
	
	private Image img;
	
	public LayerGameOver(int x, int y, int w, int h) {
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
		int score0 = Controller.getController().getScores()[0];
		int score1 = Controller.getController().getScores()[1];
		int score2 = Controller.getController().getScores()[2];
		if (Controller.getController().getNumOfAI() > 0) {
			if (score0 > score1 && score0 > score2) {
				img = ImgClassicGame.gameOver[0];
				Player.playSound("cgamewin");
			} else {
				img = ImgClassicGame.gameOver[1];
				Player.playSound("cgamelose");
			}
		} else {
			if (score0 > score1) {
				img = ImgClassicGame.gameOver[2];
			} else {
				img = ImgClassicGame.gameOver[3];
			}
			Player.playSound("cgamewin");
		}
	}
	
}
