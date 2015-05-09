package ui.setting;

import image.ImgSetting;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import media.Player;
import system.Controller;
import system.JFrameGame;

/**
 * 设置界面是否开启音乐按钮
 */
public class JButtonMusic extends JPanel implements MouseListener {

	private JFrameGame jFrameGame;
	private JPanelSetting jpanelSetting ;
	private Image img = ImgSetting.open;
	
	public JButtonMusic(JFrameGame jFrameGame, JPanelSetting jpanelSetting ) {
		this.jFrameGame = jFrameGame;
		this.jpanelSetting = jpanelSetting;
		this.setOpaque(false);
		this.setBounds(458, 218, 50, 50);
		this.addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (Controller.getController().isMusicOn())
			g.drawImage(img, 0, 0, null);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Controller.getController().isMusicOn()) {
				Controller.getController().setMusicOn(false);
				Player.stopMusic();
		} else {
			Controller.getController().setMusicOn(true);
			Player.playMusic("home");
		}
    	Player.playSound("tickselect");
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
