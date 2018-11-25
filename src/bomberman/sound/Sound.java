/**
 * @author Nguyen Quang Huy
 * @version 1.0
 * @since 2-12-2018
 */

package bomberman.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Xử lý âm thanh cho trò chơi
 */
public class Sound {
private Clip clip;
	
	/**
	 * Khai báo đường dẫn cho âm thanh
	 * @param path
	 */
	public Sound(File path) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(path);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Phát âm thanh
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * Dừng âm thanh
	 */
	public void stop() {
		clip.stop();
	}
	
	/**
	 * Lặp âm thanh
	 */
	public void loop() {
		clip.loop(-1);
	}
}
