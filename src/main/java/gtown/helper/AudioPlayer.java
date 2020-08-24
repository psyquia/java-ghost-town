package gtown.helper;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import gtown.GameEngine;

public class AudioPlayer {
	public static void playOnce(Double volume, String filePath) {
		
		if(GameEngine.DEBUG) return;
		
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filePath));              
		    Clip clip = AudioSystem.getClip();

		    clip.open(audioIn);
		    clip.start();
		    
		    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		    gainControl.setValue(20f * (float) Math.log10(0.5));
		} catch (Exception e) {
			System.out.println("unable to load music.");
			e.printStackTrace();
		}
	}
	public static Clip playLoop(Double volume, String filePath) {
		
		if(GameEngine.DEBUG) return null;

		Clip clip = null;
		try {
			
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(filePath));              
		    clip = AudioSystem.getClip();

		    clip.open(audioIn);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		    clip.start();
		    
		    // Set volume based on bgmVolume array
		    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		    gainControl.setValue(20f * (float) Math.log10(volume));
		    
		} catch (Exception e) {
			System.out.println("unable to load music.");
			e.printStackTrace();
		}
		return clip;
	}
	public static void stopMusic(Clip clip) {
		
		if(GameEngine.DEBUG) return;
 
		try {
			clip.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
