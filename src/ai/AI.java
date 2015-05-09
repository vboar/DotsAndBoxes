package ai;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import system.Controller;
import entity.Board;
import entity.Box;
import entity.Line;

/**
 * ����AI�Ļ���
 */
public class AI {
	
	/**
	 * Board���
	 */
	protected Board board = null;
	
	/**
	 * �����������AI���
	 */
	public AI(int number) {}
	
	/**
	 * draw Line ����
	 */
	public void drawLine() {
		//���δGameOver
		if (!Controller.getController().GameOver()) {
			// AIdrawLine���ı������ʽ
			Controller.getController().changeCursor(1);
			// ȥ��Line��������
			AI.this.setMouseListener(false);
			// �ȴ�300ms
			Timer timer = new Timer();
			timer.schedule(new TimerDone(), 500);
		} else {
			Controller.getController().changeCursor(2);
		}
	}
	
	/**
	 * ��ͣ�߳�
	 */
	public class TimerDone extends TimerTask {
		@Override
		public void run() {
			// AIѡ��һ��ҪDraw��Line
			AI.this.setMouseListener(true);
			AI.this.asMouseClick(AI.this.chooseLine());
			// ����ֵ�Player���Ļ�Ĭ�����
			if (Controller.getController().getTurn() == 0) {
				Controller.getController().changeCursor(2);
			}
		}
	}
	
	/**
	 * ����������
	 */
	public void setMouseListener(boolean add) {
		int num = Controller.getController().getNumOfBoxes();
		Line[][] hLines = this.board.gethLines();
		Line[][] vLines = this.board.getvLines();
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				hLines[i][j].setMouseListener(add);
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				vLines[i][j].setMouseListener(add);
			}
		}
	}
	
	/**
	 * ģ�������е��Line
	 */
	public void asMouseClick(Line line) {
		MouseEvent mouseEvent = new MouseEvent(line, 0, 0, 0, line.getX(), line.getY(), 1, false, 1);
		for (MouseListener mouseListener: line.getMouseListeners()) {
			mouseListener.mouseClicked(mouseEvent);
		}
	}
	
	/**
	 * ѡ��һ��Line��draw��AI�ܵĺ��ģ�������AI��Override
	 * �˴�ΪRandomAI
	 */
	public Line chooseLine() {
		return getRandomLine(getCanClickLines());
	}
	
	/**
	 * ���һ�������Line��������ķ���
	 */
	public Line getRandomLine(List<Line> lines) {
		int i;
		if (lines.size() > 0) {
			i  = new Random().nextInt(lines.size());
			return lines.get(i);
		}
		return null;
	}
	
	/**
	 * ���һ���ܹ�ʹBox��ɵ�Line
	 */
	public Line getMakeBoxLine() {
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				Box box = board.getBoxes()[i][j];
				if (box.numOfLineComplete() == 3) {
					if (!box.gethLine1().isClicked()) return box.gethLine1();
					if (!box.gethLine2().isClicked()) return box.gethLine2();
					if (!box.getvLine1().isClicked()) return box.getvLine1();
					if (!box.getvLine2().isClicked()) return box.getvLine2();
				}
			}
		}
		return null;
	}
	
	/**
	 * �ж�һ��Line�Ƿ��ǲ������ģ�����Χ������Box
	 */
	public boolean noDangerLine(Line line) {
		Box box1 = line.getBox1();
		if (box1 != null) {
			if (box1.numOfLineComplete() >= 2) {
				return false;
			}
		}
		Box box2 = line.getBox2();
		if (box2 != null) {
			if (box2.numOfLineComplete() >= 2) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * ���һ����������Line
	 */
	public Line getNoDangerLine() {
		List<Line> lines1 = new ArrayList<Line>();
		List<Line> lines2 = this.getCanClickLines();
		for (int i = 0; i < lines2.size(); i++) {
			if (this.noDangerLine(lines2.get(i))) {
				lines1.add(lines2.get(i));
			}
		}
		Line line = this.getRandomLine(lines1);
		return line;
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
		for (Line i : line) {
			if (i.dangerLevel() == 0) {
				safeLines.add(i);
			}
		}
		return safeLines;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}

}
