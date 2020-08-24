package gtown.gameobjects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.RunningState;

public class RegularRewardTest {
	private RegularReward reward;
	private Enemy enemy;
	private GameBoard board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GameEngine.DEBUG = true;
		RunningState.instance();
	}

	@Before
	public void setUp() throws Exception {
		board = GameBoard.instance();
		reward = new RegularReward();
		enemy = new Enemy();
	}

	@After
	public void tearDown() throws Exception {
		board = null;
		reward = null;
		enemy = null;
	}
	
	
	/**
   * MainCharacter collision test
   * Expectation: score increases by 20 and currentRewardCount increases by one
   */
	@Test
	public void playerTest() {
		int initialScore = board.getScore();
		int initialRCount = board.getRewardCount();

		reward.collision(MainCharacter.instance());
		
		int newScore = board.getScore();
		int newRCount = board.getRewardCount();

		assertEquals(initialScore+20, newScore);
		assertEquals(initialRCount+1, newRCount);
	}
	
	/**
   * Enemy collision test
   * Expectation: nothing happens to score
   */
	@Test
	public void enemyTest() {
		int initialScore = board.getScore();
		int initialRCount = board.getRewardCount();

		reward.collision(enemy);
		
		int newScore = board.getScore();
		int newRCount = board.getRewardCount();

		assertEquals(initialScore, newScore);
		assertEquals(initialRCount, newRCount);
	}

}
