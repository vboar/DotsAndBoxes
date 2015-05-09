package ui.adventuregame;

import image.ImgAdventureGame;
import image.ImgClassicGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.classicgame.JPanelClassicGame;
import ui.common.JButtonRect;

/**
 * ����ģʽ��Ϸ����������Ϸ��ť������ע�Ͳο�ui.home���е�JButtonAdventure��
 */
public class JButtonRetry extends JButtonRect {

	private JFrameGame jFrameGame;
	private PanelGame panelGame;
	private int mode;
	
	public JButtonRetry(JFrameGame jFrameGame, PanelGame panelGame, int mode) {
		super(150, 40, 0, 0, 150, 40);
		this.mode = mode;
		this.jFrameGame = jFrameGame;
		this.panelGame = panelGame;
		this.setBounds(320, 400, 150, 40);
		super.setImg(ImgAdventureGame.re);
		super.setRolloverImg(ImgAdventureGame.reE);
		super.setPressedImg(ImgAdventureGame.reC);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("agameretry");
            	Controller.getController().retry();
            	Player.stopMusic();
            	Player.playMusic("cgame" + Controller.getController().getMusicNum(10));
            	jFrameGame.remove(panelGame);
            	jFrameGame.setContentPane(new PanelGame(jFrameGame, mode, true));
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}

