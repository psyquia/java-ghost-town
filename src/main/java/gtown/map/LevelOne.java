package gtown.map;

/**
 * Implements Level one design.
 * Determines wall, regular rewards, punishments, starting and end cell position on map.
 */
public class LevelOne extends Map{
	
	/** 
	 * Constructor initializes map array.
	 */
	public LevelOne( ) {
		String[] mapTemplate = new String[] {
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W",
				"P"," "," "," "," ","r","W"," ","X","r","W"," "," "," "," "," "," "," "," ","W"," "," "," "," "," "," "," "," ","r","W",
				"W"," "," "," "," "," ","W"," "," "," ","W"," ","W"," ","W","r"," "," "," "," "," "," "," ","W","r"," ","W"," "," ","W",
				"W","W","W","W"," "," ","W"," ","W"," ","W"," ","W"," "," ","X","W"," "," "," "," "," ","M","W"," ","X","W"," "," ","W",
				"W","r","X","W"," "," "," "," ","W"," "," "," ","W"," "," "," "," "," "," ","W","X"," "," "," "," "," "," "," "," ","W",
				"W"," "," "," "," "," ","W"," ","W"," ","W"," "," "," ","W","W","W"," ","W","W","W"," "," ","W","W","W","W"," "," ","W",
				"W"," "," "," "," "," ","W"," ","W"," ","W"," "," "," ","W","X"," "," "," ","X","W"," "," "," "," "," "," "," "," ","W",
				"W"," ","X","W"," "," "," "," ","W"," "," "," ","W"," ","W"," "," "," "," "," ","W"," ","r","W","X"," "," "," "," ","W",
				"W"," "," ","W"," "," ","W"," ","W"," ","W"," ","W","M","W"," ","W","W","W"," ","W"," ","W","W","W"," ","W"," "," ","W",
				"W"," ","W","W","W"," ","W"," "," "," ","W"," ","W"," ","W"," ","r","W","r"," ","W"," ","r","W","X"," ","W","X"," ","W",
				"W"," "," ","r"," "," ","W","r","X"," ","W"," "," ","r","W"," ","W","W","W"," ","W"," "," "," "," "," ","W"," "," ","W",
				"W"," ","W","W","W"," ","W","W","W","W","W","W","W","W","W"," ","r","W","X"," ","W"," "," ","X"," "," ","W"," ","W","W",
				"W"," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ","W","W","W"," "," "," "," "," "," "," ","W"," "," ","W",
				"W","r","W","W","W"," "," "," ","W"," ","W"," "," "," "," "," ","X","W","r"," "," "," ","X","W","r"," ","W","X"," ","W",
				"W"," "," "," "," ","M","W"," ","W"," ","W"," ","W","W","W"," ","W","W","W"," ","W"," ","W","W","W"," ","W"," "," ","W",
				"W"," ","W","W","W"," ","W"," ","W"," ","W"," "," ","X","W"," "," "," "," "," ","W"," ","r","W","X"," ","W"," ","X","W",
				"W"," "," "," "," ","r","W"," "," "," "," "," "," ","r","W","r"," "," "," ","X","W"," "," "," "," "," ","W"," "," ","W",
				"W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","W","E","W"

		};
		setMapElements(mapTemplate);
	}
}