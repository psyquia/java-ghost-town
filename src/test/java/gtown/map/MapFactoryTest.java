package gtown.map;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameEngine;

public class MapFactoryTest {

	private MapFactory mapFactory;
	private Map map;
	
	/**
	 * Make map test
	 * Expectation: The right map is created and returned
	 */
	
	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Test
	public void makeMapTest() {
		mapFactory = new MapFactory();
		map = mapFactory.makeMap(1);		
		assertTrue(map instanceof LevelOne);
		assertFalse(map instanceof LevelTwo);
		assertFalse(map instanceof LevelThree);
		
		map = mapFactory.makeMap(2);		
		assertFalse(map instanceof LevelOne);
		assertTrue(map instanceof LevelTwo);
		assertFalse(map instanceof LevelThree);
		
		map = mapFactory.makeMap(3);		
		assertFalse(map instanceof LevelOne);
		assertFalse(map instanceof LevelTwo);
		assertTrue(map instanceof LevelThree);
		
	}

}
