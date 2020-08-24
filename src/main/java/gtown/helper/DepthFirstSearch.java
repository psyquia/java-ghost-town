package gtown.helper;

import java.util.List;

import gtown.Cell;
import gtown.GameBoard;
import gtown.gameobjects.Enemy;

public class DepthFirstSearch{


	public DepthFirstSearch() {
	}

	public static boolean searchPath(Enemy thisEnemy, int x, int y , int px, int py) {
		
		GameBoard board = GameBoard.instance();
		Cell advance_x = null;
		if((x<30 && x>=0) && (y<18 && y>=0))									
			advance_x = board.getCell(x, y);									 
		/**
		 * set the game board size as the boundary for movement and get the object within the cell
		 */
		
        if(thisEnemy.canMoveTo(advance_x) && (x<30 && x>=0) && (y<18 && y>=0)){
	        /**
	         * check the cell is not a barrier cell
	         */ 
        
        	if (x == px && y== py) { 											
        																		 
	            thisEnemy.path.add(x);
	            thisEnemy.path.add(y);

	            return true;
	        }
	        /**
	         * base case: if the (x,y) equals to player's (x,y)
	         * then add this final coordinate to the path and turn true
	         */
        	
        	
        	
	        else if (check(thisEnemy.visited,x,y)) {							
	        	/**
	        	 * check if the coordinate has been visited
	        	 */

	        	thisEnemy.visited.add(x);										 
				thisEnemy.visited.add(y);
	        	/**
	        	 * since its not visited, add the coordinate to visited list 
	        	 */
	            int dx = -1;													
	            int dy = 0;
	            if (searchPath(thisEnemy, x + dx, y + dy,  px,  py)) {			 
	            		thisEnemy.path.add(x);									 
		                thisEnemy.path.add(y);
	                return true;
		        	/**
		        	 * check if the coordinate to the left is a legal move, if yes then continue recursive call and return true
		        	 * add current coordinate to path
		        	 */
	            }

	            dx = 1;
	            dy = 0;
	            if (searchPath(thisEnemy, x + dx, y + dy, px,  py)) {			
	            		thisEnemy.path.add(x);									
		                thisEnemy.path.add(y);
	                return true;
	                /**
		        	 * check if the coordinate to the right is a legal move, if yes then continue recursive call and return true
		        	 * add current coordinate to path
		        	 */
	            }

	            dx = 0;
	            dy = -1;
	            if (searchPath(thisEnemy, x + dx, y + dy,  px, py)) {			
	            		thisEnemy.path.add(x);									
		                thisEnemy.path.add(y);
	                return true;
	                /**
		        	 * check if the coordinate to go up is a legal move, if yes then continue recursive call and return true
		        	 * add current coordinate to path
		        	 */
	            }

	            dx = 0;
	            dy = 1;
	            if (searchPath(thisEnemy, x + dx, y + dy, px,  py)) {			
	            		thisEnemy.path.add(x);									
	 	                thisEnemy.path.add(y);

	                return true;
	                /**
		        	 * check if the coordinate to go down is a legal move, if yes then continue recursive call and return true
		        	 * add current coordinate to path
		        	 */
	            }
	        }
        }
        return false;															
    }
	/**
	 * if there is no further legal movement or no unvisited coordinate, return false
	 */
	protected static boolean check(List<Integer> c, int dest_x, int dest_y){	
		
		for(int i=0;i<c.size();i+=2){
			
			if (((int)c.get(i)== dest_x) && ((int)c.get(i+1) == dest_y)){
				return false;
				
			}
					} 

		return true;
		/**
		 * A simple loop to check if the position has been visited, if visited return false
		 */
	}
}
