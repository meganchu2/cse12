/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/


package hw1;

import org.junit.*;
import static org.junit.Assert.*;


/**
* A class that tests the methods in a simple statistic tracking array class
*
* @version 1.0
* @author  Megan Chu
* @since   2017-01-15
*/
public class StatsTest 
{
  private Stats stat;

  /* this sets up the test fixture. JUnit invokes this method before
     every testXXX method.  
     The @Before tag tells JUnit to run this method 
     before each test */
  @Before
  public void setUp() throws Exception 
  {
    stat = new Stats(1, 2, 3);
  }


  /* 
   * Tests the total games method
   * The @Test tag tells JUnit this is a test to run 
   */
  @Test
  public void testgetTotalGames() 
  {
    System.out.println("Checking getTotalGames");
    assertEquals(6, stat.getTotalGames());
  }


  /*
   * Tests increment methods for wins and ties
   */ 
  @Test
  public void testIncrements() 
  {
    System.out.println("Checking Proper Increment");
    stat.incrementComputerWins();
    assertEquals(7, stat.getTotalGames());
    stat.incrementUserWins();
    assertEquals(8, stat.getTotalGames());
    stat.incrementTies();
    assertEquals(9, stat.getTotalGames()); 
  }
	

  /*
   * Tests the reset method
   */
  @Test
  public void testReset() 
  {
    System.out.println("Checking Reset");
    stat.reset();
    assertEquals(0, stat.getTotalGames());
  }


  /* 
   * a test that verifies the proper calculation of the average for all 
   * games make sure to test a case where no games were played.  
   */
  @Test
  public void testAverageGames()
  {
    System.out.println("Checking Average Games");
    assertEquals((int)(100*1/6), stat.averageGames(0));
    assertEquals((int)(100*2/6), stat.averageGames(1));
    assertEquals((int)(100*3/6), stat.averageGames(2));

    Stats stat2 = new Stats(0, 0, 0);
    assertEquals(0, stat2.averageGames(0));
    assertEquals(0, stat2.averageGames(1));
    assertEquals(0, stat2.averageGames(2));
  }
		

  /*
   * a test that verifies the resetWrong method. This test must FAIL
   */
   @Test
  public void testResetWrong() 
  {
    System.out.println("Checking ResetWrong");
    stat.resetWrong();
    assertEquals(0, stat.getTotalGames());
  }

} // end of StatsTest class
