package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

import java.util.ArrayList;


/**
 * Title: class MazeSolver
 *  Description: abstract class for solving maze with worklist 
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public abstract class MazeSolver 
{
	protected Maze maze = new Maze();
	private String path="Found the Escape!"; 
	boolean gameOver=false; //when stop our game
	boolean foundExit = false; //exit has been found
	MyStack<String> reversePath = new MyStack<>();
	Square next;



	abstract void makeEmpty(); //make worklist empty

	abstract boolean isEmpty(); //if (worklist.size == 0) return true;

	abstract void add(Square sq); //add Square to worklist

	abstract Square next(); //Return next item from the worklist


	/**
	 * constructor for the MazeSolver
	 * @param maze2, the maze to solve
	 */
	MazeSolver(Maze maze2)
	{
		maze = maze2;
	}
	
	

	/**
	 * getter for the maze we are solving
	 * @return the maze currently being solved
	 */
	public Maze getMaze() 
	{
		return maze;
	}
	
	
	
	/**
	 * Add start location to worklist
	 */
	public void addStartToWorklist() 
	{
		add(maze.getStart());
	}
	
	
	
	/**
	 * repeatedly call step() until you get to 
	 * the exit square or the worklist is empty.
	 */
	public void solve()
	{
		addStartToWorklist();
		next = next();
		while(step() != null)
		{
		}
	}
	
	
	/**
	 * explores the next square
	 * @return null if the worklist is empty, and the current explored 
	 *         Square if not
	 */
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
			next = next();
			if(next.isFinish()) // if current square is the finish
			{
				setFoundExit();
				setGameOver();
				
				String toAdd = " [" + next.getRow() + ", " + 
				               next.getCol() + "]";
				reversePath.addElement(toAdd); // add to path to finish
				
				Square prev = next.getPrevious();
				while(prev != maze.getStart()) // add squares to finish path
				{
					prev.setFinalPath();
					String willAdd = " [" + prev.getRow() + ", " + 
					                 prev.getCol() + "]";
					reversePath.addElement(willAdd);
					prev = prev.getPrevious();
				}
			
				Square [][] mazeArray = maze.getMaze(); 
				// store maze in an array
				int numRows = mazeArray.length;
				int numCol = 0;
			
				if(numRows != 0)
				{
					numCol = mazeArray[0].length;
				}
				
				for(int i = 0; i < numRows; i++) // loop through array
				{
					for(int j = 0; j < numCol; j++)
					{
						if(mazeArray[i][j].isVisited() && 
						   !(mazeArray[i][j].onFinalPath()))
						{
							mazeArray[i][j].setDot(); // set all squares 
							// visited but not on final path to dots
						}
					}
				}
				return next;
			}
			
			else
			{				
				ArrayList<Square> toAdd = maze.getNeighbors(next);
				
				for(int i = 0; i < toAdd.size(); i++)
				{
					if(!(toAdd.get(i).isVisited()) && 
					   !(toAdd.get(i).onWorklist())) 
					// add neighbors to worklist
					{
						toAdd.get(i).setPrevious(next);
						add(toAdd.get(i));
					}
				}
				
				next.setVisited();
				return next;
			}
		} 
	}

	

	/**
	 * tells if a finish path has been found
	 * @return
	 */
	public boolean foundExit() 
	{
		if(foundExit)
		{
			return true;
		}		
		return false; 
	}

	
	
	/**
	 * tells if game has ended
	 * @return
	 */
	public boolean gameOver() 
	{
		if(gameOver)
		{
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * sets foundExit to true if exit found
	 */
	public void setFoundExit() 
	{
		foundExit = true;
	}
	
	
	
	/**
	 * sets gameOver to true if there is no exit or finish is found
	 */
	public void setGameOver() 
	{
		gameOver = true;
	}
	
	
	
	/**
	 * 
	 * @return Path from S to E as a list of coordinates [i,j]
	 * If not solvable, print message
	 */
	public String getPath() 
	{ 
		if (foundExit()) 
		{	
			path += "\nPath from Start to Exit :";
			for(int i = reversePath.size(); i > 0; i--)
			{
				path += reversePath.removeElement();
				// path now from start to finish
			}
			
			return path;
		}
		else 
		{
			path = "Uh Oh!! There's no escape!!";
			return path;

		}
	}
}



