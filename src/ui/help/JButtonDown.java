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
public class JButtonDown extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHelp jPanelHelp;
	
	public JButtonDown(JFrameGame jFrameGame, JPanelHelp jPanelHelp) {
		super(35, 35, 0, 0, 35, 35);
		this.jFrameGame = jFrameGame;
		this.jPanelHelp = jPanelHelp;
		this.setBounds(595, 458, 35, 35);
		super.setImg(ImgHelp.down);
		super.setRolloverImg(ImgHelp.downEntered);
		super.setPressedImg(ImgHelp.downClicked);
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
            	if ((picNum >= 0 && picNum < 40) || (picNum >= 42 && picNum < 51)) {
            		Player.playSound("helpupdown");
            		jPanelHelp.setPicNum(picNum + 1);
            	} else {
            		Player.playSound("helpnoupdown");
            	}
            	jPanelHelp.getMiddle().setImg(jPanelHelp.getMiddleImg()[jPanelHelp.getPicNum()]);
        		System.out.println(picNum);
            	jPanelHelp.repaint();
            } 
        };
    	return mouseAdapter;
    }
}
