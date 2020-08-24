package gtown.state;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameEngine;
import java.awt.event.KeyEvent;

public class MenuStateTest {

    private GameEngine ge;
    private MenuState ms;

    @BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
    
    @Before
	public void setUp() throws Exception {
        ge = GameEngine.instance();
        ms = MenuState.instance();
	}

	@After
	public void tearDown() throws Exception {
        ge = null;
        ms = null;
	}

    /**
     * When startGame() is called, game state will be changed
     */
    @Test
    public void startGameTest() {
        assertEquals("MenuState", ge.getCurrentState());

        ms.processPress(KeyEvent.VK_ENTER);

        assertEquals("RunningState", ge.getCurrentState());
    }
}