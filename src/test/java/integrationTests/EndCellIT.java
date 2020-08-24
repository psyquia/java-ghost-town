package integrationTests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.Cell;
import gtown.GameBoard;
import gtown.GameEngine;
import gtown.gameobjects.EndCell;
import gtown.gameobjects.MainCharacter;
import gtown.gameobjects.RegularReward;
import gtown.state.RunningState;
import gtown.state.WinState;

public class EndCellIT {

	private GameEngine engine;
	private MainCharacter player;
	private RunningState running;
	private GameBoard board;
	private EndCell endCell;
	
	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}

	@Before
	public void setUp() throws Exception {
		engine = GameEngine.instance();
		running = RunningState.instance();
		engine.state = running;
		board = GameBoard.instance();
		player = MainCharacter.instance();
		endCell = new EndCell();
		
		running.setLevel(0);		
		running.addObject(player,15,15);
		running.addObject(endCell,17,15);
	}

	@After
	public void tearDown() throws Exception {
		engine = null;
		running = null;
		player = null;
		board = null;
		endCell = null;
	}
	
	/**
	 * Tests the interaction between movement of main character and end cell when all rewards have been collected
	 * Expectation: next level is loaded
	 */
	@Test
	public void canExitTest() {
		
		int initialLevel = running.currentLevel;
		
		RegularReward R1 = new RegularReward();
		running.addObject(R1,16,15);
		board.setTotalRewardCount(1);
		
		takeARightStep();
		takeARightStep();
		
		int currentLevel = running.currentLevel;
		
		assertEquals(initialLevel+1, currentLevel);
		
	}
	
	/**
	 * Tests the interaction between movement of main character and end cell when not all rewards have been collected
	 * Expectation: character cannot move into end cell
	 */
	@Test
	public void cannotExitTest() {
		
		int initialLevel = running.currentLevel;
		Cell initialCell = player.home;
		
		RegularReward R1 = new RegularReward();
		running.addObject(R1,16,15);
		RegularReward R2 = new RegularReward();
		running.addObject(R2,16,16);
		board.setTotalRewardCount(2);
		
		takeARightStep();
		takeARightStep();
		
		int currentLevel = running.currentLevel;
		Cell finalCell = player.home;
		
		assertEquals(initialLevel, currentLevel);
		assertEquals(initialCell.x+1,finalCell.x);
		assertEquals(initialCell.y,finalCell.y);
		
	}
	
	/**
	 * Tests the interaction between moving into end cell and state change
	 * Expectation: game state changed to win state after completing level 3
	 */
	@Test
	public void winStateTest() {
		
		RunningState.instance().currentLevel = 3;
		
		RegularReward R1 = new RegularReward();
		running.addObject(R1,16,15);
		board.setTotalRewardCount(1);
		
		takeARightStep();
		takeARightStep();
				
		assertTrue(engine.state instanceof WinState);
		
	}
	
	/**
	 * helper function that moves the main character one step to the right
	 */
	public void takeARightStep() {
		KeyEvent key = new KeyEvent(engine, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
		engine.getKeyListeners()[0].keyPressed(key);
		running.update();
		engine.getKeyListeners()[0].keyReleased(key);
		
		for (int i = 0; i < 60; i++) {
			running.update();
		}
	}
	
}
