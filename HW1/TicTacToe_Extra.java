/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/


package hw1;

import java.util.Scanner;
import java.util.Random;


/**
* A class that runs a tic tac toe game
*
* @version 1.0
* @author  Megan Chu
* @since   2017-01-15
*/
public class TicTacToe_Extra
{

  static char[] [] board = new char[3][3];
  static Scanner input=new Scanner(System.in);

  //Object of Stats class to maintain statistics
  static Stats stat = new Stats();

  // index of boxes on board
  public static int firstBox = 0;
  public static int secondBox = 1;  
  public static int thirdBox = 2;
  public static int boardSize = 3;

  // keeps track of row and column of next move
  public static int row;
  public static int col;



  /**
   * Prints the TicTacToe board
   * @param arr: The board so far
   */
  public static void printBoard(char [][] arr)
  {
    System.out.println();

    for (int i=0; i<3; i++) // for every row
    {
      for (int j=0; j<3; j++) // for every column
      {
        System.out.print(arr[i][j]);
        if(j!=2)
        {
          //Print the | for readable output
          System.out.print(" " + "|" + " ");  
        }  
      } // row is printed

      System.out.println();

      if(i!=2) 
      {
        System.out.print("_   _   _ ");    // Print _ for readability
        System.out.println();;
      } // next row will print after
    }
  }
	


  /**
   * Clear the TicTacToe board before starting a new game
   * @param arr: The board so far
   */
  public static void clearBoard(char [][] arr)
  {
    for(int i = 0; i < boardSize; i++) // for ever row
    {
      for(int j = 0; j < boardSize; j++) // for every column
      {
        arr[i][j] = ' '; // makes each box on board empty
      }
    }
  }
	


  /** 
   * Determines if the player with the specified token wins
   * @param symbol: Specifies whether the player is X or O
   * @return true if player has won, false otherwise
   */
  public static boolean isWon(char symbol) 
  {
    for (int i = 0; i < boardSize; i++) //horizontal
    {
      if (board[i][0] == symbol && board[i][1] == symbol && 
          board[i][thirdBox] == symbol) // horizontal
      {
        return true;
      }
      else if(board[0][i] == symbol && board[1][i] == symbol && 
              board[thirdBox][i] == symbol) //vertical
      {
        return true;
      }
    }

    if (board[secondBox][secondBox] == symbol) // middle box
    {
      if(board[firstBox][firstBox] == symbol && board[thirdBox][thirdBox] 
         == symbol) // diagonally down
      {
        return true;
      }
      else if(board[firstBox][thirdBox] == symbol && 
              board[thirdBox][firstBox] == symbol) // diagonally up
      {
        return true;
      }
    }
    return false;
  }



  /**
   * Determines ifbox is occupied
   * @param row: row index of box
   * @param col: column index of box
   * @return true if box is empty, false otherwise
   */
  public static boolean isOccupied(int row, int col)
  {
    if(board[row][col] != ' ')
    {
      return true;
    }
    else
    {
      return false;
    }
  }

 

  /**
   * Genetrates random 0 or 1
   * @return 0 or 1, which determines a user or computer start
   */
  public static int whoStarts()
  {
    Random starter = new Random();

    // random integer 0 inclusive to 2 exclusive
    int i = starter.nextInt(thirdBox); 
    return i;
  }
	  


  /** 
   * takes care of the human's move
   * @param symbol: the character assigned to the user
   * @return returns true if the user has won
   */
  public static boolean humanTurn(char symbol)
  {
    // Prompt for a row, then column
    System.out.print("\n\nEnter your move: (row column): " );
    int row = input.nextInt();
    int col = input.nextInt();

    while(isOccupied(row, col))
    {
      System.out.print("\nSorry that cell is already occupied. Please " + 
                         "try again.");
      System.out.print("\n\nEnter your move: (row column): " );
      row = input.nextInt();
      col = input.nextInt();
    }

    // Puts a symbol (X or O) on the board, and prints the updated board
    board[row][col] = symbol;
    printBoard(board);

    if(isWon(symbol)) // If human wins: print, update stats and return true
    {
      stat.incrementUserWins();
      System.out.print("\n\nYou won!!!");
      return true;
    }
    else //  If not a win yet, return false
    {
      return false;
    }
  } 



  /**
   * checks best move for computer
   * @param symbol: the character we are checking for
   */
  public static boolean bestLinearMove(char symbol)
  {
    boolean goodMove = false;
    for (int i = 0; i < boardSize; i++) 
    {
      if ((board[i][0] == symbol && (board[i][1] == symbol || 
	  board[i][thirdBox] == symbol)) || (board[i][1] == symbol && 
	  board[i][thirdBox] == symbol)) // horizontal almost wins
      {
	if(!isOccupied(i,0)) // put move in empty space in row
	{
	  col = 0;
	}
	else if(!isOccupied(i,1))
	{
	  col = 1;
	}
	else
	{
	  col = thirdBox;
	}
        row = i;
        goodMove = true;
        break;          
      }
      else if((board[0][i] == symbol && (board[1][i] == symbol || 
	      board[thirdBox][i] == symbol)) || (board[1][i] == symbol && 
	      board[thirdBox][i] == symbol)) // vertical almost wins
      {
	if(!isOccupied(0,i)) // put move in empty space in column
	{
	  row = 0;
	}
	else if(!isOccupied(1,i))
	{
	  row = 1;
	}
	else
	{
	  row = thirdBox;
	}
	col = i;
	goodMove = true;
	break;
      }
    }

    if(goodMove) // if we have decided on a good move
    {
      return true;
    }
    return false; // if we have not found a good move
  }



  /**
   * checks for the best diagonal move
   * @param symbol: the symbol we are checking for
   */
  public static boolean bestDiagonalMove(char symbol)
  {
    if ((board[secondBox][secondBox] == symbol && (board[firstBox][firstBox]
        == symbol || board[thirdBox][thirdBox] == symbol)) || 
        (board[firstBox][firstBox] == symbol && board[thirdBox][thirdBox] 
        == symbol)) // diagonally down almost wins
    {
      if(!isOccupied(firstBox,firstBox)) // move in empty space in diagonal
      {
	row = firstBox;
	col = firstBox;
      }
      else if(!isOccupied(secondBox,secondBox))
      {
	row = secondBox;
	col = secondBox;
      }
      else if(!isOccupied(thirdBox,thirdBox))
      {
	row = thirdBox;
	col = thirdBox;
      }
      return true; // we have found a good move
    }
    else if((board[secondBox][secondBox] == symbol && 
            (board[firstBox][thirdBox] == symbol || board[thirdBox][firstBox]
            == symbol)) || (board[firstBox][thirdBox] == symbol && 
            board[thirdBox][firstBox] == symbol)) // diagonal up almost wins
    {
      if(!isOccupied(firstBox,thirdBox)) // move in empty space in diagonal
      {
	row = firstBox;
	col = thirdBox;
      }
      else if(!isOccupied(secondBox,secondBox))
      {
	row = secondBox;
	col = secondBox;
      }
      else if(!isOccupied(thirdBox,firstBox))
      {
	row = thirdBox;
	col = firstBox;
      }
      return true; // we have found a good move
    }
    return false; // we have not found a good mvoe yet  
  } 
	  


  /** 
   * takes care of the computer's move
   * @param symbol: the character assigned to the computer
   * @return returns true if the computer has won
   */
  public static boolean compTurn(char symbol) 
  {
    Random starter = new Random();
    row = starter.nextInt(boardSize); // in case a good move is not found
    col = starter.nextInt(boardSize);

    boolean goodMove = false; // we have yet to find a good move
    char storeSymbol = symbol;

    for (int j = 0; j <= 1; j++) // loop once for each symbol
    // this checks for a move to win or to prevent opponent from winning
    {
      if(bestLinearMove(symbol)) // if we find a good move
      {
        break;
      }
      else if(bestDiagonalMove(symbol)) // if we find a good move
      {   
        break; 
      }

      if(symbol == 'X') // switches the symbol to opponents symbol
      // this is so we can check if opponent will win on next move
      {
        symbol = 'O';
      }
      else
      {
        symbol = 'X';
      }
    }
    
    symbol = storeSymbol; // set symbol back to one assigned
    while(isOccupied(row,col)) // if we found no good moves
    {
      row = starter.nextInt(boardSize);
      col = starter.nextInt(boardSize);
    }
    
    System.out.println("\n\nMy Move is: "+row+" "+ col);
    board[row][col] = symbol; // Puts a symbol (X or O) on the board
    printBoard(board); // Prints the updated board

    if(isWon(symbol)) // If comp wins: print, update stats and return true
    {
      stat.incrementComputerWins();
      System.out.print("\n\nI won");
      return true;
    }
    else // If not a win yet, return false
    {
      return false;
    }
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
  public static void humanFirst()
  {
    boolean done=false;
    
    for (int i=0; i<= boardSize; i++) 
    {	
      if (humanTurn('X')) // if human has won after this turn
      {
        done=true;
        break;
      }
      if (compTurn('O')) // if computer has won after this turn
      {
        done=true;
        break;
      }
    }  //end of for loop;

    if (!done) // if no one has won yet
    {
      if (!humanTurn('X')) // if user doesn't win after last move
      {
        System.out.print("\n\nA tie!");
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
  public static void compFirst()
  {
    boolean done=false;

    compTurn('X'); // computer goes first
    for (int i = 0; i <= boardSize; i++) 
    {	
      if (humanTurn('O')) // if human has won after this move
      {
        done=true;
        break;
      }
      if (compTurn('X')) // if computer has won after this move
      {
        done=true;
        break;
      }
    }  //end of for loop;

    if (!done) // if no one has won yet
    {
      System.out.print("\n\nA tie!");
      stat.incrementTies();
    }
  }



  /**
   * main method that runs the game
   * @param args: string array that user can input when running game
   */
  public static void main(String[] args) 
  {
    // input from the user, if he wants to play another game
    String playAgain=""; 

    // input from the user, if he wants to clear stats
    String clearStats=""; 

    do //play until 'n' is pressed
    {      
      clearBoard(board);   //clear the baord
      clearStats=""; // clear clearStats preference

      //Generate Random Assignment, determines who goes first;
      int move = whoStarts();

      if (move == 0) 
      {
        System.out.println("\nI start first. I choose X and you get 0");
        compFirst();
      }
      else
      { 
        System.out.println("\nYou start first. You get X and I get 0");
        humanFirst(); 
      }

      //Print statistics and ask if a user wants to repeat a game
      System.out.print("\n\nStatistics");
      stat.printStats();
      System.out.print("\nPlay again? ");
      playAgain = input.next();

      // If user enters 'y', ask to clear statistics
      if(playAgain.charAt(0) == 'y')
      {
        System.out.print("\nClear statistics? ");
        clearStats = input.next();

        //  if user enters 'y', clear statistics and restart the game
        if(clearStats.charAt(0) == 'y') 
        {
          stat.reset();
        }
      }
    } while(playAgain.charAt(0)!='n'); //done with the outer loop
        
    System.out.println("\nBye, see you later!");
  }
} // end of TicTacToe_Extra class
