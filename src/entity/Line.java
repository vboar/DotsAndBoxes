package entity;

import image.ImgEntity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;

import media.Player;
import system.Controller;

/**
 * Line��ʹ����JPanel�������������¼����Լ�entered��ͼƬ��ͬʱ����AI����
 */
public class Line extends JPanel implements MouseListener {
	
	/**
	 * Line�Ķ���Board�����x����
	 */
	private int x;
	/**
	 * Line�Ķ���Board�����y����
	 */
	private int y;
	/**
	 * Line�Ŀ��
	 */
	private int width;
	/**
	 * Line�ĸ߶�
	 */
	private int height;
	/**
	 * Line�����ԣ�0Ϊ�������ϣ�1Ϊ�������£�2Ϊ��ֱ
	 */
	private boolean vertical;
	/**
	 * Line�Ƿ񱻵�����ԣ���ʼΪfalse
	 */
	private boolean clicked = false;
	/**
	 * Line����ʾͼƬ����ʼΪnull��
	 */
	private Image img = null;
	/**
	 * Line������Box1
	 */
	private Box box1 = null;
	/**
	 * Line������Box2
	 */
	private Box box2 = null;
	// TODO ���������漰AI�㷨���ɶ��������ע��
	private boolean pretendClicked = false;
	private boolean thinkClicked=false;
	private boolean onlyThinkClick=false;
	//0513
	private String lineNumber=null;

	/**
	 * �����������xy���꣬��Ⱥ͸߶ȣ��Լ���ֱ����ˮƽ
	 */
	public Line(int x, int y, int width, int height, boolean vertical) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vertical = vertical;
		// ��Line���panel��Ϊ͸��
		this.setOpaque(false);
		// ���ô�С��λ��
		this.setBounds(x, y, width, height);
		// ���������
		this.setMouseListener(true);
	}
	
	/**
	 * ����������
	 */
	public void setMouseListener(boolean add) {
		if (add) {
			this.addMouseListener(this);
		} else {
			this.removeMouseListener(this);
		}
	}
	
	public void setLineClicked() {
		this.clicked = true;
		if (!vertical) this.setImg(ImgEntity.hLineClicked3);
		else this.setImg(ImgEntity.vLineClicked3);
	}
	
	/**
	 * ���Line����������Box�Ƿ����
	 * ֻҪ��һ��������Box��ɣ��ͷ���true
	 */
	public boolean boxFinished() {
		boolean boxFinished = false;
		boolean box1Finshed = false;
		boolean box2Finshed = false;
		if (this.box1 != null) box1Finshed = this.box1.checkIfComplete();
		if (this.box2 != null) box2Finshed = this.box2.checkIfComplete();
		boxFinished = box1Finshed || box2Finshed;
		return boxFinished;
	}
	
	/**
	 * ����¼�Override
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// ���ֻ��Ӧû�б�click��Line
		if (!clicked) {
			
//			try {
//				Socket s = new Socket("localhost", 8080);
//				s.getOutputStream().write(lineNumber.getBytes());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			
			
			
			Player.playSound("lineclick");
			this.clicked = true;
			// ����ֵ���һ��Player����
			if (Controller.getController().getTurn() == 0) {
				// ����Line����ʾͼƬ
				if (!vertical) this.img = ImgEntity.hLineClicked1;
				else this.img = ImgEntity.vLineClicked1;
				// ���Line����������Box��û�����
				if (!this.boxFinished()) {
					// �л�Player
					Controller.getController().setTurn(1);
					// �л���ʾ������
					Controller.getController().getBoard().getGame().getTurn0().setVisible(false);
					Controller.getController().getBoard().getGame().getTurn1().setVisible(true);
					// �����AI��AI������һ��
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(0).drawLine();
					}
				}
			} else if (Controller.getController().getTurn() == 1) { // �ֵ��ڶ���Player��AI����
				// ����Line����ʾͼƬ
				if (!vertical) this.img = ImgEntity.hLineClicked2;
				else this.img = ImgEntity.vLineClicked2;
				// ���Line����������Box��û�����
				if (!this.boxFinished()) {
					// �л�Player
					if (Controller.getController().getNumOfAI() == 2) {
						Controller.getController().setTurn(2);
						// ����еڶ���AI��AI������һ��
						Controller.getController().getAis().get(1).drawLine();
					}
					else {
						Controller.getController().setTurn(0);
						// �л���ʾ������
						Controller.getController().getBoard().getGame().getTurn1().setVisible(false);
						Controller.getController().getBoard().getGame().getTurn0().setVisible(true);
					}
				} else { // AI��������
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(0).drawLine();
					}
				}
			} else { // �ֵ���һ��AI����������ģʽ�ã�
				// ����Line����ʾͼƬ
				if (!vertical) this.img = ImgEntity.hLineClicked3;
				else this.img = ImgEntity.vLineClicked3;
				// ���Line����������Box��û�����
				if (!this.boxFinished()) {
					// �л�Player
					Controller.getController().setTurn(0);
					// �л���ʾ������
					Controller.getController().getBoard().getGame().getTurn1().setVisible(false);
					Controller.getController().getBoard().getGame().getTurn0().setVisible(true);
				} else { // AI��������
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(1).drawLine();
					}
				}
			}
			// �ж��Ƿ�GameOver
			// ��GameOver�����о������
			Controller.getController().GameOver();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		// �����Line������ʾͼƬ
		if(!clicked){
			if (!vertical) this.img = ImgEntity.hLineEntered;
			else this.img = ImgEntity.vLineEntered;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// �������Line��ʧ
		if (!clicked) this.img = null;
	}
	
	/**
	 * Override Line���panel��paint��������ʾLine�Ĳ�ͬͼƬ
	 */
	@Override
	public void paint(Graphics g) {
		// ���ø���paint����������paint����
		super.paint(g);
		g.drawImage(img, 0, 0, width, height, null);
		this.repaint();
	}
	
	// TODO ���淽�����漰AI�㷨��ע���ɶ�������
	/**
	 * ��װ�߱��㣬���ڲ���line��dangerlevel��
	 */
	public void pretendMouseClicked() {
		if ((!clicked) || (!pretendClicked)) {
			this.pretendClicked = true;
		}
	}
	
	/**
	 * ���һ�����Ƿ��װ��ȫ
	 * @return
	 */
	public boolean pretendSafe(){
		if ((this.getBox1() == null || this.getBox1().numOfLinePretendComplete() < 2)
				&& (this.getBox2() == null || this.getBox2().numOfLinePretendComplete() < 2)) {
			return true;
		}
		return false;	
	}
	
	/**
	 * ����һ���ߵ�Σ�յȼ��� �涨�Ѿ����������Ϊ-2�� ����box���������Ѿ��������Ϊ-1��
	 * ����������߻ᵼ����һ�������ɵ�boxes��Ϊ�����ߵ�Σ�յȼ�
	 */
	public int dangerLevel() {
		int dangerLevel = 0;

		if (this.clicked) {
			dangerLevel = -2;
			return dangerLevel;
		} else if ((this.getBox1() != null && this.getBox1()
				.numOfLineComplete() == 3)
				|| (this.getBox2() != null && this.getBox2()
						.numOfLineComplete() == 3)) {
			dangerLevel = -1;
			return dangerLevel;
		} else if ((this.getBox1() == null || this.getBox1()
				.numOfLineComplete() < 2)
				&& (this.getBox2() == null || this.getBox2()
						.numOfLineComplete() < 2)) {
			dangerLevel = 0;
			return dangerLevel;
		} 
		else {
			return 1;
		}
	
	}
	
	/**
	 * ����һ���ߵĵ��Զ����Ƿ񱻵�ĸо������ڵ��Կ��ܺ���
	 * @return
	 */
	public int dangerThinkLevel(){
		int dangerThinkLevel=0;
		if (this.clicked) {
			dangerThinkLevel = -2;
			return dangerThinkLevel;
		}
			if ((this.getBox1() != null && this.getBox1()
				.numOfLineThinkComplete() == 3)
				|| (this.getBox2() != null && this.getBox2()
						.numOfLineThinkComplete() == 3)) {
				//System.out.println("asfasdnam,c B>cx vm, jx");
			dangerThinkLevel = -1;
	}
			return dangerThinkLevel;
	}
	
	/**
	 * 0513
	 * @return
	 */
	public boolean thinkSafe(){
		if ((this.getBox1() == null || this.getBox1().numOfLineThinkComplete() < 2)
				&& (this.getBox2() == null || this.getBox2().numOfLineThinkComplete() < 2)) {
			return true;
		}
		return false;	
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean isClicked) {
		this.clicked = isClicked;
	}

	public void setBox1(Box box1) {
		this.box1 = box1;
	}

	public void setBox2(Box box2) {
		this.box2 = box2;
	}

	public Box getBox1() {
		return box1;
	}

	public Box getBox2() {
		return box2;
	}

	public boolean isPretendClicked() {
		return pretendClicked;
	}

	public void setPretendClicked(boolean pretendClicked) {
		this.pretendClicked = pretendClicked;
	}

	public boolean isThinkClicked() {
		return thinkClicked;
	}

	public void setThinkClicked(boolean thinkClicked) {
		this.thinkClicked = thinkClicked;
	}

	public boolean isOnlyThinkClick() {
		return onlyThinkClick;
	}

	public void setOnlyThinkClick(boolean onlyThinkClick) {
		this.onlyThinkClick = onlyThinkClick;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
}
