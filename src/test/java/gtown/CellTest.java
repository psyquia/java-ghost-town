package gtown;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.Cell;
import gtown.gameobjects.MainCharacter;
import gtown.state.RunningState;

public class CellTest {
	private MainCharacter player;
	private RunningState running;
	private Cell cell;
	
	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Before
	public void setUp() throws Exception {
		running = RunningState.instance();
		player = MainCharacter.instance();
		cell = new Cell(0,0);
		running.setLevel(0);
	}

	@After
	public void tearDown() throws Exception {
		running = null;
		player = null;
		cell = null;
	}	
	
	@Test
	/*
	 * *set player as moving content first then 
	 * set player as static content
	 */
	public void setContenttest() {
	cell.setContent(player ,  true);
	assertEquals(player, cell.getMovingContent());	
	
	cell = new Cell(1,1);
	cell.setContent(player);
	assertNotEquals(player, cell.getMovingContent());	
		

	}
	
	/*
	 * *set player as static content first then 
	 * set player as moving content
	 */
	@Test
	public void setContenttestS() {
		cell.setContent(player);
		assertEquals(player, cell.getStaticContent());	
		
		cell = new Cell(1,1);
		cell.setContent(player,true);
		assertNotEquals(player, cell.getStaticContent());	
	}

	@Test
	/*
	 * *set player as static content first then remove static content
	 * then set player as moving content then remove moving content
	 */
	public void clearCelltest() {
		cell.setContent(player);
	
		cell.clearCell();

		assertEquals(null, cell.getStaticContent());	
		
		cell = new Cell(1,1);
		cell.setContent(player ,  true);
	
		cell.clearCell(player);

		assertEquals(null, cell.getMovingContent());	

	}

}
