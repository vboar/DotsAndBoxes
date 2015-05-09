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
 * 经典模式游戏界面重新游戏按钮，其余注释参考ui.home包中的JButtonAdventure类
 */
public class JButtonContinue extends JButtonRect {

	private JFrameGame jFrameGame;
	private PanelGame panelGame;
	private int start1;
	private int end1;
	private int start2;
	private int end2;
	
	public JButtonContinue(JFrameGame jFrameGame, PanelGame panelGame,
			int start1, int end1, int start2, int end2) {
		super(150, 50, 0, 0, 150, 50);
		this.start1 = start1;
		this.start2 = start2;
		this.end1 = end1;
		this.end2 = end2;
		this.jFrameGame = jFrameGame;
		this.panelGame = panelGame;
		this.setBounds(320, 400, 150, 50);
		super.setImg(ImgAdventureGame.con);
		super.setRolloverImg(ImgAdventureGame.conE);
		super.setPressedImg(ImgAdventureGame.conC);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("agamecontinue");
            	Player.stopMusic();
            	boolean win = Controller.getController().isWin();
            	jFrameGame.remove(panelGame);
            	if (win) {
            		Player.playMusic("agamewin" + Controller.getController().getMusicNum(5));
            		jFrameGame.setContentPane(new Talk(jFrameGame, start1, end1));
            	} else {
            		Player.playMusic("agamelose");
            		jFrameGame.setContentPane(new Talk(jFrameGame, start2, end2));
            	}
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}

