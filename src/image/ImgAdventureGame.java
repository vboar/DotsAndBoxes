package image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgAdventureGame {

//	public static Image[] talk = new Image[360];
	
	public static Image[] bg = new Image[11];
	
	public static Image board = new ImageIcon(
			"image/jpanels/jpaneladventuregame/board.png").getImage();
	
	static {
//		for (int i = 100; i < 114; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 130; i < 144; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 160; i < 186; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 190; i < 205; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 220; i < 233; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 250; i < 264; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 280; i < 295; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 300; i < 309; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 320; i < 332; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
//		for (int i = 340; i < 358; i++) {
//			talk[i] = new ImageIcon(
//					"image/jpanels/jpaneladventuregame/talk/" + i + ".jpg").getImage();
//		}
		for (int i = 0; i < bg.length; i++) {
			bg[i] = new ImageIcon(
					"image/jpanels/jpaneladventuregame/bg/" + i + ".jpg").getImage();
		}
	}
	
	public static Image back = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/back.png").getImage();
	
	public static Image backEntered = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/backentered.png").getImage();
	
	public static Image backClicked = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/backclicked.png").getImage();
	
	public static Image soundOn = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/soundon.png").getImage();
	
	public static Image soundOff = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/soundoff.png").getImage();
	
	public static Image soundOnEntered = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/soundonentered.png").getImage();
	
	public static Image soundOffEntered = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/soundoffentered.png").getImage();
	
	public static Image winner = new ImageIcon("image/jpanels/jpaneladventuregame/winner.png").getImage();
	
	public static Image loser = new ImageIcon("image/jpanels/jpaneladventuregame/loser.png").getImage();
	
	public static Image con = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/continue.png").getImage();
	
	public static Image conE = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/continueentered.png").getImage();
	
	public static Image conC = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/continueclicked.png").getImage();
	
	public static Image re = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/retryss.png").getImage();
	
	public static Image reE = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/retryssentered.png").getImage();
	
	public static Image reC = new ImageIcon("image/jpanels/jpaneladventuregame/jbuttons/retryssclicked.png").getImage();
}
