/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/

package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;


/*
 * You should modify the information above to add your name 
 * 
 * Finally, when your tester is complete, you will rename it DoublyLinkedList12Tester.java 
 * (renaming LinkedList to DoublyLinkedList12 everywhere in the file) so that you can
 * use it to test your DoublyLinkedList12 and MyListIterator classes.
 */

/**
 * Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-01-21
 */
public class LinkedListTester
{
  private LinkedList<Integer> empty ;
  private LinkedList<Integer> one ;
  private LinkedList<Integer> several ;
  private LinkedList<String>  slist ;
  private LinkedList<String> pattern;
  static final int DIM = 5;
  
  
  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2)
   */ 
  @Before
  public void setUp()
  {
    empty = new LinkedList<Integer>();
    one = new LinkedList<Integer>();
    one.add(0,new Integer(0));
    several = new LinkedList<Integer>() ;
    
    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
      several.add(0,new Integer(i));
    
    // List: "First","Last"
    slist = new LinkedList<String>();
    slist.add("First");
    slist.add("Last");
    
    // List: "Hi", "Bye", "Hi", "Bye"
    pattern = new LinkedList<String>();
    pattern.add("Hi");
    pattern.add("Bye");
    pattern.add("Hi");
    pattern.add("Bye");    
  }
  
  
  /** Test if heads of the lists are correct */
  @Test
  public void testGetHead()
  {
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }
  
  
  /** Test if size of lists are correct */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ;
    assertEquals("Check One Size",1,one.size()) ;
    assertEquals("Check Several Size",DIM,several.size()) ;
  }
  
  
  /** Test setting a specific entry */
  @Test
  public void testSet()
  {
    slist.set(1,"Final");
    assertEquals("Setting specific value", "Final",slist.get(1));
  }
  
  
  /** Test isEmpty */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()) ;
    assertTrue("one is not empty",!one.isEmpty()) ;
    assertTrue("several is not empty",!several.isEmpty()) ;
  }

  
  /** Test out of bounds exception on get */
  @Test
  public void testGetException()
  {
    try 
    {
      empty.get(0);
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }

  
  /** Test iterator on empty list and several list */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); )
    {
      fail("Iterating empty list and found element") ;
    }
    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
      counter++;
    assertEquals("Iterator several count", counter, DIM);
  }
  
  
  /**
   * Test MyListIterator on empty and several list
   */
  @Test
  public void testMyListIterator()
  {
	  //int counter = 0;
	  //ListIterator<Integer> iter;
	  //for(iter = empty.list)
  }
  
  
  /**
   * Tests if an element is added to end of the list or given index
   */
  @Test
  public void testAdd()
  {
    assertTrue("Check add return", slist.add("Hello"));
    assertEquals("Check add", "Hello", slist.get(2));
    slist.add(1, "Bye");
    assertEquals("Check adding at index", "Bye", slist.get(1));
  }
  
  
  /**
   * Tests null pointer and index out of bounds exceptions on add
   */
  @Test
  public void testAddException()
  {
	try 
	{
	  slist.add(null);
	  // This is how you can test when an exception is supposed 
	  // to be thrown
	  fail("Should have generated an exception");  
	}
	catch(NullPointerException e)
	{
	  //  normal
	}
	
	try 
    {
      slist.add(7, "Hi");
      // This is how you can test when an exception is supposed 
      // to be thrown
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }
  
  
  /**
   * Test to get data at an index
   */
  @Test
  public void testGet()
  {
    assertEquals("Check get at index", "First", slist.get(0));
    // exception tester for get is above in starter code
  }
  
  
  /**
   * Test null pointer and index out of bounds exceptions on set
   */
  @Test
  public void testSetException()
  {
    try 
    {
	  slist.set(0, null);
	  fail("Should have generated an exception");  
	}
	catch(NullPointerException e)
	{
	  //  normal
	}
	
	try 
    {
      slist.set(5, "Hi");
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }
  
  
  /**
   * Test removing an element from an index
   */
  @Test
  public void testRemove()
  {
	slist.remove(0);
	assertEquals("Checking remove at index", "Last", slist.get(0));
  }
  
  
  /**
   * Test index out of bounds exception on remove
   */
  @Test
  public void testRemoveException()
  {
	try
	{
		slist.remove(5);
		fail("Should have generated an exception");
	}
	catch(IndexOutOfBoundsException e)
	{
	  // normal
	}
  }
  
  
  /**
   * Test clearing list
   */
  @Test
  public void testClear()
  {
	slist.clear();
	assertEquals("Check clear", 0, slist.size());
  }
  
  
  /**
   * Test if list contains an object
   */
  @Test
  public void testContains()
  {
    assertTrue("Checking one contains", one.contains(0));
    assertTrue("Checking slist contains", slist.contains("First"));
    assertTrue("Checking slist does not contain", !(slist.contains(0)));
  }
  
  
  /**
   * Test null pointer exception on contains
   */
  @Test
  public void testContainsException()
  {
    try
    {
      slist.contains(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
    	// normal
    }
  }
  
  
  /**
   * Test removing last instance of an object in list
   */
  @Test
  public void testRemoveLastOccurrence()
  {
    assertTrue("Removing last occurence", pattern.removeLastOccurrence("Hi"));
    assertEquals("Check removed object", "Bye", pattern.get(2));
    assertTrue("Removing last occurence", pattern.removeLastOccurrence("Bye"));
    assertEquals("Check removed object", 2, pattern.size());
  }
  
  
  /**
   * Test null pointer exception in removeLastOccurrence
   */
  @Test
  public void testRemoveLastOccurrenceException()
  {
    try
    {
      slist.removeLastOccurrence(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      // normal
    }
  }
  
  
  /**
   * Test the index of first occurrence of an object in list
   */
  @Test
  public void testIndexOf()
  {
    assertEquals("Check first instance of", 1, pattern.indexOf("Bye"));
    assertEquals("Check first instance of", 0, slist.indexOf("First"));
  }
  
  
  /**
   * Test null pointer exception in indexOf
   */
  @Test
  public void testIndexOfException()
  {
    try
    {
      slist.indexOf(null);
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      // normal
    }
  }
  
}

