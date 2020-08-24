package gtown.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.helper.AudioPlayer;
import gtown.helper.SpriteLoader;

/**
 * Game Object that acts like regular rewards
 * Removed when main character walks onto it and increases the score
 */
public class RegularReward extends GameObject{

	private static int timesCollected=0;
	private BufferedImage facemask;

	/**
	   * The constructor updates the type field and 
	   * loads the face mask sprite into the tile field
	   * @see helper.SpriteLoader.Tile
	   */
	public RegularReward() {
		super();
		this.type = Type.RegularReward;
		this.facemask = SpriteLoader.Reward.facemask;
	}

	/**
	   * Generic GameObject update method 
	   * Regular reward does not have any fields to update 
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
		g.drawImage(facemask, x, y, null);
	}

	/**
	 *  Handle collision with MainCharacter
	 *  @param gobj The object entering the cell
	 */
	public void collision(GameObject gobj) {
		if(gobj.type == Type.MainCharacter) {						
			GameBoard.instance().increaseScore(20);
			GameBoard.instance().increaseCurrentRewardCount();
			
			// Jingle alternates between two sound effects
			playJingle(timesCollected % 2);
			timesCollected = (timesCollected == 9) ? 0 : timesCollected+1;
			this.delete();
		}
	}
	
	private void playJingle(int i) {
		AudioPlayer.playOnce(0.5d, GameEngine.ASSETS_PATH + "/sfx/reward" + i + ".wav");
	}

}
