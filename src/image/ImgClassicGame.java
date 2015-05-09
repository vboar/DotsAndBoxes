package image;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ¾­µäÄ£Ê½ÓÎÏ·Í¼Æ¬¿â
 */
public class ImgClassicGame {
	
	public static Image bg1 = new ImageIcon("image/jpanels/jpanelclassicgame/background1.jpg").getImage();
	
	public static Image bg2 = new ImageIcon("image/jpanels/jpanelclassicgame/background2.jpg").getImage();
	
	public static Image board = new ImageIcon("image/jpanels/jpanelclassicgame/board.png").getImage();
	
	public static Image back = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/back.png").getImage();
	
	public static Image backEntered = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/backentered.png").getImage();
	
	public static Image backClicked = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/backclicked.png").getImage();
	
	public static Image soundOn = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/soundon.png").getImage();
	
	public static Image soundOff = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/soundoff.png").getImage();
	
	public static Image soundOnEntered = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/soundonentered.png").getImage();
	
	public static Image soundOffEntered = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/soundoffentered.png").getImage();
	
	public static Image retry = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/retry.png").getImage();
	
	public static Image retryEntered = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/retryentered.png").getImage();
	
	public static Image retryClicked = new ImageIcon("image/jpanels/jpanelclassicgame/jbuttons/retryclicked.png").getImage();
	
	public static Image[] num0 = new Image[10];
	
	public static Image[] num1 = new Image[10];
	
	public static Image[] gameOver = new Image[4];
	
	/**
	 * ¾²Ì¬¿é
	 */
	static {
		for(int i = 0; i < num0.length; i++) {
			num0[i] = new ImageIcon("image/jpanels/jpanelclassicgame/number/" + i + "r.png").getImage();
		}
		for(int i = 0; i < num1.length; i++) {
			num1[i] = new ImageIcon("image/jpanels/jpanelclassicgame/number/" + i + "b.png").getImage();
		}
		for(int i = 0; i < gameOver.length; i++) {
			gameOver[i] = new ImageIcon("image/jpanels/jpanelclassicgame/gameover/" + i + ".png").getImage();
		}
	}
	
}
