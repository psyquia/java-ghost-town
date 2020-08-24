package integrationTests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.Cell;
import gtown.GameEngine;
import gtown.gameobjects.MainCharacter;
import gtown.state.RunningState;

public class MovementIT {
	private GameEngine engine;
	private MainCharacter player;
	private RunningState running;
	private Cell initialCell;
	private Cell finalCell;

	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Before
	public void setUp() throws Exception {
		engine = GameEngine.instance();
		running = RunningState.instance();
		engine.state = running;
		player = MainCharacter.instance();
		
		running.setLevel(0);		
		running.addObject(player,15,15);
		initialCell = player.home;
	}

	@After
	public void tearDown() throws Exception {
		engine = null;
		running = null;
		player = null;
		initialCell = null;
		finalCell = null;
	}
	
	/**
	 * Tests the interaction between key press "W" and character movement
	 * Expectation: destination set to the cell above
	 */
	@Test
	public void upwardMovementTest() {
		

		KeyEvent key = new KeyEvent(engine, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
		engine.getKeyListeners()[0].keyPressed(key);
		running.update();
		engine.getKeyListeners()[0].keyReleased(key);
		
		for (int i = 0; i < 60; i++) {
			running.update();
		}
		
		finalCell = player.home;
		
		assertEquals(initialCell.x, finalCell.x);
		assertEquals(initialCell.y-1, finalCell.y);
		
	}

	/**
	 * Tests the interaction between key press "S" and character movement
	 * Expectation: destination set to the cell below
	 */
	@Test
	public void downwardMovementTest() {
		

		KeyEvent key = new KeyEvent(engine, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
		engine.getKeyListeners()[0].keyPressed(key);
		running.update();
		engine.getKeyListeners()[0].keyReleased(key);
		
		for (int i = 0; i < 60; i++) {
			running.update();
		}
		
		finalCell = player.home;
		
		assertEquals(initialCell.x, finalCell.x);
		assertEquals(initialCell.y+1, finalCell.y);
		
	}
	
	/**
	 * Tests the interaction between key press "A" and character movement
	 * Expectation: destination set to the cell to the right
	 */
	@Test
	public void rightMovementTest() {
		

		KeyEvent key = new KeyEvent(engine, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
		engine.getKeyListeners()[0].keyPressed(key);
		running.update();
		engine.getKeyListeners()[0].keyReleased(key);
		
		for (int i = 0; i < 60; i++) {
			running.update();
		}
		
		finalCell = player.home;
		
		assertEquals(initialCell.x-1, finalCell.x);
		assertEquals(initialCell.y, finalCell.y);
		
	}
	
	/**
	 * Tests the interaction between key press "D" and character movement
	 * Expectation: destination set to the cell to the left
	 */
	@Test
	public void leftMovementTest() {
		

		KeyEvent key = new KeyEvent(engine, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
		engine.getKeyListeners()[0].keyPressed(key);
		running.update();
		engine.getKeyListeners()[0].keyReleased(key);
		
		for (int i = 0; i < 60; i++) {
			running.update();
		}
		
		finalCell = player.home;
		
		assertEquals(initialCell.x+1, finalCell.x);
		assertEquals(initialCell.y, finalCell.y);
		
	}
	
}
