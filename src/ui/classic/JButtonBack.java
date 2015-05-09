package ui.classic;

import image.ImgClassic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * ����ģʽ���÷��ذ�ťbutton������ע�Ͳο�ui.home���е�JButtonAdventure��
 */
public class JButtonBack extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelClassic jPanelClassic;
	
	public JButtonBack(JFrameGame jFrameGame, JPanelClassic jPanelClassic) {
		super(117, 44, 0, 0, 117, 44);
		this.jFrameGame = jFrameGame;
		this.jPanelClassic = jPanelClassic;
		this.setBounds(418, 442, 117, 44);
		super.setImg(ImgClassic.back);
		super.setRolloverImg(ImgClassic.back);
		super.setPressedImg(ImgClassic.backClicked);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("homeback");
            	JPanelHome jPanelHome = new JPanelHome(jFrameGame);
            	jFrameGame.setContentPane(jPanelHome);
            	// ����������꽹�㣬����
            	jPanelHome.setFocusable(true);
            	jPanelHome.requestFocus();
            	jFrameGame.remove(JButtonBack.this.jPanelClassic);
            	// ���������г�ʼ��
            	Controller.getController().init();
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}

