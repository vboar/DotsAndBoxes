package media;

import java.io.File;
import saint.media.MidiPlayer;
import saint.media.SimplePlayer;
import system.Controller;

/**
 * 音乐播放器
 * @author 小Y
 *
 */
public class Player {
	
	private static SimplePlayer musicPlayer = null;
	private static MidiPlayer midiPlayer = null;
	private static SimplePlayer soundPlayer = null;

	/**
	 * 播放midi文件
	 * @param .mid文件的文件名
	 */
	public static void playMidi(String name) {
		if (midiPlayer != null)
			midiPlayer.stop();
		else 
			midiPlayer = new MidiPlayer();
		if (musicPlayer != null)
			stopMusic();
		
		try {
			midiPlayer.open(new File("media/midi/" + name + ".mid"));
			midiPlayer.setLoop(true);
			midiPlayer.setLoopCount(1000);
		} catch (Exception e) {
			System.err.println("Error!");
			return;
		}
		try {
			midiPlayer.play();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 播放MP3音乐
	 * @param .mp3文件的文件名
	 */
	public static void playMusic(String name) {
		if (Controller.getController().isMusicOn()) {
			if (midiPlayer != null)
				midiPlayer.stop();
			else 
				midiPlayer = new MidiPlayer();
			
			musicPlayer = new SimplePlayer();
			try{
				musicPlayer.open(new File("media/music/" + name + ".mp3"));
				musicPlayer.setLoop(true);
				musicPlayer.setLoopCount(1000);
			}catch (Exception e) {
				System.err.println("Error！");
				return;
			}
			try {
				musicPlayer.play();	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 播放MP3音效
	 * @param .mp3文件的文件名
	 */
	public static void playSound(String name) {
		if (Controller.getController().isSoundOn()) {
			soundPlayer = new SimplePlayer();
			try {
				soundPlayer.open(new File("media/sound/" + name + ".mp3"));
				soundPlayer.setLoop(false);
			} catch (Exception e) {
				System.err.println("Error!");
				return;
			}
			try {
				soundPlayer.play();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 停止mp3音乐
	 */
	public static void stopMusic() {
		musicPlayer.setVolume(0);
	}
	
	/**
	 * 停止midi音乐
	 */
	public static void stopMidi() {
		midiPlayer.stop();
	}
}