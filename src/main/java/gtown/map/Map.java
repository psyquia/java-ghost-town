package gtown.map;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import gtown.gameobjects.Barrier;
import gtown.gameobjects.BonusReward;
import gtown.gameobjects.EndCell;
import gtown.gameobjects.Enemy;
import gtown.gameobjects.GameObject;
import gtown.gameobjects.MainCharacter;
import gtown.gameobjects.Punishment;
import gtown.gameobjects.RegularReward;

/**
 * Represents a map level in general
 */
public abstract class Map {
	
	private GameObject[] mapElements;
	private int rewardCount = 0;
	private int mapWidth = 30;
	private int mapLength = 18;
	private int mapSize = mapWidth*mapLength;

	// *********************
	//	Getters and Setters
	
	/**
	 * Gets the array representing the map 
	 * @return the array 
	 */
	public GameObject[] getMapElements() {return mapElements;}					// Get map elements
	/**
	 * Changes the current array representing the map
	 * @param newMapElements the new map array
	 */
	public void setMapElements(String[] newMapElements) {						// Set map elements from an arrays of str
		mapElements = makeNewMap(newMapElements);								// Used by children map level classes
	}	
	
	/**
	 * Gets the number of rewards on this level
	 * @return the number of rewards on this level
	 */
	public int getRewardCount() {return rewardCount;}
	
	/**
	 * Gets the width of the map in number of cells
	 * @return the width of the map in number of cells
	 */
	public int getMapwidth() {return mapWidth;}							// Get map width
	/**
	 * Changes the width of the map in number of cells
	 * @param newMapWidth The new width of the map in number of cells
	 */
	public void setMapwidth(int newMapWidth) {							// Set map width, update map size
		mapWidth = newMapWidth;
		mapSize = mapWidth*mapLength;
	}
	
	/**
	 * Gets the length of the map in number of cells
	 * @return the length of the map in number of cells
	 */
	public int getMapLength() {return mapLength;}						// Get map length
	/**
	 * Changes the length of the map in number of cells
	 * @param newMapWidth The new length of the map in number of cells
	 */	
	public void setMapLength(int newMapLength) {						// Set map length, update map size
		mapWidth = newMapLength;
		mapSize = mapWidth*mapLength;
	}	

	/**
	 * Gets the size of the map in number of cells
	 * @return the size of the map in number of cells
	 */
	public int getMapSize() {return mapSize;}							// Get map size

	
	// *********************
	//	Helper methods
	
	/**
	 * Creates a game object array from a string array that represents a map
	 * @param newMapElements A string array representing a map
	 * @return A game object array representing a map
	 */
	private GameObject[] makeNewMap(String[] newMapElements ) {		// Making an array of game objects from an array of string 
		GameObject[] newMap = new GameObject[mapSize];								// called by set map elements method
		List<Integer> freeCells = new ArrayList<Integer>();
		
		
		for (int i = 0; i < newMapElements.length; i++) {
			if (newMapElements[i] != " ") {			// ignore empty cells
				if (newMapElements[i] == "P") {		
					newMap[i] = MainCharacter.instance();
				}
				else {
					newMap[i] = makeNewElement(newMapElements[i]);

				}
				newMap[i].x = i%mapWidth;
				newMap[i].y = i/mapWidth;
			}
			else {
				newMap[i] = null;
				freeCells.add(i);
			}
		}
		
		// ADD BONUS REWARD TO RANDOM INDEX
		int bnsRewardI = getBonusRewardIndex(freeCells);
		newMap[bnsRewardI] = makeNewElement("R");
		newMap[bnsRewardI].x = bnsRewardI%mapWidth;
		newMap[bnsRewardI].y = bnsRewardI/mapWidth;
		
		return newMap;
	}
	
	/**
	 * Creates a corresponding game object from a string 
	 * @param elementType A string specifying a game object 
	 * @return A game object of the specified type
	 */
	private GameObject makeNewElement(String elementType) {
		if (elementType == "r") {									// r for regular reward
			rewardCount++;
			return new RegularReward();								
		}			
		else if (elementType == "R") return new BonusReward();		// R for bonus reward
		else if (elementType == "X") return new Punishment();		// X for punishment
		else if (elementType == "W") return new Barrier();			// W for barrier
		else if (elementType == "E") return new EndCell();
		else if (elementType == "M") return new Enemy();
		else return null;
	}
	
	/**
	 * Determines the position of the bonus reward
	 * @param freeCells A list of empty cells in the map
	 * @return The index of the bonus reward
	 */
	private int getBonusRewardIndex(List<Integer> freeCells) {
		int index = ThreadLocalRandom.current().nextInt(0, freeCells.size());
		return freeCells.get(index);
	}
	
}
