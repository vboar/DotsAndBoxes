package ui.adventuremap;

import image.ImgAdventureMap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import media.Player;
import system.JFrameGame;
import ui.common.JButtonRect;

/**
 * 设置界面返回按钮
 */
public class Machine extends JButtonRect {

	private JFrameGame jFrameGame;
	private PrinceIn princeIn;
	
	public Machine(JFrameGame jFrameGame, PrinceIn princeIn) {
		super(260, 200, 56, 48, 173, 84);
		this.jFrameGame = jFrameGame;
		this.princeIn = princeIn;
		this.setBounds(540, 400, 260, 200);
		super.setImg(ImgAdventureMap.machine);
		super.setRolloverImg(ImgAdventureMap.machineE);
		super.setPressedImg(ImgAdventureMap.machine);
	}
	
	@Override
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            	princeIn.getTimeLabel().setVisible(true);
            }
            public void mouseExited(MouseEvent e) {
            	princeIn.getTimeLabel().setVisible(false);
            } 
            public void mouseClicked(MouseEvent e) {
            	Player.playSound("princeintoallgameclick");
            	jFrameGame.setContentPane(new AllGame(jFrameGame));
            	jFrameGame.remove(princeIn);
            	jFrameGame.revalidate();
            	System.gc();
            }
        };
    	return mouseAdapter;
    }
}
