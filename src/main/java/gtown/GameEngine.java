package gtown;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import gtown.input.KeyListener;
import gtown.input.MouseInput;
import gtown.state.MenuState;
import gtown.state.State;
import gtown.ui.Window;

public class GameEngine extends Canvas{
	public static boolean DEBUG = false;
	
	public static final String ASSETS_PATH = "src/main/resources/assets";
	
	private static final long serialVersionUID = -4555183209654564999L;

	public static final int WIDTH = 1024, HEIGHT = WIDTH*9/12;
	
	public State state;
	
	private static GameEngine instance;
	
	/**
	 * Singleton instance method
	 * @return the instance of GameEngine
	 */
	public static GameEngine instance() {
		if(instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}
	
	/**
	 * privatee Singleton constructor
	 * Creates window, adds input listeners
	 */
	private GameEngine() {
		new Window(WIDTH, HEIGHT, "Ghost Town", this);
		
		state = MenuState.instance();
		
		this.addKeyListener(new KeyListener());		// initialize KeyListener
		this.addMouseListener(new MouseInput());
		this.setFocusable(true);						// set focus on game window
	}
	
	/**
	 * Starts main loop
	 * Ticks 60 times per second
	 * @return void
	 */
	public void run() {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {			// use timer to set game tick rate
			public void run() {
				state.update();
				render();
			}								
		}, 0, 1000/60);									// set game tick rate to 60 times per second			
	}
	
	/**
	 * Renders all game elements
	 * Passes Graphics2D g to all lower
	 * render methods
	 * @return void
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();	
		if(bs == null) {
			this.createBufferStrategy(3);				// create triple buffered strategy
			return;
		}
		
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();			// cast graphics into Graphics2D to use antialiasing
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
			    RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);							// fill background
		
		state.render(g);								// render game based on state
		
		g.dispose();									// dispose graphics
		bs.show();										// show BufferStrategy
	}
	
	public void focus() {
		this.requestFocus();
	}
	
	/**
	 * Get the current state name 
	 * @return string
	 */
	public String getCurrentState() {
		return state.getClass().getSimpleName();
	}
	
	/**
	 * Set the current state to game
	 * @return void
	 */
	public void setGameState(State newState) {
		state = newState;
	}
	
	public static void main(String args[]) {
		GameEngine.instance().run();									// run game
	}
}
