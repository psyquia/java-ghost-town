package gtown.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import gtown.helper.BreadthFirstSearch;
import gtown.Cell;
import gtown.helper.DepthFirstSearch;
import gtown.GameBoard;
import gtown.GameEngine;
import gtown.helper.AudioPlayer;
import gtown.helper.SpriteLoader;

/**
 * Moving Enemy GameObject 
 * Implements Movable interface
 * Searches for player, deals large amount
 * of damage to player
 * Dies after colliding with player
 */
public class Enemy extends GameObject implements Movable{
	
	protected int dest_x, dest_y;
	private final int vel = 3;
	
	private GameBoard board;
	
	protected List<String> action;
	
	private Cell destination;
	private boolean arrived = true, hasTarget = false;
	
	/**
	 * Int arrays used for Depth First Search
	 */
	public List<Integer> visited;
	public List<Integer> path;
	public ArrayList<Integer> shortestPath;
	private int playerX,playerY;
	
	private ArrayList<BufferedImage> cloud;
	
	// animation speed (lower values => faster)
	private final int frameInterval = 10;
	
	// current sprite frame and counter
	private int frameCount = 0, frame = 0;							
	// total number of frames and max counter value
	private final int maxFrames = SpriteLoader.MEnemy.Frames, maxCount = maxFrames*frameInterval;	

	// health to deduct after collision
	public final static int collisionPunishment = 50;
	
	/**
	 * Constructor sets type, reference to GameBoard
	 * and initializes arrays for Enemy Search method
	 * @see Enemy_search()
	 * Loads cloud sprite with SpriteLoader
	 * @see SpriteLoader.MEnemy
	 */
	public Enemy() {
		super();
		this.type = Type.MovingEnemy;
		this.board = GameBoard.instance();
		this.visited = new ArrayList<Integer>();
		this.path = new ArrayList<Integer>();
		this.action = new ArrayList<String>();
		this.shortestPath = new ArrayList<Integer>();
		cloud = SpriteLoader.MEnemy.cloud;
	}
	
	/**
	 * Initializes Enemy by setting destination
	 * @see setDestination
	 * Sets arrived to true
	 * @return void
	 */
	public void init(Cell c) {											// called when setCell() is first called with *this*
		setDestination(c);
		arrived = true;
	}

	/**
	 * Checks if arrived at destination cell
	 * @see atDestination()
	 * Checks if enemy has target, if not, searches for player
	 * @see Enemy_search()
	 * Checks to see if player moved => then does another search
	 * @return void
	 */
	public void update() { //update drawing
		if(!hasTarget) {
			findPlayer(2); // 1 for dfs 2 for bfs
			x = home.getContentDest()[0]-1;
			hasTarget=true;
		}
		
		int[] playerLocation = MainCharacter.instance().getCoor();
		
		if(arrived && playerLocation[0] != playerX || playerLocation[1] != playerY) {
			findPlayer(2);
		}
		
		if(atDestination()) {
			arriveAtDestination();
		}
		
		updateSprite();
	}
	
	
	/**
	 * Render the enemy sprite
	 * @see cloud
	 * @param Graphics2D g
	 * @return void
	 */
	public void render(Graphics2D g) {									// draw MainCharacter based on facing direction
		g.drawImage(cloud.get(frame), x, y, null);
	}
	
	/**
	 * Checks if enemy is at destination
	 * If not, moves enemy toward destination
	 * @return true if at destination
	 */
	public boolean atDestination() {
		if(dest_x == x && dest_y == y) return true;
		
		if(Math.abs(dest_x-x) <= vel) x = dest_x;
		if(Math.abs(dest_y-y) <= vel) y = dest_y;
		
		if(x == -1) {
			x = dest_x;
			y = dest_y;
		}
		if(y < dest_y) y+=vel; else if (y > dest_y) y-=vel;
		if(x < dest_x) x+=vel; else if (x > dest_x) x-=vel;
		
		if(dest_x == x && dest_y == y) return true;
		return false;
	}
	
	
	/**
	 * Checks if enemy can move to specified cell
	 * @param c is the cell to be checked
	 * @return true if can move to @param c
	 */
	public boolean canMoveTo(Cell c) {
		if(c == null) return false;    		 //originally false, change to true for testing
		GameObject content = c.getStaticContent();
		if(content != null) {
			if(content.type == Type.Barrier) return false;
		}
		return true;
	}

	/**
	 * Sets destination cell of enemy
	 * @param c is the destination cell
	 * @return void
	 */
	public void setDestination(Cell c) {
		if(c == null) return;
		int[] coor = c.getContentDest();
		dest_x = coor[0];
		dest_y = coor[1];
		destination = c;
		arrived = false;
	}
	
	/**
	 * Handles arrive at destination event
	 * Sets cell as destination 
	 * @see GameBoard.setCell
	 * and collides with destination's 
	 * content
	 * Initiates new move for enemy
	 * @return void
	 */
	public void arriveAtDestination() {
		arrived = true;
		GameObject collidee = destination.getMovingContent();
		if(collidee != null && collidee.type == Type.MainCharacter) {
			collision(collidee);
		}else {
			GameBoard.instance().setCell(this,destination);
		}
		
		move();
	}
	
	/**
	 * Initiates move based on Enemy_search results
	 * Gets first move from action array
	 * @see action
	 * @return void
	 */
	public void move() {
		if(home == null) return;

		if(action.size() == 0) return;
		
		if(dest_x != x || dest_y != y) return;
		
		int newx = home.x;
		int newy = home.y;
		String toDo = action.remove(0);
        
        if(toDo == "moveLeft") {
			--newx;
		}
        else if(toDo == "moveRight") {
			++newx;
		}
        else if(toDo == "moveDown") {
			++newy;
		}
        else if(toDo == "moveUp") {
			--newy;
		}
        Cell newCell = board.getCell(newx, newy);
        if(canMoveTo(newCell))
			setDestination(newCell);
	}
	
	/**
	 * Uses Depth first search to find 
	 * path to player
	 * Fills action array with strings
	 * specifying moves to do
	 * @see action
	 * @return void
	 */
	public void findPlayer(int choice){

		if(home == null) {
			return;
		}
		playerX = MainCharacter.instance().home.x ;
		playerY = MainCharacter.instance().home.y ;
		
		int startx = home.x;
		int starty = home.y;
		
		path = new ArrayList<Integer>();
		visited = new ArrayList<Integer>();


	   	 if(choice == 2) {
	   		shortestPath = new ArrayList<Integer>();
	
	   		BreadthFirstSearch.breadthFirstSearch(this, startx, starty, playerX, playerY);
	    	action = new ArrayList<String>();  //list contain string actions
	    	int tempx;
	    	int tempy;

	    	for(int i = 0; i<shortestPath.size()-2; i+=2){
	    		tempy = shortestPath.get(i+1);
	    		tempx = shortestPath.get(i);
	
	    		if(tempx<shortestPath.get(i+2)){
	    			action.add("moveRight");
	    			
	    		}
	    		if(tempx>shortestPath.get(i+2)){
	    			action.add("moveLeft");
	    			
	    		}
	    		if(tempy<shortestPath.get(i+3)){
	    			action.add("moveDown");
	    			
	    		}
	    		if(tempy>shortestPath.get(i+3)){
	    			action.add("moveUp");
	    		}
	    	}
	   	}
	}

	/**
	 * Specifies collision with player
	 * Decreases player health by 50
	 * Deletes itself on collision
	 * @param gobj is collidee
	 * @return void
	 */
	public void collision(GameObject gobj) {
		if(gobj.type == Type.MainCharacter) {
			GameBoard.instance().decreaseHealth(collisionPunishment);
			this.delete();
			playJingle();
		}
	}
	
	private void updateSprite() {
		if(frameCount >= maxCount-1)
			frameCount = 0;
		else
			frameCount++;
		frame = frameCount/frameInterval;
	}
	
	private void playJingle() {
		// Play jingle @params are Clip, Volume, and FilePath
		AudioPlayer.playOnce(1d, GameEngine.ASSETS_PATH + "/sfx/hit.wav");
	}

}



