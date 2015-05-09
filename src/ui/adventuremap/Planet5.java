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
public class Planet5 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet5(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(460, 280, 160, 160);
		if (jPanelAdventureMap.getOpen()[5]) {
			super.startImg = ImgAdventureMap.pOpen[5];
			super.rolloverImg = ImgAdventureMap.pOpenE[5];
			super.pressedImg = ImgAdventureMap.pOpenC[5];
		} else {
			super.startImg = ImgAdventureMap.pLock[5];
			super.rolloverImg = ImgAdventureMap.pLock[5];
			super.pressedImg = ImgAdventureMap.pLock[5];
		}

	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[5]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[5]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet5");
            		Talk talk = new Talk(jFrameGame,220, 227);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet5.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
