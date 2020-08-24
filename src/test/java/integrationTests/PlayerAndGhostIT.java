package integrationTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Test;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.gameobjects.Enemy;
import gtown.gameobjects.MainCharacter;
import gtown.state.RunningState;
import gtown.ui.ScoreBoard;

public class PlayerAndGhostIT {
    private MainCharacter player;
    private Enemy ghostOne;
    private Enemy ghostTwo;
    private GameBoard board;
    private RunningState running;

    @BeforeClass
	public static void init() throws Exception {
		GameEngine.DEBUG = true;
	}
    
    @Before
	public void setUp() throws Exception {
        running = RunningState.instance();
        ghostOne = new Enemy();
        ghostTwo = new Enemy();
        player = MainCharacter.instance();
        board = GameBoard.instance();
        running.addObject(player,0,0);
        running.setLevel(0);
    }

    @After
	public void tearDown() throws Exception {
        ghostOne = null;
        ghostTwo = null;
        player = null;
        board.resetScoreboard();
        running = null;
    }

    /**
     * Player's health should decrese by 50 after bumping into a ghost
     */
    @Test 
    public void playerDescreseHealthAfterCollisionWithEnemyTest() {
        // initially the play should have full health
        assertEquals(ScoreBoard.max_health, board.getHealth());

        // after player bumps into a ghost, their health should desrese by 50
        ghostOne.collision(player);
        assertEquals(ScoreBoard.max_health - Enemy.collisionPunishment, board.getHealth());
    }

    /**
     * Player should restart at the current level after their health goes to 0
     * (by bumping into ghosts 2 times)
     */
    @Test 
    public void playerIsDeadAfterHittingGhosts2TimesTest() {
        // initially the play should have full health
        assertEquals(ScoreBoard.max_health, board.getHealth());
        ghostOne.collision(player);
        assertEquals(ScoreBoard.max_health - Enemy.collisionPunishment, board.getHealth());
        ghostTwo.collision(player);
        assertEquals(ScoreBoard.max_health, board.getHealth()); // because game restarts
        assertEquals(0, running.currentLevel);
    }
}