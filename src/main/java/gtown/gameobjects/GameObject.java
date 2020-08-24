package gtown.gameobjects;

import java.awt.Graphics2D;

import gtown.Cell;
import gtown.state.RunningState;

public abstract class GameObject {
	
	public int x;
	public int y;
	protected Type type;					// specify GameObject type (ex. Type.MainCharacter)
	public Cell home;					// specify home Cell
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
	}
	
	public abstract void update();							// update GameObject
	public abstract void render(Graphics2D g);								// draw GameObject
	public abstract void collision(GameObject gobj);	// handles collision with @param gobj
	
	public void clearHome() {				// generic clearHome() function
		if(home == null) return;
		home.clearCell(this);
		home = null;
	}
	
	public void delete() {
		RunningState.instance().removeObject(this);
	}

}
