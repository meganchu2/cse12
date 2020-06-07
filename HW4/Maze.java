package hw4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Maze {
	public Square[][] maze; // a 2D Maze 
	protected int numRows; // No of rows
	protected int numCols; // No of cols

	public Maze(){
		numRows = 0;
		numCols = 0;
	}

	public boolean loadMaze(String fname){

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
				if (numRows == 0) {  // true if reading first line in file, containing  numRows numColums
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
						maze[currentRow][col] = new Square(currentRow, col, Character.getNumericValue(line.charAt(c)));
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


	public ArrayList<Square> getNeighbors(Square sq){
		return null; //XXX-CHANGE-XXX
	}

	public Square getStart(){
		return null; //XXX-CHANGE-XXX
	}
	
	public Square getFinish(){
		return null; //XXX-CHANGE-XXX

	}

	public void setVisit(int row, int col)
	{
		//TODO
	}

	public void clearMaze() {
		//TODO
	}
	
	public Square[][] getMaze() {
		return null; ////XXX-CHANGE-XXX
	}

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


}
