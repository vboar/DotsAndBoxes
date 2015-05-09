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
 * 第二个星球：爱虚荣的人
 */
public class Planet2 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet2(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(75, 265, 160, 160);
		if (jPanelAdventureMap.getOpen()[2]) {
			super.startImg = ImgAdventureMap.pOpen[2];
			super.rolloverImg = ImgAdventureMap.pOpenE[2];
			super.pressedImg = ImgAdventureMap.pOpenC[2];
		} else {
			super.startImg = ImgAdventureMap.pLock[2];
			super.rolloverImg = ImgAdventureMap.pLock[2];
			super.pressedImg = ImgAdventureMap.pLock[2];
		}

	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[2]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[2]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet2");
            		Talk talk = new Talk(jFrameGame,130, 135);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet2.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
