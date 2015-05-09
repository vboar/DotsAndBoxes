package ui.classicgame;

import image.ImgClassicGame;
import image.ImgEntity;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import media.Player;
import system.Controller;
import system.JFrameGame;
import ui.common.JPanelCycleMovie;
import ui.common.Layer;
import ui.common.LayerBackground;
import entity.Board;
import entity.Box;
import entity.Line;

/**
 * 经典模式游戏panel
 */
public class JPanelClassicGame extends JPanel {
	
	protected JFrameGame game;
	protected Layer[] layers = null;
	protected Board board = null;
	// 红色萌物
	protected JPanelCycleMovie turn0;
	// 蓝色萌物
	protected JPanelCycleMovie turn1;
	// GameOver界面
	protected LayerGameOver gameOver;
	private int mode;
	
	public JPanelClassicGame(JFrameGame game, int mode) {
		this.game = game;
		this.mode = mode;
		this.setLayout(null);
		this.setOpaque(false);
		// 获取萌物
		turn0 = new JPanelCycleMovie(game, 7, 67, 168, 84, 84,
				"image/jpanels/jpanelclassicgame/turn/red", 200, true);
		turn1 = new JPanelCycleMovie(game, 7, 653, 346, 84, 84,
				"image/jpanels/jpanelclassicgame/turn/blue", 200, true);
		// 设置萌物初始显示
		if (Controller.getController().isPlayerFirst()) {
			this.turn1.setVisible(false);
		} else {
			this.turn0.setVisible(false);
		}
		// 设置分数
		Controller.getController().setPlusScore();
		this.setAdventure(mode);
		this.add(board);
		this.add();
		// repaint线程开启
		new Thread(new PaintThread()).start();
	
		
	}

	public void add() {
		// 添加萌物到面板上
		this.add(turn0);
		this.add(turn1);
		// 根据人机人人先手后手设置背景
		LayerBackground lb = null;
		if (Controller.getController().getNumOfAI() != 0) {
			lb = new LayerBackground(0, 0, 800, 600, ImgClassicGame.bg1);
		} else{
			lb = new LayerBackground(0, 0, 800, 600, ImgClassicGame.bg2);
		}
		gameOver = new LayerGameOver(0, 0, 800, 600);
		layers = new Layer[] {
				lb,
				new LayerBackground(200, 100, 400, 400, ImgClassicGame.board),
				new LayerScore(0, 0, 800, 600),
				gameOver
		};
		this.add(new JButtonBack(96, 96, game, this));
		this.add(new JButtonSound(55, 55, game, this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
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
	
	/**
	 * 剧情模式设置
	 */
	public void setAdventure(int mode) {
		if (mode == 0) {
			board = new Board(210, 110, 380, this, mode);
		}
		if (mode == 7) {
			// 5*5 已被占盒子棋盘
    		Controller.getController().setPlusScore(21);
    		Controller.getController().setDifficulty(2);
    		board = new Board(210, 110, 380, this, mode);
    		Box[][] boxes = this.board.getBoxes();
	    	Random random = new Random();
	    	int type = random.nextInt(10);
	    	if (type == 0) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[0][4].setAllLineClicked();
	    		boxes[4][0].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	} else if (type == 1) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	} else if (type == 2) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[1][1].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	} else if (type == 3) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[0][4].setAllLineClicked();
	    		boxes[4][0].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    	} else if (type == 4) {
	    		boxes[1][2].setAllLineClicked();
	    		boxes[2][1].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    	}  else if (type == 5) {
	    		boxes[2][2].setAllLineClicked();
	    		boxes[2][3].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    	} else if (type == 6) {
	    		boxes[2][1].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[2][3].setAllLineClicked();
	    		boxes[2][4].setAllLineClicked();
	    	} else if (type == 7) {
	    		boxes[1][2].setAllLineClicked();
	    		boxes[3][1].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    	} else if (type == 8) {
	    		boxes[1][1].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    	} else if (type == 9) {
	    		boxes[2][2].setAllLineClicked();
	    		boxes[1][3].setAllLineClicked();
	    		boxes[0][4].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	}
		} else if (mode == 8) {
			// 6*6 已被占盒子棋盘
			Controller.getController().setNumOfBoxes(6);
    		Controller.getController().setPlusScore(31);
    		Controller.getController().setDifficulty(2);
    		board = new Board(210, 110, 380, this, mode);
    		Box[][] boxes = this.board.getBoxes();
	    	Random random = new Random();
	    	int type = random.nextInt(10);
	    	if (type == 0) {
	    		Controller.getController().setPlayerFirst(false);
	    		boxes[0][3].setAllLineClicked();
	    		boxes[2][3].setAllLineClicked();
	    		boxes[4][3].setAllLineClicked();
	    		boxes[1][2].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    	} else if (type == 1) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[1][1].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	} else if (type == 2) {
	    		boxes[0][0].setAllLineClicked();
	    		boxes[1][1].setAllLineClicked();
	    		boxes[5][5].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    		boxes[4][4].setAllLineClicked();
	    	} else if (type == 3) {
	    		Controller.getController().setPlusScore(29);
	    		boxes[0][0].setAllLineClicked();
	    		boxes[0][5].setAllLineClicked();
	    		boxes[5][0].setAllLineClicked();
	    		boxes[5][5].setAllLineClicked();
	    		boxes[2][2].setAllLineClicked();
	    		boxes[3][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    	} else if (type == 4) {
	    		Controller.getController().setPlusScore(27);
	    		for (int i = 1; i < 4; i++) {
	    			for (int j = 2; j < 5; j++) {
	    				boxes[i][j].setAllLineClicked();
	    			}
	    		}
	    	} else if (type == 5) {
	    		for (int j = 1; j < 6; j++) {
	    			boxes[3][j].setAllLineClicked();
	    		}
	    	} else if (type == 6) {
	    		Controller.getController().setPlusScore(27);
	    		for (int i = 1; i < 6; i++) {
	    			boxes[i][3].setAllLineClicked();
	    		}
	    		for (int j = 1; j < 6; j++) {
	    			boxes[3][j].setAllLineClicked();
	    		}
	    	} else if (type == 7) {
	    		Controller.getController().setPlusScore(27);
	    		for (int i = 1; i < 4; i++) {
	    			boxes[i][1].setAllLineClicked();
	    		}
	    		for (int i = 1; i < 5; i++) {
	    			boxes[i][4].setAllLineClicked();
	    		}
	    		boxes[3][2].setAllLineClicked();
	    		boxes[3][3].setAllLineClicked();
	    	} else if (type == 8) {
	    		Controller.getController().setPlusScore(27);
	    		for (int i = 0; i < 5; i++) {
	    			boxes[i][i].setAllLineClicked();
	    		}
	    		boxes[0][5].setAllLineClicked();
	    		boxes[1][4].setAllLineClicked();
	    		boxes[4][1].setAllLineClicked();
	    		boxes[5][0].setAllLineClicked();
	    	} else if (type == 9) {
	    		Controller.getController().setPlusScore(27);
	    		for (int i = 1; i < 6; i += 2) {
	    			for (int j = 2; j < 5; j++) {
	    				boxes[i][j].setAllLineClicked();
	    			}
	    		}
	    	} 
		} else if (mode == 9) {
			// 5*5 线已被占的棋盘
			Controller.getController().setDifficulty(2);
    		board = new Board(210, 110, 380, this, mode);
    		Line[][] hLines = this.board.gethLines();
    		Line[][] vLines = this.board.getvLines();
	    	Random random = new Random();
	    	int type = random.nextInt(10);
	    	if (type == 0) {
	    		for (int j = 0; j < 5; j++) {
	    			hLines[0][j].setLineClicked();
	    			hLines[5][j].setLineClicked();
	    		}
	    		for (int i = 0; i < 5; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][5].setLineClicked();
	    		}
	    	} else if (type == 1) {
	    		for (int j = 0; j < 5; j++) {
	    			hLines[0][j].setLineClicked();
	    			hLines[5][j].setLineClicked();
	    		}
	    		for (int i = 0; i < 5; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][5].setLineClicked();
	    		}
	    		for (int j = 1; j < 4; j++) {
	    			hLines[2][j].setLineClicked();
	    		}
	    		
	    	} else if (type == 2) {
	    		for (int i = 0; i < 5; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][5].setLineClicked();
	    		}
	    		for (int j = 0; j < 5; j++) {
	    			hLines[3][j].setLineClicked();
	    		}
	    	} else if (type == 3) {
	    		for (int i = 1; i < 4; i++) {
	    			vLines[i][1].setLineClicked();
		    		vLines[i][4].setLineClicked();
	    		}
	    		for (int j = 1; j < 4; j++) {
	    			hLines[1][j].setLineClicked();
	    			hLines[4][j].setLineClicked();
	    		}
	    	} else if (type == 4) {
	    		for (int i = 1; i < 4; i++) {
	    			vLines[i][4].setLineClicked();
	    		}
	    		for (int j = 1; j < 4; j++) {
	    			hLines[2][j].setLineClicked();
	    		}
	    	} else if (type == 5) {
	    		for (int i = 0; i < 5; i++) {
	    			vLines[i][4].setLineClicked();
	    		}
	    		for (int j = 0; j < 5; j++) {
	    			hLines[4][j].setLineClicked();
	    		}
	    	} else if (type == 6) {
	    		for (int i = 0; i < 5; i++) {
	    			vLines[i][5].setLineClicked();
	    		}
	    		for (int j = 0; j < 5; j++) {
	    			hLines[5][j].setLineClicked();
	    		}
	    	} else if (type == 7) {
	    		for (int j = 0; j < 4; j++) {
	    			hLines[0][j].setLineClicked();
	    			hLines[2][j].setLineClicked();
	    			hLines[4][j].setLineClicked();
	    		}
	    	} else if (type == 8) {
	    		for (int i = 1; i < 4; i++) {
	    			vLines[i][2].setLineClicked();
	    			vLines[i][3].setLineClicked();
	    		}
	    	} else if (type == 9) {
	    		for (int j = 0; j < 5; j++) {
	    			hLines[j][j].setLineClicked();
	    		}
	    	}
		} else if (mode == 10) {
			Controller.getController().setNumOfBoxes(6);
			Controller.getController().setPlusScore();
			Controller.getController().setDifficulty(3);
    		board = new Board(210, 110, 380, this, mode);
    		Line[][] hLines = this.board.gethLines();
    		Line[][] vLines = this.board.getvLines();
	    	Random random = new Random();
	    	int type = random.nextInt(5);
	    	if (type == 0) {
	    		for (int j = 0; j < 6; j++) {
	    			hLines[3][j].setLineClicked();
	    		}
	    		for (int i = 0; i < 6; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][6].setLineClicked();
	    		}
	    	} else if (type == 1) {
	    		for (int i = 0; i < 6; i++) {
	    			vLines[i][i].setLineClicked();
	    		}
	    		for (int i = 0; i < 6; i++) {
	    			vLines[i][6 - i].setLineClicked();
	    		}
	    	} else if (type == 2) {
	    		for (int j = 0; j < 6; j++) {
	    			hLines[0][j].setLineClicked();
	    			hLines[6][j].setLineClicked();
	    		}
	    		for (int i = 0; i < 6; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][6].setLineClicked();
	    		}
	    	} else if (type == 3) {
	    		for (int j = 0; j < 6; j++) {
	    			hLines[0][j].setLineClicked();
	    			hLines[6][j].setLineClicked();
	    		}
	    		for (int i = 0; i < 6; i++) {
	    			vLines[i][0].setLineClicked();
		    		vLines[i][6].setLineClicked();
	    		}
	    		for (int j = 2; j < 4; j++) {
	    			hLines[2][j].setLineClicked();
	    			hLines[4][j].setLineClicked();
	    		}
	    		for (int i = 2; i < 4; i++) {
	    			vLines[i][4].setLineClicked();
		    		vLines[i][2].setLineClicked();
	    		}
	    	} else if (type == 4) {
	    		for (int j = 1; j < 5; j++) {
	    			hLines[1][j].setLineClicked();
	    			hLines[5][j].setLineClicked();
	    		}
	    		for (int i = 1; i < 5; i++) {
	    			vLines[i][1].setLineClicked();
		    		vLines[i][5].setLineClicked();
	    		}
	    	}
		} else if (mode == 1) {
			Controller.getController().setNumOfBoxes(2);
			Controller.getController().setDifficulty(2);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 2) {
			Controller.getController().setNumOfBoxes(4);
			Controller.getController().setDifficulty(2);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 3) {
			Controller.getController().setDifficulty(1);
			Controller.getController().setPlayerFirst(false);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 4) {
			// TODO 要输的AI
			Controller.getController().setDifficulty(0);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 5) {
			Controller.getController().setDifficulty(2);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 6) {
			Controller.getController().setDifficulty(3);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 11) {
			Controller.getController().setDifficulty(1);
			Controller.getController().setNumOfBoxes(7);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 12) {
			Controller.getController().setDifficulty(2);
			Controller.getController().setNumOfBoxes(7);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 13) {
			Controller.getController().setDifficulty(4);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 14) {
			Controller.getController().setDifficulty(1);
			Controller.getController().setNumOfBoxes(11);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 15) {
			Controller.getController().setDifficulty(0);
			Controller.getController().setNumOfAI(2);
			board = new Board(210, 110, 380, this, mode);
		} else if (mode == 16) {
			Controller.getController().setDifficulty(3);
			Controller.getController().setNumOfBoxes(9);
			Controller.getController().setPlusScore();
			board = new Board(210, 110, 380, this, mode);
		}
	}

	/**
	 * GameOver之后改变显示
	 */
	public void gameOver() {
		this.turn0.setVisible(true);
		this.turn1.setVisible(true);
//		Player.stopMusic();
		this.gameOver.setImg();
		this.add(new JButtonRetry(game, this));
	}
	
	public void retry() {
		this.gameOver.setImg();
	}

	public JPanelCycleMovie getTurn0() {
		return turn0;
	}

	public JPanelCycleMovie getTurn1() {
		return turn1;
	}

	public int getMode() {
		return mode;
	}
	
}
