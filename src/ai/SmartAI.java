package ai;

import java.util.ArrayList;
import java.util.List;
import system.Controller;
import entity.Line;

/**
 * SmartAI
 */
public class SmartAI extends AI {

	private Line store = null;

	/**
	 * ������
	 */
	public SmartAI(int number) {
		super(number);
	}
	
	/**
	 * AI��AI��(������)
	 */
//	private class DrawThread implements Runnable {
//
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			while (!Controller.getController().GameOver()) {
//				SmartAI.this.drawLine();
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	/**
	 * ѡ��һ��Line��draw��AI�ܵĺ��ģ�
	 */
	@SuppressWarnings("unused")
	public Line chooseLine() {
		Line chooseCanCleanLine =store;		
		if (store != null && (!store.isClicked())) {//������ַ����ˣ�֮ǰ���������ĺ��ӣ��ܳ�ȴû�гԣ��ͳԵ���
			System.out.println("here0");
			chooseCanCleanLine = store;
			store = null;
			chooseCanCleanLine.setThinkClicked(false);
			return chooseCanCleanLine;
		}
		if (getCaneatLine()!=null) {			
				if (getSafeLines(getCanClickLines()).size() != 0&&store!=null&&!store.isClicked()) {			
					System.out.println("here1.5");	
					store.setThinkClicked(false);
					return store;
				}
				else{
					if(store!=null){
					store.setThinkClicked(false);
					}
				}
				System.out.println("here1");
				return  getCaneatLine();
			

		} else if (getSafeLines(getCanClickLines()).size() != 0) {
			System.out.println("here2");
			return getRandomLine(getSafeLines(getCanClickLines()));
		} else if (true) {
			System.out.println("here3");
			return lowestDangerLine();
		}
		return getRandomLine(getCanClickLines());
	}

	/**
	 * ���һ���ܹ�ʹBox��ɵ�Line�������ʱ�����죬�ͻ����
	 */
	public Line getCaneatLine() {
		Line choice = null;
		Line choice2 = null;
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				Line line = board.gethLines()[i][j];
				if (line.dangerThinkLevel() == -1) {
					// System.out.println(i+"  1h "+j);
					choice = line;
				}
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				Line line = board.getvLines()[i][j];
				if (line.dangerThinkLevel() == -1) {
					choice = line;
				}
			}
		}
		if (board.dangerPoint() == 2&&getCanClickLines().size()!=2) {
			choice.setThinkClicked(true);
			store = choice;
			for (int i = 0; i <= num; i++) {
				for (int j = 0; j < num; j++) {
					Line line2 = board.gethLines()[i][j];
					if (line2.dangerThinkLevel() == -1 && line2 != choice) {
						choice2 = line2;
					}
				}
			}
			for (int i = 0; i < num; i++) {
				for (int j = 0; j <= num; j++) {
					Line line2 = board.getvLines()[i][j];
					if (line2.dangerThinkLevel() == -1 && line2 != choice) {
						choice2 = line2;
					}
				}
			}
			return choice2;
		}
		return choice;
	}

	/**
	 * ѡȡ����ɺ����С��һ����
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
		return choice;
	}

}