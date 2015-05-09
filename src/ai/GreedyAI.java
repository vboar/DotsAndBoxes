package ai;

import java.util.ArrayList;
import java.util.List;
import system.Controller;
import entity.Line;

/**
 * GreedyAI
 */
public class GreedyAI extends AI {
	
	/**
	 * 构造器
	 */
	public GreedyAI(int number) {
		super(number);
	}
	/**
	 * 选择一条Line来draw（AI总的核心）
	 */
	@SuppressWarnings("unused")
	@Override
	public Line chooseLine() {
		if(getCaneatLine()!=null){
			return getCaneatLine();
		}
		else if(getSafeLines(getCanClickLines()).size()!=0){
			return getRandomLine(getSafeLines(getCanClickLines()));
					}
			else if(true){
				return lowestDangerLine();
		}
		return getRandomLine(getCanClickLines());
	}

	/**
	 * 获得可以吃的AI
	 */
	public Line getCaneatLine(){
		int num = Controller.getController().getNumOfBoxes();
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j < num; j++) {
				Line line = board.gethLines()[i][j];
				if (line.dangerLevel()==-1) {
					return line;
				}
			}
		}
		for (int i = 0; i < num; i++) {
			for (int j = 0; j <= num; j++) {
				Line line = board.getvLines()[i][j];
				if (line.dangerLevel()==-1) {
					return line;
				}
			}
		}									
		return null;
	}
	public Line lowestDangerLine(){
		List<Line> canClickLinesInOrder=new ArrayList<Line>();
		canClickLinesInOrder=getCanClickLines();
		Line choice=null;
		 canClickLinesInOrder.get(0).setPretendClicked(true);
		int min=board.dangerPoint();
		for(Line i:canClickLinesInOrder){
			i.setPretendClicked(true);
			if(board.dangerPoint()<=min){
				i.setPretendClicked(true);
				min=board.dangerPoint();
				choice=i;
			}
			
		}
		return choice;
	}
	
}