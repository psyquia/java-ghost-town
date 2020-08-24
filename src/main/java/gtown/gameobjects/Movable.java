package gtown.gameobjects;

import gtown.Cell;

public interface Movable {
	
	public void init(Cell c);							// calls setDestination on @param c and sets arrived to true 
	
	public void move();					// handles movement decision; calls canMoveTo() and setDestination()
	
	public boolean canMoveTo(Cell c);					// checks if @param c is null and/or if @param c contains barrier
	
	public void setDestination(Cell c);					// initializes dest_x, dest_y, and sets destination to @param c
	
	public boolean atDestination();						// moves to destination; returns true when first arriving at destination
	
	public void arriveAtDestination();	// collide with destination's content and call setCell()  
	
}


