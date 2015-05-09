package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.classic.JPanelClassic;
import ui.common.JButtonRect;

/**
 * �����澭��ģʽ��ťbutton������ע�Ͳο�JButtonAdventure��
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
            	// ������ȡ������
            	jPanelHome.setFocusable(false);
            	// ����Ĭ��PanelΪ����ģʽ����panel
            	jFrameGame.setContentPane(new JPanelClassic(jFrameGame));
            	// �Ƴ�������panel
            	jFrameGame.remove(jPanelHome);
            	// Frame�ػ�
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}

