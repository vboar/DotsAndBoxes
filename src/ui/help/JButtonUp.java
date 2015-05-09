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
public class JButtonUp extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHelp jPanelHelp;
	
	public JButtonUp(JFrameGame jFrameGame, JPanelHelp jPanelHelp) {
		super(35, 35, 0, 0, 35, 35);
		this.jFrameGame = jFrameGame;
		this.jPanelHelp = jPanelHelp;
		this.setBounds(595, 421, 35, 35);
		super.setImg(ImgHelp.up);
		super.setRolloverImg(ImgHelp.upEntered);
		super.setPressedImg(ImgHelp.upClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            } 
            public void mouseClicked(MouseEvent e) {
            	int picNum = jPanelHelp.getPicNum();
            	if ((picNum > 0 && picNum < 41) || (picNum > 42 && picNum < 52)) {
            		Player.playSound("helpupdown");
            		jPanelHelp.setPicNum(picNum - 1);
            	} else {
            		Player.playSound("helpnoupdown");
            	}
            	jPanelHelp.getMiddle().setImg(jPanelHelp.getMiddleImg()[jPanelHelp.getPicNum()]);
            	jPanelHelp.repaint();
            }
        };
    	return mouseAdapter;
    }
}
