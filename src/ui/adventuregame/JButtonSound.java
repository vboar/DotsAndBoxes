package ui.adventuregame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonCircle;
import image.ImgAdventureGame;
import image.ImgClassicGame;

/**
 * 经典模式游戏音量开关按钮，圆形按钮
 */
public class JButtonSound extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private PanelGame panelGame;

	public JButtonSound(int diameter, int inDiameter, JFrameGame jFrameGame, 
			PanelGame panelGame) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.panelGame = panelGame;
		this.setBounds(600, 510, 48, 48);
		if (!Controller.getController().isMusicOn() && !Controller.getController().isSoundOn()) {
			this.setOff();
		} else {
			this.setOn();
		}
	}
	
	public void setOn() {
		startImg = ImgAdventureGame.soundOn;
		rolloverImg = ImgAdventureGame.soundOnEntered;
		pressedImg = ImgAdventureGame.soundOff;
	}
	
	public void setOff() {
		startImg = ImgAdventureGame.soundOff;
		rolloverImg = ImgAdventureGame.soundOffEntered;
		pressedImg = ImgAdventureGame.soundOn;
	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
        		if (!Controller.getController().isMusicOn() && !Controller.getController().isSoundOn()) {
        			JButtonSound.this.setOn();
        			Controller.getController().setMusicOn(true);
        			Controller.getController().setSoundOn(true);
        	    	int mode = panelGame.getMode();
        			if (mode == 1) {
        				Player.playMusic("cgame0");
        			} else if (mode == 2) {
        				Player.playMusic("cgame0");
        			} else if (mode == 3) {
        				Player.playMusic("cgame1");
        			} else if (mode == 4) {
        				Player.playMusic("cgame1");
        			} else if (mode == 5) {
        				Player.playMusic("cgame2");
        			} else if (mode == 6) {
        				Player.playMusic("cgame2");
        			} else if (mode == 7) {
        				Player.playMusic("cgame3");
        			} else if (mode == 8) {
        				Player.playMusic("cgame3");
        			} else if (mode == 9) {
        				Player.playMusic("cgame4");
        			} else if (mode == 10) {
        				Player.playMusic("cgame4");
        			} else if (mode == 11) {
        				Player.playMusic("cgame5");
        			} else if (mode == 12) {
        				Player.playMusic("cgame5");
        			} else if (mode == 13) {
        				Player.playMusic("cgame6");
        			} else if (mode == 14) {
        				Player.playMusic("cgame7");
        			} else if (mode == 15) {
        				Player.playMusic("cgame8");
        			} else if (mode == 16) {
        				Player.playMusic("cgame9");
        			}
        		} else {
        			JButtonSound.this.setOff();
        			Controller.getController().setMusicOn(false);
        			Controller.getController().setSoundOn(false);
        			Player.stopMusic();
        		}
            }
        };
    	return mouseAdapter;
    }

}
