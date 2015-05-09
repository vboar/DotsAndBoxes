package ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import system.Controller;
import entity.Board;
import entity.Box;
import entity.Line;

/**
 * Demo AI
 * 
 * @author vboar
 * 
 */
public class MoreSmartAI extends AI {
	private List<Line> store = new ArrayList<Line>();
	private boolean greedyLock = false;

	/**
	 * ������
	 * 
	 * @param number
	 */
	public MoreSmartAI(int number) {
		super(number);
	}

	/**
	 * ѡ��һ��Line��draw��AI�ܵĺ��ģ�
	 * 
	 * @return
	 */
	public Line chooseLine() {
		System.out.println("---------------------------------");

		if (canWin()) {
			System.out.println("��Ӯ�Ļ��Ͳ���һ�еس�");
			return eatNomal();
		}

		while (!store.isEmpty()) {
			Line temp1 = store.get(0);
			store.remove(0);
			if (!temp1.isClicked()) {
				System.out.println("�Ե�֮ǰ���˵�");
				return temp1;
			}
		}
		// --------------------------------------------------------------------------------------//
		int num = Controller.getController().getNumOfBoxes();
		System.out.println("---------------------------------");
		int safeLinesNum = pretendClickAllSafeLine();
		int changeControl = canChangeDangerNum();
		board.setThinkBack();
		System.out.println("safeLinesNum=" + safeLinesNum);
		System.out.println("changeControl=" + changeControl);
		int safeLineSize = getSafeLines(getCanClickLines()).size();
		int canClickSize = getCanClickLines().size();
		Line temp = getCaneatLine();
		// -------------------------------------------------------------------------------------//
		if (temp != null) {
			int number = mustLine(true);
			if (number % 2 == 1) {
				greedyLock = false;
			}
			if (safeLineSize != 0 && !greedyLock) {
				return eatNomal();
			} else
				return temp;
		} else if (safeLineSize != 0) {
			if (safeLinesNum != 0 && safeLinesNum < Controller.getController().timeToThink) {
				if (safeLinesNum < Controller.getController().greedyLock) {
					greedyLock = true;
				}
				System.out.println("����һ���ǲ���Ҫ�����ؿӵ�");
				System.out.print("֮ǰ�ģ�");
				int number = mustLine(true);

				if (Controller.getController().doubleDanger == 1) {
					Line checkbreakLine = doubleBreakDangerChangeLine();
					if (checkbreakLine != null) {
						System.out.println("û�µģ���Ҫ�£����о�");
						return checkbreakLine;
					}
				}

				if (number % 2 == 0) {
					Line trapLine0 = safeLineRetry();
					if (trapLine0 != null) {
						System.out.println("ͨ����ȫ���������ɹ�");
						return trapLine0;
					}
					Line trapLine = lowestDangerTrapLine();
					if (trapLine != null) {
						System.out.println("��ʼ�����ؿӵ���");
						return trapLine;
					}
				}
				System.out.println("���ˣ�û��Ҫ�ӵ���");
				Line breakDangerChangeLine = breakDangerChangeLine();
				if (breakDangerChangeLine != null) {
					return breakDangerChangeLine;
				}

			}
			if (Controller.getController().breakLoop == 1) {
				Line breakLoop = breakLoop();
				if (breakLoop != null) {

					System.out.println("�ƻ�LOOP");
					return breakLoop;

				}
			}
			System.out.println("�߰�ȫ����");
			return getRandomLine(getSafeLines(getCanClickLines()));
		} else if (true) {
			System.out.println("��Σ�ճ̶���͵���");
			return lowestDangerLine();
		}
		System.out.println("���ڲ�����");
		return getRandomLine(getCanClickLines());
	}

	/**
	 * ���һ�������Line��������ķ���
	 * 
	 * @param lines
	 * @return
	 */
	public Line getRandomLine(List<Line> lines) {
		int i;
		if (lines.size() > 0) {
			i = new Random().nextInt(lines.size());
			// System.out.println(i);

			return lines.get(i);
		}
		return null;
	}

	/**
	 * ���һ���ܹ�ʹBox��ɵ�Line�������ʱ�����죬�ͻ����
	 * 
	 * @return
	 */
	public Line getCaneatLine() {
		int num = Controller.getController().getNumOfBoxes();
		int point = board.dangerTruePoint();
		int lineNumber = board.canClickLineNum();
		System.out.println("linenum=" + lineNumber);
		System.out.println("point=" + point);
		if (point == 0) {
			return null;
		} else if (point == lineNumber) {
			if (point > 3) {
				System.out.println("������1");
				return eatNomal();// ������
			}
			if (point == 3) {
				Line preLine = eatNomal();
				System.out.println("С�ĵس�");
				return ThreeEatLine(preLine);
			}
			if (point == 2) {
				if (getCanClickLines().size() != 2) {
					int number = mustLine(true);
					if (number % 2 == 1) {
						System.out.println("���ÿӣ�ֱ�ӳ�");
						return eatNomal();
					}
					System.out.println("chain����");
					return chainTrap();// chain����
				} else {
					System.out.println("���һ���ˣ����Ӹ�ƨ��");
					return eatNomal();
				}
			}
			if (point == 1) {
				System.out.println("������2");
				return eatNomal();// ������

			}
		} else if (point == lineNumber + 1) {
			if (point > 4) {
				System.out.println("������3");
				return eatNomal();// ������loop
			} else if (point == 4) {
				if (getCanClickLines().size() != 3) {
					System.out.println("loop����");
					return loopTrap();// loop����*/
				} else {
					System.out.println("���һ���ˣ����Ӹ�ƨ��");
					return eatNomal();
				}
			} else {
				System.out.println("������4");
				return eatNomal();// ������

			}
		} else {
			System.out.println("������5");
			return eatNomal();// ������
		}
		return null;
	}

	public Line eatNomal() {
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					return board.getBoxes()[i][j].LinePretendUncomplete();
				}
			}
		}
		return null;

	}

	public Line ThreeEatLine(Line preLine) {
		Line getLine1 = null;
		Line getLine2 = null;
		preLine.setPretendClicked(true);
		int num = Controller.getController().getNumOfBoxes();
		loop1: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					getLine1 = board.getBoxes()[i][j].LinePretendUncomplete();
					getLine1.setPretendClicked(true);
					break loop1;
				}
			}
		}
		loop2: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					getLine2 = board.getBoxes()[i][j].LinePretendUncomplete();
					getLine2.setPretendClicked(true);
					break loop2;
				}
			}
		}

		preLine.setPretendClicked(true);
		getLine1.setPretendClicked(false);
		getLine2.setPretendClicked(true);
		int point = board.dangerTruePoint();
		if (point == 2) {
			System.out.println("�Է��ǵ�����");
			return preLine;
		}
		getLine1.setPretendClicked(true);
		point = board.dangerTruePoint();
		if (point == 3) {
			getLine1.setPretendClicked(true);
			preLine.setPretendClicked(true);
			point = board.dangerTruePoint();
			if (point == 2) {
				System.out.println("�Է��ǵ�1A��");
				return preLine;
			} else {
				System.out.println("�Է��ǵ�1B��");
				return getLine2;
			}
		}
		getLine2.setPretendClicked(true);
		point = board.dangerTruePoint();
		if (point == 3) {
			getLine2.setPretendClicked(true);
			preLine.setPretendClicked(true);
			point = board.dangerTruePoint();
			if (point == 2) {
				System.out.println("�Է��ǵ�2A��");
				return preLine;
			} else {
				System.out.println("�Է��ǵ�2B��");
				return getLine1;
			}
		}
		preLine.setPretendClicked(true);
		point = board.dangerTruePoint();
		if (point == 3) {
			getLine1.setPretendClicked(true);
			preLine.setPretendClicked(true);
			point = board.dangerTruePoint();
			if (point == 2) {
				System.out.println("�Է��ǵ�3A��");
				return getLine1;
			} else {
				System.out.println("�Է��ǵ�3B��");
				return getLine2;
			}
		}
		System.out.println("bug");
		return null;
	}

	public Line chainTrap() {
		int num = Controller.getController().getNumOfBoxes();
		List<Line> temp = new ArrayList<Line>();
		loop1: while (true) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
						Line tempLine = board.getBoxes()[i][j]
								.LinePretendUncomplete();
						temp.add(tempLine);
						tempLine.setPretendClicked(true);
						continue loop1;
					}
				}
			}
			break loop1;
		}
		for (Line i : temp) {
			i.setPretendClicked(false);
		}
		store.add(temp.get(0));
		return temp.get(1);
	}

	public Line loopTrap() {
		Line tempLine1 = null;
		Line tempLine2 = null;
		Line tempLine3 = null;

		int num = Controller.getController().getNumOfBoxes();
		loop1: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					tempLine1 = board.getBoxes()[i][j].LinePretendUncomplete();
					tempLine1.setPretendClicked(true);
					break loop1;
				}
			}
		}
		loop2: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					tempLine2 = board.getBoxes()[i][j].LinePretendUncomplete();
					tempLine2.setPretendClicked(true);
					break loop2;
				}
			}
		}
		loop3: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					tempLine3 = board.getBoxes()[i][j].LinePretendUncomplete();
					tempLine3.setPretendClicked(true);
					break loop3;
				}
			}
		}
		tempLine2.setPretendClicked(false);
		tempLine3.setPretendClicked(false);
		if (board.dangerTruePoint() == 4) {
			tempLine1.setPretendClicked(false);
			store.add(tempLine2);
			store.add(tempLine3);
			return tempLine1;
		}
		tempLine2.setPretendClicked(true);
		tempLine1.setPretendClicked(false);
		tempLine3.setPretendClicked(false);
		if (board.dangerTruePoint() == 4) {
			tempLine2.setPretendClicked(false);
			store.add(tempLine1);
			store.add(tempLine3);
			return tempLine2;
		}
		tempLine3.setPretendClicked(true);
		tempLine1.setPretendClicked(false);
		tempLine2.setPretendClicked(false);
		if (board.dangerTruePoint() == 4) {
			tempLine3.setPretendClicked(false);
			store.add(tempLine2);
			store.add(tempLine1);
			return tempLine3;
		}
		return null;

	}

	/**
	 * ѡȡ����ɺ����С��һ����
	 * 
	 * @return
	 */
	public Line lowestDangerLine() {
		List<Line> canClickLinesInOrder = new ArrayList<Line>();
		canClickLinesInOrder = getCanClickLines();
		Line choice = null;
		canClickLinesInOrder.get(0).setPretendClicked(true);
		int min = board.dangerPoint();
		for (Line i : canClickLinesInOrder) {
			if (i.isThinkClicked()) {
				continue;
			}
			i.setPretendClicked(true);
			if (board.dangerPoint() <= min) {
				i.setPretendClicked(true);
				min = board.dangerPoint();
				choice = i;
			}

		}
		if (min == 2) {
			return dangerTwoLine(choice);
		}
		return choice;
	}

	/**
	 * 0504
	 */
	public Line lowestDangerTrapLine() {
		int num = Controller.getController().getNumOfBoxes();
		List<Line> canClickLinesInOrder = new ArrayList<Line>();
		canClickLinesInOrder = getCanClickLines();
		Line choice = null;
		int leastPoint = 0;
		int min = 100;
		int testTime = 0;
		loop1: while (true) {
			if (canClickLinesInOrder.isEmpty()) {
				System.out.println("ǭ¿����Ӳ�������");
				return null;
			}
			for (Line i : canClickLinesInOrder) {
				if (i.isThinkClicked()) {
					continue;
				}
				i.setPretendClicked(true);
				int point = board.dangerTruePoint();
				if (point <= min && point > leastPoint) {
					i.setPretendClicked(true);
					min = board.dangerPoint();
					choice = i;
				}
			}
			System.out.println("���Կ�" + choice.getLineNumber());
			if (testTime > 25) {
				System.out.println("ǭ¿����Ӳ�������");
				return null;
			}
			if (canMakeTrap(choice)) {
				break loop1;
			} else {
				testTime++;
				Iterator<Line> sListIterator = canClickLinesInOrder.iterator();
				while (sListIterator.hasNext()) {
					Line e = sListIterator.next();
					if (e.equals(choice)) {
						sListIterator.remove();
					}
				}
				min = 100;
				continue loop1;
			}
		}
		choice.setThinkClicked(true);
		int point = board.dangerTruePoint();
		int k = 0;
		Box questionbox = null;
		if (point == 2) {
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < num; j++) {
					if (board.getBoxes()[i][j].numOfLineThinkComplete() == 3) {
						questionbox = board.getBoxes()[i][j];
						k++;
					}
				}
			}
			if (k == 2) {
				board.setThinkBack();
			} else {
				choice = questionbox.LineThinkUncomplete();
				board.setThinkBack();
			}
			System.out.println("��һ����������ȥ����");
		} else {
			choice.setThinkClicked(false);
		}
		System.out.println("��������" + choice.getLineNumber());

		return choice;
	}

	public Line dangerTwoLine(Line preLine) {
		Line getLine1 = null;
		Line getLine2 = null;
		preLine.setPretendClicked(true);
		int num = Controller.getController().getNumOfBoxes();
		loop1: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					getLine1 = board.getBoxes()[i][j].LinePretendUncomplete();
					getLine1.setPretendClicked(true);
					break loop1;
				}
			}
		}
		loop2: for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				if (board.getBoxes()[i][j].numOfLinePretendComplete() == 3) {
					getLine2 = board.getBoxes()[i][j].LinePretendUncomplete();
					getLine2.setPretendClicked(true);
					break loop2;
				}
			}
		}
		preLine.setPretendClicked(false);
		int point = board.dangerTruePoint();
		getLine1.setPretendClicked(false);
		getLine2.setPretendClicked(false);
		if (point == 2) {
			return preLine;
		}

		return getLine1;
	}

	/**
	 * ���һ����������Line
	 * 
	 * @return
	 */
	public Line getNoDangerLine() {
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				Line line = board.gethLines()[i][j];
				if (line.dangerLevel() == 0) {
					return line;
				}
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				Line line = board.getvLines()[i][j];
				if (line.dangerLevel() == 0) {
					return line;
				}
			}
		}
		return null;
	}

	/**
	 * ������п���ȥclick��Line
	 * 
	 * @return
	 */
	public List<Line> getCanClickLines() {
		List<Line> canClickLines = new ArrayList<Line>();
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				Line line = board.gethLines()[i][j];
				if (!line.isClicked()) {
					canClickLines.add(line);
				}
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				Line line = board.getvLines()[i][j];
				if (!line.isClicked()) {
					canClickLines.add(line);
				}
			}
		}
		return canClickLines;
	}

	/**
	 * ��ð�ȫ��LINE
	 */
	public List<Line> getSafeLines(List<Line> line) {
		List<Line> safeLines = new ArrayList<Line>();
		line = getCanClickLines();
		int num = Controller.getController().getNumOfBoxes();
		for (Line i : line) {
			if (i.dangerLevel() == 0) {
				safeLines.add(i);
			}

		}
		return safeLines;
	}

	/**
	 * 0504
	 */
	public int canChangeDangerNum() {
		int numOfSingleDanger = 0;
		List<Line> lines = getCanClickLines();
		for (Line i : lines) {
			if (!i.isThinkClicked()) {
				i.setThinkClicked(true);
				if (board.dangerTruePoint() == 1
						|| board.dangerTruePoint() == 2) {
					numOfSingleDanger++;
					System.out.println("��װ���˵��ߵ�����" + i.getLineNumber());
					board.thinkComplete();
				} else {
					i.setThinkClicked(false);
				}
			}
		}
		return numOfSingleDanger;

	}

	public int pretendClickAllSafeLine() {
		List<Line> safeLines = getSafeLines(getCanClickLines());
		int numOfSafeLines = 0;
		loop1: while (true) {
			for (Line i : safeLines) {
				if (i.thinkSafe() && !i.isThinkClicked()) {
					i.setPretendClicked(true);
					i.setThinkClicked(true);
					numOfSafeLines++;
					System.out.println("��װ�㰲ȫ���ߵ�����" + i.getLineNumber());
					continue loop1;
				}
			}
			break loop1;
		}
		return numOfSafeLines;
	}

	public int mustLine(boolean back) {
		board.thinkComplete();
		int mustLine = 0;
		int a = pretendClickAllSafeLine();
		int b = canChangeDangerNum();
		System.out.println("a=" + a + " b=" + b);
		mustLine = a + b;
		if (back) {
			board.setThinkBack();
		}
		return mustLine;
	}

	/**
	 * 0505
	 */
	public boolean canWin() {
		int score = Controller.getController().getScores()[1];
		int point = board.dangerTruePoint();
		int num = Controller.getController().getNumOfBoxes();
		if (score + point > num * num / 2 && point != 0) {
			return true;
		}
		return false;
	}

	public boolean canMakeTrap(Line preLine) {
		boolean canMakeTrap = false;
		board.setThinkBack();
		preLine.setThinkClicked(true);
		board.thinkComplete();
		System.out.print("֮��ģ�");
		int mustLine = mustLine(true);
		board.setThinkBack();
		if (mustLine % 2 == 0) {
			canMakeTrap = true;
		}
		if (!canMakeTrap) {
			System.out.println("�ƺ�����Ҳû��");
		}
		return canMakeTrap;
	}

	/**
	 * 0513
	 */
	public Line breakLoop() {
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i < num; i++) {
			for (int j = 1; j < num; j++) {
				if (!board.getvLines()[i][j].isClicked()
						&& board.getvLines()[i][j - 1].isClicked()
						&& board.getvLines()[i][j + 1].isClicked()) {
					if (board.getvLines()[i][j].dangerLevel() == 0) {
						return board.getvLines()[i][j];
					} else if (board.getvLines()[i][j].getBox1().gethLine1()
							.isClicked()
							&& board.getvLines()[i][j].getBox2().gethLine1()
									.isClicked()) {
						if (i < num - 1
								&& board.getvLines()[i + 1][j].dangerLevel() == 0) {
							return board.getvLines()[i + 1][j];
						}
					} else if (board.getvLines()[i][j].getBox1().gethLine2()
							.isClicked()
							&& board.getvLines()[i][j].getBox2().gethLine2()
									.isClicked()) {
						if (i > 0
								&& board.getvLines()[i - 1][j].dangerLevel() == 0) {
							return board.getvLines()[i - 1][j];
						}
					}
				}// end if v
				if (!board.gethLines()[j][i].isClicked()
						&& board.gethLines()[j - 1][i].isClicked()
						&& board.gethLines()[j + 1][i].isClicked()) {
					if (board.gethLines()[j][i].dangerLevel() == 0) {
						return board.gethLines()[j][i];
					} else if (board.gethLines()[j][i].getBox1().getvLine1()
							.isClicked()
							&& board.gethLines()[j][i].getBox2().getvLine1()
									.isClicked()) {
						if (i < num - 1
								&& board.gethLines()[j][i + 1].dangerLevel() == 0) {
							return board.gethLines()[j][i + 1];
						}
					} else if (board.gethLines()[j][i].getBox1().getvLine2()
							.isClicked()
							&& board.getvLines()[j][i].getBox2().getvLine2()
									.isClicked()) {
						if (i > 0
								&& board.gethLines()[j][i - 1].dangerLevel() == 0) {
							return board.gethLines()[j][i - 1];
						}
					}
				}// end if h
			}// end for j
		}// end for i
		return null;
	}

	/**
	 * 0514
	 */
	public Line safeLineRetry() {
		List<Line> safeLines = getSafeLines(getCanClickLines());
		for (Line i : safeLines) {
			boolean trap = canMakeTrap(i);
			if (trap) {
				return i;
			}
		}
		return null;
	}

	/**
	 * todo
	 * 
	 * @return
	 */
	public Line hasCanChangeSafeLine() {
		List<Line> safeLines = getSafeLines(getCanClickLines());
		for (Line i : safeLines) {
			boolean trap = canMakeTrap(i);
			if (!trap) {
				System.out.println("����з��գ�Ҫ����");
				return i;
			} else {
				System.out.println("��Ȼ���Ƕ��ڶ�����˵");
			}
		}
		System.out.println("�����ϰ�ȫ����û��Σ��");
		return null;
	}

	public Line breakDangerChangeLine() {
		Line choice = null;
		int point;
		List<Line> safeLines = getSafeLines(getCanClickLines());
		List<Line> canClickLines = getCanClickLines();
		for (Line i : safeLines) {
			for (Line j : canClickLines) {

				j.setThinkClicked(true);
				point = board.dangerTruePoint();
				if (point > 2 || point == 0) {
					j.setThinkClicked(false);
					continue;
				}
				board.thinkComplete();
				if (!i.thinkSafe()) {
					int mustLine = mustLine(true);
					if (mustLine % 2 == 1) {
						System.out
								.println("��ֹ����dangerLine" + j.getLineNumber());
						System.out.println(" ����ѡ�������" + i.getLineNumber());
						choice = i;
					}
				}
				board.setThinkBack();

			}
		}

		return choice;

	}

	public Line doubleBreakDangerChangeLine() {
		List<Line> storeComplete1=new ArrayList<Line>();
		List<Line> storeComplete2=new ArrayList<Line>();
		int times = 0;
		Line choice = null;
		Line choice1 = null;
		Line choice2 = null;
		int point;
		List<Line> safeLines = getSafeLines(getCanClickLines());
		List<Line> canClickLines = getCanClickLines();
		loop1: for (Line i : safeLines) {
			for (Line j : canClickLines) {

				j.setThinkClicked(true);
				point = board.dangerTruePoint();
				if (point > 2 || point == 0) {
					j.setThinkClicked(false);
					continue;
				}
				
				storeComplete1=board.thinkComplete();
				/*for(Line x:storeComplete1){
					x.setClicked(true);
				}*/
				if (!i.thinkSafe()) {
					int mustLine = mustLine(true);
					if (mustLine % 2 == 1) {
						System.out.println("2222222222222222222222222222");
						System.out.println("��һ����ֹ����Σ������" + j.getLineNumber());
						System.out.println("��һ������ѡ�������" + i.getLineNumber());
						choice1 = i;
						times++;
						break loop1;
					}
				}
				board.setThinkBack();

			}
		}
		if (choice1 == null) {
			return null;
		}
		choice1.setClicked(true);
		 safeLines = getSafeLines(getCanClickLines());
		 canClickLines = getCanClickLines();
		loop2: for (Line i : safeLines) {
			if (!i.thinkSafe() && i.isClicked()) {
				continue;
			}
			for (Line j : canClickLines) {
				j.setThinkClicked(true);
				point = board.dangerTruePoint();
				if (point > 2 || point == 0) {
					j.setThinkClicked(false);
					continue;
				}
				
				storeComplete2=board.thinkComplete();
				/*for(Line x:storeComplete2){
					x.setClicked(true);
				}*/
				if (!i.thinkSafe()) {
					int mustLine = mustLine(true);
					if (mustLine % 2 == 0) {
						System.out.println("�ڶ�����ֹ����Σ������" + j.getLineNumber());
						System.out.println("�ڶ�������ѡ�������" + i.getLineNumber());
						System.out.println("2222222222222222222222222222");
						choice2 = i;
						times++;
						break loop2;
					}
				}
				board.setThinkBack();
			}
		}
		if (choice2 != null) {
			choice2.setClicked(true);
		}
		if (times == 1 || times == 0) {
			choice1.setClicked(false);
			if (choice2 != null)
				choice2.setClicked(false);
			System.out.println("Σ�յ���ֻ��" + times + "��������");
			return null;
		}		
		safeLines = getSafeLines(getCanClickLines());
		canClickLines = getCanClickLines();
		choice = getRandomLine(safeLines);			
		choice1.setClicked(false);
		if (choice2 != null)
			choice2.setClicked(false);
		System.out.println("Σ�յ���ֻ��" + times + "����");
		if(choice!=null)System.out.println("���ǵ�ѡ����" + choice.getLineNumber());
		
		
		return choice;

	}

	public Line canChange() {
		int num = Controller.getController().getNumOfBoxes();
		int number = 0;
		int point;
		int mustLine1;
		int mustLine2;
		List<Line> store = new ArrayList<Line>();
		List<Line> safeLines = getSafeLines(getCanClickLines());
		List<Line> canClickLines = getCanClickLines();
		loop1: while (true) {
			for (Line i : safeLines) {
				for (Line j : canClickLines) {
					if (j.isThinkClicked()) {
						continue;
					}
					j.setThinkClicked(true);
					point = board.dangerTruePoint();
					if (point > 2 || point == 0) {
						j.setThinkClicked(false);
						continue;
					}
					board.thinkComplete();

					if (!i.thinkSafe()) {
						mustLine1 = mustLine(false);
						i.setThinkClicked(true);
						board.thinkComplete();
						mustLine2 = mustLine(false);
						if (mustLine2 % 2 != mustLine1 % 2) {
							number++;
							for (int m = 0; m <= num; m++) {
								for (int n = 0; n < num; n++) {
									if (board.gethLines()[m][n]
											.isThinkClicked()) {
										store.add(board.gethLines()[m][n]);
									}
									if (board.getvLines()[n][m]
											.isThinkClicked()) {
										store.add(board.gethLines()[n][m]);
									}
								}
							}
							System.out.println("���µ����ǣ�" + j.getLineNumber());
							if (number == 2) {
								break loop1;
							}
							continue loop1;
						}
					}
					board.setThinkBack();
					for (Line x : store) {
						x.setThinkClicked(true);
					}
					continue;
				}
			}
			break loop1;
		}
		Line choice = null;
		if (number == 2) {
			if (safeLines.size() > 2) {
				for (int i = 0; i < safeLines.size(); i++) {
					if (safeLines.get(i).isThinkClicked()) {
						safeLines.remove(i);
					}
				}
			}
			choice = getRandomLine(safeLines);

		}
		board.setThinkBack();
		return choice;

	}
}