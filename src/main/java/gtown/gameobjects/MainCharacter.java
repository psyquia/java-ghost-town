package gtown.gameobjects;

import static gtown.Direction.LEFT;
import static gtown.Direction.RIGHT;
import static gtown.Direction.UP;
import static gtown.Direction.DOWN;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gtown.Cell;
import gtown.Direction;
import gtown.GameBoard;
import gtown.helper.SpriteLoader;

public class MainCharacter extends GameObject implements Movable{
	
	private ArrayList<BufferedImage> leftSprites;		// array for leftSprites
	private ArrayList<BufferedImage> rightSprites;		// array for rightSprites
	private ArrayList<BufferedImage> downSprites;		// array for downSprites
	private ArrayList<BufferedImage> upSprites;			// array for upSprites
	
	
	protected int frameCount = 0;							// current sprite frame
	
	protected final int maxFrames = SpriteLoader.Doctor.Frames;	// total number of frames
		
	protected int dest_x, dest_y;
	private final int vel = 4;
	
	public boolean movingRight = false;
	public boolean movingLeft = false;
	public boolean movingUp = false;
	public boolean movingDown = false;
	
	private Direction facing = RIGHT;
	
	protected Cell destination;
	protected boolean arrived = true;
	
	private static MainCharacter instance;
	
	public static MainCharacter instance() {
		if(instance == null) {
			instance = new MainCharacter();
		}
		return instance;
	}

	private MainCharacter() {
		super();
		
		this.type = Type.MainCharacter;
		
		leftSprites = SpriteLoader.Doctor.left;
		rightSprites = SpriteLoader.Doctor.right;
		downSprites = SpriteLoader.Doctor.down;
		upSprites = SpriteLoader.Doctor.up;
		
	}
	
	public void init(Cell c) {											// called when setCell() is first called with *this*
		setDestination(c);
		arrived = true;
	}

	public void update() {
		if(atDestination())
			arriveAtDestination();										// checks if arrived at destination then calls arrive function
		
		if(isMoving()) {												// if the player is moving
			if(arrived) {												// can only move again if arrived at prev destination 
				move();
				if(arrived) frameCount = 4;
				else if(frameCount >= maxFrames-2) {					// reset frame to first
					frameCount = 0;
				}else if(frameCount<3)frameCount++;
			}
		}else {
			frameCount = 0;
		}
	}
	
	public void render(Graphics2D g) {									// draw MainCharacter based on facing direction
		g.setColor(Color.green);
		if(facing == LEFT)
			g.drawImage(leftSprites.get(frameCount), x, y, null);
		else if(facing == RIGHT)
			g.drawImage(rightSprites.get(frameCount), x, y, null);
		else if(facing == DOWN)
			g.drawImage(downSprites.get(frameCount), x, y, null);
		else if(facing == UP)
			g.drawImage(upSprites.get(frameCount), x, y, null);
	}
	
	public boolean atDestination() {									// checks if at destination; if not => move to destination
		if(dest_x == x && dest_y == y) return false;
		
		if(Math.abs(dest_x-x) <= vel) x = dest_x;						// instantly moves to destination of distance < velocity
		if(Math.abs(dest_y-y) <= vel) y = dest_y;
		
		
		if(y < dest_y) y+=vel; else if (y > dest_y) y-=vel;
		if(x < dest_x) x+=vel; else if (x > dest_x) x-=vel;
		
		if(dest_x == x && dest_y == y) return true;						// only return true if haven't arrived before
		return false;
	}
	
	/**
	 * Helper function to determine if player is moving
	 * @return boolean 
	 */
	private boolean isMoving() {
		if(movingUp || movingDown || movingRight || movingLeft) return true;
		return false;
	}
	
	public void move() {									// moves MainCharacter 
		int newx = home.x;												// calculates newx and new y based on inputs held
		int newy = home.y;												// moves to Cell with newx and newy
		if(movingRight) {
			newx++;
			facing = RIGHT;
		}else if(movingLeft) {
			newx--;
			facing = LEFT;
		}
		else if(movingUp) {
			newy--;
			facing = UP;
			
		}else if (movingDown) {
			newy++;
			facing = DOWN;
		}
		
		Cell newCell = GameBoard.instance().getCell(newx, newy);
		if(canMoveTo(newCell))
			setDestination(newCell);
	}
	
	public boolean canMoveTo(Cell c) {									// checks if @param c exists and/or contains barrier
		if(c == null) return false;
		GameObject content = c.getStaticContent();
		if(content != null) {
			if(content.type == Type.Barrier) return false;
			else if (content.type == Type.EndCell && !GameBoard.instance().levelClear()) return false;
		}
		return true;
	}
	
	public void setDestination(Cell c) {								// changes initializes new destination to @param c
		if(c == null) return;
		int[] coor = c.getContentDest();
		dest_x = coor[0];
		dest_y = coor[1];
		destination = c;
		arrived = false;
	}
	
	public void arriveAtDestination() {					// collides with destination's content and 
		if(destination == null) return;									// calls setCell() on destination
		GameObject collidee = destination.getStaticContent();
		GameObject colMoving = destination.getMovingContent();
		if(collidee != null) collidee.collision(this);
		if(colMoving != null) colMoving.collision(this);
		GameBoard.instance().setCell(this,destination);
		arrived = true;
	}
	
	public int[] getCoor() {
		return new int[] {home.x,home.y};
	}

	public void collision(GameObject gobj) {
		
	}


}
