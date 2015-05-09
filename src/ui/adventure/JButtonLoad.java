package ui.adventure;

import image.ImgAdventure;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import media.Player;
import system.JFrameGame;
import ui.adventuremap.JPanelAdventureMap;
import ui.common.JButtonRect;
import ui.home.JPanelHome;

/**
 * 设置界面返回按钮
 */
public class JButtonLoad extends JButtonRect {

	private JFrameGame jFrameGame;
	private JPanelAdventure jPanelAdventure;
	
	public JButtonLoad(JFrameGame jFrameGame, JPanelAdventure jPanelAdventure) {
		super(180, 60, 0, 0, 180, 60);
		this.jFrameGame = jFrameGame;
		this.jPanelAdventure = jPanelAdventure;
		this.setBounds(310, 270, 180, 60);
		super.setImg(ImgAdventure.loadGame);
		super.setRolloverImg(ImgAdventure.loadGameEntered);
		super.setPressedImg(ImgAdventure.loadGameClicked);
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
            	Player.playSound("homegame");
//        		ImageIcon gif = new ImageIcon("image/2.gif");
//        		JLabel label = new JLabel(gif);
//        		label.setBounds(0, 0, gif.getIconWidth(),gif.getIconHeight());
//        		JButtonLoad.this.jFrameGame.add(label);
            	Player.stopMusic();
        		jFrameGame.remove(jPanelAdventure);
        		jFrameGame.setContentPane(new JPanelAdventureMap(jFrameGame, true));
            	jFrameGame.repaint();
            	jFrameGame.revalidate();
            }
        };
    	return mouseAdapter;
    }
}
