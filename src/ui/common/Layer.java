package ui.common;

import java.awt.Graphics;

public abstract class Layer {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	public void createWindow(Graphics g) {
		this.paint(g);
	}
	
	abstract public void paint(Graphics g);
	
}
