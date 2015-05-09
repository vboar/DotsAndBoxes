package system;

import image.ImgSystem;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ai.AI;
import ai.CanEatAI;
import ai.GreedyAI;
import ai.MoreSmartAI;
import ai.SmartAI;
import entity.Board;

/**
 * 系统控制器，控制一些很多类都调用的事物, 使用单例模式
 */
public class Controller {

	/**
	 * 格子数目
	 */
	private int numOfBoxes = 5;
	/**
	 * AI数目
	 */
	private int numOfAI = 1;
	// TODO
	private int turn = 0;
	/**
	 * 分数数组
	 */
	private int[] scores = new int[3];
	/**
	 * AI的ArrayList
	 */
	private List<AI> ais = new ArrayList<AI>();
	/**
	 * 是否开启音乐
	 */
	private boolean musicOn = true;
	/**
	 * 是否开启音效
	 */
	private boolean soundOn = true;
	/**
	 * AI难度级别
	 */
	private int difficulty = 1;
	/**
	 * 总分
	 */
	private int plusScore = 25;
	/**
	 * Board
	 */
	private Board board;
	/**
	 * Frame
	 */
	private JFrameGame game;
	/**
	 * 玩家是否先手
	 */
	private boolean playerFirst = true;
	/**
	 * 输赢
	 */
	private boolean win = false;
	
	private int musicNum;

	public int timeToThink = 10;
	public int greedyLock = 5;
	public int doubleDanger = 0;
	public int breakLoop = 0;
	
	/**
	 * 单例模式
	 */
	private static Controller controller;
	
	private Controller() {}
	
	public static Controller getController(){
		if(controller == null){
			controller = new Controller();
		}
		return controller;
	}
	
	/**
	 * 按照难度添加AI
	 */
	public void addAI() {
		if (numOfAI == 1) {
			if (difficulty == 0) {
				AI ai = new AI(1);
				ais.add(ai);
			}
			if (difficulty == 1) {
				CanEatAI ai = new CanEatAI(1);
				ais.add(ai);
			}
			if (difficulty == 2) {
				GreedyAI ai = new GreedyAI(1);
				ais.add(ai);
			}
			if (difficulty == 3) {
				SmartAI ai = new SmartAI(1);
				ais.add(ai);
			}
			if (difficulty == 4) {
				MoreSmartAI ai = new MoreSmartAI(1);
				ais.add(ai);
			}
		} else {
			if (difficulty == 0) {
				MoreSmartAI ai1 = new MoreSmartAI(1);
				MoreSmartAI ai2 = new MoreSmartAI(2);
				ais.add(ai1);
				ais.add(ai2);
			}
		}
	}
	
	/**
	 * 初始化控制器
	 */
	public void init() {
		for (int i = 0; i < 3; i++)
			scores[i] = 0;
		turn = 0;
		ais = new ArrayList<AI>();
		numOfBoxes = 5;
		plusScore = 25;
		numOfAI = 1;
		difficulty = 1;
		playerFirst = true;
		win = false;
	}
	
	/**
	 * 改变鼠标样式
	 */
	public void changeCursor(int type) {
		Cursor cursor;
		if (type == 1) {
			cursor = Toolkit.getDefaultToolkit().
					createCustomCursor(ImgSystem.cursorAI, new Point(0,0), "mycursor2");
		} else if (type == 2){
			cursor = Toolkit.getDefaultToolkit().
					createCustomCursor(ImgSystem.cursor, new Point(0,0), "mycursor");
		} else {
			cursor = Toolkit.getDefaultToolkit().
					createCustomCursor(ImgSystem.cursorNull, new Point(0,0), "mycursor");
		}
		Controller.getController().getGame().setCursor(cursor);
	}
	
	/**
	 * 游戏结束
	 */
	public boolean GameOver() {
		if (((numOfAI <= 1) && (scores[0] + scores[1] == plusScore))
				|| ((numOfAI == 2) && (scores[0] + scores[1]  + scores[2] == plusScore))) {
			// AI等待
			Timer timer = new Timer();
			timer.schedule(new TimerDone(), 900);
			return true;
		}
		return false;
	}
	
	/**
	 * 设置总分
	 */
	public void setPlusScore() {
		plusScore = numOfBoxes * numOfBoxes;
	}
	
	/**
	 * AI等待类
	 */
	public class TimerDone extends TimerTask {
		@Override
		public void run() {
			Controller.this.board.getGame().gameOver();
			Controller.this.board.removeAll();
		}
	}
	
	/*
	 * Retry游戏
	 */
	public void retry() {
		this.turn = 0;
		for (int i = 0; i < 3; i++)
			scores[i] = 0;
	}
	
	public int getNumOfBoxes() {
		return numOfBoxes;
	}
	
	public void setNumOfBoxes(int numOfBoxes) {
		this.numOfBoxes = numOfBoxes;
	}
	
	public int getNumOfAI() {
		return numOfAI;
	}
	
	public void setNumOfAI(int numOfAI) {
		this.numOfAI = numOfAI;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public List<AI> getAis() {
		return ais;
	}
	
	public boolean isMusicOn() {
		return musicOn;
	}
	
	public void setMusicOn(boolean musicOn) {
		this.musicOn = musicOn;
	}
	
	public boolean isSoundOn() {
		return soundOn;
	}
	
	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}
	
	public boolean isPlayerFirst() {
		return playerFirst;
	}
	
	public void setPlayerFirst(boolean playerFirst) {
		this.playerFirst = playerFirst;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public JFrameGame getGame() {
		return game;
	}
	
	public void setGame(JFrameGame game) {
		this.game = game;
	}

	public void setPlusScore(int plusScore) {
		this.plusScore = plusScore;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public int getMusicNum(int num) {
		Random random = new Random();
		musicNum = random.nextInt(num);
		return musicNum;
	}

}
