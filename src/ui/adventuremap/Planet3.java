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
 * 第三个星球：酒鬼
 */
public class Planet3 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet3(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(220, 250, 160, 160);
		if (jPanelAdventureMap.getOpen()[3]) {
			super.startImg = ImgAdventureMap.pOpen[3];
			super.rolloverImg = ImgAdventureMap.pOpenE[3];
			super.pressedImg = ImgAdventureMap.pOpenC[3];
		} else {
			super.startImg = ImgAdventureMap.pLock[3];
			super.rolloverImg = ImgAdventureMap.pLock[3];
			super.pressedImg = ImgAdventureMap.pLock[3];
		}

	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[3]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[3]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet3");
            		Talk talk = new Talk(jFrameGame,160, 175);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet3.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
