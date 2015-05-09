package image;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImgAdventureMap {

	public static Image bg0 = new ImageIcon("image/jpanels/jpaneladventuremap/bg/bg0.jpg").getImage();
	
	public static Image bg1 = new ImageIcon("image/jpanels/jpaneladventuremap/bg/bg0.jpg").getImage();
	
	public static Image[] pOpen = new Image[8];
	
	public static Image[] pOpenE = new Image[8];
	
	public static Image[] pOpenC = new Image[8];
	
	public static Image[] pLock = new Image[8];
	
	public static Image back = new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/back.png").getImage();
	
	public static Image backEntered = new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/backentered.png").getImage();
	
	public static Image backClicked = new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/backclicked.png").getImage();
	
	public static Image prince = new ImageIcon("image/jpanels/jpaneladventuremap/planets/prince.png").getImage();
	
	public static Image princeEntered = new ImageIcon("image/jpanels/jpaneladventuremap/planets/princeentered.png").getImage();
	
	public static Image princeBack = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/back.png").getImage();
	
	public static Image princeBackE = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/backentered.png").getImage();
	
	public static Image princeBackC = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/backclicked.png").getImage();
	
	public static Image machine = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/machine.png").getImage();
	
	public static Image machineE = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/machineentered.png").getImage();
	
	public static Image princeBg = new ImageIcon("image/jpanels/jpaneladventuremap/prince/bg.jpg").getImage();
	
	public static Image princebackHome = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/backhome.png").getImage();
	
	public static Image princebackHomeE = new ImageIcon("image/jpanels/jpaneladventuremap/prince/buttons/backhomeentered.png").getImage();
	
	public static Image timeBg = new ImageIcon("image/jpanels/jpaneladventuremap/prince/timebg.jpg").getImage();
	
	public static Image[] level = new Image[16];
	
	
	static {
		for (int i = 0; i < pOpen.length; i++) {
			pOpen[i] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/planets/" + i + "open.png").getImage();
		}
		for (int i = 0; i < pOpenE.length; i++) {
			pOpenE[i] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/planets/" + i + "opene.png").getImage();
		}
		for (int i = 0; i < pOpenC.length; i++) {
			pOpenC[i] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/planets/" + i + "openc.png").getImage();
		}
		for (int i = 0; i < pLock.length; i++) {
			pLock[i] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/planets/" + i + "lock.png").getImage();
		}
		for (int i = 1; i <= level.length; i++) {
			level[i-1] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/prince/level/" + i + ".png").getImage();
		}
	}
	
}
