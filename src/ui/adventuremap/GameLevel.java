package ui.adventuremap;

import image.ImgAdventureMap;
import image.ImgHome;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.adventuregame.PanelGame;
import ui.common.JButtonRect;

public class GameLevel extends JButtonRect {

	private Image img;
	private int mode;
	private JFrameGame game;
	
	public GameLevel(int x, int y, int width, int height, int mode, JFrameGame game) {
		super(width, height, 0, 0, width, height);
		this.setBounds(x, y, width, height);
		this.mode = mode;
		this.game = game;
		this.setImg();
	}

	public void setImg() {
		super.setImg(null);
		super.setRolloverImg(ImgAdventureMap.level[mode-1]);
		super.setPressedImg(ImgAdventureMap.level[mode-1]);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("allgameenter");
            }
            public void mouseExited(MouseEvent e) {} 
            public void mouseClicked(MouseEvent e) {
            	Player.stopMusic();
            	Player.playSound("allgameclick");
            	game.setContentPane(new PanelGame(game, mode, true));
            	game.revalidate();
            }
        };
    	return mouseAdapter;
    }
}
