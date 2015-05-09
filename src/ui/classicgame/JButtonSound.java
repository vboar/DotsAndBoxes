package ui.classicgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonCircle;
import image.ImgClassicGame;

/**
 * 经典模式游戏音量开关按钮，圆形按钮
 */
public class JButtonSound extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelClassicGame jpanelClassicGame;

	public JButtonSound(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelClassicGame jpanelClassicGame) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jpanelClassicGame = jpanelClassicGame;
		this.setBounds(600, 510, 50, 50);
		if (!Controller.getController().isMusicOn() && !Controller.getController().isSoundOn()) {
			this.setOff();
		} else {
			this.setOn();
		}
	}
	
	public void setOn() {
		startImg = ImgClassicGame.soundOn;
		rolloverImg = ImgClassicGame.soundOnEntered;
		pressedImg = ImgClassicGame.soundOff;
	}
	
	public void setOff() {
		startImg = ImgClassicGame.soundOff;
		rolloverImg = ImgClassicGame.soundOffEntered;
		pressedImg = ImgClassicGame.soundOn;
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
//        	    	Player.playSound("cgamesound");
        	    	Player.playMusic("cgame" + Controller.getController().getMusicNum(10));
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
