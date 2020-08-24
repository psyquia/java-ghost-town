package gtown.map;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameEngine;
import gtown.gameobjects.Barrier;
import gtown.gameobjects.BonusReward;
import gtown.gameobjects.EndCell;
import gtown.gameobjects.GameObject;
import gtown.gameobjects.MainCharacter;
import gtown.gameobjects.Punishment;

public class MapTest {
	
	private Map map;
	private GameObject[] mapElements;

	/**
	 * Make new map test
	 * Expectation: A correct array of GameObjects is created and returned
	 */
	
	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Test
	public void makeNewMapTest() {
		map = new LevelOne();
		mapElements = new GameObject[map.getMapSize()];
		map.setMapElements(new String[] {
			"W","P"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," ","X"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
			" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","E"
		});
		mapElements = map.getMapElements();
		
		assertTrue(mapElements[184] instanceof Punishment);
		assertEquals(mapElements[184].x, 4);
		assertEquals(mapElements[184].y, 6);
		
		assertTrue(mapElements[0] instanceof Barrier);
		assertEquals(mapElements[0].x, 0);
		assertEquals(mapElements[0].y, 0);
		
		assertTrue(mapElements[539] instanceof EndCell);
		assertEquals(mapElements[539].x, 29);
		assertEquals(mapElements[539].y, 17);
		
		assertTrue(mapElements[1] instanceof MainCharacter);
		assertEquals(mapElements[1].x, 1);
		assertEquals(mapElements[1].y, 0);
		
		int bonusRewardCount = 0;
		for (GameObject go: mapElements) {
			if (go instanceof BonusReward) bonusRewardCount++;
		}
		assertEquals(bonusRewardCount, 1);
		
	}

}
