package ui.classic;

import image.ImgClassic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import media.Player;
import system.Controller;
import system.JFrameGame;

/**
 * 经典模式玩家是否先手设置电脑先手
 */
public class JButtonLast extends JPanel implements MouseListener {

	private JFrameGame jFrameGame;
	private JPanelClassic jPanelClassic ;
	private Image img = ImgClassic.check;
	
	public JButtonLast(JFrameGame jFrameGame, JPanelClassic jPanelClassic ) {
		this.jFrameGame = jFrameGame;
		this.jPanelClassic = jPanelClassic;
		this.setOpaque(false);
		this.setBounds(520, 396, 32, 25);
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!Controller.getController().isPlayerFirst())
			g.drawImage(img, 0, 0, null);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Controller.getController().getNumOfAI() > 0) {
	    	Player.playSound("tickselect");
			Controller.getController().setPlayerFirst(false);
		} else {
			Player.playSound("warn");
		}

	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}
