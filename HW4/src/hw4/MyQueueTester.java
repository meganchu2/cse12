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
 * Title: class MyQueueTester
 *  Description: tester class for MyQueue class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MyQueueTester 
{
	private MyQueue<Integer> empty;
	private MyQueue<Integer> one;
	private MyQueue<Integer> several;

	
	/**
	 * sets up the testing queue lists
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		empty = new MyQueue<>(); // empty list
		
		one = new MyQueue<>(); // list with one element
		one.addElement(1);
		
		several = new MyQueue<>(); // list with 4 elements
		several.addElement(0);
		several.addElement(1);
		several.addElement(2);
		several.addElement(3);
	}

	
	/**
	 * tests the isEmpty method
	 */
	@Test
	public void testIsEmpty() 
	{
		assertTrue("Checking empty", empty.isEmpty());
		assertTrue("Checking empty", !(one.isEmpty()));
	}

	
	/**
	 * tests the addElement method
	 */
	@Test
	public void testAddElement() 
	{
		empty.addElement(100);
		assertEquals("Checking add", 1, empty.size());
		assertEquals("Checking last add", 100, (int)empty.getLastAdd());
		// checks what was added
		
		one.addElement(200);
		assertEquals("Checking add", 2, one.size());
		assertEquals("Checking last add", 200, (int)one.getLastAdd());
		//checks what was added
		
		several.addElement(300);
		assertEquals("Checking add", 5, several.size());
		assertEquals("Checking last add", 300, (int)several.getLastAdd());
		//checks what was added
	}

	/**
	 * tests the removeElement method
	 */
	@Test
	public void testRemoveElement() 
	{
		assertEquals("Checking remove", 1, (int)one.removeElement());
		assertEquals("Checking remove", 0, one.size());
		//checks size and elements removed
		
		assertEquals("Checking remove", 0, (int)several.removeElement());
		assertEquals("Checking remove", 3, several.size());
		// checks size ad elements removed
	}

	
	/**
	 * tests the size method
	 */
	@Test
	public void testSize() 
	{
		assertEquals("Checking add", 0, empty.size()); // should be empty
		assertEquals("Checking add", 1, one.size()); // should be 1
		assertEquals("Checking add", 4, several.size()); // should be 4
	}

	
	/**
	 * tests the peek method
	 */
	@Test
	public void testPeek() 
	{
		assertEquals("Checking peek", 1, (int)one.peek());
		assertEquals("Checking peek", 0, (int)several.peek());
		
		try // throw exception if list is empty
		{
			empty.peek();
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
		}
	}
	
	
	/**
	 * tests getLastAdd method
	 */
	@Test
	public void testGetLastAdd()
	{
		assertEquals("Checking getLastAdd", 1, (int)one.getLastAdd());
		assertEquals("Checking getLastAdd", 3, (int)several.getLastAdd());
	}
} // end of MyQueueTester class
