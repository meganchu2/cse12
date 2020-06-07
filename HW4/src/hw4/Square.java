package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class Square
 *  Description: class to describe each coordinate in maze
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class Square 
{
	private int row;
	private int col;
	private char type;
	private boolean visited;
	private Square previous; // links to previous square in maze
	private boolean onFinalPath; // tells if this square is on solution path
	private boolean onWorklist; // if this square was added to the worklist 
	private int start = 2;
	private int finish = 3;
	
	
	/**
	 * constructor for Square, initializes variables
	 * @param row the row index of coordinate
	 * @param col the column index of coordinate
	 * @param type indicator for wall, space, start, or exit
	 */
	public Square(int row, int col, char type)
	{
		this.row = row;
		this.col = col;
		this.type = type;
		previous = null;
		onWorklist = false;
	}
	
	
	/**
	 * returns the string representing what each type indicates
	 * @return String, representation of this Square
	 */
	public String toString()
	{
		switch(type)
		{
		case 0:
			return "_";
		case 1:
			return "#";
		case 2:
			return "S";
		case 3:
			return "E";
		case 'x':
			return "x";
		case 'o':
			return "o";
		case '.':
			return ".";
		}
		return null;
	}
	
	
	/**
	 * getter for the row
	 * @return int, the row value
	 */
	public int getRow()
	{
		return row;
	}
	
	
	/**
	 * getter for the column
	 * @return int, the column value
	 */
	public int getCol()
	{
		return col;
	}
	
	
	/**
	 * getter for the type
	 * @return int, the type value
	 */
	public int getType()
	{
		return type;
	}
	
	
	/**
	 * tells if this square is the finish
	 * @return true if the type is 3
	 */
	public boolean isFinish()
	{
		if(type == 3)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * tells if this square is the start
	 * @return true if the type is 2
	 */
	public boolean isStart()
	{
		if(type == 2)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * tells if this square can be explored
	 * @return true if type is not 1 or 2 (wall or start)
	 */
	public boolean isValid()
	{
		if(type != 1 && type != 2)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * tells if this square has been explored
	 * @return true if square has been visited
	 */
	public boolean isVisited()
	{
		if(visited == true)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * marks this square as visited
	 */
	void setVisited()
	{
		visited = true;
		setX();
	}
	
	
	/**
	 * marks this square as not visited, and its pointers to null
	 */
	void clearVisited()
	{
		visited = false;
		previous = null;
	}
	
	
	/**
	 * sets the previous square
	 * @param q the previous square that called this one
	 */
	public void setPrevious(Square q)
	{
		previous = q;
	}
	
	
	/**
	 * getter for the previous square of this one
	 * @return the previous square
	 */
	public Square getPrevious()
	{
		return previous;
	}
	
	
	/**
	 * sets this square to be on the final path
	 */
	public void setFinalPath()
	{
		onFinalPath = true;
	}
	
	
	/**
	 * tells if on the final path
	 * @return true if square is on final path
	 */
	public boolean onFinalPath()
	{
		if(onFinalPath)
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * sets the representation of this square
	 */
	public void setX()
	{
		if(type == 0 || type == 'o')
		{
			type = 'x';		
		}
	}
	
	
	/**
	 * sets the representation of this square
	 */
	public void setO()
	{
		type = 'o';
	}
	
	
	/**
	 * sets the representation of this square
	 */
	public void setDot()
	{
		if(!onFinalPath && visited && type != start)
		{
			type = '.';		
		}
	}
	
	
	/**
	 * sets the representation of this square
	 */
	public void setSpace()
	{
		type = 0;		
	}
	
	
	/**
	 * tells whether square is on worklist already
	 * @return boolean true if square is on worklist
	 */
	public boolean onWorklist()
	{
		if(onWorklist)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * setter for whether square is on worklist
	 * @param b, boolean which to set for square
	 */
	public void setOnWorkList(boolean b)
	{
		if(b == true && type == 0)
		{
			type = 'o';
		}
		onWorklist = b;
	}
	
} // end of Square class
