package system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 剧情模式数据传送
 */
public class AdventureDao {

	private boolean[] open = new boolean[9];
	
	private String str;
	
	/**
	 * 单例模式
	 */
	private static AdventureDao dao;
	
	private AdventureDao() {}
	
	public static AdventureDao getAdventureDao(){
		if(dao == null){
			dao = new AdventureDao();
		}
		return dao;
	}
	
	public boolean[] load() {
		String[] strs = null;
		try {
			FileReader fr = new FileReader(new File("data/adventure.dab"));
			BufferedReader br = new BufferedReader(fr);
			strs = br.readLine().split(" ");
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equalsIgnoreCase("1")) {
				this.open[i] = true;
			} else {
				this.open[i] = false;
			}
		}
		return open;
	}
	
	public void save(boolean[] open) {
		try {
			String[] strs = new String[9];
			for (int i = 0; i < strs.length; i++) {
				if (open[i]) {
					strs[i] = "1";
				} else {
					strs[i] = "0";
				}
			}
			FileWriter fw = new FileWriter(new File("data/adventure.dab"));
			String str = strs[0] + " " + strs[1] + " " + strs[2]  + " " + 
					strs[3] + " " + strs[4] + " " + strs[5] + " " + strs[6]
					+ " " + strs[7] + " " + strs[8];
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		boolean[] open = this.load();
		for (int i = 2; i < 9; i++) {
			open[i] =false;
		}
		open[0] =true;
		this.save(open);
	}
	
}
