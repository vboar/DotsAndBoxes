package ai;

import entity.Line;

/**
 * EasyAI
 */
public class CanEatAI extends AI {
	
	/**
	 * ������
	 */
	public CanEatAI(int number) {
		super(number);
	}

	/**
	 * ѡ��һ��Line��draw��AI�ܵĺ��ģ�
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
