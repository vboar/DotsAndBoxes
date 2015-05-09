package ui.setting;

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
	private JPanelSetting jpanelSetting ;
	
	public JButtonBack(JFrameGame jFrameGame, JPanelSetting jpanelSetting ) {
		super(140, 50, 0, 0, 140, 50);
		this.jFrameGame = jFrameGame;
		this.jpanelSetting = jpanelSetting;
		this.setBounds(330, 360, 140, 50);
		super.setImg(ImgSetting.back);
		super.setRolloverImg(ImgSetting.back);
		super.setPressedImg(ImgSetting.backClicked);
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
            	// 主界面请求获取焦点
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	jFrameGame.remove(jpanelSetting);
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}
