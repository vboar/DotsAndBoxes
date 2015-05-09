package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.setting.JPanelSetting;

/**
 * 主界面设置按钮button，其余注释参考JButtonAdventure类
 */
public class JButtonSetting extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHome jPanelHome;
	
	public JButtonSetting(JFrameGame jFrameGame, JPanelHome jPanelHome ) {
		super(100, 90, 13, 15, 74, 57);
		this.jFrameGame = jFrameGame;
		this.jPanelHome = jPanelHome;
		this.setBounds(290, 348, 100, 90);
		super.setImg(ImgHome.setting);
		super.setRolloverImg(ImgHome.settingEntered);
		super.setPressedImg(ImgHome.settingClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeclick");
            	// 设置默认Panel为设置panel
            	jFrameGame.setContentPane(new JPanelSetting(jFrameGame));
            	// 移除主界面panel
            	jFrameGame.remove(jPanelHome);
            	// Frame重绘
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }

}
