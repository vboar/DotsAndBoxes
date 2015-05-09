package ui.classic;

import image.ImgClassic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.classicgame.JPanelClassicGame;
import ui.common.JButtonRect;

/**
 * 经典模式设置开始游戏按钮button，其余注释参考ui.home包中的JButtonAdventure类
 */
public class JButtonStart extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelClassic jPanelClassic;
	
	public JButtonStart(JFrameGame jFrameGame, JPanelClassic jPanelClassic ) {
		super(117, 44, 0, 0, 117, 44);
		this.jFrameGame = jFrameGame;
		this.jPanelClassic = jPanelClassic;
		this.setBounds(253, 442, 117, 44);
		super.setImg(ImgClassic.open);
		super.setRolloverImg(ImgClassic.open);
		super.setPressedImg(ImgClassic.openClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homegame");
            	JButtonStart.this.setVisible(false);
            	jFrameGame.setContentPane(new JPanelClassicGame(jFrameGame, 0));
            	jFrameGame.remove(JButtonStart.this.jPanelClassic);
            	Player.stopMusic();
            	Player.playMusic("cgame" + Controller.getController().getMusicNum(10));
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}

