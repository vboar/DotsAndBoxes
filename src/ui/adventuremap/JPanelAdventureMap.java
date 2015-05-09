package ui.adventuremap;

import image.ImgAdventureMap;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import media.Player;
import system.AdventureDao;
import system.Controller;
import system.JFrameGame;
import ui.adventuregame.Talk;
import ui.common.JButtonRect;
import ui.common.JPanelCycleMovie;
import ui.common.JPanelOnceMovie;
import ui.common.Layer;
import ui.common.LayerBackground;

/**
 * 剧情模式地图panel
 */
public class JPanelAdventureMap extends JPanel {
	
	private JFrameGame game;
	private Layer[] layers = null;
	private JPanelOnceMovie fade = null;
	private JPanelOnceMovie loading = null;
	private JPanelOnceMovie introduction = null;
	private boolean[] open;
	private boolean show;
	private JLabel uncompleteShow;
	private JLabel unlock;
	private boolean uncomplete;
	private JLabel skipTips;
	private Skip skip;
	private boolean skipShow;
	private JLabel starLabel;
	private LayerBackground bg = new LayerBackground(0, 0, 800, 600, ImgAdventureMap.bg1);
	private int num;
	
	public JPanelAdventureMap(JFrameGame game, boolean show) {
		
		this.game = game;
		this.show = show;
		this.setLayout(null);
		this.loadData();
		layers = new Layer[] {
				bg
		};
		if (show) {
			loading = new JPanelOnceMovie(game, 21, 0, 0, 800, 600, 
					"image/jpanels/common/loading/", false, 100);
			this.add(loading);
			new Thread(new MapThread()).start();
		} else {
			Player.playMusic("amap");
			this.add();
		}

	}
	
	private class Skip extends JButtonRect {

		public Skip() {
			super(140, 140, 0, 0, 140, 140);
			this.setBounds(650, 480, 140, 140);
			this.setVisible(false);
			super.setImg(new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/skip.png").getImage());
			super.setRolloverImg(new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/skip.png").getImage());
			super.setPressedImg(new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/skipc.png").getImage());
			skipTips = new JLabel(new ImageIcon("image/jpanels/jpaneladventuremap/jbuttons/skiptips.png"));
			skipTips.setBounds(520, 345, 200, 200);
			skipTips.setVisible(false);
			JPanelAdventureMap.this.add(skipTips);
			skipTips.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					skipTips.setVisible(false);
					skipShow = false;
					Player.playSound("homerollover");
	            }
			});
		}
		@Override
	    public MouseAdapter getMouseAdapter() {
	    	MouseAdapter mouseAdapter = new MouseAdapter(){
	            public void mouseClicked(MouseEvent e) {
	            	num++;
	            	if (num > 10) {
	            		introduction.setOver(true);
	            	}
	            	if (skipShow) {
	            		skipTips.setVisible(false);
	            		skipShow = false;
	            		Player.playSound("homerollover");
	            	} else {
	            		skipTips.setVisible(true);
	            		skipShow = true;
	            		Player.playSound("homerollover");
	            	}
	            	repaint();
	            }
	        };
	    	return mouseAdapter;
	    }
	}
	
	/**
	 * 添加各种组件
	 */
	public void add() {
		
		starLabel = new JLabel(new ImageIcon("image/jpanels/jpaneladventuremap/starlabel.png"));
		starLabel.setBounds(295, 395, 180, 160);
		starLabel.setVisible(false);
		this.add(starLabel);
		uncompleteShow = new JLabel(new ImageIcon("image/jpanels/jpaneladventuremap/uncompleteshow.png"));
		uncompleteShow.setBounds(150, 20, 180, 160);
		uncompleteShow.setVisible(false);
		uncompleteShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 12 && e.getX() < 163 && e.getY() > 22 && e.getY() < 124) {
					uncomplete = false;
					uncompleteShow.setVisible(false);
					Player.playSound("amapuncompleteshow");
				}
			}
		});
		this.add(uncompleteShow);
		
		unlock = new JLabel(new ImageIcon("image/jpanels/jpaneladventuremap/unlock.png"));
		unlock.setBounds(210, 140, 360, 320);
		unlock.setVisible(false);
		unlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() > 11 && e.getX() < 295 && e.getY() > 45 && e.getY() < 225) {
					Player.playSound("amapunlockshowclick");
					unlock.setVisible(false);
				}
			}
		});
		this.add(unlock);
		this.add(new BackHome(game, this));
		this.add(new Planet1(70, 70, game, this));
		this.add(new Planet2(70, 70, game, this));
		this.add(new Planet3(80, 80, game, this));
		this.add(new Planet4(80, 80, game, this));
		this.add(new Planet5(86, 86, game, this));
		this.add(new Planet6(130, 130, game, this));
		this.add(new Planet7(91, 91, game, this));
		this.add(new Prince(300, 300, game, this));
		
		JPanelCycleMovie star = new JPanelCycleMovie(game, 6, 460, 440, 120, 120,
				"image/jpanels/jpaneladventuremap/star/", 400, true);
		this.add(star);
		this.add(new JPanelCycleMovie(game, 6, 215, 405, 80, 80,
				"image/jpanels/jpaneladventuremap/star/2/", 400, true));
		star.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseEntered(MouseEvent e) {
            	Player.playSound("homerollover");
            	starLabel.setVisible(true);
            }
            public void mouseExited(MouseEvent e) {
            	starLabel.setVisible(false);
            	Player.playSound("homerollover");
            } 
		});
		
	}
	
	/**
	 * 读取数据
	 */
	public void loadData() {
		open = AdventureDao.getAdventureDao().load();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.length; i++) {
			layers[i].createWindow(g);
		}
	}
	
	/**
	 * 内部类线程
	 */
	private class MapThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				if (loading != null && loading.isOver()) {
					skip = new Skip();
					loading.setOver(false);
					introduction = new JPanelOnceMovie(game, 28, 0, 0, 800, 600, 
							"image/jpanels/jpaneladventuremap/introduction/", false, 4000);
					bg.setImg(ImgAdventureMap.bg0);
					repaint();
					if (open[0]) {
						Player.playMusic("aintro");
						JPanelAdventureMap.this.add(skip);
						skip.setVisible(true);
						JPanelAdventureMap.this.add(introduction);
						open[0] = false;
						AdventureDao.getAdventureDao().save(open);
					} else {
						introduction.setOver(true);
					}
				} else if (introduction != null && introduction.isOver()) {
					skip.setVisible(false);
					skipTips.setVisible(false);
					JPanelAdventureMap.this.remove(skip);
					introduction.setOver(false);
					introduction.setVisible(false);
					introduction = null;
					Player.stopMusic();
					Player.playMusic("amap");
					JPanelAdventureMap.this.add();
					game.revalidate();
					game.repaint();
				} 
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean[] getOpen() {
		return open;
	}

	public void setOpen(boolean[] open) {
		this.open = open;
	}

	public JLabel getUncompleteShow() {
		return uncompleteShow;
	}

	public JLabel getUnlock() {
		return unlock;
	}

	public boolean isUncomplete() {
		return uncomplete;
	}

	public void setUncomplete(boolean uncomplete) {
		this.uncomplete = uncomplete;
	}
	
}
