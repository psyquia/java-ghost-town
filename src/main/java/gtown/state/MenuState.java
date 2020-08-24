package gtown.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import gtown.GameEngine;
import gtown.helper.AudioPlayer;


public class MenuState implements State {
	
	private static MenuState instance;
	public static Rectangle playBtn;
	private Font bloodFont;
	private Clip clip;
	
	private MenuState() {
		playBtn = 	new Rectangle(GameEngine.WIDTH / 2 - 70, 300, 150, 50);
		
		GraphicsEnvironment ge = null;
	    try{
	    	bloodFont = Font.createFont(Font.TRUETYPE_FONT, new File(GameEngine.ASSETS_PATH + "/fonts/bloodblocks.ttf"));
	        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(bloodFont);
	    } catch(FontFormatException e){} catch (IOException e) {
	    	System.out.println("failed to load font!");
			e.printStackTrace();
	    }
	    
	    playMusic();
	}
	
	/**
	 * Return the instance of menu state
	 * @return MenuState
	 */
	public static MenuState instance() {
		if(instance == null) {
			instance = new MenuState();
		}
		return instance;
	}
	
	/**
	 * Render Menu
	 * @param g Graphics2D object
	 * @return void
	 * @throws IOException 
	 */
	public void render(Graphics2D g){
		try {
			Image img = ImageIO.read(new File(GameEngine.ASSETS_PATH + "/main_menu/bg.jpg"));
			g.drawImage(img, 0, 0, null);
		} catch(IOException e) {
			System.out.println("failed to load image!");
			e.printStackTrace();
		}

		g.setFont(bloodFont.deriveFont(150f));
		g.setColor(Color.red);
		g.drawString("Ghost Town", GameEngine.WIDTH / 2 - 280, 250);
		
		
		g.fillRect((int) playBtn.getX(), (int) playBtn.getY(), (int) playBtn.getWidth(), (int) playBtn.getHeight()); 
		
		Font playText = new Font("arial", Font.BOLD, 30);
		g.setFont(playText);
		g.setColor(Color.white);
		g.drawString("Play", playBtn.x+50, playBtn.y+35);
		g.draw(playBtn);
	}
	
	/**
	 * Start playing background music
	 * @return void
	 */
	private void playMusic() {
		// Play music loop @params are Clip, Volume, and FilePath
		clip = AudioPlayer.playLoop(1d, GameEngine.ASSETS_PATH + "/music/menu.wav");
	}
	
	/**
	 * Start Stop background music
	 * @return void
	 */
	private void stopMusic() {
		AudioPlayer.stopMusic(clip);
	}
	
	/**
	 * Start running the start
	 * @return void
	 */
	private void startGame() {
		GameEngine ge = GameEngine.instance();
		ge.setGameState(RunningState.instance());
		RunningState.instance().setLevel(1);
		stopMusic();
	}
	
	/**
	 * Update the state
	 * @return void
	 */
	public void update() {
		GameEngine.instance().focus();
	};						
	
	/**
	 * Define the action after click
	 * @param MouseEvent e
	 * @return void
	 */
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
	
	/**
	 * Define the action after click release
	 * @param int key
	 * @return void
	 */
	public void processRelease(int key) {
		
	}

	/**
	 * Define the action after click press
	 * @param int key
	 * @return void
	 */
	public void processPress(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(key == KeyEvent.VK_ENTER) {
			startGame();
		}
	}
}
