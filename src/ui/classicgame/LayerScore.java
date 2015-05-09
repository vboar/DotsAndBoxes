package ui.classicgame;

import image.ImgClassicGame;
import java.awt.Graphics;
import java.awt.Image;
import system.Controller;
import ui.common.Layer;

/**
 * 分数界面
 */
public class LayerScore extends Layer {
	
	private Image img;
	
	private int hundred0 = 0;
	private int ten0 = 0;
	private int one0 = 0;
	
	private int hundred1 = 0;
	private int ten1 = 0;
	private int one1 = 0;
	
	private int hundred2 = 0;
	private int ten2 = 0;
	private int one2 = 0;
	
	public LayerScore(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * 计算分数，分成百位十位个位
	 */
	public void calculate() {
		int score0 = Controller.getController().getScores()[0];
		int score1 = Controller.getController().getScores()[1];
		int score2 = Controller.getController().getScores()[2];
		hundred0 = score0 / 100;
		hundred1 = score1 / 100;
		hundred2 = score2 / 100;
		ten0 = score0 % 100 / 10;
		ten1 = score1 % 100 / 10;
		ten2 = score2 % 100 / 10;
		one0 = score0 % 100 % 10;
		one1 = score1 % 100 % 10;
		one2 = score2 % 100 % 10;
	}
	
	@Override
	public void paint(Graphics g) {
		this.calculate();
		if (hundred0 != 0)	g.drawImage(ImgClassicGame.num0[hundred0], 60, 418, null);
		if (hundred1 != 0)	g.drawImage(ImgClassicGame.num1[hundred1], 641, 254, null);
		if (hundred0 != 0 || (hundred0 == 0)&&(ten0 != 0))
			g.drawImage(ImgClassicGame.num0[ten0], 98, 418, null);
		if (hundred1 != 0 || (hundred1 == 0)&&(ten1 != 0))
			g.drawImage(ImgClassicGame.num1[ten1], 679, 254, null);
		g.drawImage(ImgClassicGame.num0[one0], 135, 418, null);
		g.drawImage(ImgClassicGame.num1[one1], 716, 254, null);
		
		if (Controller.getController().getNumOfAI() == 2) {
			if (hundred2 != 0)	g.drawImage(ImgClassicGame.num1[hundred2], 641, 384, null);
			if (hundred2 != 0 || (hundred2 == 0)&&(ten2 != 0))
				g.drawImage(ImgClassicGame.num1[ten2], 679, 384, null);
			g.drawImage(ImgClassicGame.num1[one2], 716, 384, null);
		}
		
	}
	
}
