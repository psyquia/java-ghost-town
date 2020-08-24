package gtown.gameobjects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.Cell;
import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class MainCharacterTest {
	private MainCharacter player;
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
		
		running.setLevel(0);
		
		running.addObject(player,15,15);
	}

	@After
	public void tearDown() throws Exception {
		running = null;
		player = null;
		board = null;
	}

	/**
    * Tests move() correctly sets the player destination
    * to the right cell based on the moving boolean values
    * Expectation: destination corresponds to moving... boolean
    */
	@Test
	public void moveTest() {
		// Move left test
		Cell initialCell = player.home;
		
		moveLeft();
		player.move();
		
		Cell leftCell = player.destination;
		
		assertEquals(initialCell.x - 1, leftCell.x);
		
		// Move right test
		initialCell = player.home;
		
		moveRight();
		player.move();
		
		Cell rightCell = player.destination;
		
		assertEquals(initialCell.x + 1, rightCell.x);
		
		// Move up test
		initialCell = player.home;
		
		moveUp();
		player.move();
		
		Cell upCell = player.destination;
		
		assertEquals(initialCell.y - 1, upCell.y);
		
		// Move down test
		initialCell = player.home;
		
		moveDown();
		player.move();
		
		Cell downCell = player.destination;
		
		assertEquals(initialCell.y + 1, downCell.y);
	}
	
	/**
    * if canMoveTo() properly returns true when a cell does not contain
    * a barrier and false if it does 
    * NOTE: canMoveTo() is and should only be used on adjacent cells
    * Expectation: return false if cell contains barrier, true otherwise
    */
	@Test
	public void canMoveToTest() {
		// Case 1: no barrier 
		Cell leftCell = board.getCell(player.home.x-1, player.home.y);
		
		boolean noBarrier = player.canMoveTo(leftCell);
		
		assertTrue(noBarrier);
		
		// Case 2: cell with barrier
		
		// Add barrier to the right of the player
		running.addObject(new Barrier(), player.home.x+1, player.home.y);
		
		Cell rightCell = board.getCell(player.home.x+1, player.home.y);
		
		boolean withBarrier = player.canMoveTo(rightCell);
		
		assertFalse(withBarrier);
	}
	
	/**
    * Tests that setDestination properly sets properties: destination, 
    * dest_x, dest_y, and arrived in MainCharacter
    * Expectation:  destination is the cell passed to setDestination(),
    * 				dest_x and dest_y are the corresponding pixel coordinates,
    * 				arrived is equal to false
    */
	@Test
	public void setDestinationTest() {
		// Get soon-to-be destination cell
		Cell dest = board.getCell(10, 10);
		
		player.setDestination(dest);
		
		assertEquals(dest, player.destination);
		
		assertEquals(Cell.x_offset + 10*(Cell.width+1), player.dest_x);
		
		assertEquals(Cell.y_offset + 10*(Cell.width+1), player.dest_y);
		
		assertFalse(player.arrived);
	}
	
	/**
    * Tests that arriveAtDestination() properly sets the current destination
    * as the MainCharacter home and sets arrived to true
    */
	@Test
	public void arriveAtDestinationTest() {
		Cell dest = board.getCell(player.home.x+1, player.home.y);
		
		player.setDestination(dest);
		
		player.arriveAtDestination();
		
		assertEquals(dest, player.home);
		
		assertTrue(player.arrived);
	}
	
	/**
    * Tests that atDestination() moves player toward destination 
    * and if player moved to destination, it returns true
    * Expectation: new position is closer to destination that initial position
    * and if moved to destination, return true
    */
	@Test
	public void atDestinationTest() {
		// Test that player is moving closer
		Cell dest = board.getCell(player.home.x+1, player.home.y+1);
		
		player.setDestination(dest);
		
		int initial_x = player.x;
		int initial_y = player.y;
		
		player.atDestination();
		
		int new_x = player.x;
		int new_y = player.y;
		
		int initial_x_diff = Math.abs(initial_x - player.dest_x);
		int new_x_diff = Math.abs(new_x - player.dest_x);
		
		int initial_y_diff = Math.abs(initial_y - player.dest_y);
		int new_y_diff = Math.abs(new_y - player.dest_y);
		
		assertTrue(new_x_diff < initial_x_diff);
		assertTrue(new_y_diff < initial_y_diff);
		
		
		// Test that returns true when moved to destination
		
		// Place player very close to destination
		player.x = player.dest_x-1;
		player.y = player.dest_y-1;
		
		// Let atDestination() move player to destination
		assertTrue( player.atDestination() );
	}
	
	/**
    * Tests that frame is properly updated and reset when max frame is reached
    * Expectation: update increases frame by one or resets
    */
	@Test
	public void updateTest() {
		moveRight();
		int initialFrame = player.frameCount; 
		int no_of_frames = player.maxFrames-1;
		
		// When player has arrived at destination and update is called, frameCount 
		// is increased by one or reset when at the max frame
		for(int i = 0; i < no_of_frames+1; ++i) {
			assertEquals( (initialFrame + i) % no_of_frames, player.frameCount);
			player.arrived = true;
			player.update();
		}
	}
	
	
	/**
    * HELPER FUNCTIONS
    * used in moveTest()
    */
	private void moveLeft() {
		player.movingLeft = true;
		player.movingRight = false;
		player.movingUp = false;
		player.movingDown= false;
	}
	private void moveRight() {
		player.movingLeft = false;
		player.movingRight = true;
		player.movingUp = false;
		player.movingDown= false;
	}
	private void moveUp() {
		player.movingLeft = false;
		player.movingRight = false;
		player.movingUp = true;
		player.movingDown= false;
	}
	private void moveDown() {
		player.movingLeft = false;
		player.movingRight = false;
		player.movingUp = false;
		player.movingDown= true;
	}

}
