package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


/**
 * Title: class MazeTester
 *  Description: tester class for Maze class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MazeTester 
{

	private Maze maze;
	private Square[][] mazeArray;
	
	
	/**
	 * initialize and loads the maze and an array to store it
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		maze = new Maze();
		maze.loadMaze("maze-4.txt");
		mazeArray = maze.getMaze();
	}

	
	/**
	 * tests the loadMaze method
	 */
	@Test
	public void testLoadMaze() 
	{
		assertTrue("Check load", (mazeArray != null));
	}

	
	/**
	 * test the betNeightbors method
	 */
	@Test
	public void testGetNeighbors() 
	{
		ArrayList<Square> toCompare = maze.getNeighbors(mazeArray[9][8]);
		
		assertEquals("Check neighbors", 2, toCompare.size()); // tests size
		
		assertEquals("Check neighbors", 8, toCompare.get(0).getRow());
		assertEquals("Check neighbors", 8, toCompare.get(0).getCol());
		// tests indexes of one neighbor
		
		assertEquals("Check neighbors", 9, toCompare.get(1).getRow());
		assertEquals("Check neighbors", 7, toCompare.get(1).getCol());
	}

	
	/**
	 * tests getStart method
	 */
	@Test
	public void testGetStart() 
	{
		assertEquals("Checking start", 0, maze.getStart().getRow());
		assertEquals("Checking start", 1, maze.getStart().getCol());
		// indexes should match
	}

	
	/**
	 * tests the getFinish method
	 */
	@Test
	public void testGetFinish() 
	{
		assertEquals("Checking Finish", 5, maze.getFinish().getRow());
		assertEquals("Checking Finish", 4, maze.getFinish().getCol());
		// indexes should match
	}

	
	/**
	 * tests the setVIsit method
	 */
	@Test
	public void testSetVisit() 
	{
		assertTrue("Checking visit", !(mazeArray[0][0].isVisited()));
		// maze has not been visited
		
		maze.setVisit(0, 0);
		assertTrue("Checking visit", mazeArray[0][0].isVisited());
		// maze should have been marked as visited
	}

	
	/**
	 * tests clearMaze method
	 */
	@Test
	public void testClearMaze() 
	{
		maze.setVisit(0, 0);
		maze.clearMaze();
		
		assertTrue("Checking clear", !(mazeArray[0][0].isVisited()));
		assertTrue("Checking clear", !(mazeArray[1][1].isVisited()));
		// maze that was visited and maze that was not visited should 
		// all not be visited		
	}

	
	/**
	 * tests getMaze method, and prints out the returned array
	 */
	@Test
	public void testGetAndPrintMaze() 
	{
		int rows = mazeArray.length;
		int cols = 0;
		if(mazeArray.length > 0) // if not empty array
		{
			cols = mazeArray[0].length; // get number of columns
		}
		
		for(int i = 0; i < rows; i++) // loop through rows
		{
			for(int j = 0; j < cols; j++) // loop through columns
			{
				System.out.print(mazeArray[i][j].getType() + " ");
			}
			System.out.println();
		}
	}

	
	/**
	 * tests the toString method
	 */
	@Test
	public void testToString() 
	{
		assertEquals("Checking toString", "#", mazeArray[1][1].toString());
		assertEquals("Checking toString", "_", mazeArray[0][0].toString());
		assertEquals("Checking toString", "S", mazeArray[0][1].toString());
		assertEquals("Checking toString", "E", mazeArray[5][4].toString());
	}

} // end of MazeTester class
