package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.adventure.JPanelAdventure;
import ui.classic.JPanelClassic;
import ui.common.JButtonRect;

/**
 * 主界面剧情模式按钮button
 */
public class JButtonAdventure extends JButtonRect {

	/**
	 * Frame
	 */
	private JFrameGame jFrameGame;
	/**
	 * 主界面panel
	 */
	private JPanelHome jPanelHome;
	
	/**
	 * 构造器，获得Frame和主界面Panel
	 */
	public JButtonAdventure(JFrameGame jFrameGame, JPanelHome jPanelHome ) {
		super(270, 100, 35, 17, 188, 65);
		this.jFrameGame = jFrameGame;
		this.jPanelHome = jPanelHome;
		this.setBounds(374, 329, 270, 100);
		super.setImg(ImgHome.adventure);
		super.setRolloverImg(ImgHome.adventureEntered);
		super.setPressedImg(ImgHome.adventureClicked);
	}
	
	/**
	 * 鼠标事件Override
	 */
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseExited(MouseEvent e) {} 
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeclick");
            	// 主界面取消焦点
            	jPanelHome.setFocusable(false);
            	// 设置默认Panel为经典模式设置panel
            	jFrameGame.setContentPane(new JPanelAdventure(jFrameGame));
            	// 移除主界面panel
            	jFrameGame.remove(jPanelHome);
            	// Frame重绘
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}


