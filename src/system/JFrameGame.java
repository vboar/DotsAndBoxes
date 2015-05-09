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
 * 游戏Frame类
 */
public class JFrameGame extends JFrame {
	
	/**
	 * 鼠标
	 */
	private Cursor cursor;
	
	/**
	 * 实现移动
	 */
    private boolean in = false;
    private boolean moving;
    private int nowX;
    private int nowY;

	/**
	 * 构造器，有关Frame的一些设置
	 */
	public JFrameGame() {
		
		// 设置标题
		this.setTitle("Dots & Boxes");
		// 设置尺寸
		this.setSize(800, 600);
		// 设置为不可更改
		this.setResizable(false);
		// 隐藏默认边框
		this.setUndecorated(true);
		// 为控制器添加对Frame的引用
		Controller.getController().setGame(this);
		// 获得当前屏幕尺寸从而设置窗体在屏幕的显示位置，居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (screen.width - this.getWidth()) / 2;
		int y = (screen.height - this.getHeight()) / 2 - 25;
		this.setLocation(x, y);
		// 设置默认鼠标
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				ImgSystem.cursor, new Point(0,0), "mycursor");
	  	this.setCursor(cursor);
	  	// 设置图标
	  	this.setIconImage(ImgSystem.icon);
		// 设置为自由布局
	  	this.setLayout(null);
	  	// 设置起始panel
	  	this.setJPanelMovie();
	  	// 实现移动
	  	this.setMove();
	  	
	}
	
	/**
	 * 实现移动
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
	 * 设置初始panel为startMovie
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
