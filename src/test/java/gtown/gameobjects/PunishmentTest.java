package gtown.gameobjects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class PunishmentTest {

	private GameBoard gameBoard;
	private Punishment punishment;
	private Enemy enemy;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameEngine.DEBUG = true;
		RunningState.instance();
	}
	
	@Before
	public void setUp() throws Exception {
		gameBoard = GameBoard.instance();
		punishment = new Punishment();
		enemy = new Enemy();
	}

	@After
	public void tearDown() throws Exception {
		punishment = null;
		gameBoard = null;
		enemy = null;
	}
	
	
	/**
	 * Player collision test
	 * Expectation: Player health is decreased by 20
	 */
	@Test
	public void playerCollisionTest() {
		int initialHealth = gameBoard.getHealth();

		punishment.collision(MainCharacter.instance());
		
		int newHealth = gameBoard.getHealth();

		assertEquals(initialHealth-20, newHealth);
			
	}
	
	/**
	 * Enemy collision test
	 * Expectation: nothing happens to health
	 */
	@Test
	public void enemyCollisionTest() {
		int initialHealth = gameBoard.getHealth();

		punishment.collision(enemy);
		
		int newHealth = gameBoard.getHealth();

		assertEquals(initialHealth, newHealth);
	}
}
