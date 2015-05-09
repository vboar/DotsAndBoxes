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
 * ����Բ�ΰ�ť
 * 
 * @author Vboar
 *
 */
public class JButtonCircle extends JButton {
	
	/**
	 * ���水ť����״,���ڼ���������ť�¼�
	 */
    private Shape shape = null;
    
    /**
     * Բ�ΰ�ťֱ��
     */
    private int diameter;
    
    /**
     * Բ��ʶ������ֱ��
     */
    private int inDiameter;
    
    /**
     * ��ťԭʼͼƬ
     */
    protected Image startImg;
    
    /**
     * ��ť����ͼƬ
     */
    protected Image pressedImg;
    
    /**
     * ��꾭����ťͼƬ
     */
    protected Image rolloverImg;
    
    /**
     * ������
     */
    public JButtonCircle(int diameter, int inDiameter) {
    	
    	// �̳�JButton������
    	super();
        
    	this.diameter = diameter;
    	this.inDiameter = inDiameter;
    	
    	// ��Ӽ���
    	this.addMouseListener(this.getMouseAdapter());
        
    	// ��ȡ��ť����Ѵ�С
        Dimension size = getPreferredSize();
        // ������ť�Ĵ�С,��ɱ߳�Ϊ���趨��Բ��ֱ����������
        size.width = size.height = diameter;
        setPreferredSize(size);
        // ʹ��button��������,������ʾ���α���,���������ǻ�һ��Բ�ı���
        setContentAreaFilled(false);
    }
    
    /**
     *  Ϊ��ť��Ӹ���״̬��ͼƬ
     */
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // getModel������������ģ��ButtonModel
            // �����갴�°�ť����buttonModel��armed����Ϊ��
            g.drawImage(pressedImg, 0, 0, null);
        } else if (getModel().isRollover()) {
        	g.drawImage(rolloverImg, 0, 0, null);
        } else {
        	g.drawImage(startImg, 0, 0, null);
        }  
        super.paintComponents(g);
    }
    
    /**
     * ȡ����ť�ı߽�
     */
    protected void paintBorder(Graphics g) {
        g.setColor(null);
    }

    /**
     * �ж�����Ƿ���ڰ�ť��
     */
    public boolean contains(int x, int y) {
        // �����ť�߿�,λ�÷����ı�,�����һ���µ���״����
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {  
            // ������Բ�Ͷ���
            shape = new Ellipse2D.Float(
            		(getWidth() - inDiameter) / 2, 
            		(getHeight() - inDiameter) / 2, 
            		inDiameter,
            		inDiameter);
        }
        // �ж�����x,y�����Ƿ����ڰ�ť��״��
        return shape.contains(x, y);
    }
    
    /**
     * ����������
     */
    public MouseAdapter getMouseAdapter() {
    	MouseAdapter mouseAdapter = new MouseAdapter(){
            /**
             * �����밴ť����
             * {@inheritDoc}
             */ 
            public void mouseEntered(MouseEvent e) {
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * ����뿪��ť����
             * {@inheritDoc}
             */
            public void mouseExited(MouseEvent e) {  
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
            } 
            /**
             * �������ť
             * {@inheritDoc}
             */
            public void mouseClicked(MouseEvent e) {
//                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        };
    	return mouseAdapter;
    }
}

