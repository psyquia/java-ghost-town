package gtown.helper;

import java.util.ArrayList;
import java.util.ArrayDeque;

import gtown.Cell;
import gtown.GameBoard;
import gtown.gameobjects.Enemy;

public class BreadthFirstSearch{



	public BreadthFirstSearch() {
	}
	/**
	 * BFS using queue. Keep adding unvisited neighbour of the currently pulled cell into the queue and 
	 * the action will be terminated by either queue is empty or finding the goal index
	 */
	
    public static ArrayList<Integer> breadthFirstSearch(Enemy thisEnemy, int x, int y , int px, int py) {
    		
 
    		ArrayList<Integer> neighboursList =  new ArrayList<Integer>();
    		
    		ArrayList<Integer> path = new ArrayList<Integer>();

        	if (x == px && y== py) { 											

	            return path;
    		}

    		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();


    		ArrayList<Integer> visited = new ArrayList<Integer>();

    		queue.offer(x);
    		queue.offer(y);

    		while (!queue.isEmpty()) {


    			neighboursList.clear();
    			int vertex_x = queue.poll();
    			int vertex_y = queue.poll();


    			visited.add(vertex_x);
    			visited.add(vertex_y);

    			neighboursList = getNeighbours(thisEnemy,vertex_x,vertex_y); 
	        	

    			
    			
    			int index = 0;
    			int neighboursSize = neighboursList.size();


    			while (index < neighboursSize) {

    				int neighbour = neighboursList.get(index);
    				int currentx = neighbour;
		
    				path.add(neighbour);

    				index++;
    				neighbour = neighboursList.get(index);

    				int currenty = neighbour;
    				path.add(neighbour);
    				
    				path.add(vertex_x);
    				path.add(vertex_y);

    				if (currentx == px && currenty== py) {

    					neighboursList.clear();

    					return processPath(thisEnemy,x, y, px, py, path);
    				} 
    				else {
    				

    					if (check(visited, currentx, currenty)) {
    		    			
    						queue.offer(currentx);
    						queue.offer(currenty);
    						
    					}
    				}

    				index++;
    			}
    		}

    		
    		return null;
    	}
    
	/**
	 * A simple loop function to check if the position has been visited, if visited return false
	 */
	protected static boolean check( ArrayList<Integer> c, int dest_x, int dest_y){	

		for(int i=0;i<c.size();i+=2){
	    	
			if (c.get(i)== dest_x && c.get(i+1) == dest_y){
				return false;
				
			}
					} 

		return true;

	}
	
	/**
	 * function return a list of cell's moveable neighbour
	 */
    public static  ArrayList<Integer> getNeighbours(Enemy thisEnemy, int x, int y) {
    	ArrayList<Integer> neighboursList = new ArrayList<Integer> ();
        
        GameBoard board = GameBoard.instance();
        Cell advance_x = null;
        advance_x = board.getCell(x+1, y);
        
  
        
        if(thisEnemy.canMoveTo(advance_x) && (x+1<30 && x+1>=0) && (y<17 && y>=0)) {

        	neighboursList.add(x+1);
        	neighboursList.add(y);
 
        }

        advance_x = null;
        advance_x = board.getCell(x-1, y);

        if(thisEnemy.canMoveTo(advance_x) && (x-1<30 && x-1>=0) && (y<17 && y>=0)) {


        	neighboursList.add(x-1);
        	neighboursList.add(y);

        }

        advance_x = null;
        advance_x = board.getCell(x, y+1);

        if(thisEnemy.canMoveTo(advance_x) && (x<30 && x>=0) && (y+1<17 && y+1>=0)) {

        	neighboursList.add(x);
        	neighboursList.add(y+1);
        }
        advance_x = null;
        advance_x = board.getCell(x, y-1);

        if(thisEnemy.canMoveTo(advance_x) && (x<30 && x>=0) && (y-1<17 && y-1>=0)) {
        	neighboursList.add(x);
        	neighboursList.add(y-1);
        }
 

        return  neighboursList;
        
        
        
        

    }
    
    
	/**
	 * function recive a long list of repeating indexes from original position
	 * to the target. By recursively calling itself, functino eliminate excessive 
	 * indexes and produce shortest path
	 */
    private static ArrayList<Integer> processPath(Enemy thisEnemy, int x, int y, int px, int py,
            ArrayList<Integer> path) {


    	int i = 0;
    	int index_x  = 0;
    	int index_y  = 0;
    	while(i<path.size()) {
    		if (path.get(i) == px && path.get(i+1) == py) {
    			 index_x  = i;
    			 index_y  =i+1;
    			 break;
    		}
    		i+=2;
    	}

    	int source_x = path.get(index_x + 2);
    	int source_y = path.get(index_y + 2);

    	thisEnemy.shortestPath.add(0, px);
    	thisEnemy.shortestPath.add(1, py);
    	
    	
    	if (source_x == x && source_y == y) {

    		thisEnemy.shortestPath.add(0, x);
    		thisEnemy.shortestPath.add(1, y);

    		return thisEnemy.shortestPath;
    		}
    	else {

    			return processPath(thisEnemy, x, y , source_x, source_y, path);
    			}
    }
	
}