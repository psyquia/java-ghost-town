package gtown.gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gtown.GameBoard;
import gtown.helper.SpriteLoader;
import gtown.state.RunningState;

/**
 * GameObject that allows player to reach next level
 * Starts in locked state and opens after all regular
 * rewards are collected
 */
public class EndCell extends GameObject{

	private final Color locked = Color.black;
	
	private BufferedImage exit;

	/**
	 * Constructer sets type and loads 'exit' sprite
	 * @see SpriteLoader.Tile
	 * @return void
	 */
	public EndCell() {
		super();
		this.type = Type.EndCell;
		this.exit = SpriteLoader.Tile.exit;
	}

	/**
	 * Generic GameObject update method
	 * State controller by GameBoard
	 * @see GameBoard.instance().levelClear()
	 * @return void
	 */
	public void update() {
		
	}
	
	/**
	 * Renders black tile if locked or exit 
	 * sprite if unlocked
	 * Exit unlocked when level is cleared by
	 * collecting all regular rewards
	 * @see GameBoard.levelClear()
	 * @return void
	 */
	public void render(Graphics2D g) {
		if (!GameBoard.instance().levelClear()) {
			g.setColor(locked);
			g.fillRect(x,y, 32, 32);
		} else {
			g.drawImage(exit,x,y,null);
		}
	}

	/**
	 * Overloaded GameObject collision method
	 * When colliding with player, go to next level
	 * @see RunningState.nextLevel()
	 * @return void
	 */
	public void collision(GameObject gobj) {
		if(gobj.type == Type.MainCharacter && GameBoard.instance().levelClear()) {						// handle collision with MainCharacter
			RunningState.instance().nextLevel();
		}
		
	}

}
