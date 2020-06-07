package hw4;

import java.util.ArrayList;

public abstract class MazeSolver {

	protected Maze maze = new Maze();
	private String path="Found the Escape!"; 
	boolean gameOver=false; //when stop our game
	boolean foundExit = false; //exit has been found



	abstract void makeEmpty(); //make worklist empty

	abstract boolean isEmpty(); //if (worklist.size == 0) return true;

	abstract void add(Square sq); //add Square to worklist

	abstract Square next(); //Return next item from the worklist


	MazeSolver(Maze maze2){
		maze = maze2;
	}

	public Maze getMaze() {
		return maze;
	}
	
	//Add start location to worklist
	public void addStartToWorklist() {
	}
	
	/**
	 * repeatedly call step() until you get to 
	 * the exit square or the worklist is empty.
	 */
	public void solve()
	{
		//TODO
	}
	
	public Square step()
	{
		if (isEmpty()) //if the worklist is empty
		{
			makeEmpty();
			addStartToWorklist();
			return null;
		}
		else
		{
			//TODO
		}
		return null; //XXX-CHANGE-XXX
	}


	public boolean foundExit() {
		return false; //XXX-CHANGE-XXX
	}

	public boolean gameOver() {
		return false; //XXX-CHANGE-XXX
	}
	
	public void setFoundExit() {
		//TODO
	}
	public void setGameOver() {
		//TODO
	}
	
	/**
	 * 
	 * @return Path from S to E as a list of coordinates [i,j]
	 * If not solvable, print message
	 */
	public String getPath() { 

		if (foundExit()) {
			return path;
		}
		else 
		{
			path = "Uh Oh!! There's no escape!!";
			return path;

		}
	}
}



