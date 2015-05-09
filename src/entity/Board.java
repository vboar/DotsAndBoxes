package entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import system.Controller;
import ui.classicgame.JPanelClassicGame;

/**
 * ��Ϸ���Board�࣬�����м����ʾ����
 */
public class Board extends JPanel {
	
	/**
	 * num * num Box����Ŀ
	 */
	private int num;
	/**
	 * �߿��
	 */
	private int padding = 20;
	/**
	 * Dot�ı߳�
	 */
	private int dotLength;
	/**
	 * Box�ı߳�
	 */
	private int boxLength;
	/**
	 * Dot�Ķ�ά����
	 */
	private Dot[][] dots = null;
	/**
	 * ƽ��Line�Ķ�ά����
	 */
	private Line[][] hLines = null;
	/**
	 * ��ֱLine�Ķ�ά����
	 */
	private Line[][] vLines = null;
	/**
	 * Box�Ķ�ά����
	 */
	private Box[][] boxes = null;
	/**
	 * ������Ϸpanel
	 */
	private JPanelClassicGame game;
	/**
	 * ����ģʽ
	 */
	private int mode = 0;
	// TODO ����AI�㷨���ɶ������
	private Line line = null;
	private int dangerPoint = 0;
	private int dangerTruePoint = 0;

	/**
	 * ���������������panel��Board������panel�е�xy���꣬�Լ�board�ı߳��������ĸ�ģʽ
	 */
	public Board(int x, int y,int length, JPanelClassicGame game, int mode) {
		this.setBounds(x, y, length, length);
		this.game = game;
		this.mode = mode;
		// ����ʼ��
		this.init();
	}
	
	/**
	 * ����ʼ��
	 */
	public void init() {
		// ��Board��Ϊ͸��
		this.setOpaque(false);
		// ��������Ϊ���ɲ���
		this.setLayout(null);
		// �ӿ������л��Box����Ŀ
		this.num = Controller.getController().getNumOfBoxes();
		// ����Dot�ı߳���Box�ı߳�
		this.dotLength = 340 / (4 * num + 1);
		this.boxLength = 340 * 3 / (4 * num + 1) + 1;
		// ���Dot��Line��Box
		this.addDots();
		this.addLines();
		this.addBoxes();
		// �ÿ��������Board������
		Controller.getController().setBoard(this);
		// ���ú���ع�ϵ
		this.setRelationBox();
		this.setRelationLineAndBox();
		// ���������̣߳�����repaint
		new Thread(new PaintThread()).start();
		// ��ӵ���AI
    	Controller.getController().addAI();
    	// �����AI����AI���Board������
    	if (Controller.getController().getNumOfAI() != 0)
    		Controller.getController().getAis().get(0).setBoard(this);
    	if (Controller.getController().getNumOfAI() == 2)
    		Controller.getController().getAis().get(1).setBoard(this);
    	//���AI���֣���AI����һ��
		if (Controller.getController().getNumOfAI() != 0 && !Controller.getController().isPlayerFirst()) {
			Controller.getController().setTurn(1);
			Controller.getController().getAis().get(0).drawLine();
		}
	}
	
	/**
	 * ���Dot
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
	 * ���Line
	 */
	public void addLines() {
		hLines = new Line[num + 1][num + 1];
		vLines = new Line[num + 1][num + 1];
		// ���ƽ��Line
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				hLines[i][j] = new Line(padding + dotLength*(j+1) + boxLength*j,
						padding + dotLength*i + boxLength*i, boxLength, dotLength, false);
				//��Line��ӵ������
				this.add(hLines[i][j]);
				hLines[i][j].setLineNumber("h"+i+j);//0513
			}
		}
		// �����ֱLine
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				vLines[i][j] = new Line(padding + dotLength*j + boxLength*j,
						padding + dotLength*(i+1) + boxLength*i, dotLength, boxLength, true);
				//��Line��ӵ������
				this.add(vLines[i][j]);
				vLines[i][j].setLineNumber("v"+i+j);//0513
			}
		}
	}
	
	/**
	 * ���Box
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
	 * ȥ��Dot��Line��Box��Ԫ�أ�����GameOver�����ʾ
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
	 * ��Board�ϵ�Dot��Box����ʼͼƬΪnull�����Ƶ������
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
	 * Ϊÿ��box���ù���������Line
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
	 * Ϊÿ��Line���ù���������Box
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
	 * �����ڲ����߳�
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
	
	// TODO ���������ɶ����ḻ
	/**
	 * 0429 ���board��dangertruepoint
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
						// System.out.println("�����1");
						if (i < num - 1
								&& getBoxes()[i + 1][j]
										.numOfLineThinkComplete() == 3) {
							dangerTruePoint++;
							// System.out.println("���·��ļ�1");
							check1 = true;
						}
						if (j < num - 1
								&& getBoxes()[i][j + 1]
										.numOfLineThinkComplete() == 3) {
							dangerTruePoint++;
							// System.out.println("�ҷ���1");
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
								// System.out.println("�·���1");
							}
						}
						if (check2) {
							if (j < num - 1
									&& getBoxes()[i][j + 1]
											.numOfLineThinkComplete() == 3) {
								dangerTruePoint--;
								// System.out.println("�ҷ���1");
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
	 * ���board��dangerpoint����Ϊline��dangerlevel
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
						// System.out.println("�����1");
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
	 * ���board��dangerpoint����Ϊline��dangerlevel
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
	 * �����ܼٳԵ�
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
		//System.out.println("��ɵ�����Ϊ��"+thinkClickNum);
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
