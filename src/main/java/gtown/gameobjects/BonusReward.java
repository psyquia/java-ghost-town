package gtown.gameobjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.helper.AudioPlayer;
import gtown.helper.SpriteLoader;

/**
 * Reward Game Object that gives a large amount of points  
 * and gives the player health
 * BonusReward also disappears after a while @see ttl
 */
public class BonusReward extends GameObject{
	
	/**
	 * ttl = "time to live" in game 'ticks', BonusReward disappears 
	 * when ttl=0 mod is used to increase the speed at which 
	 * BonusReward is blinking
	 */
	private double max_ttl = 1000, mod=100, ttl;
	
	private BufferedImage syringe;

	/**
	 * Constructor sets type, initializes ttl and loads 
	 * syringe sprite into syringe @see SpriteLoader.Reward.syringe
	 */
	public BonusReward() {
		super();
		this.type = Type.BonusReward;
		this.ttl = max_ttl;
		this.syringe = SpriteLoader.Reward.syringe;
	}
	
	/**
	 * Getter for mod
	 * @return mod
	 */
	public double getMod() {return mod;}
	/**
	 * Setter for mod
	 * @param x The new value of mod
	 */
	public void setMod(double x) {mod = x;}
	
	/**
	 * Getter for ttl
	 * @return ttl
	 */
	public double getTtl() {return ttl;}
	/**
	 * Setter for ttl
	 * @param x The new value of ttl
	 */
	public void setTtl(double x) {ttl = x;}
	
	/**
	 * ttl decremented every tick, mod is halved when ttl is 75%, 50%,
	 * 25% to increase 'blinking' speed
	 * When ttl is zero, BonusReward is deleted @see GameObject.delete()
	 */
	public void update() {
		--ttl;
		if(ttl % (max_ttl/4) == 0 && mod > 10) {
			mod/=2;
		}
		if(ttl <= 0) {
			this.delete();
		}
	}
	
	/**
   * Overloaded GameObject render function
   * @param g is used to render the tile sprite
   * @see tile
   * @return Nothing.
   */
	public void render(Graphics2D g) {
		if((ttl % mod) > mod/2) {
			g.drawImage(syringe, x, y, null);
		}
	}

	/**
   * Overloaded GameObject collision function
   * @param gobj is the GameObject collidee
   * if @param gobj is type MainCharacter, score 
   * and health of player is increased
   * In addition, a jingle is played @see playJingle()
   * @return Nothing.
   */
	public void collision(GameObject gobj) {
		if(gobj.type == Type.MainCharacter) {						// handle collision with MainCharacter
			GameBoard.instance().increaseScore(100);
			GameBoard.instance().increaseHealth(50);
			playJingle();
			this.delete();
		}
	}
	
	/**
	* Uses helper audio player to play sfx from specified path
	* @see helper.AudioPlayer
	* @return Nothing.
    */
	private void playJingle() {
		// Play jingle @params are Clip, Volume, and FilePath
		AudioPlayer.playOnce(0.5d, GameEngine.ASSETS_PATH + "/sfx/bonusreward.wav");
	}

}
