package gtown.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import gtown.GameEngine;

public class SpriteLoader {
	
	public static class Doctor{
		private final static String Path = GameEngine.ASSETS_PATH + "/png/doctor/";
		public final static Integer Frames = 5;
		public static ArrayList<BufferedImage> left;
		public static ArrayList<BufferedImage> right;
		public static ArrayList<BufferedImage> down;
		public static ArrayList<BufferedImage> up;
	}
	public static class Food{
		private final static String Path = GameEngine.ASSETS_PATH + "/png/food/";
		public final static Integer Frames = 2;
		public static ArrayList<BufferedImage> meat;
	}
	public static class Reward{
		private final static String Path = GameEngine.ASSETS_PATH + "/png/reward/";
		public static BufferedImage facemask;
		public static BufferedImage syringe;
	}
	public static class Tile{
		private final static String Path = GameEngine.ASSETS_PATH + "/png/tiles/";
		public final static Integer floorFrames = 2;
		public final static Integer wallFrames = 1;
		public static ArrayList<BufferedImage> floor;
		public static ArrayList<BufferedImage> wall;
		public static BufferedImage exit;
	}
	public static class MEnemy{
		private final static String Path = GameEngine.ASSETS_PATH + "/png/enemy/";
		public final static Integer Frames = 4;
		public static ArrayList<BufferedImage> cloud;
	}
	
	public static void init() throws IOException{
		Doctor.left = new ArrayList<BufferedImage>();
		Doctor.right = new ArrayList<BufferedImage>();
		Doctor.down = new ArrayList<BufferedImage>();
		Doctor.up = new ArrayList<BufferedImage>();
		Food.meat = new ArrayList<BufferedImage>();
		Tile.floor = new ArrayList<BufferedImage>();
		Tile.wall = new ArrayList<BufferedImage>();
		MEnemy.cloud = new ArrayList<BufferedImage>();

		// LOAD DOCTOR SPRITES
		for(int i = 1; i <= Doctor.Frames; ++i) {	// add sprites to sprite arrays
			Doctor.left.add(ImageIO.read(new File(Doctor.Path + "left/" + i + ".png")));
			Doctor.right.add(ImageIO.read(new File(Doctor.Path + "right/" + i + ".png")));
			Doctor.down.add(ImageIO.read(new File(Doctor.Path + "down/" + i + ".png")));
			Doctor.up.add(ImageIO.read(new File(Doctor.Path + "up/" + i + ".png")));
		}
		
		// LOAD FOOD SPRITES
		for(int i = 1; i <= Food.Frames; ++i) {	// add sprites to sprite arrays
			Food.meat.add(ImageIO.read(new File(Food.Path + "meat" + i + ".png")));
		}
		
		// LOAD REWARD SPRITE
		Reward.facemask = ImageIO.read(new File(Reward.Path + "smallfacemask.png"));
		Reward.syringe = ImageIO.read(new File(Reward.Path + "syringe.png"));
		
		// LOAD FLOOR SPRITES
		for(int i = 1; i <= Tile.floorFrames; ++i) {	// add sprites to sprite array
			Tile.floor.add(ImageIO.read(new File(Tile.Path + "tile" + i + ".png")));
		}
		
		// LOAD WALL SPRITES
		for(int i = 1; i <= Tile.wallFrames; ++i) {	// add sprites to sprite array
			Tile.wall.add(ImageIO.read(new File(Tile.Path + "wall" + i + ".png")));
		}
		
		// LOAD EXIT SPRITE
		Tile.exit= ImageIO.read(new File(Tile.Path + "exit.png"));
		
		// LOAD ENEMY SPRITE
		for(int i = 1; i <= MEnemy.Frames; ++i) {	// add sprites to sprite arrays
			MEnemy.cloud.add(ImageIO.read(new File(MEnemy.Path + i + ".png")));
		}

		
	}

}
