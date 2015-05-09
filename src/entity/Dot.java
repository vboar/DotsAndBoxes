package entity;

import image.ImgEntity;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Dot�࣬��ʹ��Panel����ΪDot�������
 */
public class Dot {
	
	/**
	 * Dot�Ķ���Board�����x����
	 */
	private int x;
	/**
	 * Dot�Ķ���Board���y����
	 */
	private int y;
	/**
	 * Dot�ı߳�
	 */
	private int length;
	/**
	 * Dot�Ĺ̶�ͼƬ
	 */
	private Image img = ImgEntity.dot;
	
	/**
	 * �����������xy����ͱ߳�
	 */
	public Dot(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}
	
	/**
	 * ��DotͼƬ���Ƶ�Board��
	 */
	public void paint(Graphics g) {
		g.drawImage(img, x, y, length, length, null);
	}
	
	/**
	 * DotͼƬ��set������ֻ����GameOverʱDot������
	 */
	public void setImg(Image img) {
		this.img = img;
	}

}
