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
 * ���������ģʽ��ťbutton
 */
public class JButtonAdventure extends JButtonRect {

	/**
	 * Frame
	 */
	private JFrameGame jFrameGame;
	/**
	 * ������panel
	 */
	private JPanelHome jPanelHome;
	
	/**
	 * �����������Frame��������Panel
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
	 * ����¼�Override
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
            	// ������ȡ������
            	jPanelHome.setFocusable(false);
            	// ����Ĭ��PanelΪ����ģʽ����panel
            	jFrameGame.setContentPane(new JPanelAdventure(jFrameGame));
            	// �Ƴ�������panel
            	jFrameGame.remove(jPanelHome);
            	// Frame�ػ�
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}


