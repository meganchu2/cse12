package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 * Title: class DoubleEndedLLTester
 *  Description: tester class for DoubleEndedLL class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class DoubleEndedLLTester 
{
	private DoubleEndedLL<Integer> empty ;
	private DoubleEndedLL<Integer> one ;
	private DoubleEndedLL<Integer> several ;

	/**
	 * sets up tester lists
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		empty = new DoubleEndedLL<>(); // empty list
		
		one = new DoubleEndedLL<>(); // list with 1 element
		one.addLast(1);
		
		several = new DoubleEndedLL<>(); // list with 7 elements
		several.addLast(0);
		several.addLast(1);
		several.addLast(2);
		several.addLast(2);
		several.addLast(3);
		several.addLast(2);
		several.addLast(3);
		
	}

	
	/**
	 * tests the constructor for DoubleEndedLL
	 */
	@Test
	public void testDoubleEndedLL() 
	{
		DoubleEndedLL<Integer> test = new DoubleEndedLL<>();
		assertEquals("Checking constructor", 0, test.size());
	}

	
	/**
	 * tests isEmpty method
	 */
	@Test
	public void testIsEmpty() 
	{
		assertTrue("Checking empty", empty.isEmpty());
		assertTrue("Checking not empty", !(one.isEmpty()));
	}

	
	/**
	 * tests size method
	 */
	@Test
	public void testSize() 
	{
		assertEquals("Checking empty size", 0, empty.size());
		assertEquals("Checking one size", 1, one.size());
		assertEquals("Checking several size", 7, several.size());
	}

	
	/**
	 * tests add first method
	 */
	@Test
	public void testAddFirst() 
	{
	    empty.addFirst(1); // will test size and value at first index
	    assertEquals("Checking Add First", 1, empty.size());
	    assertEquals("Checking Add First", 1, (int)empty.getHeadValue());
	    
	    several.addFirst(7);;
	    assertEquals("CHecking AddFirst", 7, (int)several.getHeadValue());
	}

	
	/**
	 * tests add last method
	 */
	@Test
	public void testAddLast() 
	{
		empty.addLast(7); // tests size and value at end of list
		assertEquals("Checking Add Last", 1, empty.size());
		assertEquals("Checking Add Last", 7, (int)empty.getTailValue());
		
	    several.addLast(7);;
	    assertEquals("Checking Add Last", 7, (int)several.getTailValue());
	}

	
	/**
	 * tests remove first method, throws exception if list is empty
	 */
	@Test
	public void testRemoveFirst() 
	{
		assertEquals("Checking Remove First", 1, (int)one.removeFirst());
		assertEquals("Checking Remove First", 0, one.size()); 
		// check size after remove
		
		assertEquals("Checking Remove First", 0, (int)several.removeFirst()); 
		
		try
		{
			empty.removeFirst();
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
		}
	}

	
	/**
	 * tests removing last element, should throw exception, if list is empty
	 */
	@Test
	public void testRemoveLast() 
	{
		assertEquals("Checking Remove Last", 1, (int)one.removeLast());
		assertEquals("Checking Remove Last", 0, one.size());
		//checks size after removing
		 
		assertEquals("Checking Remove Last", 3, (int)several.removeLast());
		assertEquals("Checking Remove Last", 2, (int)several.getTailValue());
		//checks ending value after removing		
		
		try
		{
			empty.removeLast();
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
		}
	}
	
	
	/**
	 * tests getHeadValue
	 */
	@Test
	public void testGetHeadValue()
	{
		assertEquals("Checking getNth", 1, (int)one.getHeadValue());
		assertEquals("Checking getNth", 0, (int)several.getHeadValue());		
		// should return data in first node of list
	}
	
	
	/**
	 * tests getTailValue
	 */
	@Test
	public void testGetTailValue()
	{
		assertEquals("Checking getNth", 1, (int)one.getTailValue());
		assertEquals("Checking getNth", 3, (int)several.getTailValue());		
		// should return data in last node of list
	}

} // end of DoubleEndedLLTester class
