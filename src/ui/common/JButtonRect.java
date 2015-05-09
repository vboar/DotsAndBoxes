package ui.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;

/**
 * ·½ÐÎ°´Å¥
 */
public abstract class JButtonRect extends JButton {
	

    private Shape shape = null;
    private Image img = null;
    private Image pressedImg = null;
    private Image rolloverImg = null;
    private int width;
    private int height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    public JButtonRect(int width, int height, int startX, int startY, int endX, int endY) {
    	super();
    	this.startX = startX;
    	this.startY = startY;
    	this.endX = endX;
    	this.endY = endY;
    	this.addMouseListener(this.getMouseAdapter());
        Dimension size = getPreferredSize();
        size.width = width;
        size.height = height;
        setPreferredSize(size);
        setContentAreaFilled(false);
    }
    
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.drawImage(pressedImg, 0, 0, null);
        } else if (getModel().isRollover()) {
        	g.drawImage(rolloverImg, 0, 0, null);
        } else {
        	g.drawImage(img, 0, 0, null);
        }  
        super.paintComponents(g);
    }
    
    protected void paintBorder(Graphics g) {
        g.setColor(null);
    }
    
    @Override
    public boolean contains(int x, int y) {
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {  
            shape = new Rectangle2D.Float(startX, startY, endX, endY);
        }
        return shape.contains(x, y);
    }
    
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){};
    	return mouseAdapter;
    }

	public void setImg(Image img) {
		this.img = img;
	}

	public void setPressedImg(Image pressedImg) {
		this.pressedImg = pressedImg;
	}

	public void setRolloverImg(Image rolloverImg) {
		this.rolloverImg = rolloverImg;
	}
    
    
}



