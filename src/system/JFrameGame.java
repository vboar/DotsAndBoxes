package system;

import image.ImgAdventureGame;
import image.ImgHelp;
import image.ImgSystem;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import ui.startmovie.JPanelStartMovie;

/**
 * ��ϷFrame��
 */
public class JFrameGame extends JFrame {
	
	/**
	 * ���
	 */
	private Cursor cursor;
	
	/**
	 * ʵ���ƶ�
	 */
    private boolean in = false;
    private boolean moving;
    private int nowX;
    private int nowY;

	/**
	 * ���������й�Frame��һЩ����
	 */
	public JFrameGame() {
		
		// ���ñ���
		this.setTitle("Dots & Boxes");
		// ���óߴ�
		this.setSize(800, 600);
		// ����Ϊ���ɸ���
		this.setResizable(false);
		// ����Ĭ�ϱ߿�
		this.setUndecorated(true);
		// Ϊ��������Ӷ�Frame������
		Controller.getController().setGame(this);
		// ��õ�ǰ��Ļ�ߴ�Ӷ����ô�������Ļ����ʾλ�ã�����
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - this.getWidth()) / 2;
		int y = (screen.height - this.getHeight()) / 2 - 25;
		this.setLocation(x, y);
		// ����Ĭ�����
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				ImgSystem.cursor, new Point(0,0), "mycursor");
	  	this.setCursor(cursor);
	  	// ����ͼ��
	  	this.setIconImage(ImgSystem.icon);
		// ����Ϊ���ɲ���
	  	this.setLayout(null);
	  	// ������ʼpanel
	  	this.setJPanelMovie();
	  	// ʵ���ƶ�
	  	this.setMove();
	  	
	}
	
	/**
	 * ʵ���ƶ�
	 */
	private void setMove() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (e.getY() < 35) {
            		moving = true;
                    requestFocus();
                    nowX = e.getX();
                    nowY = e.getY();
            	}
            }
            public void mouseReleased(MouseEvent e) {
            	if (e.getY() < 35) {
            		moving = false;
            	}
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            	if (e.getY() < 35) {
                    setIn(true);
            	}
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (moving) {
                    int left = getLocation().x;
                    int top = getLocation().y;
                    JFrameGame.this.setLocation(left + e.getX() - nowX, top + e.getY() - nowY);
                }
            }
        });
	}
	
	/**
	 * ���ó�ʼpanelΪstartMovie
	 */
	public void setJPanelMovie() {
		this.setContentPane(new JPanelStartMovie(this));
	}
	
    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }
	
}
