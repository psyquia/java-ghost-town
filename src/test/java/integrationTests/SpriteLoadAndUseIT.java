package integrationTests;

import java.awt.Graphics2D;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import gtown.Cell;
import gtown.GameEngine;
import gtown.gameobjects.Barrier;
import gtown.gameobjects.BonusReward;
import gtown.gameobjects.Enemy;
import gtown.gameobjects.MainCharacter;
import gtown.gameobjects.Punishment;
import gtown.gameobjects.RegularReward;
import gtown.helper.SpriteLoader;

public class SpriteLoadAndUseIT {
	private static boolean spritesLoaded = false;
	private static Graphics2D g;
	
	@BeforeClass
	public static void setUpGraphics() {
		GameEngine.DEBUG = true;
		g = Mockito.mock(Graphics2D.class);
	}

	@Before
	public void setUp() throws Exception {
		if(spritesLoaded) return;
		
		try {
			SpriteLoader.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		spritesLoaded = true;
	}

	/**
	 * Tests if MainCharacter sprites are properly loaded
	 */
	@Test
	public void mainCharacterSprites() {
		try {
			// Constructor sets player sprites
			MainCharacter.instance().render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if Enemy sprites are properly loaded
	 */
	@Test
	public void enemySprites() {
		try {
			// Constructor sets player sprites
			Enemy e = new Enemy();
			e.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if RegularReward sprite is properly loaded
	 */
	@Test
	public void rewardSprites() {
		try {
			// Constructor sets reward sprite
			RegularReward rr = new RegularReward();
			rr.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if BonusReward sprite is properly loaded
	 */
	@Test
	public void bnsRewardSprite() {
		try {
			// Constructor sets reward sprite
			BonusReward br = new BonusReward();
			br.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if Punishment sprites are properly loaded
	 */
	@Test
	public void punishmentSprites() {
		try {
			// Constructor sets punishment sprites
			// *punishment sprite alternates during creation of new punishment
			Punishment p1 = new Punishment();
			Punishment p2 = new Punishment();
			p1.render(g);
			p2.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if Barrier sprite is properly loaded
	 */
	@Test
	public void barrierSprite() {
		try {
			// Constructor sets barrier sprite
			Barrier b = new Barrier();
			b.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if Cell sprite is properly loaded
	 */
	@Test
	public void cellSprite() {
		try {
			// Constructor sets cell sprite
			Cell c = new Cell(0,0);
			c.render(g);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
