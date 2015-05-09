package ai;

import entity.Line;

/**
 * EasyAI
 */
public class CanEatAI extends AI {
	
	/**
	 * 构造器
	 */
	public CanEatAI(int number) {
		super(number);
	}

	/**
	 * 选择一条Line来draw（AI总的核心）
	 */
	@Override
	public Line chooseLine() {
		Line line = this.getMakeBoxLine();
		if (line != null) {
			return line;
		}
		line = this.getNoDangerLine();
		if ( line != null) {
			return line;
		}
		line = getRandomLine(getCanClickLines());
		return line;
	}
	
}
