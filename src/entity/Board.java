package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import system.Controller;
import ui.classicgame.JPanelClassicGame;

/**
 * 游戏面板Board类，仅是中间的显示区域
 */
public class Board extends JPanel {
	
	/**
	 * num * num Box的数目
	 */
	private int num;
	/**
	 * 边框距
	 */
	private int padding = 20;
	/**
	 * Dot的边长
	 */
	private int dotLength;
	/**
	 * Box的边长
	 */
	private int boxLength;
	/**
	 * Dot的二维数组
	 */
	private Dot[][] dots = null;
	/**
	 * 平行Line的二维数组
	 */
	private Line[][] hLines = null;
	/**
	 * 竖直Line的二维数组
	 */
	private Line[][] vLines = null;
	/**
	 * Box的二维数组
	 */
	private Box[][] boxes = null;
	/**
	 * 整个游戏panel
	 */
	private JPanelClassicGame game;
	/**
	 * 剧情模式
	 */
	private int mode = 0;
	// TODO 关于AI算法的由对座完成
	private Line line = null;
	private int dangerPoint = 0;
	private int dangerTruePoint = 0;

	/**
	 * 构造器，获得整个panel，Board在整个panel中的xy坐标，以及board的边长，剧情哪个模式
	 */
	public Board(int x, int y,int length, JPanelClassicGame game, int mode) {
		this.setBounds(x, y, length, length);
		this.game = game;
		this.mode = mode;
		// 面板初始化
		this.init();
	}
	
	/**
	 * 面板初始化
	 */
	public void init() {
		// 将Board设为透明
		this.setOpaque(false);
		// 将布局设为自由布局
		this.setLayout(null);
		// 从控制器中获得Box的数目
		this.num = Controller.getController().getNumOfBoxes();
		// 计算Dot的边长和Box的边长
		this.dotLength = 340 / (4 * num + 1);
		this.boxLength = 340 * 3 / (4 * num + 1) + 1;
		// 添加Dot、Line和Box
		this.addDots();
		this.addLines();
		this.addBoxes();
		// 让控制器获得Board的引用
		Controller.getController().setBoard(this);
		// 设置好相关关系
		this.setRelationBox();
		this.setRelationLineAndBox();
		// 启动绘制线程，不断repaint
		new Thread(new PaintThread()).start();
		// 添加电脑AI
    	Controller.getController().addAI();
    	// 如果有AI，让AI获得Board的引用
    	if (Controller.getController().getNumOfAI() != 0)
    		Controller.getController().getAis().get(0).setBoard(this);
    	if (Controller.getController().getNumOfAI() == 2)
    		Controller.getController().getAis().get(1).setBoard(this);
    	//如果AI先手，让AI先走一步
		if (Controller.getController().getNumOfAI() != 0 && !Controller.getController().isPlayerFirst()) {
			Controller.getController().setTurn(1);
			Controller.getController().getAis().get(0).drawLine();
		}
	}
	
	/**
	 * 添加Dot
	 */
	public void addDots() {
		dots = new Dot[num + 1][num + 1];
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j <= num; j++) {
				dots[i][j] = new Dot(padding + (dotLength + boxLength) * j, 
						padding + (dotLength + boxLength) * i, dotLength);
			}
		}
	}
	
	/**
	 * 添加Line
	 */
	public void addLines() {
		hLines = new Line[num + 1][num + 1];
		vLines = new Line[num + 1][num + 1];
		// 添加平行Line
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				hLines[i][j] = new Line(padding + dotLength*(j+1) + boxLength*j,
						padding + dotLength*i + boxLength*i, boxLength, dotLength, false);
				//将Line添加到面板上
				this.add(hLines[i][j]);
				hLines[i][j].setLineNumber("h"+i+j);//0513
			}
		}
		// 添加竖直Line
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				vLines[i][j] = new Line(padding + dotLength*j + boxLength*j,
						padding + dotLength*(i+1) + boxLength*i, dotLength, boxLength, true);
				//将Line添加到面板上
				this.add(vLines[i][j]);
				vLines[i][j].setLineNumber("v"+i+j);//0513
			}
		}
	}
	
	/**
	 * 添加Box
	 */
	public void addBoxes() {
		boxes = new Box[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				boxes[i][j] = new Box(padding + dotLength*(j+1) + boxLength*j,
						padding + dotLength*(i+1) + boxLength*i, boxLength);
			}
		}
	}
	
	/**
	 * 去除Dot、Line、Box等元素，用于GameOver后的显示
	 */
	public void removeAll() {
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j <= num; j++) {
				dots[i][j].setImg(null);
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				boxes[i][j].setImg(null);
			}
		}
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				hLines[i][j].setVisible(false);
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				vLines[i][j].setVisible(false);
			}
		}
	}
	
	/**
	 * 将Board上的Dot和Box（初始图片为null）绘制到面板上
	 */
	public void paintComponent(Graphics g) {
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j <= num; j++) {
				dots[i][j].paint(g);
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				boxes[i][j].paint(g);
			}
		}
		super.paintComponent(g);
	}
	
	/**
	 * 为每个box设置关联的四条Line
	 */
	public void setRelationBox() {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				boxes[i][j].sethLine1(hLines[i][j]);
				boxes[i][j].sethLine2(hLines[i+1][j]);
				boxes[i][j].setvLine1(vLines[i][j]);
				boxes[i][j].setvLine2(vLines[i][j+1]);
			}
		}
	}
	
	/**
	 * 为每条Line设置关联的两个Box
	 */
	public void setRelationLineAndBox() {
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				if (i > 0) {
					hLines[i][j].setBox1(boxes[i-1][j]);
				}
				if (i < num) {
					hLines[i][j].setBox2(boxes[i][j]);
				}
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				if (j > 0) {
					vLines[i][j].setBox1(boxes[i][j-1]);
				}
				if (j < num) {
					vLines[i][j].setBox2(boxes[i][j]);
				}
			}
		}
	}
	
	/**
	 * 绘制内部类线程
	 */
	private class PaintThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// TODO 下面内容由对座丰富
	/**
	 * 0429 检查board的dangertruepoint
	 */
	public int dangerTruePoint() {
		dangerTruePoint = 0;
		boolean check1 = false;
		boolean check2 = false;
		loop: while (true) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					check1 = false;
					check2 = false;
					if (getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						int kick = getBoxes()[i][j].numOfLineThinkUncomplete();
						dangerTruePoint++;
						// System.out.println("自身加1");
						if (i < num - 1
								&& getBoxes()[i + 1][j]
										.numOfLineThinkComplete() == 3) {
							dangerTruePoint++;
							// System.out.println("正下方的加1");
							check1 = true;
						}
						if (j < num - 1
								&& getBoxes()[i][j + 1]
										.numOfLineThinkComplete() == 3) {
							dangerTruePoint++;
							// System.out.println("右方加1");
							check2 = true;
						}

						if (kick == 1)
							this.getBoxes()[i][j].gethLine1()
									.setPretendClicked(true);
						if (kick == 2)
							this.getBoxes()[i][j].gethLine2()
									.setPretendClicked(true);
						if (kick == 3)
							this.getBoxes()[i][j].getvLine1()
									.setPretendClicked(true);
						if (kick == 4)
							this.getBoxes()[i][j].getvLine2()
									.setPretendClicked(true);
						// System.out.println("kick=" + kick);
						if (check1) {
							if (i < num - 1
									&& getBoxes()[i + 1][j]
											.numOfLineThinkComplete() == 3) {
								dangerTruePoint--;
								// System.out.println("下方减1");
							}
						}
						if (check2) {
							if (j < num - 1
									&& getBoxes()[i][j + 1]
											.numOfLineThinkComplete() == 3) {
								dangerTruePoint--;
								// System.out.println("右方减1");
							}
						}
						continue loop;
					}
				}
			}
			break loop;
		}
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				this.gethLines()[i][j].setPretendClicked(false);
				this.getvLines()[j][i].setPretendClicked(false);
			}
		}

		// System.out.println(dangerTruePoint);
		return dangerTruePoint;
	}

	/**
	 * 检查board的dangerpoint，作为line的dangerlevel
	 */
	public int dangerPoint() {
		dangerPoint = 0;
		loop: while (true) {
			for (int i = 0; i < num; i++) {
				loop2: for (int j = 0; j < num; j++) {
					if (this.getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						if (!(this.getBoxes()[i][j].gethLine1().isClicked() || this
								.getBoxes()[i][j].gethLine1()
								.isPretendClicked())) {
							this.getBoxes()[i][j].gethLine1()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if (!(this.getBoxes()[i][j].gethLine2().isClicked() || this
								.getBoxes()[i][j].gethLine2()
								.isPretendClicked())) {
							this.getBoxes()[i][j].gethLine2()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if (!(this.getBoxes()[i][j].getvLine1().isClicked() || this
								.getBoxes()[i][j].getvLine1()
								.isPretendClicked())) {
							this.getBoxes()[i][j].getvLine1()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if ((!this.getBoxes()[i][j].getvLine2().isClicked())
								&& (!this.getBoxes()[i][j].getvLine2()
										.isPretendClicked())) {
							this.getBoxes()[i][j].getvLine2()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
					} else {
						continue loop2;
					}
				}
			}
			break loop;
		}
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				this.gethLines()[i][j].setPretendClicked(false);
				this.getvLines()[j][i].setPretendClicked(false);
			}
		}

		return dangerPoint;
	}

	
	
	public int canClickLineNum() {
		dangerTruePoint = 0;
		loop: while (true) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						int kick = getBoxes()[i][j].numOfLineThinkUncomplete();
						dangerTruePoint++;
						// System.out.println("自身加1");
						if (kick == 1)
							this.getBoxes()[i][j].gethLine1()
									.setPretendClicked(true);
						if (kick == 2)
							this.getBoxes()[i][j].gethLine2()
									.setPretendClicked(true);
						if (kick == 3)
							this.getBoxes()[i][j].getvLine1()
									.setPretendClicked(true);
						if (kick == 4)
							this.getBoxes()[i][j].getvLine2()
									.setPretendClicked(true);
						// System.out.println("kick=" + kick);
						continue loop;
					}
				}
			}
			break loop;
		}
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				this.gethLines()[i][j].setPretendClicked(false);
				this.getvLines()[j][i].setPretendClicked(false);
			}
		}

		// System.out.println(dangerTruePoint);
		return dangerTruePoint;
	}

	/**
	 * 检查board的dangerpoint，作为line的dangerlevel
	 */
	public int canClickLine() {
		dangerPoint = 0;
		loop: while (true) {
			for (int i = 0; i < num; i++) {
				loop2: for (int j = 0; j < num; j++) {
					if (this.getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						if (!(this.getBoxes()[i][j].gethLine1().isClicked() || this
								.getBoxes()[i][j].gethLine1()
								.isPretendClicked())) {
							this.getBoxes()[i][j].gethLine1()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if (!(this.getBoxes()[i][j].gethLine2().isClicked() || this
								.getBoxes()[i][j].gethLine2()
								.isPretendClicked())) {
							this.getBoxes()[i][j].gethLine2()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if (!(this.getBoxes()[i][j].getvLine1().isClicked() || this
								.getBoxes()[i][j].getvLine1()
								.isPretendClicked())) {
							this.getBoxes()[i][j].getvLine1()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
						if ((!this.getBoxes()[i][j].getvLine2().isClicked())
								&& (!this.getBoxes()[i][j].getvLine2()
										.isPretendClicked())) {
							this.getBoxes()[i][j].getvLine2()
									.setPretendClicked(true);
							dangerPoint++;
							continue loop;
						}
					} else {
						continue loop2;
					}
				}
			}
			break loop;
		}
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				this.gethLines()[i][j].setPretendClicked(false);
				this.getvLines()[j][i].setPretendClicked(false);
			}
		}

		return dangerPoint;
	}
	
	/**
	 * 0504
	 */
	public void setThinkBack() {
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				this.gethLines()[i][j].setThinkClicked(false);
				this.getvLines()[j][i].setThinkClicked(false);
			}
		}
	}
	
	/**
	 * 画完能假吃的
	 */
	public List<Line> thinkComplete() {
		List<Line> store=new ArrayList<Line>();
		int num = Controller.getController().getNumOfBoxes();
		int thinkClickNum=0;
		loop1:while (true) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						getBoxes()[i][j].LineThinkUncomplete().setThinkClicked(
								true);
						thinkClickNum++;
						store.add(getBoxes()[i][j].LineThinkUncomplete());
						continue loop1;
					}
				}
			}
			break loop1;
		}
		//System.out.println("完成的数量为："+thinkClickNum);
		return store;
	}
	
	public Line[][] gethLines() {
		return hLines;
	}

	public void sethLines(Line[][] hLines) {
		this.hLines = hLines;
	}

	public Line[][] getvLines() {
		return vLines;
	}

	public void setvLines(Line[][] vLines) {
		this.vLines = vLines;
	}

	public Box[][] getBoxes() {
		return boxes;
	}

	public JPanelClassicGame getGame() {
		return game;
	}

	public Dot[][] getDots() {
		return dots;
	}

	public Line getLine() {
		return line;
	}
	
}
