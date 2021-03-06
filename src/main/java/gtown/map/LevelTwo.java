package gtown.map;

/**
 * Implements Level three design.
 * Determines wall, regular rewards, punishments, starting and end cell position on map.
 */
public class LevelTwo extends Map{
	
	/** 
	 * Constructor initializes map array.
	 */
	public LevelTwo() {
		String[] mapTemplate = new String[] {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"P"," "," "," "," "," "," ","r"," ","W","r"," "," "," "," "," "," "," "," ","r"," "," ","W","X","r"," ","r"," ","X","W",
				"W"," ","W"," ","W","W","W","W"," ","W","W","W"," ","W"," ","W","W","W","W","W","W"," ","W"," "," ","W","X"," "," ","W",
				"W"," ","W"," "," "," "," "," "," ","W"," "," "," ","W"," "," "," ","r"," "," "," "," ","W","r","X","W","W","X"," ","W",
				"W"," ","W","W","W"," "," ","W","W","W"," ","W","W","W"," ","W","W","W","W","W","W"," ","W"," ","M","X","W"," ","r","W",
				"W"," "," "," ","W"," ","X","W"," "," "," "," "," ","W"," "," "," "," "," "," ","W"," ","W","X"," "," "," "," ","X","W",
				"W"," ","W"," ","W"," "," ","W","r","W","W","W"," ","W","W"," "," ","W","W"," ","W"," ","W","W","W"," "," ","W","W","W",
				"W"," ","W","r","W"," "," ","W","W","W"," "," "," "," ","W","W"," ","X","W"," ","W"," ","r","W"," "," "," "," "," ","W",
				"W"," ","W"," "," "," "," "," "," "," "," ","X"," "," "," "," "," "," "," "," ","W","W","W","W"," ","W","W","W"," ","W",
				"W","r","W"," ","W","W"," "," "," ","W","W","W","W"," "," ","W","W","W","W"," ","W"," "," "," "," "," "," "," "," ","W",
				"W"," ","W"," "," "," "," ","W"," "," "," "," "," "," "," "," "," "," "," "," ","W"," ","W","r"," ","W"," ","W"," ","W",
				"W"," ","W"," ","W","W"," ","W","W","W","W","W"," ","W"," ","W"," "," ","W"," "," "," ","r","W"," ","W"," ","W"," ","W",
				"W"," "," "," "," "," ","M","W"," "," "," "," "," "," "," ","W","r","X","W","X"," "," "," "," "," ","W"," ","W"," ","W",
				"W","X","W","W","W","W","W","W"," ","W"," ","W","W","W"," ","W","W","W","W","W","W","W","W","W","W","W"," "," "," ","W",
				"W"," "," "," "," "," "," "," "," ","W"," "," ","r"," "," "," "," "," "," "," "," "," ","r","W"," "," "," ","W"," ","W",
				"W","r","W","W"," "," ","W","W","r","W"," ","W","W","W"," ","W","W","W","W"," ","W","W","W","W"," ","W"," ","W"," ","W",
				"W"," "," "," "," "," "," "," "," ","W"," "," ","r"," "," ","W","r"," "," ","M"," "," "," "," "," "," "," ","W"," ","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","E","W"
		};
		setMapElements(mapTemplate);
	}
}
