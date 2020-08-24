package gtown.gameobjects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class BonusRewardTest {

	private BonusReward bonusReward;
	private Enemy enemy;
	private GameBoard gameBoard;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameEngine.DEBUG = true;
		RunningState.instance();
	}

	@Before
	public void setUp() throws Exception {
		gameBoard = GameBoard.instance();
		bonusReward = new BonusReward();
	}

	@After
	public void tearDown() throws Exception {
		bonusReward = null;
		gameBoard = null;
	}
	
	/** 
	 * Update function test
	 * Expectation: ttl and mod is updated 
	 */
	@Test
	public void updateTest() {
		double delta = 0.001;
		double initialTtl = bonusReward.getTtl();
		double initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		double newTtl = bonusReward.getTtl();
		double newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod, newMod, delta);
		
		// test ttl at 75%
		bonusReward.setTtl(751);
		initialTtl = bonusReward.getTtl();
		bonusReward.setMod(100);
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod/2, newMod, delta);
		
		// test ttl between 74% and 51%
		initialTtl = bonusReward.getTtl();
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod, newMod, delta);
		
		// test ttl at 50%
		bonusReward.setTtl(501);
		initialTtl = bonusReward.getTtl();
		bonusReward.setMod(100);
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod/2, newMod, delta);
		
		// test ttl between 49% and 26%
		initialTtl = bonusReward.getTtl();
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod, newMod, delta);
		
		// test ttl at 25%
		bonusReward.setTtl(251);
		initialTtl = bonusReward.getTtl();
		bonusReward.setMod(100);
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod/2, newMod, delta);
		
		// test ttl between 24% and 1%
		initialTtl = bonusReward.getTtl();
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod, newMod, delta);
		
		// test ttl at 75%, mod < 10
		bonusReward.setTtl(751);
		initialTtl = bonusReward.getTtl();
		bonusReward.setMod(9);
		initialMod = bonusReward.getMod();
		
		bonusReward.update();
		
		newTtl = bonusReward.getTtl();
		newMod = bonusReward.getMod();
		
		assertEquals(initialTtl, newTtl+1, delta);
		assertEquals(initialMod, newMod, delta);
		
	}
	
  /**
   * MainCharacter collision test
   * Expectation: score increases by 100 and health increases by 50 but not exceeding max health
   */
	@Test
	public void playerTest() {
		int initialScore = gameBoard.getScore();
		int initialHealth = gameBoard.getHealth();

		bonusReward.collision(MainCharacter.instance());
		
		int newScore = gameBoard.getScore();
		int newHealth = gameBoard.getHealth();

		assertEquals(initialScore+100, newScore);
		assertEquals(initialHealth, newHealth);
		
		gameBoard.decreaseHealth(30);
		bonusReward.collision(MainCharacter.instance());
		newHealth = gameBoard.getHealth();
		assertEquals(initialHealth, newHealth);
		
		gameBoard.decreaseHealth(50);
		bonusReward.collision(MainCharacter.instance());
		newHealth = gameBoard.getHealth();
		assertEquals(initialHealth, newHealth);
		
		gameBoard.decreaseHealth(80);
		bonusReward.collision(MainCharacter.instance());
		newHealth = gameBoard.getHealth();
		assertEquals(initialHealth-30, newHealth);
		
	}
	
  /**
   * Enemy collision test
   * Expectation: nothing happens to score and health
   */
	@Test
	public void enemyTest() {
		int initialScore = gameBoard.getScore();
		int initialHealth = gameBoard.getHealth();
		enemy = new Enemy();

		bonusReward.collision(enemy);
		
		int newScore = gameBoard.getScore();
		int newHealth = gameBoard.getHealth();

		assertEquals(initialScore, newScore);
		assertEquals(initialHealth, newHealth);
	}

}
