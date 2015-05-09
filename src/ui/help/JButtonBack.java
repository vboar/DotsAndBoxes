package ui.help;

import image.ImgHelp;
import image.ImgSetting;

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
	private JPanelHelp jPanelHelp ;
	
	public JButtonBack(JFrameGame jFrameGame, JPanelHelp jPanelHelp) {
		super(135, 145, 10, 39, 113, 88);
		this.jFrameGame = jFrameGame;
		this.jPanelHelp = jPanelHelp;
		this.setBounds(645, 426, 135, 145);
		super.setImg(ImgHelp.back);
		super.setRolloverImg(ImgHelp.backEntered);
		super.setPressedImg(ImgHelp.backClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            } 
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeback");
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	jFrameGame.remove(jPanelHelp);
            	jPanelHelp = null;
            	// 主界面请求获取焦点
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	jFrameGame.revalidate();
            	System.gc();
            }
        };
    	return mouseAdapter;
    }
}
