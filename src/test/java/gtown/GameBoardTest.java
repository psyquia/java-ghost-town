package gtown;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.Cell;
import gtown.GameBoard;
import gtown.gameobjects.Enemy;
import gtown.gameobjects.MainCharacter;
import gtown.state.RunningState;

public class GameBoardTest {
	private MainCharacter player;
	private Enemy enemy;
	private RunningState running;
	private GameBoard board;
	private Cell cell;

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
		cell = null;
		running.setLevel(0);
		
		running.addObject(player,0,0);

	}

	@After
	public void tearDown() throws Exception {
		running = null;
		board = null;

		enemy = null;
		cell = null;
	}
	/**
	 * cell contain player at first, then change to enemy
	 * if content changed means setCell is correct
	 */
	@Test
	public void setCelltest() {

		board.setCell(enemy,1,1);
		cell = board.getCell(1, 1);
		
		assertEquals(enemy, cell.getMovingContent());
		
		cell = null;
		
		board.setCell(player,1,1);
		
		cell = board.getCell(1, 1);
		assertNotEquals(enemy, cell.getMovingContent());
		
		assertEquals(player.home.x, 1);
		board.setCell(player,2,2);
		assertEquals(player.home.x, 2);
		

	}
	/**
	 * set player on (0,0). Since player is the only moving content at this point
	 * if content is equal means correct (x,y) are obtained
	 */
	@Test
	public void getCelltest() {
		cell = board.getCell(0, 0);

		assertEquals(player, cell.getMovingContent());

	}
	/**
	 * set player on (0,0). Then test if player is properly removed
	 */
	@Test
	public void removeObjecttest() {
		cell = board.getCell(0, 0);
		assertEquals(player, cell.getMovingContent());
		
		board.removeObject(player);
		
		assertEquals(cell.getMovingContent(), null);

	}


}
