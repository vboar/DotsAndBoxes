package image;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImgMovie {

	public static Image[] loading = new Image[17];
	
	public static Image[] homeFade = new Image[11];
	
	public static Image[] introduction = new Image[168];
	
	static {
		for (int i = 0; i < loading.length; i++) {
			loading[i] = new ImageIcon(
					"image/jpanels/common/loading/" + i + ".jpg").getImage();
		}
		for (int i = 0; i < homeFade.length; i++) {
			homeFade[i] = new ImageIcon(
					"image/jpanels/jpaneladventure/fade/" + i + ".jpg").getImage();
		}
		for (int i = 0; i < introduction.length; i++) {
			introduction[i] = new ImageIcon(
					"image/jpanels/jpaneladventuremap/introduction/Part 1-" + i + ".jpg").getImage();
		}
		
	}
	
}
