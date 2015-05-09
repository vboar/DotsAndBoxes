package ui.adventuremap;

import image.ImgAdventureMap;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.JFrameGame;
import ui.common.JPanelCycleMovie;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * Ð¡Íõ×ÓÐÇÇò
 */
public class PrinceIn extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	private JLabel timeLabel;
	
	public PrinceIn(JFrameGame game) {
		
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgAdventureMap.princeBg),
		};
		timeLabel = new JLabel(new ImageIcon("image/jpanels/jpaneladventuremap/prince/timelabel.png"));
		timeLabel.setBounds(400, 325, 240, 200);
		timeLabel.setVisible(false);
		this.add(timeLabel);
		this.add(new PrinceBack(game, this));
		this.add(new JPanelCycleMovie(game, 7, 650, 370, 160, 160,
				"image/jpanels/jpaneladventuremap/prince/prince/", 500, true));
		this.add(new JPanelCycleMovie(game, 30, 335, 140, 200, 200,
				"image/jpanels/jpaneladventuremap/prince/sheep/", 600, true));
		this.add(new JPanelCycleMovie(game, 28, 118, 53, 200, 200,
				"image/jpanels/jpaneladventuremap/prince/rose/", 600, true));
		this.add(new Machine(game, this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}

	public JLabel getTimeLabel() {
		return timeLabel;
	}
	
}
