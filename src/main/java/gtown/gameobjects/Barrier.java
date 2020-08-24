package gtown.gameobjects;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gtown.helper.SpriteLoader;

/**
 * Game Object that acts like walls 
 * Moving Game Objects cannot go through barriers
 */
public class Barrier extends GameObject{
	
	private ArrayList<BufferedImage> tile;
	
	/**
   * The constructor updates the type field and 
   * loads the wall sprite into the tile field
   * @see helper.SpriteLoader.Tile
   */
	public Barrier() {
		super();
		this.type = Type.Barrier;
		this.tile = SpriteLoader.Tile.wall;
	}
	
	/**
   * Generic GameObject update method 
   * Barrier does not have any fields to update 
   * @return Nothing.
   */
	public void update() {
		
	}
	
	/**
   * Generic GameObject render function
   * @param g is used to render the tile sprite
   * @see tile
   * @return Nothing.
   */
	public void render(Graphics2D g) {
		g.drawImage(tile.get(0), x, y, null);
	}

	/**
   * Generic GameObject collision function
   * barrier collision handled by Movable object
   * @param gobj is the GameObject colliding with barrier
   * @return Nothing.
   */
	public void collision(GameObject gobj) {		
		
	}

}
