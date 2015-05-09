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
public class Planet6 extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Planet6(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(600, 135, 180, 180);
		if (jPanelAdventureMap.getOpen()[6]) {
			super.startImg = ImgAdventureMap.pOpen[6];
			super.rolloverImg = ImgAdventureMap.pOpenE[6];
			super.pressedImg = ImgAdventureMap.pOpenC[6];
		} else {
			super.startImg = ImgAdventureMap.pLock[6];
			super.rolloverImg = ImgAdventureMap.pLock[6];
			super.pressedImg = ImgAdventureMap.pLock[6];
		}

	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[6]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[6]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("planet6");
            		Talk talk = new Talk(jFrameGame,250, 257);
                	jFrameGame.setContentPane(talk);
                	talk.add(new JPanelOnceMovie(jFrameGame, 21, 0, 0, 800, 600, 
							"image/jpanels/common/loading/", false, 100));
                	jFrameGame.remove(Planet6.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
            	}
            }
        };
    	return mouseAdapter;
    }

}
