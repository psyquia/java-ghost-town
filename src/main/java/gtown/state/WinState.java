package gtown.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import gtown.GameEngine;
import gtown.helper.AudioPlayer;


public class WinState implements State {
	
	private static WinState instance;
	protected Rectangle playBtn;
	private boolean switched = true;
	
	private WinState() {
		playBtn = 	new Rectangle(GameEngine.WIDTH / 2 - 70, 300, 150, 50);
	}
	
	public static WinState instance() {
		if(instance == null) {
			instance = new WinState();
		}
		return instance;
	}
	
	/**
	 * Render Menu
	 * @param g Graphics2D object
	 * @return void
	 */
	public void render(Graphics2D g){
		Font bahn = new Font("bahnschrift", Font.PLAIN, 24);

		g.setFont(bahn.deriveFont(150f));
		g.setColor(Color.red);
		g.drawString("Ghost Town", 120, 250);
		
		Font playText = new Font("arial", Font.ITALIC, 80);
		g.setFont(playText);
		g.setColor(Color.white);
		g.drawString("YOU WON", 290, 400);
		Font other = new Font("bahnschrift", Font.BOLD, 30);
		g.setFont(other);
		g.setColor(Color.RED);
		g.drawString("Press R to restart...", 350, 550);
		g.setColor(Color.WHITE);
		g.drawString("Press Q to quit...", 350, 600);
	}
	
	private void playMusic() {
		// Play music loop @params are Clip, Volume, and FilePath
		AudioPlayer.playOnce(1d, GameEngine.ASSETS_PATH + "/sfx/win.wav");
	}
	
	private void startGame() {
		switched = true;
		GameEngine ge = GameEngine.instance();
		ge.setGameState(RunningState.instance());
		RunningState.instance().setLevel(1);
	}
	
	public void update() {
		if(switched) {
			playMusic();
			switched = false;
		}
		GameEngine.instance().focus();
	};						
	
	public void processClick(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		// the current state is menu and the mouse position is within the play button
		if(		mx >= playBtn.getX() 
				&& mx <= playBtn.getX() + playBtn.getWidth()
				&& my >= playBtn.getY()
				&& my <= playBtn.getY() + playBtn.getHeight()) {
			startGame();
		} 
		
	};			
	public void processRelease(int key) {
		
	}

	@Override
	public void processPress(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_Q) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_R) {
			startGame();
		}
		
	}
}
