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
 * Line类使用了JPanel，便于添加鼠标事件，以及entered变图片，同时便于AI操作
 */
public class Line extends JPanel implements MouseListener {
	
	/**
	 * Line的对于Board的相对x坐标
	 */
	private int x;
	/**
	 * Line的对于Board的相对y坐标
	 */
	private int y;
	/**
	 * Line的宽度
	 */
	private int width;
	/**
	 * Line的高度
	 */
	private int height;
	/**
	 * Line的属性，0为左下右上，1为左上右下，2为竖直
	 */
	private boolean vertical;
	/**
	 * Line是否被点击属性，初始为false
	 */
	private boolean clicked = false;
	/**
	 * Line的显示图片，初始为null；
	 */
	private Image img = null;
	/**
	 * Line关联的Box1
	 */
	private Box box1 = null;
	/**
	 * Line关联的Box2
	 */
	private Box box2 = null;
	// TODO 以下属性涉及AI算法，由对座来完成注释
	private boolean pretendClicked = false;
	private boolean thinkClicked=false;
	private boolean onlyThinkClick=false;
	//0513
	private String lineNumber=null;

	/**
	 * 构造器，获得xy坐标，宽度和高度，以及竖直还是水平
	 */
	public Line(int x, int y, int width, int height, boolean vertical) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vertical = vertical;
		// 将Line这个panel设为透明
		this.setOpaque(false);
		// 设置大小和位置
		this.setBounds(x, y, width, height);
		// 添加鼠标监听
		this.setMouseListener(true);
	}
	
	/**
	 * 设置鼠标监听
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
	 * 检查Line关联的两个Box是否完成
	 * 只要有一个关联的Box完成，就返回true
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
	 * 鼠标事件Override
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 鼠标只响应没有被click的Line
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
			// 如果轮到第一名Player操作
			if (Controller.getController().getTurn() == 0) {
				// 更改Line的显示图片
				if (!vertical) this.img = ImgEntity.hLineClicked1;
				else this.img = ImgEntity.vLineClicked1;
				// 如果Line关联的两个Box都没有完成
				if (!this.boxFinished()) {
					// 切换Player
					Controller.getController().setTurn(1);
					// 切换显示的萌物
					Controller.getController().getBoard().getGame().getTurn0().setVisible(false);
					Controller.getController().getBoard().getGame().getTurn1().setVisible(true);
					// 如果有AI则AI启动下一步
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(0).drawLine();
					}
				}
			} else if (Controller.getController().getTurn() == 1) { // 轮到第二名Player或AI操作
				// 更改Line的显示图片
				if (!vertical) this.img = ImgEntity.hLineClicked2;
				else this.img = ImgEntity.vLineClicked2;
				// 如果Line关联的两个Box都没有完成
				if (!this.boxFinished()) {
					// 切换Player
					if (Controller.getController().getNumOfAI() == 2) {
						Controller.getController().setTurn(2);
						// 如果有第二个AI则AI启动下一步
						Controller.getController().getAis().get(1).drawLine();
					}
					else {
						Controller.getController().setTurn(0);
						// 切换显示的萌物
						Controller.getController().getBoard().getGame().getTurn1().setVisible(false);
						Controller.getController().getBoard().getGame().getTurn0().setVisible(true);
					}
				} else { // AI连续操作
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(0).drawLine();
					}
				}
			} else { // 轮到下一名AI操作（剧情模式用）
				// 更改Line的显示图片
				if (!vertical) this.img = ImgEntity.hLineClicked3;
				else this.img = ImgEntity.vLineClicked3;
				// 如果Line关联的两个Box都没有完成
				if (!this.boxFinished()) {
					// 切换Player
					Controller.getController().setTurn(0);
					// 切换显示的萌物
					Controller.getController().getBoard().getGame().getTurn1().setVisible(false);
					Controller.getController().getBoard().getGame().getTurn0().setVisible(true);
				} else { // AI连续操作
					if (Controller.getController().getNumOfAI() != 0) {
						Controller.getController().getAis().get(1).drawLine();
					}
				}
			}
			// 判断是否GameOver
			// 若GameOver，进行具体操作
			Controller.getController().GameOver();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标在Line上面显示图片
		if(!clicked){
			if (!vertical) this.img = ImgEntity.hLineEntered;
			else this.img = ImgEntity.vLineEntered;
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// 鼠标移走Line消失
		if (!clicked) this.img = null;
	}
	
	/**
	 * Override Line这个panel的paint方法，显示Line的不同图片
	 */
	@Override
	public void paint(Graphics g) {
		// 调用父类paint方法，避免paint错误
		super.paint(g);
		g.drawImage(img, 0, 0, width, height, null);
		this.repaint();
	}
	
	// TODO 后面方法均涉及AI算法，注释由对座完善
	/**
	 * 假装线被点，用于测试line的dangerlevel；
	 */
	public void pretendMouseClicked() {
		if ((!clicked) || (!pretendClicked)) {
			this.pretendClicked = true;
		}
	}
	
	/**
	 * 检测一条线是否假装安全
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
	 * 定义一条线的危险等级： 规定已经被点击的线为-2； 所在box有三条线已经被点击的为-1；
	 * 当点击这条线会导致下一名玩家完成的boxes数为该条线的危险等级
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
	 * 定义一条线的电脑对它是否被点的感觉，用于电脑坑熊孩子
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
