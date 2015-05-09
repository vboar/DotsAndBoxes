package ui.home;

import image.ImgHome;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;
import ui.help.JPanelHelp;

/**
 * �����������ťbutton������ע�Ͳο�JButtonAdventure��
 */
public class JButtonHelp extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelHome jPanelHome;
	
	public JButtonHelp(JFrameGame jFrameGame, JPanelHome jPanelHome ) {
		super(120, 90, 14, 13, 98, 64);
		this.jFrameGame = jFrameGame;
		this.jPanelHome = jPanelHome;
		this.setBounds(613, 341, 120, 90);
		super.setImg(ImgHome.help);
		super.setRolloverImg(ImgHome.helpEntered);
		super.setPressedImg(ImgHome.helpClicked);
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
            	jFrameGame.setContentPane(new JPanelHelp(jFrameGame));
            	// �Ƴ�������panel
            	jFrameGame.remove(jPanelHome);
            	// Frame�ػ�
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
	
}



