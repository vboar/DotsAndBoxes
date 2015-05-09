package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.setting.JPanelSetting;

/**
 * ���������ð�ťbutton������ע�Ͳο�JButtonAdventure��
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
            	// ����Ĭ��PanelΪ����panel
            	jFrameGame.setContentPane(new JPanelSetting(jFrameGame));
            	// �Ƴ�������panel
            	jFrameGame.remove(jPanelHome);
            	// Frame�ػ�
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }

}
