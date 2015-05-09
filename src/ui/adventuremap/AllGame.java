package ui.adventuremap;

import image.ImgAdventureMap;
import image.ImgHome;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import media.Player;
import system.JFrameGame;
import ui.adventuregame.PanelGame;
import ui.common.JButtonRect;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 小王子星球展示所有模式
 */
public class AllGame extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	
	public AllGame(JFrameGame game) {
		
		this.game = game;
		this.setLayout(null);
		layers = new Layer[] {
				new LayerBackground(0, 0, 800, 600, ImgAdventureMap.timeBg),
		};
		this.add(new GameLevel(60, 153, 140, 60, 1, game));
		this.add(new GameLevel(195, 131, 140, 60, 2, game));
		this.add(new GameLevel(59, 379, 140, 60, 3, game));
		this.add(new GameLevel(60, 452, 140, 60, 4, game));
		this.add(new GameLevel(262, 216, 60, 140, 5, game));
		this.add(new GameLevel(261, 387, 60, 140, 6, game));
		this.add(new GameLevel(357, 125, 140, 60, 7, game));
		this.add(new GameLevel(357, 200, 140, 60, 8, game));
		this.add(new GameLevel(365, 388, 60, 140, 9, game));
		this.add(new GameLevel(439, 388, 60, 140, 10, game));
		this.add(new GameLevel(510, 140, 60, 140, 11, game));
		this.add(new GameLevel(510, 311, 60, 140, 12, game));
		this.add(new GameLevel(585, 128, 140, 60, 13, game));
		this.add(new GameLevel(585, 203, 140, 60, 14, game));
		this.add(new GameLevel(585, 309, 140, 60, 15, game));
		this.add(new GameLevel(585, 382, 140, 60, 16, game));
		this.add(new Back());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
	private class Back extends JButtonRect {

		public Back() {
			super(180, 180, 17, 91, 145, 71);
			this.setBounds(610, 418, 180, 180);
			super.setImg(ImgAdventureMap.princebackHome);
			super.setRolloverImg(ImgAdventureMap.princebackHomeE);
			super.setPressedImg(ImgAdventureMap.princebackHome);
		}
		@Override
	    public MouseAdapter getMouseAdapter() {
	    	MouseAdapter mouseAdapter = new MouseAdapter(){
	            public void mouseEntered(MouseEvent e) {
	            	Player.playSound("homerollover");
	            }
	            public void mouseExited(MouseEvent e) {} 
	            public void mouseClicked(MouseEvent e) {
	            	Player.playSound("homeback");
	            	game.setContentPane(new PrinceIn(game));
	            	game.revalidate();
	            }
	        };
	    	return mouseAdapter;
	    }
		
	}
}
