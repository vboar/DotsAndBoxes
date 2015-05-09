package ui.adventure;

import image.ImgAdventure;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * 设置界面返回按钮
 */
public class JButtonBack extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelAdventure jPanelAdventure ;
	
	public JButtonBack(JFrameGame jFrameGame, JPanelAdventure jPanelAdventure) {
		super(180, 60, 0, 0, 180, 60);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventure = jPanelAdventure;
		this.setBounds(310, 350, 180, 60);
		super.setImg(ImgAdventure.back);
		super.setRolloverImg(ImgAdventure.backEntered);
		super.setPressedImg(ImgAdventure.backClicked);
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
            	Player.playSound("homeback");
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	// 主界面请求获取焦点
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	jFrameGame.remove(jPanelAdventure);
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}
