package ui.adventuremap;

import image.ImgAdventureMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * ����ģʽ��Ϸ����������Ϸ��ť������ע�Ͳο�ui.home���е�JButtonAdventure��
 */
public class BackHome extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;
	
	public BackHome(JFrameGame jFrameGame, JPanelAdventureMap jPanelAdventureMap) {
		super(160, 160, 10, 86, 137, 61);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(635, 435, 160, 160);
		super.setImg(ImgAdventureMap.back);
		super.setRolloverImg(ImgAdventureMap.backEntered);
		super.setPressedImg(ImgAdventureMap.backClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("amapback");
            	Player.stopMusic();
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	// ���������󡢻�ý���
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	Player.playMusic("home");
            	jFrameGame.remove(jPanelAdventureMap);
            	// ��������ʼ��
        		Controller.getController().init();
            	jFrameGame.revalidate();
            	// ����ϵͳ����
            	System.gc();
            }
        };
    	return mouseAdapter;
    }
}

