package ui.adventuregame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.adventuremap.AllGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.common.JButtonCircle;
import ui.home.JPanelHome;
import image.ImgAdventureGame;
import image.ImgClassicGame;

/**
 * ����ģʽ��Ϸ���淵�ذ�ť��Բ�ΰ�ť
 */
public class JButtonBack extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private PanelGame panelGame;
	private boolean prince = true;

	public JButtonBack(int diameter, int inDiameter, JFrameGame jFrameGame, 
			PanelGame panelGame, boolean prince) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.panelGame = panelGame;
		this.prince = prince;
		this.setBounds(668, 474, 100, 100);
		super.startImg = ImgAdventureGame.back;
		super.rolloverImg = ImgAdventureGame.backEntered;
		super.pressedImg = ImgAdventureGame.backClicked;
	}
	
    /**
     * ����������
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (prince) {
                	Player.stopMusic();
                	Player.playSound("agameback");
                	Player.playMusic("princein");
                	jFrameGame.setContentPane(new AllGame(jFrameGame));
                	// ��������ʼ��
            		Controller.getController().init();
                	jFrameGame.revalidate();
                	// ����ϵͳ����
                	System.gc();
            	} else {
                	Player.stopMusic();
                	Player.playSound("agameback");
                	JPanelAdventureMap jPanelAdventureMap = new JPanelAdventureMap(jFrameGame, false);
                	jFrameGame.setContentPane(jPanelAdventureMap);
                	// ��������ʼ��
            		Controller.getController().init();
                	jFrameGame.revalidate();
                	// ����ϵͳ����
                	System.gc();
            	}
            }
        };
    	return mouseAdapter;
    }

}
