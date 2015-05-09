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
 * 第一个星球：国王
 */
public class Planet1 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet1(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(5, 400, 160, 160);
		if (jPanelAdventureMap.getOpen()[1]) {
			super.startImg = ImgAdventureMap.pOpen[1];
			super.rolloverImg = ImgAdventureMap.pOpenE[1];
			super.pressedImg = ImgAdventureMap.pOpenC[1];
		} else {
			super.startImg = ImgAdventureMap.pLock[1];
			super.rolloverImg = ImgAdventureMap.pLock[1];
			super.pressedImg = ImgAdventureMap.pLock[1];
		}

	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[1]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[1]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet1");
            		Talk talk = new Talk(jFrameGame,100, 107);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet1.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
