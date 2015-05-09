package ui.adventuremap;

import image.ImgAdventureMap;
import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.AdventureDao;
import system.JFrameGame;
import ui.adventure.JPanelAdventure;
import ui.adventuregame.PanelGame;
import ui.common.JButtonCircle;
import ui.common.JButtonRect;
import ui.common.JPanelOnceMovie;

/**
 * 小王子星球
 */
public class Prince extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;

	public Prince(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelAdventureMap jPanelAdventureMap) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(-130, -165, 450, 450);
		super.startImg = ImgAdventureMap.prince;
		super.rolloverImg = ImgAdventureMap.princeEntered;
		super.pressedImg = ImgAdventureMap.prince;
		
	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	if (jPanelAdventureMap.getOpen()[8]) {
                	Player.playSound("amaprollover");
            	}
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	// TODO
            	if (jPanelAdventureMap.getOpen()[8]) {
            		Player.playSound("amapclick");
            		Player.stopMusic();
            		Player.playMusic("princein");
                	jFrameGame.setContentPane(new PrinceIn(jFrameGame));
                	jFrameGame.remove(Prince.this.jPanelAdventureMap);
                	jFrameGame.revalidate();
            	} else {
            		Player.playSound("amaplock");
                	if (!jPanelAdventureMap.isUncomplete()) {
                    	jPanelAdventureMap.setUncomplete(true);
                    	jPanelAdventureMap.getUncompleteShow().setVisible(true);
                	} else {
                		jPanelAdventureMap.setUncomplete(false);
                		jPanelAdventureMap.getUncompleteShow().setVisible(false);
                	}
            	}
            }
        };
    	return mouseAdapter;
    }

}
