package ui.adventuregame;

import image.ImgAdventureGame;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.classicgame.JPanelClassicGame;
import ui.classicgame.LayerScore;
import ui.common.Layer;
import ui.common.LayerBackground;

public class PanelGame extends JPanelClassicGame {

	private int mode;
	private Image bg;
	private GameOver gameOver;
	private JButtonContinue jc;
	private JButtonRetry jr;
	private JLabel tips;
	private boolean prince;
	
	public PanelGame(JFrameGame game, int mode, boolean prince) {
		super(game, mode);
		this.prince = prince;
		this.add(new JButtonBack(80, 80, game, this, prince));
		this.add();
	}

	@Override
	public void add() {
		this.mode = super.getMode();
		this.setInit();
		LayerBackground lb = null;
		gameOver = new GameOver(0, 0, 800, 600);
		lb = new LayerBackground(0, 0, 800, 600, bg);
		layers = new Layer[] {
				lb,
				new LayerBackground(200, 100, 400, 400, ImgAdventureGame.board),
				new LayerScore(0, 0, 800, 600),
				gameOver
		};
		this.add(new JButtonSound(34, 34, game, this));
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (e.getX() <50 && e.getY() > 550) {
//					int[] scores = new int[3];
//					scores[0] = 999;
//					scores[1] = 0;
//					scores[2] = 0;
//					Controller.getController().setScores(scores);
//					Controller.getController().getBoard().getGame().gameOver();
//					Controller.getController().getBoard().removeAll();
//					gameOver();
//				}
//				if (e.getX() <50 && e.getY() < 550 && e.getY() > 500) {
//					int[] scores = new int[3];
//					scores[0] = 0;
//					scores[1] = 999;
//					scores[2] = 999;
//					Controller.getController().setScores(scores);
//					Controller.getController().getBoard().getGame().gameOver();
//					Controller.getController().getBoard().removeAll();
//					gameOver();
//				} 
//			}
//		});
	}
	
	public void setInit() {
    	Player.stopMusic();
		if (mode == 1) {
			bg = ImgAdventureGame.bg[1];
			jc = new JButtonContinue(game, this, 108, 110, 107, 107);
			Player.playMusic("cgame0");
		} else if (mode == 2) {
			bg = ImgAdventureGame.bg[1];
			jc = new JButtonContinue(game, this, 112, 113, 111, 111);
			Player.playMusic("cgame0");
		} else if (mode == 3) {
			bg = ImgAdventureGame.bg[2];
			jc = new JButtonContinue(game, this, 138, 141, 136, 137);
			Player.playMusic("cgame1");
		} else if (mode == 4) {
			bg = ImgAdventureGame.bg[2];
			jc = new JButtonContinue(game, this, 142, 142, 143, 143);
			Player.playMusic("cgame1");
		} else if (mode == 5) {
			bg = ImgAdventureGame.bg[3];
			jc = new JButtonContinue(game, this, 177, 179, 176, 176);
			Player.playMusic("cgame2");
		} else if (mode == 6) {
			bg = ImgAdventureGame.bg[3];
			jc = new JButtonContinue(game, this, 181, 185, 180, 180);
			Player.playMusic("cgame2");
		} else if (mode == 7) {
			bg = ImgAdventureGame.bg[4];
			jc = new JButtonContinue(game, this, 198, 201, 197, 197);
			Player.playMusic("cgame3");
		} else if (mode == 8) {
			bg = ImgAdventureGame.bg[4];
			jc = new JButtonContinue(game, this, 203, 204, 202, 202);
			Player.playMusic("cgame3");
		} else if (mode == 9) {
			bg = ImgAdventureGame.bg[5];
			jc = new JButtonContinue(game, this, 229, 229, 228, 228);
			Player.playMusic("cgame4");
		} else if (mode == 10) {
			bg = ImgAdventureGame.bg[5];
			jc = new JButtonContinue(game, this, 231, 232, 230, 230);
			Player.playMusic("cgame4");
		} else if (mode == 11) {
			bg = ImgAdventureGame.bg[6];
			jc = new JButtonContinue(game, this, 259, 261, 258, 258);
			Player.playMusic("cgame5");
		} else if (mode == 12) {
			bg = ImgAdventureGame.bg[6];
			jc = new JButtonContinue(game, this, 263, 263, 262, 262);
			Player.playMusic("cgame5");
		} else if (mode == 13) {
			bg = ImgAdventureGame.bg[7];
			jc = new JButtonContinue(game, this, 290, 306, 289, 289);
			Player.playMusic("cgame6");
		} else if (mode == 14) {
			bg = ImgAdventureGame.bg[8];
			jc = new JButtonContinue(game, this, 308, 328, 307, 307);
			Player.playMusic("cgame7");
		} else if (mode == 15) {
			bg = ImgAdventureGame.bg[9];
			jc = new JButtonContinue(game, this, 330, 355, 329, 329);
			Player.playMusic("cgame8");
		} else if (mode == 16) {
			bg = ImgAdventureGame.bg[10];
			jc = new JButtonContinue(game, this, 357, 357, 356, 356);
			Player.playMusic("cgame9");
		}
	}
	
	@Override
	public void gameOver() {
		gameOver.setImg();
		if (prince) {
			jr = new JButtonRetry(game, this, mode);
			this.add(jr);
		} else {
			this.add(jc);
		}
	}
	
}
