package ui.adventuremap;

import image.ImgAdventureMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.common.JButtonRect;

/**
 * ���ý��淵�ذ�ť
 */
public class PrinceBack extends JButtonRect {

	private JFrameGame jFrameGame;
	private PrinceIn princeIn;
	
	public PrinceBack(JFrameGame jFrameGame, PrinceIn princeIn) {
		super(100, 140, 17, 21, 69, 67);
		this.jFrameGame = jFrameGame;
		this.princeIn = princeIn;
		this.setBounds(30, 15, 100, 140);
		super.setImg(ImgAdventureMap.princeBack);
		super.setRolloverImg(ImgAdventureMap.princeBackE);
		super.setPressedImg(ImgAdventureMap.princeBackC);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            }
            public void mouseExited(MouseEvent e) {
            } 
            public void mouseClicked(MouseEvent e) {
            	Player.stopMusic();
            	Player.playSound("agameback");
            	JPanelAdventureMap jPanelAdventureMap = new JPanelAdventureMap(jFrameGame, false);
            	jFrameGame.setContentPane(jPanelAdventureMap);
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
