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
 * 经典模式格子数目设置nine类
 */
public class JButtonNine extends JPanel implements MouseListener {

	private JFrameGame jFrameGame;
	private JPanelClassic jPanelClassic ;
	private Image img = ImgClassic.check;
	
	public JButtonNine(JFrameGame jFrameGame, JPanelClassic jPanelClassic ) {
		this.jFrameGame = jFrameGame;
		this.jPanelClassic = jPanelClassic;
		this.setOpaque(false);
		this.setBounds(455, 247, 32, 25);
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (jPanelClassic.getNumOfBoxes() == 3)
			g.drawImage(img, 0, 0, null);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
    	Player.playSound("tickselect");
		Controller.getController().setNumOfBoxes(9);
		this.jPanelClassic.setNumOfBoxes(3);
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
