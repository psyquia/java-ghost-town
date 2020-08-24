package gtown.gameobjects;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gtown.GameBoard;
import gtown.GameEngine;
import gtown.helper.AudioPlayer;
import gtown.helper.SpriteLoader;

public class Punishment extends GameObject{

	
	private ArrayList<BufferedImage> meat; 
	private static int meatCount = 0;
	private int variant;

	public Punishment() {
		super();
		meat = SpriteLoader.Food.meat;
		this.type = Type.Punishment;
		
		variant = meatCount % 2;
		meatCount++;
	}

	public void update() {
		
	}
	
	public void render(Graphics2D g) {
		g.drawImage(meat.get(variant), x, y, null);
	}

	public void collision(GameObject gobj) {	
		if(gobj.type == Type.MainCharacter) {						// handle collision with MainCharacter
			GameBoard.instance().decreaseHealth(20);
			playJingle(0);
			this.delete();
		}
	}
	
	private void playJingle(int i) {
		AudioPlayer.playOnce(0.5d, GameEngine.ASSETS_PATH + "/sfx/punishment" + i + ".wav");
	}

}
