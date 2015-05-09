package ui.adventure;

import image.ImgAdventure;

import java.awt.Graphics;

import javax.swing.JPanel;

import system.JFrameGame;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 剧情模式设置panel
 */
public class JPanelAdventure extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	
	public JPanelAdventure(JFrameGame game) {
		
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgAdventure.bg)
		};
		this.add();
	}
	
	/**
	 * 添加各种button
	 */
	public void add() {
		this.add(new JButtonNew(game, this));
		this.add(new JButtonLoad(game, this));
		this.add(new JButtonBack(game, this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
}
