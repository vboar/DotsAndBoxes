package entity;

import image.ImgEntity;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Dot类，不使用Panel，因为Dot本身不会变
 */
public class Dot {
	
	/**
	 * Dot的对于Board的相对x坐标
	 */
	private int x;
	/**
	 * Dot的对于Board相对y坐标
	 */
	private int y;
	/**
	 * Dot的边长
	 */
	private int length;
	/**
	 * Dot的固定图片
	 */
	private Image img = ImgEntity.dot;
	
	/**
	 * 构造器，获得xy坐标和边长
	 */
	public Dot(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}
	
	/**
	 * 将Dot图片绘制到Board上
	 */
	public void paint(Graphics g) {
		g.drawImage(img, x, y, length, length, null);
	}
	
	/**
	 * Dot图片的set方法，只用于GameOver时Dot的隐藏
	 */
	public void setImg(Image img) {
		this.img = img;
	}

}
