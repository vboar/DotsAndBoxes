package ui.adventuremap;

import image.ImgAdventureMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * 经典模式游戏界面重新游戏按钮，其余注释参考ui.home包中的JButtonAdventure类
 */
public class BackHome extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelAdventureMap jPanelAdventureMap;
	
	public BackHome(JFrameGame jFrameGame, JPanelAdventureMap jPanelAdventureMap) {
		super(160, 160, 10, 86, 137, 61);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventureMap = jPanelAdventureMap;
		this.setBounds(635, 435, 160, 160);
		super.setImg(ImgAdventureMap.back);
		super.setRolloverImg(ImgAdventureMap.backEntered);
		super.setPressedImg(ImgAdventureMap.backClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("amapback");
            	Player.stopMusic();
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	// 主界面请求、获得焦点
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	Player.playMusic("home");
            	jFrameGame.remove(jPanelAdventureMap);
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

