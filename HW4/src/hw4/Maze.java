package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Title: class Maze
 *  Description: class for describing an 2D array for the maze
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class Maze 
{
	public Square[][] maze; // a 2D Maze 
	protected int numRows; // No of rows
	protected int numCols; // No of cols

	
	/**
	 * constructor for maze, sets number of rows and columns to zero
	 */
	public Maze()
	{
		numRows = 0;
		numCols = 0;
	}

	
	/**
	 * loads the maze from a file into an array
	 * @param fname name of file to load
	 * @return default returns true if maze is loaded
	 */
	public boolean loadMaze(String fname)
	{

		String line;
		BufferedReader inputStrem;
		StringTokenizer st;


		try {
			int currentRow = 0;

			inputStrem = new BufferedReader(new FileReader(fname));
			line = inputStrem.readLine();
			if(line != null)
			{
				st = new StringTokenizer(line);
				numRows = Integer.parseInt(st.nextToken());
				numCols = Integer.parseInt(st.nextToken());
				maze = new Square[numRows][numCols];
			}
			else {
				return false;
			}

			while ((line = inputStrem.readLine()) != null) {
				if (numRows == 0) 
				// true if reading first line in file,containing 
				// numRows numColums
				{  
					st = new StringTokenizer(line);
					numRows = Integer.parseInt(st.nextToken());
					numCols = Integer.parseInt(st.nextToken());
					maze = new Square[numRows][numCols];
				} else if (line.length() == 1)
					break; 
				else {
					int col=0;
					for (int c = 0; c < line.length(); c++) {

						if(line.charAt(c) == ' ') continue;
						maze[currentRow][col] = new Square(currentRow, col, 
							(char)Character.getNumericValue(line.charAt(c)));
						col++;
					}
					currentRow ++;
				}
			}
		}
		catch (IOException e) {
			System.out.println (e.toString());
			System.out.println("Could not find file " + fname);
		} 

		return true;
	}


	/**
	 * gets valid open spaces that are neightbors to given square
	 * @param sq, square whose neighbors wel ook for
	 * @return and ArrayList containing the neightbors in the order
	 *         north, east, south, west
	 */
	public ArrayList<Square> getNeighbors(Square sq)
	{
		ArrayList<Square> toReturn = new ArrayList<>();
		
		int row = sq.getRow(); // coordinates for this square
		int col = sq.getCol();
		
		if((row - 1) >= 0 && maze[row - 1][col].isValid()) // north
		{
			toReturn.add(maze[row - 1][col]);
		}
		if((col + 1) < numCols && maze[row][col + 1].isValid()) // east
		{
			toReturn.add(maze[row][col + 1]);
		}
		if((row + 1) < numRows && maze[row + 1][col].isValid()) // south
		{
			toReturn.add(maze[row + 1][col]);
		}
		if((col - 1) >= 0 && maze[row][col - 1].isValid()) // west
		{
			toReturn.add(maze[row][col - 1]);
		}
		
		return toReturn;
	}

	
	/**
	 * getter for starting square of maze
	 * @return the Square marked as the start
	 */
	public Square getStart()
	{
		Square toReturn = maze[numRows - 1][numCols - 1]; 
		// set to corner square in maze
		
		for(int i = 0; i < numRows; i++) // loop through rows
		{
			for(int j = 0; j < numCols; j++) // loop through columns
			{
				if(maze[i][j].isStart()) // if square marked as start
				{
					toReturn = maze[i][j]; 
					i = numRows; // will break out of loop, more efficient
					j = numCols;
				}
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * getter for the finish square in the maze
	 * @return
	 */
	public Square getFinish()
	{
		Square toReturn = maze[0][0]; // set to corner square by default
		
		for(int i = 0; i < numRows; i ++) // loop through rows
		{
			for(int j = 0; j < numCols; j++) // loop through columns
			{
				if(maze[i][j].isFinish()) // if square is marked as finish
				{
					toReturn = maze[i][j]; 
					i = numRows; // will break out of loop, more efficient
					j = numCols;
				}
			}
		}
		
		return toReturn;
	}

	
	/**
	 * marks specified square as visited
	 * @param row, vertical index of square
	 * @param col, horizontal index of square
	 */
	public void setVisit(int row, int col)
	{
		maze[row][col].setVisited();
	}

		
	/**
	 * clears all squares of maze from being visited, 
	 * resets the markers for the exploration path
	 */
	public void clearMaze() 
	{
		for(int i = 0; i < numRows; i ++) // loop through rows
		{
			for(int j = 0; j < numCols; j++) // loop through columns
			{
				maze[i][j].clearVisited();
				maze[i][j].setOnWorkList(false);
				
				if(maze[i][j].getType() == 'x' || maze[i][j].getType() == 'o' 
				   || maze[i][j].getType() == '.')
				{
					maze[i][j].setSpace();
				}
			}
		}
	}	
	
	
	/**
	 * getter for the maze array
	 * @return the maze stored in array in this class
	 */
	public Square[][] getMaze() 
	{
		return maze; ////XXX-CHANGE-XXX
	}
	
	
	/**
	 * returns a string representation of the maze
	 * @return String that represents array
	 */
	@Override
	public String toString() {

		String s="";
		for (int r = 0; r < numRows; r++) 
		{
			for (int c = 0; c < numCols; c++) 
				s=s+maze[r][c].toString();
			s=s+"\n";
		}
		return s;  
	}
	
} // end of Maze class
