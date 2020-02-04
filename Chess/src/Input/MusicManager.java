package Input;

import javax.sound.sampled.*;
import java.io.File;

public class MusicManager {

	//Res.music
	private File audioFile;
	private AudioInputStream audioStream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip audioClip;



	private Clip background;
	private long clipTime = 0;

	public MusicManager(){
		background = getClip(loadAudio("background"));
	}

	private AudioInputStream loadAudio(String url) {
		try {
			audioFile = new File("res/music/" + url + ".wav");
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			format = audioStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);

			if(url.equals("background")){
				audioClip.loop(-1);
			}else{
				audioClip.loop(0);
			}

			return audioStream;

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	private Clip getClip(AudioInputStream stream) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			return clip;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void resumeBackground(){
		background.setMicrosecondPosition(clipTime);
		background.start();
	}

	public void pauseBackground(){
		clipTime = background.getMicrosecondPosition();
		background.stop();
	}

	public void restartBackground() {
		clipTime = 0;
		resumeBackground();
	}
	public boolean ended(){
		return background.getMicrosecondLength()-10<=background.getMicrosecondPosition();
	}

	public void play(String str) {
		Clip clip = getClip(loadAudio(str));
		clip.start();
	}


}
