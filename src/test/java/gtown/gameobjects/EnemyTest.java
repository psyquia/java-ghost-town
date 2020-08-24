package gtown.gameobjects;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class EnemyTest {
	private MainCharacter player;
	private Enemy enemy;
	private RunningState running;
	private GameBoard board;

	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Before
	public void setUp() throws Exception {
		running = RunningState.instance();
		board = GameBoard.instance();
		player = MainCharacter.instance();
		enemy = new Enemy();
		
		running.setLevel(0);
		
		running.addObject(enemy,0,0);
		running.addObject(player,10,0);
	}

	@After
	public void tearDown() throws Exception {
		running = null;
		board = null;
		player = null;
		enemy = null;
	}
	
	/**
    * Tests if path calculated by findPlayer will correctly 
    * lead the enemy to the player if no walls are inbetween
    * @see checkPath() 
    * Expectation: final position is the player location
    */
	@Test
	public void findPlayerTest_noWalls() {
		checkPath();
	}
	
	/**
    * Tests if path calculated by findPlayer will correctly 
    * lead the enemy to the player if only one path leads to
    * the player
    * @see checkPath() 
    * Expectation: final position is the player location
    */
	@Test
	public void findPlayerTest_onlyOnePath() {
		for(int i = 0; i < 29; ++i) {
			running.addObject(new Barrier(), i, 1);
		}
		for(int i = 1; i < 17; ++i) {
			running.addObject(new Barrier(), 28, i);
		}
		checkPath();
	}
	
	/**
    * Tests if update changes the action array in response
    * to player movement
    * Expectation: initial action array and final action array are not equal
    */
	@Test
	public void updateTest() {
		// intially find player and set action arraylist
		enemy.findPlayer(2);
		
		// Initial action array
		List<String> before = enemy.action;
		
		// Simulating the player moving up one cell
		GameBoard.instance().setCell(player, player.home.x, player.home.y-1);
		
		// Call update and check if enemy.action changes
		enemy.update();
		
		// Action array after player move
		List<String> after = enemy.action;

		assertNotEquals(before, after);
	}
	
   /**
    * Tests if collision properly removes health
    * on player collision
    */
	@Test
	public void collisionTest() {
		// Case 1: health > 50
		// Expectation: Player health reduced by 50 
		board.setHealth(100);
		
		int initialHp = board.getHealth();
		
		enemy.collision(player);
		
		int finalHp = board.getHealth();
		
		assertEquals(initialHp-50, finalHp);
		
		// Case 2: health < 50 and health > 0
		// Expectation: Player dies, level resets => health = 100
		board.setHealth(30);
		
		initialHp = board.getHealth();

		enemy.collision(player);
		
		finalHp = board.getHealth();
		
		// Health is reset to 100 
		assertEquals(100, finalHp);
	}
	
	/**
	* Tests if move() properly removes the first 
	* action from the action arraylist
	* Expectation: the action array size decreases by exactly one
	* after a move
   	*/
	@Test
	public void moveTest() {
		enemy.findPlayer(2);
		
		int before = enemy.action.size();
		
		enemy.move();
		
		int after = enemy.action.size();
		
		assertEquals(before-1, after);
	}
	
	/**
	* Simulates enemy traversal following the action array 
   	* created in the findPlayer() method
   	*/
	public void checkPath() {
		
		int startX = enemy.home.x;
		int startY = enemy.home.y;
		
		int destX = player.home.x;
		int destY = player.home.y;
		
		
		enemy.findPlayer(2);
		
		
		int endX = startX;
		int endY = startY;
		
		for(String step : enemy.action) {
			if(step == "moveLeft") {
				--endX;
			}
	        else if(step == "moveRight") {
				++endX;
			}
	        else if(step == "moveDown") {
				++endY;
			}
	        else if(step == "moveUp") {
				--endY;
			}
		}

		assertEquals(endX, destX);
		assertEquals(endY, destY);
	}
}
