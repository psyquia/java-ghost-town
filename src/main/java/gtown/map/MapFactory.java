package gtown.map;


/**
 * Factory for creating map objects
 */
public class MapFactory {
	
	/** 
	 * Make map function
	 * @param newMapLevel an integer that indicates which level to create
	 * @return the map object of the corresponding level 
	 */
	public Map makeMap(int newMapLevel) {
		
		if (newMapLevel == 1) {
			return new LevelOne();
		}
		else if (newMapLevel == 2) {
			return new LevelTwo();
		}
		else if (newMapLevel == 3) {
			return new LevelThree();
		}
		else if (newMapLevel == 0) {
			return new LevelZero();
		}
		else return null;
		
	}

}
