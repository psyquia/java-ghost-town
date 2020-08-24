package gtown;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gtown.gameobjects.GameObject;
import gtown.gameobjects.Movable;
import gtown.helper.SpriteLoader;

public class Cell {
	// defines left and top margin of GameBoard
	public static final int x_offset = 10;									
	public static final int y_offset = 40;
	
	// width = height (square cells)
	public static final int width = 32;									
	
	public GameObject movingContent;
	public GameObject staticContent;
	public int x;												// x value in GameBoard
	public int y;												// y value in GameBoard
	
	private ArrayList<BufferedImage> tile;
	private int variant;
	
	/**
	 * Constructor for Cell
	 * @param x and y stored as this.x and this.y
	 * tile sprite is loaded @see SpriteLoader.Tile
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.tile = SpriteLoader.Tile.floor;
		this.variant = (new Random()).nextInt(100) % 2;
	}
	
	/**
	 * Set content of cell 
	 * @param con set as staticContent
	 * Home of @param con is set
	 * @return void
	 */
	public void setContent(GameObject con) {						// set content as @param con
		if(con == null) return;
		con.x = x_offset + x*(width+1);
		con.y = y_offset + y*(width+1);
		staticContent = con;
		con.home = this;
	}
	
	/**
	 * Overloaded setContent for Movable objects
	 * @see Movable
	 * @param con set as movingContent 
	 * Home of @param con is set
	 * @return void
	 */
	public void setContent(GameObject con, boolean movable) {		// emulating @param movable with default value = false
		if(movable) { 												// OVERLOAD setContent() for Movable objects that need to call init()
			if(con.home == null) ((Movable) con).init(this);
			con.x = x_offset + x*(width+1);
			con.y = y_offset + y*(width+1);
			movingContent = con;
			con.home = this;
		}
	}
	
	/**
	 * Returns x and y in GameBoard context
	 * @return array containing x and y values 
	 * of cell (emulating 'tuple')
	 */
	public int[] getContentDest() {									
		return new int[] {x_offset + x*(width+1), y_offset + y*(width+1)};
	}
	
	/**
	 * @return cell's movingContent
	 */
	public GameObject getMovingContent() {								
		return movingContent;
	}
	
	/**
	 * @return cell's staticContent
	 */
	public GameObject getStaticContent() {								// returns Cell content
		return staticContent;
	}
	
	/**
	 * Render tile sprite of cell
	 * @param g, Graphics2D
	 * @return void
	 */
	public void render(Graphics2D g) {								// draws cell
		g.drawImage(tile.get(variant), x_offset + x*(width+1), y_offset + y*(width+1), null);
	}
	
	/**
	 * Clears all content of cell
	 * @return void
	 */
	public void clearCell() {										// clears content
		staticContent = null;
		movingContent = null;
	}
	
	/**
	 * Overloaded clearCell
	 * Only clears specified type of object
	 * (Movable or non Movable)
	 * @param old is the GameObject being
	 * removed
	 * @return void
	 */
	public void clearCell(GameObject old) {							// clears content
		if(old instanceof Movable) {
			movingContent = null;
		}else {
			staticContent = null;
		}
	}
	
	
}
