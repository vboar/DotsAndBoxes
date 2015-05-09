package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.classic.JPanelClassic;
import ui.common.JButtonRect;

/**
 * 主界面经典模式按钮button，其余注释参考JButtonAdventure类
 */
public class JButtonClassic extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHome jPanelHome;
	
	public JButtonClassic(JFrameGame jFrameGame, JPanelHome jPanelHome ) {
		super(270, 100, 41, 8, 193, 67);
		this.jFrameGame = jFrameGame;
		this.jPanelHome = jPanelHome;
		this.setBounds(300, 434, 270, 100);
		super.setImg(ImgHome.classic);
		super.setRolloverImg(ImgHome.classicEntered);
		super.setPressedImg(ImgHome.classicClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeclick");
            	// 主界面取消焦点
            	jPanelHome.setFocusable(false);
            	// 设置默认Panel为经典模式设置panel
            	jFrameGame.setContentPane(new JPanelClassic(jFrameGame));
            	// 移除主界面panel
            	jFrameGame.remove(jPanelHome);
            	// Frame重绘
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}

