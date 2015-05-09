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
public class Planet4 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet4(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(335, 180, 160, 160);
		if (jPanelAdventureMap.getOpen()[4]) {
			super.startImg = ImgAdventureMap.pOpen[4];
			super.rolloverImg = ImgAdventureMap.pOpenE[4];
			super.pressedImg = ImgAdventureMap.pOpenC[4];
		} else {
			super.startImg = ImgAdventureMap.pLock[4];
			super.rolloverImg = ImgAdventureMap.pLock[4];
			super.pressedImg = ImgAdventureMap.pLock[4];
		}
	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[4]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[4]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet4");
            		Talk talk = new Talk(jFrameGame,190, 196);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet4.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
