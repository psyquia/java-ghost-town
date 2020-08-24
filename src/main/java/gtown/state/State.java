package gtown.state;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public interface State {
	public void update();						// update game based on state
	public void render(Graphics2D g);			// render game based on state
	public void processPress(int key);			
	public void processRelease(int key);		// handle user input based on state
	public void processClick(MouseEvent key);	// handle user input based on state
}
