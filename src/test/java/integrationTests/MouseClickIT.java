package integrationTests;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.state.MenuState;
import gtown.state.RunningState;

public class MouseClickIT {
	private static GameEngine engine;
	
	@BeforeClass
	public static void init() throws AWTException {
		GameEngine.DEBUG = true;
		engine = GameEngine.instance();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		engine = null;
	}
	
	/**
	 * Tests the interaction between Play button and game state
	 * Expectation: game state changes from MenuState to RunningState
	 */
	@Test
	public void clickPlayTest() {
		engine.setGameState(MenuState.instance());

		assertEquals("MenuState", engine.getCurrentState());
		
		// Play button x and y
		int x = GameEngine.WIDTH / 2 - 70;
		int y = 300;
		
		MouseListener ml = engine.getMouseListeners()[0];
		MouseEvent me = new MouseEvent(engine, 0, 0, 0, x, y, 1, false);

		ml.mousePressed(me);
		
		assertEquals("RunningState", engine.getCurrentState());
	}
	
	/**
	 * Tests interaction between MouseInput, RunningState and GameBoard
	 * Expectation: timeElapsed and level are reset after restart
	 */
	@Test
	public void clickRestartTest() throws InterruptedException {
		engine.setGameState(RunningState.instance());
		
		// In state with restart button
		assertEquals("RunningState", engine.getCurrentState());
		
		engine.setGameState(RunningState.instance());
		
		long initialTime = GameBoard.instance().getTimeElapsed();
		int initialLevel = RunningState.instance().currentLevel;
		
		// Sleep to let time pass
		TimeUnit.MILLISECONDS.sleep(1000);
		
		long laterTime = GameBoard.instance().getTimeElapsed();
		
		// Ensure that time has elapsed
		assertTrue(laterTime > initialTime);
		
		// Restart button x and y
		int x = 670;
		int y = 0;
		
		MouseListener ml = engine.getMouseListeners()[0];
		MouseEvent me = new MouseEvent(engine, 0, 0, 0, x, y, 1, false);
		
		ml.mousePressed(me);
		
		long restartTime = GameBoard.instance().getTimeElapsed();
		int restartLevel = RunningState.instance().currentLevel;
		
		// Ensure time elapsed has reset to zero
		assertEquals(0, restartTime);
		
		// Ensure level is the same
		assertEquals(initialLevel, restartLevel);
	}
}
