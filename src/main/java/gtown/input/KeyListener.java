package gtown.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import gtown.GameEngine;

public class KeyListener extends KeyAdapter{
	
	
	public KeyListener() {
	}

	public void keyPressed(KeyEvent e) {			// handle input based on state
		int key = e.getKeyCode();
		GameEngine.instance().state.processPress(key);
	}
	
	public void keyReleased(KeyEvent e) {			// handle input based on state
		int key = e.getKeyCode();
		GameEngine.instance().state.processRelease(key);
	}
}
