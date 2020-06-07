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
import java.util.NoSuchElementException;


/**
 * Title: class DoublyLinkedListTester
 *  Description: JUnit test class for DoublyLinkedList class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-01-25
 */
public class DoublyLinkedListTester
{
  private DoublyLinkedList<Integer> empty ;
  private DoublyLinkedList<Integer> one ;
  private DoublyLinkedList<Integer> several ;
  private DoublyLinkedList<String>  slist ;
  private DoublyLinkedList<String> pattern;
  static final int DIM = 5;


  /**
   * Standard Test Fixture. An empty list, a list with one entry (0) and 
   * a list with several entries (0,1,2), a list with two entries,
   * a list with alternating entries
   */ 
  @Before
  public void setUp()
  {
    empty = new DoublyLinkedList<Integer>(); // empty list
    one = new DoublyLinkedList<Integer>(); // list with one element
    one.add(0,new Integer(0));
    several = new DoublyLinkedList<Integer>(); // list with 5 elements

    // List: 1,2,3,...,Dim
    for (int i = DIM; i > 0; i--)
    several.add(0,new Integer(i));

    // List: "First","Last"
    slist = new DoublyLinkedList<String>(); // list with 2 elements
    slist.add("First");
    slist.add("Last");

    // List: "Hi", "Bye", "Hi", "Bye"
    pattern = new DoublyLinkedList<String>(); 
    // list with 4 alternating elements
    pattern.add("Hi");
    pattern.add("Bye");
    pattern.add("Hi");
    pattern.add("Bye");    
  }


  /** 
   * Test if heads of the lists are correct 
   */
  @Test
  public void testGetHead()
  {
    // checks for correct element at index 0
    assertEquals("Check 0",new Integer(0),one.get(0)) ;
    assertEquals("Check 0",new Integer(1),several.get(0)) ;
  }


  /** 
   * Test if size of lists are correct 
   */
  @Test
  public void testListSize()
  {
    assertEquals("Check Empty Size",0,empty.size()) ; // equals 0
    assertEquals("Check One Size",1,one.size()) ; // equals 1
    assertEquals("Check Several Size",DIM,several.size()) ; // equals 5
  }


  /** 
   * Test setting a specific element at a given index
   */
  @Test
  public void testSet()
  {
    slist.set(1,"Final"); // sets index 1 to final
    assertEquals("Setting specific value", "Final",slist.get(1));

    try 
    {
      slist.set(0, null); // null data should throw exception
      fail("Should have generated an exception");  
    }
    catch(NullPointerException e)
    {
      //  normal
    }

    try 
    {
      slist.set(5, "Hi"); // out of bounds index should throw exception
      fail("Should have generated an exception");  
    }
    catch(IndexOutOfBoundsException e)
    {
      //  normal
    }
  }


  /** 
   * Test isEmpty returns true is list is empty
   */
  @Test
  public void testEmpty()
  {
    assertTrue("empty is empty",empty.isEmpty()); // empty
    assertTrue("one is not empty",!one.isEmpty()); // not empty
    assertTrue("several is not empty",!several.isEmpty()); // not empty
  }


  /**
   * Test to get data at an index
   */
  @Test
  public void testGet()
  {
    assertEquals("Check get at index", "First", slist.get(0));
    // first element in slist should be "First"

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


  /** 
   * Test iterator on empty list and several list 
   */
  @Test
  public void testIterator()
  {
    int counter = 0 ;
    ListIterator<Integer> iter;
    for (iter = empty.listIterator() ; iter.hasNext(); ) 
    // hasNext should never return true for empty list
    {
      fail("Iterating empty list and found element") ;
    }

    counter = 0 ;
    for (iter = several.listIterator() ; iter.hasNext(); iter.next())
    {
      counter++; // should add up to length of several
    }
    assertEquals("Iterator several count", counter, DIM);
  }


/**
   * Tests if an element is added to end of the list
   */
  @Test
  public void testAdd()
  {
    assertTrue("Check add return", slist.add("Hello"));
    // add should always return true
    assertEquals("Check add", "Hello", slist.get(2));
    // should add to end of list

    try 
    {
      slist.add(null); // if element is null, exception should be thrown
      fail("Should have generated an exception");  
    }
    catch(NullPointerException e)
    {
      //  normal
    }   
  }


  /**
   * Tests if an element is added to the list at the given index
   */
  @Test
  public void testAddAtIndex()
  {
    slist.add(1, "Bye");
    assertEquals("Check adding at index", "Bye", slist.get(1));
    // adding at an index should add at that index

    try 
    {
      slist.add(0, null); // if element is null, exception should be thrown
      fail("Should have generated an exception");  
    }
    catch(NullPointerException e)
    {
      //  normal
    }

    try 
    {
      slist.add(7, "Hi"); // if index out of bounds, exception thrown
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
    slist.remove(0); // removing first element will shift rest left
    assertEquals("Checking remove at index", "Last", slist.get(0));

    try
    {
      slist.remove(5); // if try to remove out of bounds index
      // exception should be thrown
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
    slist.clear(); // all nodes at all indexes are removed
    assertEquals("Check clear", 0, slist.size());
  }


  /**
   * Test if list contains an object
   */
  @Test
  public void testContains()
  {
    assertTrue("Checking slist contains", slist.contains("First"));
    assertTrue("Checking slist does not contain", !(slist.contains(0)));
    assertTrue("Checking one contains", slist.contains("Last"));

    try
    {
      slist.contains(null); // lists should never contain null element
      // should throw exception
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
    assertTrue("Removing last occurence", 
               pattern.removeLastOccurrence("Hi"));
    // only last Hi should be removed
    assertEquals("Check removed object", "Bye", pattern.get(2));
    assertEquals("Check removed object", 3, pattern.size());

    assertTrue("Removing last occurence", 
               pattern.removeLastOccurrence("Bye"));
    // only last Bye should be removed
    assertEquals("Check removed object", 2, pattern.size());

    try
    {
      slist.removeLastOccurrence(null); // list should never contain null
      // should throw exception
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

    try
    {
       slist.indexOf(null); // list should never contain null
       // should throw exception
       fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      // normal
    }
  }


  /**
   * Test MyListIterator hasNext reutrns true only if cursor not at tail
   */
  @Test
  public void testMLIHasNext()
  {
    ListIterator<Integer> iter = one.listIterator();

    // one element list should have next element
    assertTrue("Iterating list with one element", (iter.hasNext()));

    iter.next(); // after traversing first element,
    // list should not have anymore next elements
    assertTrue("Iterating list with one element", !(iter.hasNext()));
  }


  /**
   * Test MyListIterator next returns element to the right before 
   * moving up one index
   */
  @Test
  public void testMLINext()
  {
    ListIterator<Integer> iter = several.listIterator(); 

    int counter = 0;
    for(; iter.hasNext(); iter.next())
    {
      counter++; // should add up to size of list
    }
    assertEquals("Iterator several count", counter, DIM);

    try
    {
      iter = empty.listIterator();
      iter.next(); // calling next when list does not have a next element
      // should throw exception
      fail("Should have generated an exception");
    }
    catch(NoSuchElementException e)
    {
      // normal
    }
  }


  /**
   * Test MyListIterator hasPrevious returns true only if iterator is 
   * not next to head
   */
  @Test
  public void testMLIHasPrevious()
  {
    ListIterator<Integer> iter = one.listIterator();

    iter.next(); // at end of list
    assertTrue("Iterating list with one element", (iter.hasPrevious()));

    iter.previous(); // at beginning of list
    assertTrue("Iterating list with one element", !(iter.hasPrevious()));
  }


  /**
   * Test MyListIterator previous moves cursor down one index, and 
   * returns element that was on the left before the call
   */
  @Test
  public void testMLIPrevious()
  {
    ListIterator<Integer> iter = several.listIterator(); 

    for(;iter.hasNext(); iter.next())
    {
      // traverses to end of list
    }

    int prevCount = 0;
    for(;iter.hasPrevious(); iter.previous())
    {
      prevCount++; // should add up to size of list
    }
    assertEquals("Iterator several previous count", prevCount, DIM);

    try
    {
      iter = empty.listIterator();
      iter.previous(); // empty list should never have previous
      // should throw exception
      fail("Should have generated an exception");
    }
    catch(NoSuchElementException e)
    {
      // normal
    }
  } 


  /**
   * Test MyListIterator add, puts new element between left and right 
   * nodes of cursor, and puts cursor after new element
   */
  @Test
  public void testMLIAdd()
  {
    ListIterator<String> iter = slist.listIterator();

    iter.next(); // between first and second nodes
    iter.add("Hi"); // adds "Hi" between first and second nodes
    assertEquals("Test Iterator Add", "Hi", iter.previous());

    try
    {
      iter.add(null); // cannot add null element, should throw exception
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      // normal
    }
  }


  /**
   * Test MyListIterator nextIndex that returns index of element of 
   * next call to next()
   */
  @Test
  public void testMLINextIndex()
  {
    ListIterator<Integer> iter = several.listIterator();

    iter.next(); // returned element at index 0
    assertEquals("Test next index", 1, iter.nextIndex());
  }


  /**
   * Test MyListIterator previousIndex returns index of element of 
   * next call to previous()
   */
  @Test
  public void testMLIPrevIndex()
  {
    ListIterator<Integer> iter = several.listIterator();

    iter.next(); // reutned element at index 0
    assertEquals("Test previous index", 0, iter.previousIndex());
  }


  /**
   * Test MyListIterator remove, removes element returned by last 
   * call to next() or previous()
   */
  @Test
  public void testMLIRemove()
  {
    ListIterator<String> iter = pattern.listIterator();

    iter.next(); // between first Hi and Bye
    iter.next(); // between first Bye and second Hi
    iter.remove(); // should remove first Bye
    assertEquals("Test remove", "Hi", iter.previous());
    iter.remove(); // removes first Hi

    try
    {
      iter.remove(); // cannot call without call to next or previous
      // should throw exception
      fail("Should have generated an exception");
    }
    catch(IllegalStateException e)
    {
      // normal
    }
  }


  /**
   * Test MyListIterator set, places new element in last node 
   * returned by next or previous
   */
  @Test
  public void testMLISet()
  {
    ListIterator<String> iter = pattern.listIterator();

    iter.next(); // between first Hi and Bye
    iter.set("Fun"); // sets Fun between first Hi and Bye
    assertEquals("Test remove", "Fun", iter.previous());

    try
    {
      iter.set(null); // cannot have null element in list
      // should throw exception
      fail("Should have generated an exception");
    }
    catch(NullPointerException e)
    {
      // normal
    }

    try
    {
      iter.next();
      iter.remove();
      iter.set("Hi"); // cannot call set unless next or previous was 
      // called immediately before, should throw exception
      fail("Should have generated an exception");
    }
    catch(IllegalStateException e)
    {
      // normal
    }
  }  

} // end of DoublyLinkedListTester class


