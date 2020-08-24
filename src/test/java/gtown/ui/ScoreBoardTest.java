package gtown.ui;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameEngine;
import gtown.state.RunningState;

public class ScoreBoardTest {
    private ScoreBoard sb;
    private RunningState rs;

    @BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
    
    @Before
	public void setUp() throws Exception {
        rs = RunningState.instance();
        sb = new ScoreBoard();
	}

	@After
	public void tearDown() throws Exception {
        rs = null;
        sb = null;
    }
    
    /**
     * health does not go over max_health (100)
     */
    @Test 
    public void increaseHealthTest() {
        assertEquals(100, sb.getHealth());

        sb.increaseHealth(1);

        assertEquals(100, sb.getHealth());
    }

    /**
     * if health is <= 0, reset level
     */
    @Test 
    public void checkState() {
        // At level 1, check state will reset the level back to 1 when health == 0
        assertEquals(1, rs.currentLevel);
        sb.decreaseHealth(sb.max_health);
        assertEquals(0, sb.getHealth());
        assertEquals(1, rs.currentLevel);

        // At level 2, check state will reset the level back to 2 when health == 0
        rs.nextLevel();
        assertEquals(2, rs.currentLevel);
        sb.decreaseHealth(sb.max_health);
        assertEquals(2, rs.currentLevel);
    }
}