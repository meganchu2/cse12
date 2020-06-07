package hw1;

import java.util.Scanner;
import java.util.Random;


public class HW1 {

	static char[] [] board = new char[3][3];
	static Scanner input=new Scanner(System.in);


	//Object of Stats class to maintain statistics
	static Stats stat = new Stats();  

	
	/**
	 * Prints the TicTacToe board
	 * @param arr: The board so far
	 */
	public static void printBoard(char [][] arr){
		System.out.println();
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<3; j++)
			{
				System.out.print(arr[i][j]);
				if(j!=2)

					//Print the | for readable output
					System.out.print(" " + "|" + " ");    
			}
			System.out.println();
			if(i!=2) {
				System.out.print("_   _   _ ");    // Print _ for readability
				System.out.println();;
			}
		}
	}
	
	/**
	 * Clear the TicTacToe board before starting a new game
	 * @param arr: The board so far
	 */
	public static void clearBoard(char [][] arr){
		//TODO
	}
	
	/** Determines if the player with the specified token wins
	 * 
	 * @param symbol: Specifies whether the player is X or O
	 * @return true if player has won, false otherwise
	 */
	  public static boolean isWon(char symbol) {
		  
	    for (int i = 0; i < 3; i++) //horizontal
	      if (board[i][0] == symbol 
	          && board[i][1] == symbol
	          && board[i][2] == symbol) {
	        return true;
	      }

	    //TODO!!! Complete the method

	    return false;
	  }

	 
	  public static boolean isOccupied(int row, int col){
		  
		//TODO
	  }
	  
	  public static int whoStarts(){
		  //TODO: Randomly chooses between 0 and 1 and returns the choice
	  }
	  
	  /** takes care of the human's move
	   * 1. Prompt for a cell, then column
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a human wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false */
	  
	  public static boolean humanTurn(char symbol){
		  
		  System.out.print("\n\nEnter your move: (row column): " );
			int row = input.nextInt();
			int col = input.nextInt();
			
			//TODO!! Complete the method!
			
			return true;
	  } 
	  
	  /** takes care of the computer's move
	   * 1. Generates numbers until finds an empty cell
	   * 2. Puts a symbol (X or O) on the board
	   * 3. Prints the updated board
	   * 4. If a comp wins: prints, updates stats and returns true
	   * 5. If not a win yet, returns false */
	  
	  public static boolean compTurn(char symbol) {
		  
		    int row, col;
		   // TODO!!!
		   
	        
			System.out.println("\n\nMy Move is: "+row+" "+ col);

			//TODO!!!!
			
			
	       return true;
			
	  }
	  
	  /** If human goes first:
	   * We have 9 moves in total (max). 8 moves will be in a loop
	   * and the last human move is outside of the loop:
	   * 1. human goes first, with a X
	   * 2. If the returned value is true (human won), then boolean flag=true
	   *    and we break out of the loop. done indicates that the game is over.
	   * 3. If the game is not over, then it is computer's turn. 
	   * 4. If the returned value is true (comp won), then boolean flag=true
	   *    and we break out of the loop. done indicates that the game is over
	   * 5. Repeat the two steps above 3 more times. 
	   * 6. If the done is still false, then a human performs one more move and
	   * we check if the move led to the win or tie.    
	   * */
	  
	  public static void humanFirst(){
			boolean done=false;

		  for (int i=0; i<4; i++) {	
				if (humanTurn('X')) {
					done=true;
					break;
				}
				if (compTurn('O')){
					done=true;
					break;
				}
			  }  //end of for loop;
		    if (!done){
		    	if (!humanTurn('X')) {
		    		System.out.println("\n\nA tie!");
		    		stat.incrementTies();
		    	}
			}
	  }
	  
	  /**
	   * Same logic as above, only the first computer's move happens before
	   * the loop. We do not need to check for winning combination here, since
	   * comp can't win after one move. 
	   * After the loop we check if the game is done. If not, report a tie and
	   * update statistics.
	   */
	  
	  public static void compFirst(){
	    
	    //TODO: Complete the method
		  
	  }
	  
	public static void main(String[] args) {
		
		// input from the user, if he wants to play another game
		String playAgain=""; 

		// input from the user, if he wants to clear stats
		String clearStats=""; 
		
		do {      //play until 'n' is pressed
		clearBoard(board);   //clear the baord

		//Generate Random Assignment, determines who goes first;
		int move = whoStarts();
		if (move == 0) {
			System.out.println("\nI start first. I choose X and you get 0");
			computerFirst();
		}
		else{ 
			System.out.println("\nYou start first. You get X and I get 0");
			humanFirst(); 
		}
		
		//TODO!!!
		
		//Print statistics and ask if a user wants to repeat a game
		// If user enters 'y', ask to clear statistics
		      //  if user enters 'y', clear statistics and restart the game
		      //If user enters 'n', continue without clearing
		// //If user enters 'n', quit the game
			  
			
		} while(playAgain.charAt(0)!='n'); //done with the outer loop
	    
	    System.out.println("\nBye, see you later!");
	}
}
