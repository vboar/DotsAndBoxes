package ui.common;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 * 抽象圆形按钮
 * 
 * @author Vboar
 *
 */
public class JButtonCircle extends JButton {
	
	/**
	 * 保存按钮的形状,便于监听单击按钮事件
	 */
    private Shape shape = null;
    
    /**
     * 圆形按钮直径
     */
    private int diameter;
    
    /**
     * 圆形识别区域直径
     */
    private int inDiameter;
    
    /**
     * 按钮原始图片
     */
    protected Image startImg;
    
    /**
     * 按钮按下图片
     */
    protected Image pressedImg;
    
    /**
     * 鼠标经过按钮图片
     */
    protected Image rolloverImg;
    
    /**
     * 构造器
     */
    public JButtonCircle(int diameter, int inDiameter) {
    	
    	// 继承JButton构造器
    	super();
        
    	this.diameter = diameter;
    	this.inDiameter = inDiameter;
    	
    	// 添加监听
    	this.addMouseListener(this.getMouseAdapter());
        
    	// 获取按钮的最佳大小
        Dimension size = getPreferredSize();
        // 调整按钮的大小,变成边长为所设定的圆的直径的正方形
        size.width = size.height = diameter;
        setPreferredSize(size);
        // 使得button不画背景,即不显示方形背景,而允许我们画一个圆的背景
        setContentAreaFilled(false);
    }
    
    /**
     *  为按钮添加各种状态的图片
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // getModel方法返回鼠标的模型ButtonModel
            // 如果鼠标按下按钮，则buttonModel的armed属性为真
            g.drawImage(pressedImg, 0, 0, null);
        } else if (getModel().isRollover()) {
        	g.drawImage(rolloverImg, 0, 0, null);
        } else {
        	g.drawImage(startImg, 0, 0, null);
        }  
        super.paintComponents(g);
    }
    
    /**
     * 取消按钮的边界
     */
    protected void paintBorder(Graphics g) {
        g.setColor(null);
    }

    /**
     * 判断鼠标是否点在按钮上
     */
    public boolean contains(int x, int y) {
        // 如果按钮边框,位置发生改变,则产生一个新的形状对象
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {  
            // 构造椭圆型对象
            shape = new Ellipse2D.Float(
            		(getWidth() - inDiameter) / 2, 
            		(getHeight() - inDiameter) / 2, 
            		inDiameter,
            		inDiameter);
        }
        // 判断鼠标的x,y坐标是否落在按钮形状内
        return shape.contains(x, y);
    }
    
    /**
     * 鼠标监听方法
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            /**
             * 鼠标进入按钮区域
             * {@inheritDoc}
             */ 
            public void mouseEntered(MouseEvent e) {
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * 鼠标离开按钮区域
             * {@inheritDoc}
             */
            public void mouseExited(MouseEvent e) {  
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
            } 
            /**
             * 鼠标点击按钮
             * {@inheritDoc}
             */
            public void mouseClicked(MouseEvent e) {
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        };
    	return mouseAdapter;
    }
}

