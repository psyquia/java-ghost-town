package gtown.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gtown.state.RunningState;

public class LevelCount {
		
	public void render(Graphics2D g) {							// render level display
		Font arial = new Font("bahnschrift", Font.PLAIN, 24);
		g.setFont(arial);
		g.setColor(Color.WHITE);
		g.drawString("Level " + RunningState.instance().currentLevel, 900, 30);
	}
}
