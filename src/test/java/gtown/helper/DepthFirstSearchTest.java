package gtown.helper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.helper.DepthFirstSearch;
import gtown.GameEngine;
import gtown.gameobjects.Enemy;
import gtown.gameobjects.MainCharacter;
import gtown.state.RunningState;

import java.util.ArrayList;
import java.util.List;
public class DepthFirstSearchTest {
	private MainCharacter player;
	private Enemy enemy;
	private RunningState running;

	public List<Integer> path;

	@BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
	
	@Before
	public void setUp() throws Exception {
		running = RunningState.instance();
		player = MainCharacter.instance();
		enemy = new Enemy();

		running.setLevel(0);
		
		running.addObject(enemy,0,0);
		running.addObject(player,29,17);
	}

	@After
	public void tearDown() throws Exception {
		running = null;
		player = null;
		enemy = null;

	}
	
	
	/**
	    * Tests if coordinates in path list make a legal path
	    * checking if distance between each move is 1
	    */
	@Test
	public void destination_test() {

		enemy.path = new ArrayList<Integer>();

		
		
		int playerX = player.home.x ;
		int playerY = player.home.y ;
		
       

		int startx = enemy.home.x;
		int starty = enemy.home.y;

		
		DepthFirstSearch.searchPath(  enemy ,  startx,  starty, playerX, playerY );
       // System.out.print(enemy.path);
        int i ;
        int tempx;
        int tempx2;
        int tempy;
        int tempy2;

        tempx = enemy.path.get(0);


        for (i = 0; i < enemy.path.size()-3; i+=2) {

        	 tempx = enemy.path.get(i);
        	 tempx2 = enemy.path.get(i+2);
        	 tempy = enemy.path.get(i+1);
        	 tempy2 = enemy.path.get(i+3);
        	 
        	 assertEquals((Math.abs(tempx-tempx2) + Math.abs(tempy-tempy2)),1);
        	}
        
        

	}

}
