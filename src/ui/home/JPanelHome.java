package ui.home;

import image.ImgHome;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import media.Player;
import system.Controller;
import system.JFrameGame;
import system.TempFrame;
import ui.classicgame.JPanelClassicGame;
import ui.common.JPanelCycleMovie;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 主界面panel
 */
public class JPanelHome extends JPanel implements KeyListener {
	
	private JFrameGame game;
	private Layer[] layers = null;
	
	public JPanelHome(JFrameGame game) {
		
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgHome.bg),
		};
		// 添加动态标题
		this.add(new JPanelCycleMovie(game, 11, 75, 75, 525, 225,
				"image/jpanels/jpaneltitle/title", 150, true));
		this.add(new JButtonSetting(game, this));
		this.add(new JButtonClassic(game, this));
		this.add(new JButtonAdventure(game, this));
		this.add(new JButtonHelp(game, this));
		this.add(new JButtonExit(game, this));
		this.addKeyListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1 || e.getKeyCode() == KeyEvent.VK_F2) {
			if (e.getKeyCode() == KeyEvent.VK_F2) {
				Controller.getController().setPlayerFirst(false);
			}
			Controller.getController().setDifficulty(4);
        	game.setContentPane(new JPanelClassicGame(game, 0));
        	game.remove(JPanelHome.this);
        	Player.stopMusic();
        	Player.playMusic("cgame1");
        	game.revalidate();
		}
		if (e.getKeyCode() == KeyEvent.VK_F12) {
			new TempFrame();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
