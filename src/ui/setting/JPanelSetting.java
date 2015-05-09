package ui.setting;

import image.ImgSetting;
import java.awt.Graphics;
import javax.swing.JPanel;
import system.JFrameGame;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * …Ë÷√panel
 */
public class JPanelSetting extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	
	public JPanelSetting(JFrameGame game) {
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgSetting.bg),
		};
		this.add(new JButtonBack(game, this));
		this.add(new JButtonMusic(game, this));
		this.add(new JButtonSound(game, this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
}
