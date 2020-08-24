package gtown.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gtown.GameEngine;

public class MouseInput implements MouseListener {
	
	public MouseInput() {
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		GameEngine.instance().state.processClick(e);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
