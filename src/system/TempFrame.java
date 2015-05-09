package system;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TempFrame extends JFrame {

	public TempFrame() {
		this.setSize(400, 300);
		this.setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - this.getWidth()) / 2;
		int y = (screen.height - this.getHeight()) / 2 - 25;
		this.setLocation(x, y);
		this.setLayout(null);
		this.setVisible(true);
		this.setContentPane(new Jp());
		this.revalidate();
		
	}
	
	private class Jp extends JPanel {
		
		public Jp() {
			this.setLayout(null);
			
			JLabel timeToThinkL = new JLabel("timeToThink");
			timeToThinkL.setBounds(10, 10, 100, 20);
			this.add(timeToThinkL);
			final JTextField timeToThinkJF = new JTextField();
			timeToThinkJF.setBounds(120, 10, 100, 20);
			this.add(timeToThinkJF);
			
			JLabel greedyLockL = new JLabel("greedyLock");
			greedyLockL.setBounds(10, 40, 100, 20);
			this.add(greedyLockL);
			final JTextField greedyLockJF = new JTextField();
			greedyLockJF.setBounds(120, 40, 100, 20);
			this.add(greedyLockJF);
			
			JLabel doubleDangerL = new JLabel("doubleDanger");
			doubleDangerL.setBounds(10, 70, 100, 20);
			this.add(doubleDangerL);
			final JTextField doubleDangerJF = new JTextField();
			doubleDangerJF.setBounds(120, 70, 100, 20);
			this.add(doubleDangerJF);
			JLabel breakLoopL = new JLabel("breakLoop");
			breakLoopL.setBounds(10, 100, 100, 20);
			this.add(breakLoopL);
			final JTextField breakLoopJF = new JTextField();
			breakLoopJF.setBounds(120, 100, 100, 20);
			this.add(breakLoopJF);
	
			final JLabel j1 = new JLabel(Integer.toString(Controller.getController().timeToThink));
			final JLabel j2 = new JLabel(Integer.toString(Controller.getController().greedyLock));
			final JLabel j3 = new JLabel(Integer.toString(Controller.getController().doubleDanger));
			final JLabel j4 = new JLabel(Integer.toString(Controller.getController().breakLoop));
			j1.setBounds(10, 150, 100, 20);
			j2.setBounds(10, 180, 100, 20);
			j3.setBounds(10, 210, 100, 20);
			j4.setBounds(10, 240, 100, 20);
			this.add(j1);
			this.add(j2);
			this.add(j3);
			this.add(j4);
			
			JButton jb = new JButton("Submit");
			jb.setBounds(250, 10, 100, 30);
			this.add(jb);
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Controller.getController().timeToThink = Integer.parseInt(timeToThinkJF.getText());
					Controller.getController().greedyLock = Integer.parseInt(greedyLockJF.getText());
					Controller.getController().doubleDanger = Integer.parseInt(doubleDangerJF.getText());
					Controller.getController().breakLoop = Integer.parseInt(breakLoopJF.getText());
					System.out.println(Controller.getController().timeToThink);
					System.out.println(Controller.getController().greedyLock);
					System.out.println(Controller.getController().doubleDanger);
					System.out.println(Controller.getController().breakLoop);
					
					j1.setText(Integer.toString(Controller.getController().timeToThink));
					j2.setText(Integer.toString(Controller.getController().greedyLock));
					j3.setText(Integer.toString(Controller.getController().doubleDanger));
					j4.setText(Integer.toString(Controller.getController().breakLoop));
					
					repaint();
				}
			});
			
		}
	}
}
