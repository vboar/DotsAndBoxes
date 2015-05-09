package entity;

import image.ImgEntity;
import java.awt.Graphics;
import java.awt.Image;
import system.Controller;

/**
 * Box�࣬��ʹ��panel����Ϊ�ǲ����
 */
public class Box {
	
	/**
	 * Box�Ķ���Board�����x����
	 */
	private int x;
	/**
	 * Box�Ķ���Board�����y����
	 */
	private int y;
	/**
	 * Box�ı߳�
	 */
	private int length;
	/**
	 * Box����Line
	 */
	private Line hLine1;
	/**
	 * Box����Line
	 */
	private Line hLine2;
	/**
	 * Box����Line
	 */
	private Line vLine1;
	/**
	 * Box����Line
	 */
	private Line vLine2;
	/**
	 * Box����ʾͼƬ
	 */
	private Image img = null;
	
	/**
	 * �����������xy����ͱ߳�
	 */
	public Box(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}
	
	/**
	 * �ж�Box�Ƿ��Ѿ���ɣ���4��Line���ѱ�Click
	 */
	public boolean checkIfComplete() {
		if (hLine1.isClicked() && hLine2.isClicked() 
				&& vLine1.isClicked() && vLine2.isClicked()) {
			if (Controller.getController().getTurn() == 0) {
				// Player1������1
				int[] score0;
				score0=Controller.getController().getScores();
				score0[0]++;
				Controller.getController().setScores(score0);
				// Box��ʾͼƬ�ı�
				this.img = ImgEntity.box1;
			} else{	
				int[] score1;
				int player;
				score1 = Controller.getController().getScores();
				player = Controller.getController().getTurn();
				score1[player]++;
				Controller.getController().setScores(score1);
				// Box��ʾͼƬ�ı�
				if (player == 1) this.img = ImgEntity.box2;
				else this.img = ImgEntity.box3;
			}
			return true;
		}
		return false;
	}
	
	public void setAllLineClicked() {
		this.hLine1.setClicked(true);
		this.hLine1.setImg(ImgEntity.hLineClicked3);
		this.hLine2.setClicked(true);
		this.hLine2.setImg(ImgEntity.hLineClicked3);
		this.vLine1.setClicked(true);
		this.vLine1.setImg(ImgEntity.vLineClicked3);
		this.vLine2.setClicked(true);
		this.vLine2.setImg(ImgEntity.vLineClicked3);
		this.setImg(ImgEntity.box3);
	}
	
	/**
	 * �ж�Box�Ƿ��Ѿ���ɣ����Ǹ÷������ı�Box����ʾͼƬ
	 */
	public boolean boxComplete() {
		if (hLine1.isClicked() && hLine2.isClicked() 
				&& vLine1.isClicked() && vLine2.isClicked()) {
			return true;
		}
		return false;
	}
	
	/**
	 * ͳ�Ƹ�Box�ѱ�Click�ı���
	 */
	public int numOfLineComplete() {
		int num = 0;
		if (hLine1.isClicked()) num++;
		if (hLine2.isClicked()) num++;
		if (vLine1.isClicked()) num++;
		if (vLine2.isClicked()) num++;
		return num;
	}
	
	// TODO �����ɶ��������
	/**
	 * ��װbox���㣬���ڼ����ߵ�dangerlevel
	 * 
	 * @return
	 */
	public boolean pretendComplete() {
		if ((hLine1.isClicked() || hLine1.isPretendClicked())
				&& (hLine2.isClicked() || hLine2.isPretendClicked())
				&& (vLine1.isClicked() || vLine1.isPretendClicked())
				&& (vLine2.isClicked()) || vLine2.isPretendClicked()) {
			return true;
		}
		return false;
	}

	public int numOfLineThinkComplete() {
		int num = 0;
		if (hLine1.isClicked() || hLine1.isThinkClicked()
				|| hLine1.isPretendClicked())
			num++;
		if (hLine2.isClicked() || hLine2.isThinkClicked()
				|| hLine2.isPretendClicked())
			num++;
		if (vLine1.isClicked() || vLine1.isThinkClicked()
				|| vLine1.isPretendClicked())
			num++;
		if (vLine2.isClicked() || vLine2.isThinkClicked()
				|| vLine2.isPretendClicked())
			num++;
		return num;
	}

	/**
	 * 0429
	 * 
	 * @return
	 */
	public int numOfLineThinkUncomplete() {
		int num = 0;
		if (!(hLine1.isClicked() || hLine1.isThinkClicked() || hLine1
				.isPretendClicked()))
			num = 1;
		if (!(hLine2.isClicked() || hLine2.isThinkClicked() || hLine2
				.isPretendClicked()))
			num = 2;
		if (!(vLine1.isClicked() || vLine1.isThinkClicked() || vLine1
				.isPretendClicked()))
			num = 3;
		if (!(vLine2.isClicked() || vLine2.isThinkClicked() || vLine2
				.isPretendClicked()))
			num = 4;
		return num;
	}

	public Line LinePretendUncomplete() {
//		int test = 0;
		Line line = null;
		if (!(hLine1.isClicked() || hLine1.isPretendClicked())) {
			line = hLine1;
//			test = 1;
		}
		if (!(hLine2.isClicked() || hLine2.isPretendClicked())) {
			line = hLine2;
//			test = 2;
		}
		if (!(vLine1.isClicked() || vLine1.isPretendClicked())) {
			line = vLine1;
//			test = 3;
		}
		if (!(vLine2.isClicked() || vLine2.isPretendClicked())) {
			line = vLine2;
//			test = 4;
		}
		//System.out.println("test=" + test);
		return line;
	}

	public Line lineUncomplete() {
		Line line = null;
		if (!(hLine1.isClicked()))
			line = hLine1;
		if (!(hLine2.isClicked()))
			line = hLine2;
		if (!(vLine1.isClicked()))
			line = vLine1;
		if (!(vLine2.isClicked()))
			line = vLine2;
		return line;
	}

	/**
	 * ͳ�Ƹ�Box�ѱ�Clicked���߼�װclicked�ı��������ڼ���line��dangerlevel
	 * 
	 * @return
	 */
	public int numOfLinePretendComplete() {
		int num = 0;
		if (hLine1.isClicked() || hLine1.isPretendClicked())
			num++;
		if (hLine2.isClicked() || hLine2.isPretendClicked())
			num++;
		if (vLine1.isClicked() || vLine1.isPretendClicked())
			num++;
		if (vLine2.isClicked() || vLine2.isPretendClicked())
			num++;
		return num;
	}
	
	public Line LineThinkUncomplete() {
//		int test = 0;
		Line line = null;
		if (!(hLine1.isClicked() || hLine1.isPretendClicked()|| hLine1.isThinkClicked())) {
			line = hLine1;
//			test = 1;
		}
		if (!(hLine2.isClicked() || hLine2.isPretendClicked()|| hLine2.isThinkClicked())) {
			line = hLine2;
//			test = 2;
		}
		if (!(vLine1.isClicked() || vLine1.isPretendClicked()|| vLine1.isThinkClicked())) {
			line = vLine1;
//			test = 3;
		}
		if (!(vLine2.isClicked() || vLine2.isPretendClicked()|| vLine2.isThinkClicked())) {
			line = vLine2;
//			test = 4;
		}
		//System.out.println("test=" + test);
		return line;
	}
	
	/**
	 * ��Box���Ƶ�Board��
	 */
	public void paint(Graphics g) {
		g.drawImage(img, x, y, length, length, null);
	}

	public void sethLine1(Line hLine1) {
		this.hLine1 = hLine1;
	}

	public void sethLine2(Line hLine2) {
		this.hLine2 = hLine2;
	}

	public void setvLine1(Line vLine1) {
		this.vLine1 = vLine1;
	}

	public void setvLine2(Line vLine2) {
		this.vLine2 = vLine2;
	}

	public Line gethLine1() {
		return hLine1;
	}

	public Line gethLine2() {
		return hLine2;
	}

	public Line getvLine1() {
		return vLine1;
	}

	public Line getvLine2() {
		return vLine2;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
}
