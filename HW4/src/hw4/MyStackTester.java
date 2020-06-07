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
 * Title: class MyStackTester
 *  Description: tester class that tests MyStack
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MyStackTester 
{
	private MyStack<Integer> empty;
	private MyStack<Integer> one;
	private MyStack<Integer> several;

	/**
	 * sets up tester stacks
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		empty = new MyStack<>(); // empty stack
		
		one = new MyStack<>(); // stack with one element
		one.addElement(1);
		
		several = new MyStack<>(); // stack with 4 elements
		several.addElement(0);
		several.addElement(1);
		several.addElement(2);
		several.addElement(3);		
	}

	
	/**
	 * tests isEmpty method
	 */
	@Test
	public void testIsEmpty() 
	{
		assertTrue("Checking empty", empty.isEmpty());
		assertTrue("Checking empty", !(several.isEmpty()));
	}

	
	/**
	 * tests addELement method
	 */
	@Test
	public void testAddElement() 
	{
		empty.addElement(100);
		assertEquals("Checking add", 1, empty.size()); // check size
		
		one.addElement(200);
		assertEquals("Checking add", 2, one.size()); // check size
		
		several.addElement(300);
	    assertEquals("Checking add", 5, several.size()); // check size
	}

	
	/**
	 * tests removeElement method
	 */
	@Test
	public void testRemoveElement() 
	{
		assertEquals("Checking remove", 1, (int)one.removeElement());
		assertEquals("Checking remove", 0, one.size()); // check size
		
		assertEquals("Checking remove", 3, (int)several.removeElement());
		assertEquals("Checking remove", 3, several.size()); // check size
	}

	
	/**
	 * tests size method
	 */
	@Test
	public void testSize() 
	{
		assertEquals("Checking size", 0, empty.size()); // should be 0
		assertEquals("Checking size", 1, one.size()); // should be 1
		assertEquals("Checking size", 4, several.size()); // should be 4
	}

	
	/**
	 * tests peek method
	 */
	@Test
	public void testPeek() 
	{
		assertEquals("Checking peek", 1, (int)one.peek()); 
		assertEquals("Checking peek", 3, (int)several.peek());
		// should return last added element
		
		try // throw exception if list is empty
		{
			empty.peek();
			fail("Should have geberated an exception");
		}
		catch(NullPointerException e)
		{
		}
	}

} // end of MyStackTester class
