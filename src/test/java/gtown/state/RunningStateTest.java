package gtown.state;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import gtown.GameEngine;
import gtown.helper.SpriteLoader;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpriteLoader.class)
@PowerMockIgnore("javax.*")
public class RunningStateTest {

    private RunningState rs;

    @BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
    
    @Before
	public void setUp() throws Exception {
        rs = RunningState.instance();
        rs.setLevel(1);
	}

	@After
	public void tearDown() throws Exception {
        rs = null;
	}
    
    /**
     * Current level should be one
     */
    @Test 
    public void RunningStateConstructorTest() {
        assertEquals(1, rs.currentLevel);
    }

    /**
     * Current level should be unchanged 
     */
    @Test
    public void resetLevelTest() {
        assertEquals(1, rs.currentLevel);
        rs.resetLevel();
        assertEquals(1, rs.currentLevel);

        rs.nextLevel();
        assertEquals(2, rs.currentLevel);
        rs.resetLevel();
        assertEquals(2, rs.currentLevel);
    }  
    
    /**
     * currentLevel increased after nextLevel() is callled
     */
    @Test 
    public void nextLevelTest() {
        assertEquals(1, rs.currentLevel);
        rs.nextLevel();
        assertEquals(2, rs.currentLevel);
    }

    /**
     * currentMap is correct type and currentLevel set
     */
    @Test 
    public void setLevelTest() {
        RunningState rs = RunningState.instance();
        GameEngine ge = GameEngine.instance();
        rs.setLevel(1);
        assertEquals(1, rs.currentLevel);
        assertEquals("LevelOne", rs.getCurrentMap().getClass().getSimpleName());

        rs.setLevel(2);
        assertEquals(2, rs.currentLevel);
        assertEquals("LevelTwo", rs.getCurrentMap().getClass().getSimpleName());

        rs.setLevel(3);
        assertEquals(3, rs.currentLevel);
        assertEquals("LevelThree", rs.getCurrentMap().getClass().getSimpleName());

        rs.setLevel(4);
        assertEquals("WinState", ge.getCurrentState());
    }
}