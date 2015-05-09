package ui.adventure;

import image.ImgAdventure;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.AdventureDao;
import system.JFrameGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * 设置界面返回按钮
 */
public class JButtonNew extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelAdventure jPanelAdventure;
	
	public JButtonNew(JFrameGame jFrameGame, JPanelAdventure jPanelAdventure) {
		super(180, 60, 0, 0, 180, 60);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventure = jPanelAdventure;
		this.setBounds(310, 190, 180, 60);
		super.setImg(ImgAdventure.newGame);
		super.setRolloverImg(ImgAdventure.newGameEntered);
		super.setPressedImg(ImgAdventure.newGameClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseExited(MouseEvent e) {
            } 
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homegame");
            	AdventureDao.getAdventureDao().init();
            	Player.stopMusic();
            	jFrameGame.setContentPane(new JPanelAdventureMap(jFrameGame, true));
            	jFrameGame.remove(jPanelAdventure);
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}
