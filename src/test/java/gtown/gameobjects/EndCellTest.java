package gtown.gameobjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class EndCellTest {
	
	private GameBoard gameBoard;
	private EndCell endCell;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameEngine.DEBUG = true;
		RunningState.instance();
	}
	
	@Before
	public void setUp() throws Exception {
		gameBoard = GameBoard.instance();
		endCell = new EndCell();
	}
	
	/**
	 * Player collision test
	 * Expectation: nextLevel is called if the level is cleared
	 */
	@Test
	public void playerCollisionTest() {
		gameBoard.setTotalRewardCount(5);
		
		int oldLevel = RunningState.instance().currentLevel;
		
		endCell.collision(MainCharacter.instance());
		
		int newLevel = RunningState.instance().currentLevel;
		
		assertEquals(oldLevel, newLevel);
		
		gameBoard.setTotalRewardCount(0);	
		endCell.collision(MainCharacter.instance());
		
		newLevel = RunningState.instance().currentLevel;
		
		assertEquals(oldLevel+1, newLevel);
			
	}
	
	/**
	 * Enemy collision test
	 * Expectation: nextLevel is not called
	 */
	@Test
	public void enemyCollisionTest() {
		int oldLevel = RunningState.instance().currentLevel;
		
		endCell.collision(MainCharacter.instance());
		
		int newLevel = RunningState.instance().currentLevel;
		
		assertEquals(oldLevel, newLevel);

			
	}
	
}
