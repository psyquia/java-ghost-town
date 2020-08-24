package gtown;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import gtown.gameobjects.GameObject;
import gtown.gameobjects.Movable;
import gtown.state.RunningState;
import gtown.ui.ExitButton;
import gtown.ui.LevelCount;
import gtown.ui.RestartButton;
import gtown.ui.ScoreBoard;
import gtown.ui.TimeElapsed;

public class GameBoard {
	
	private ScoreBoard scoreBoard;
	private RestartButton restartBtn;
	private ExitButton exitBtn;
	private LevelCount levelCount;
	private TimeElapsed timeElapsed;
	
	private ArrayList<Cell> cells;								// holds width * height cells of board
	public CopyOnWriteArrayList<GameObject> gobjects;		// using CopyOnWriteArrayList to prevent concurrentmodificationexception on remove
		
	private final int width = 30, height = 18;					// const width and height of board
	
	private final int width_literal = width * (Cell.width+1) + 1;		// pixel equivalent of width
	private final int height_literal = height * (Cell.width+1) + 1;		// pixel equivalent of height

	private static GameBoard instance;
	
	/**
	 * Singleton instance method
	 * @return singleton instance of GameBoardd
	 */
	public static GameBoard instance() {
		if(instance == null) {
			instance = new GameBoard();
		}
		return instance;
	}
	
	/**
	 * Private constructor 
	 * only accessed by instance method
	 * @see instance()
	 */
	private GameBoard() {
		gobjects = new CopyOnWriteArrayList<GameObject>();
		scoreBoard = new ScoreBoard();
		levelCount = new LevelCount();
		restartBtn = new RestartButton();
		exitBtn = new ExitButton();
		timeElapsed = new TimeElapsed();
		
		cells = new ArrayList<Cell>();
		for(int i = 0; i < height*width; ++i) {
			cells.add( new Cell(i - width * (int)Math.floor(i/width), (int)Math.floor(i/width)) );	
		}														// initializing cells linearly and storing  
	}

	/**
	 * Return cell with specified x and y
	 * @param x and y coordinates of cell
	 * @return Cell specified by x and y
	 */
	public Cell getCell(int x, int y) {							// return cell with specified x and y
		if(x>width || x<0 || y>height || y<0) return null;
		return cells.get(y*width + x);
	}
	
	/**
	 * Remove specified game object
	 * @param g, game object to be removed
	 * @return void
	 */
	public void removeObject(GameObject g) {
		g.clearHome();
		gobjects.remove(g);
	}
	
	/**
	 * Set cell contents
	 * @param g GameObject to be content
	 * @param x and y are cell coordinates
	 * @return void
	 */
	public void setCell(GameObject g, int x, int y) {		// clean cell and set content as @param g
		if(x>width || x<0 || y>height || y<0) return;
		if(g == null) return;
		
		g.clearHome();
		gobjects.add(g);
		if(g instanceof Movable)
			getCell(x,y).setContent(g, true);				// use overloaded setContent() for Movable objects
		else
			getCell(x,y).setContent(g);
	}
	
	/**
	 * Overloaded setCell using cell instead of coordinates
	 * @param g, game object to be content
	 * @param c, cell destination
	 * @return void
	 */
	public void setCell(GameObject g, Cell c) {				// overloaded method for Cell @param c
		if(g == null) return;
		g.clearHome();
		
		if(g instanceof Movable) 
			c.setContent(g, true);
		else
			c.setContent(g);
	}
	
	/**
	 * Updates all game objects by calling 
	 * update() on all of them
	 * @return void
	 */
	public void update() {									// Update all game objects 
		for(GameObject gobj : gobjects) {
			gobj.update();
		}
	}
	
	/**
	 * Renders UI elements, cells, and 
	 * game objects
	 * @param Graphics2D g
	 * @return void
	 */
	public void render(Graphics2D g) {
		g.setColor(Color.black);							// set board background
		g.fillRect(Cell.x_offset-1, Cell.y_offset-1, width_literal, height_literal);
		for(Cell c : cells) {
			c.render(g);									// render each cell
		}
		scoreBoard.render(g);
		levelCount.render(g);
		restartBtn.render(g);
		exitBtn.render(g);
		timeElapsed.render(g);
		
		for(int i = gobjects.size()-1; i >= 0; --i) {					
		gobjects.get(i).render(g);							// render each game object
		}
	}
	
	/**
	 * Processes click in 
	 * @param MouseEvent e, RunningState state
	 * @return void
	 */
	public void processClick(MouseEvent e, RunningState state) {
		restartBtn.processClick(e, state);
		exitBtn.processClick(e, state);
	}
	
	/**
	 * Get score from scoreboard 
	 * @see scoreBoard.getScore()
	 * @return score
	 */
	public int getScore() {								// get score
		return scoreBoard.getScore();
	}
	
	/**
	 * Set score 
	 * @see ScoreBoard.setScore()
	 * @return void
	 */
	public void setScore(int s) {							// set score to @param s
		scoreBoard.setScore(s);
	}
	
	/**
	 * Increase score
	 * @return void
	 */
	public void increaseScore(int s) {						// increase score by @param s
		scoreBoard.increaseScore(s);
	}
	
	/**
	 * Decrease score
	 * @return void
	 */
	public void decreaseScore(int s) {						// decrease score by @param s
		scoreBoard.decreaseScore(s);
	}
	
	/**
	 * Returns health from scoreboard
	 * @see ScoreBoard.getHealth()
	 * @return health
	 */
	public long getTimeElapsed() {
		return timeElapsed.timeElapsed;
	}
	
	/**
	 * Returns health from scoreboard
	 * @see ScoreBoard.getHealth()
	 * @return health
	 */
	public int getHealth() {						
		return scoreBoard.getHealth();
	}
	
	/**
	 * Set health 
	 * @param int h
	 * @see ScoreBoard.setHealth()
	 * @return void
	 */
	public void setHealth(int h) {
		scoreBoard.setHealth(h);
	}
	
	/**
	 * Increase health
	 * @return void
	 */
	public void increaseHealth(int x) {
		scoreBoard.increaseHealth(x);
	}
	
	/**
	 * Decrease health
	 * @return void
	 */
	public void decreaseHealth(int s) {						// decrease score by @param s
		scoreBoard.decreaseHealth(s);
	}
	
	
	/**
	 * Returns reward count 
	 * @return int
	 */
	public int getRewardCount() {
		return scoreBoard.getRewardCount();
	}
	
	/**
	 * Sets reward count to allow
	 * @see EndCell
	 * @return void
	 */
	public void setTotalRewardCount(int x) {
		scoreBoard.setTotalRewardCount(x);
		scoreBoard.resetRewardCount();
	}
	
	/**
	 * Increases reward count
	 * @see EndCell
	 * @return void
	 */
	public void increaseCurrentRewardCount() {scoreBoard.increaseCurrentRewardCount();}
	
	/**
	 * Check if all rewards collected
	 * @see EndCell
	 * @return true if all rewards collected
	 */
	public boolean levelClear() {return scoreBoard.levelClear();}
	
	
	/**
	 * Clear all GameObjects 
	 * @see GameObject.delete()
	 * @return void
	 */
	public void clearBoard() {
		for(GameObject g : gobjects) {
			g.delete();
		}
	}
	
	/**
	 * Reset score
	 * @see ScoreBoard.reset()
	 * @return void
	 */
	public void resetScoreboard() {
		scoreBoard.reset();
	}
	
	/**
	 * Reset timeElapsed
	 * @return void
	 */
	public void resetTimeElapsed() {
		timeElapsed.reset();
	}
	
}
