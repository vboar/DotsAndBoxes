package ui.classicgame;

import image.ImgClassicGame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.classicgame.JPanelClassicGame;
import ui.common.JButtonRect;

/**
 * 经典模式游戏界面重新游戏按钮，其余注释参考ui.home包中的JButtonAdventure类
 */
public class JButtonRetry extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelClassicGame jPanelClassicGame;
	
	public JButtonRetry(JFrameGame jFrameGame, JPanelClassicGame jPanelClassicGame) {
		super(150, 50, 0, 0, 150, 50);
		this.jFrameGame = jFrameGame;
		this.jPanelClassicGame = jPanelClassicGame;
		this.setBounds(320, 392, 150, 50);
		super.setImg(ImgClassicGame.retry);
		super.setRolloverImg(ImgClassicGame.retryEntered);
		super.setPressedImg(ImgClassicGame.retryClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("cgameretry");
            	Controller.getController().retry();
            	Player.stopMusic();
            	Player.playMusic("cgame" + Controller.getController().getMusicNum(10));
            	jFrameGame.remove(jPanelClassicGame);
            	jFrameGame.setContentPane(new JPanelClassicGame(jFrameGame, 0));
            	jFrameGame.revalidate();
            	System.gc();
            }
        };
    	return mouseAdapter;
    }
}

