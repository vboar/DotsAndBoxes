package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;

/**
 * 主界面退出按钮button，其余注释参考JButtonAdventure类
 */
public class JButtonExit extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHome jPanelHome;
	
	public JButtonExit(JFrameGame jFrameGame, JPanelHome jPanelHome ) {
		super(140, 120, 22, 15, 97, 88);
		this.jFrameGame = jFrameGame;
		this.jPanelHome = jPanelHome;
		this.setBounds(558, 425, 140, 120);
		super.setImg(ImgHome.exit);
		super.setRolloverImg(ImgHome.exitEntered);
		super.setPressedImg(ImgHome.exitClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeclick");
            	// 程序关闭
            	System.exit(0);
            }
        };
    	return mouseAdapter;
    }
	
}



