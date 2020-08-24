package gtown.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.Clip;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.gameobjects.GameObject;
import gtown.gameobjects.MainCharacter;
import gtown.helper.AudioPlayer;
import gtown.helper.SpriteLoader;
import gtown.map.Map;
import gtown.map.MapFactory;

public class RunningState implements State{				
	
	protected GameBoard board;
	public int currentLevel;
	protected MapFactory mapFactory;
	private Map currentMap;
	private Clip clip;
	
	private final List<Double> bgmVolume = Arrays.asList(1d, 0.28, 0.15);
	
	private static RunningState instance;
	
	public static RunningState instance() {
		if(instance == null) {
			instance = new RunningState();
		}
		return instance;
	}
	
	private RunningState() {
		try {
			SpriteLoader.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		board = GameBoard.instance();
		mapFactory = new MapFactory();
		currentLevel = 1;
		
		setLevel(currentLevel);
	}
	
	public void update() {					// update board and request focus 
		board.update();
		GameEngine.instance().focus();
	}
	
	public void render(Graphics2D g) {						// render GameBoard
		board.render(g);
	}
	
	public void addObject(GameObject gobj, int x, int y) {	// add @param gobj to board with specified @param x & y
		board.setCell(gobj, x, y);
	}
	
	public void removeObject(GameObject gobj) {
		board.removeObject(gobj);
	}
	
	public void processPress(int key) {						// handle user input when game is running
		if(key == KeyEvent.VK_W) {
			MainCharacter.instance().movingUp = true;
		}
		if(key == KeyEvent.VK_S) {
			MainCharacter.instance().movingDown = true;
		}
		if(key == KeyEvent.VK_D) {
			MainCharacter.instance().movingRight = true;
		}
		if(key == KeyEvent.VK_A) {
			MainCharacter.instance().movingLeft = true;
		}
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_R) {
			resetLevel();
		}
	}

	public void processRelease(int key) {					// handle user input when game is running
		if(key == KeyEvent.VK_W) {
			MainCharacter.instance().movingUp = false;
		}
		if(key == KeyEvent.VK_S) {
			MainCharacter.instance().movingDown = false;
		}
		if(key == KeyEvent.VK_D) {
			MainCharacter.instance().movingRight = false;
		}
		if(key == KeyEvent.VK_A) {
			MainCharacter.instance().movingLeft = false;
		}
	}
	
	public void processClick(MouseEvent e) {
		board.processClick(e, this);
	}
	
	public void resetLevel() {
		stopMusic();
		setLevel(currentLevel);
	}
	
	public void nextLevel() {
		stopMusic();
		setLevel(++currentLevel);
	}
	
	public void setLevel(int lvl) {
		if(lvl > 3) {
			GameEngine.instance().setGameState(WinState.instance());
			return;
		}
		currentLevel = lvl;
		board.clearBoard();
		board.resetScoreboard();
		currentMap = mapFactory.makeMap(lvl);
		
		for (GameObject goTemp: currentMap.getMapElements()) {
			if (goTemp != null)
				addObject(goTemp, goTemp.x, goTemp.y);
		}
		board.setTotalRewardCount(currentMap.getRewardCount());
		
		playMusic(lvl);
		board.resetTimeElapsed();
	}

	private void playMusic(int lvl) {
		// Play music loop @params are Clip, Volume, and FilePath
		if(lvl <= 0) return;
		clip = AudioPlayer.playLoop(bgmVolume.get((lvl-1)%3), GameEngine.ASSETS_PATH + "/music/level" 
										+ Integer.toString((lvl-1)%3+1) + ".wav");
	}
	
	public void stopMusic() {
		AudioPlayer.stopMusic(clip);
	}

	/**
	 * Return the current map at the current level
	 * @return Map
	 */
	public Map getCurrentMap() {
		return currentMap;
	}
}
