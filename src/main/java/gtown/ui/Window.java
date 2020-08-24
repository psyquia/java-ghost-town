package gtown.ui;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import gtown.GameEngine;

public class Window extends Canvas {

	private static final long serialVersionUID = 933388139676499366L;
	
	public Window(int width, int height, String title, GameEngine game) {		// create game Window
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
	}
}
