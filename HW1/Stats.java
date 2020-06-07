/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/


package hw1;


/**
* A class that implements a simple statistic tracking array
*
* @version 1.0
* @author  Megan Chu
* @since   2017-01-15
*/ 
public class Stats 
{

  //private array of size 3
  private int[] Stat = new int[3];

  //makes a fraction a percentage
  private int percent = 100;

  //refers to corresponding cells in the array
  private int first = 0;
  private int second = 1;
  private int third = 2;


  /**
   * creates an empty array 
   */
  public Stats()
  {
    // Nothing to do here
  }


  /**
   * Creates an array with specified values
   * for JUnit testers
   * @param first: assigned to the first cell
   * @param second: assigned to the second cell
   * @param third: assigned to the third cell
   */
  public Stats(int first, int second, int third)
  {
    Stat[0] = first;
    Stat[1] = second;
    Stat[2] = third;
  }


  /**
   * Calculates the number of games played
   * @return The total number of played games
   */
  public int getTotalGames() 
  {
    return Stat[first]+Stat[second]+Stat[third];
  }


 /**
  * Increments the number of games won by a user
  */ 
  public void incrementUserWins() 
  {
    Stat[first]++;
  }


 /**
  * Increments the number of games won by a computer
  */ 
  public void incrementComputerWins() 
  {
    Stat[second]++;
  }


 /**
  * Increments the number of ties between a user and a computer
  */ 
  public void incrementTies() 
  {
    Stat[third]++;
  }


  /**
   * returns average percentage of wins or ties
   * @param choice: depending on the value of choice 
   * the corresponding average (percent) is returned:
   * if choice is 0, return the average for a user
   * if choice is 1, return the average for a computer
   * if choice is 2, return the average for ties
   * otherwise return -1
   * @return percentage of won games or ties, depending on the 
   * parameter choice.
   */
  public int averageGames(int choice)
  {
    if(getTotalGames() == 0)
    {
      return 0;
    }

    else if(choice == first) // average for a user
    {
       // average % calculated by wins/total games * 100
       return (int)(percent*Stat[first]/getTotalGames());
    }

    else if(choice == second) // average for a computer
    {
      return (int)(percent*Stat[second]/getTotalGames());
    }

    else if(choice == third) // average for ties
    {
      return (int)(percent*Stat[third]/getTotalGames());
    }

    else // if choice variable is neither 0, 1, or 2
    {
      return (-1);
    }	     
  }


 /**
  * Prints the statistics message
  */
  public void printStats()
  {
    System.out.println("  I won: " + averageGames(second) + "%   You won: " 
                       + averageGames(first) + "%    We tied: "+ 
                       averageGames(third) + "%\n");
  }


 /**
  * Resets the statistic array if the player wants to clear stats again
  */ 
  public void reset() 
  {
    // makes elements of Stat array equal to 0
    Stat[first] = 0;
    Stat[second] = 0;
    Stat[third] = 0;
  }


 /**
  * 
  */
  public void resetWrong() // wrong reset method that must fail
  {
    // makes elements of Stat array not equal to 0
    Stat[first] = -1;
    Stat[second] = -1;
    Stat[third]= -1;
  }

} // end of Stats class

