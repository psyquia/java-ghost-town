package gtown.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import gtown.GameEngine;
import gtown.state.RunningState;

public class RestartButton {
	private int btnStartPosX;
	private int btnStartPosY;
	private int btnWidth;
	private int btnHeight;
	private GameEngine ge;
	
	public RestartButton() {
		btnStartPosX = 670;
		btnStartPosY = 0;
		ge = GameEngine.instance();
	}

	/**
	 * Render the exit button onto canvas
	 * @param g Graphics2D
	 */
	public void render(Graphics2D g) {
		String text = "Restart";
		Font font = new Font("bahnschrift", Font.PLAIN, 24);
		g.setFont(font);
		g.setColor(Color.GREEN);
		g.drawString(text, btnStartPosX, 30);
		
		FontMetrics fm = g.getFontMetrics(font);
		btnWidth = fm.stringWidth(text);
		btnHeight = fm.getHeight();
	}
		
	/**
	 * Define the action after click
	 * @param e MouseEvent object
	 * @param state RunningState object
	 */
	public void processClick(MouseEvent e, RunningState state) {
		int mx = e.getX();
		int my = e.getY();

		if(ge.getCurrentState().contentEquals("RunningState")
				&& mx >= btnStartPosX
				&& mx <= btnStartPosX + btnWidth
				&& my >= btnStartPosY
				&& my <= btnStartPosY + btnHeight) {

			state.stopMusic();
			state.setLevel(1);
		}
	}
}
