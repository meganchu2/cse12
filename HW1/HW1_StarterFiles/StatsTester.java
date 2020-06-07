/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/

package hw1;
import org.junit.*;
import static org.junit.Assert.*;


public class StatsTester 
{

  /* TODO: Add your name, login, and ID as specified in the instructions */
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


  /* The @Test tag tells JUnit this is a test to run */
  @Test
  public void testgetTotalGames() 
  {
    System.out.println("Checking getTotalGames");
    assertEquals(6, stat.getTotalGames());
  }


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
	

  @Test
  public void testReset() 
  {
    System.out.println("Checking Reset");
    stat.reset();
    assertEquals(0, stat.getTotalGames());
  }

  /* TODO: write a test that verifies the proper calculation of the average for all games  
   * make sure to test a case where no games were played.  
   */
  @Test
  public void testAverageGames
		

  /*TODO: write a test that verifies the resetWrong method. This test must FAIL*/

}

