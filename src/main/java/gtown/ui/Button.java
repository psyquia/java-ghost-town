package gtown.ui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import gtown.state.RunningState;

public interface Button {
	/**
	 * Define the interface for the button
	 * @param g Graphics2D
	 */
	public void render(Graphics2D g);
	
	/**
	 * Define the action for the mouse event
	 * @param state RunningState
	 * @param e MouseEvent
	 */
	public void processClick(MouseEvent e, RunningState state);
}
