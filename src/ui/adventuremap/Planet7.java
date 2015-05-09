package ui.adventuremap;

import image.ImgAdventureMap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.adventuregame.Talk;
import ui.common.JButtonCircle;
import ui.common.JPanelOnceMovie;

/**
 * �ڶ������򣺰����ٵ���
 */
public class Planet7 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet7(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(520, 15, 160, 160);
		if (jPanelAdventureMap.getOpen()[7]) {
			super.startImg = ImgAdventureMap.pOpen[7];
			super.rolloverImg = ImgAdventureMap.pOpenE[7];
			super.pressedImg = ImgAdventureMap.pOpenC[7];
		} else {
			super.startImg = ImgAdventureMap.pLock[7];
			super.rolloverImg = ImgAdventureMap.pLock[7];
			super.pressedImg = ImgAdventureMap.pLock[7];
		}

	}
	
    /**
     * ����������
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[7]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[7]) {
            		Player.stopMusic();
            		Player.playMusic("planet7");
            		Player.playSound("amapclick");
            		Talk talk = new Talk(jFrameGame,280, 288);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet7.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
