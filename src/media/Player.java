package media;

import java.io.File;
import saint.media.MidiPlayer;
import saint.media.SimplePlayer;
import system.Controller;

/**
 * ���ֲ�����
 * @author СY
 *
 */
public class Player {
	
	private static SimplePlayer musicPlayer = null;
	private static MidiPlayer midiPlayer = null;
	private static SimplePlayer soundPlayer = null;

	/**
	 * ����midi�ļ�
	 * @param .mid�ļ����ļ���
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
	 * ����MP3����
	 * @param .mp3�ļ����ļ���
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
				System.err.println("Error��");
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
	 * ����MP3��Ч
	 * @param .mp3�ļ����ļ���
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
	 * ֹͣmp3����
	 */
	public static void stopMusic() {
		musicPlayer.setVolume(0);
	}
	
	/**
	 * ֹͣmidi����
	 */
	public static void stopMidi() {
		midiPlayer.stop();
	}
}