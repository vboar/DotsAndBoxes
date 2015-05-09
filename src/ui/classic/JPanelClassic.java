package ui.classic;

import image.ImgClassic;
import java.awt.Graphics;
import javax.swing.JPanel;
import system.JFrameGame;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 经典模式设置panel
 */
public class JPanelClassic  extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	private int numOfBoxes = 1;
	private boolean AI = true;
	
	public JPanelClassic(JFrameGame game) {
		
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgClassic.menu)
		};
		this.add();
	}
	
	/**
	 * 添加各种button
	 */
	public void add() {
		this.add(new JButtonRJ(game, this));
		this.add(new JButtonRR(game, this));
		this.add(new JButtonFive(game, this));
		this.add(new JButtonSeven(game, this));
		this.add(new JButtonNine(game, this));
		this.add(new JButtonEleven(game, this));
		this.add(new JButtonEasy(game, this));
		this.add(new JButtonMedium(game, this));
		this.add(new JButtonHard(game, this));
		this.add(new JButtonFirst(game, this));
		this.add(new JButtonLast(game, this));
		this.add(new JButtonStart(game, this));
		this.add(new JButtonBack(game, this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}

	public int getNumOfBoxes() {
		return numOfBoxes;
	}

	public void setNumOfBoxes(int numOfBoxes) {
		this.numOfBoxes = numOfBoxes;
	}

	public boolean isAI() {
		return AI;
	}

	public void setAI(boolean aI) {
		AI = aI;
	}
	
}
