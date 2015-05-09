package ui.classicgame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonCircle;
import ui.home.JPanelHome;
import image.ImgClassicGame;

/**
 * 经典模式游戏界面返回按钮，圆形按钮
 */
public class JButtonBack extends JButtonCircle {
	
	private JFrameGame jFrameGame;
	private JPanelClassicGame jpanelClassicGame;

	public JButtonBack(int diameter, int inDiameter, JFrameGame jFrameGame, 
			JPanelClassicGame jpanelClassicGame) {
		super(diameter, inDiameter);
		this.jFrameGame = jFrameGame;
		this.jpanelClassicGame = jpanelClassicGame;
		this.setBounds(668, 474, 100, 100);
		super.startImg = ImgClassicGame.back;
		super.rolloverImg = ImgClassicGame.backEntered;
		super.pressedImg = ImgClassicGame.backClicked;
	}
	
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {  
            }
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("cgameback");
            	Player.stopMusic();
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	// 主界面请求、获得焦点
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	Player.playMusic("home");
            	jFrameGame.remove(jpanelClassicGame);
            	// 控制器初始化
        		Controller.getController().init();
            	jFrameGame.revalidate();
            	// 请求系统回收
            	System.gc();
            }
        };
    	return mouseAdapter;
    }

}
