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
 * ����ģʽ��Ϸ���淵�ذ�ť��Բ�ΰ�ť
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
     * ����������
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
            	// ���������󡢻�ý���
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	Player.playMusic("home");
            	jFrameGame.remove(jpanelClassicGame);
            	// ��������ʼ��
        		Controller.getController().init();
            	jFrameGame.revalidate();
            	// ����ϵͳ����
            	System.gc();
            }
        };
    	return mouseAdapter;
    }

}
