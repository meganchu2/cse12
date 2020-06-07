package hw6;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;


/**
 * Title: class dHeapTester
 *  Description: tester class for dHeap class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-16
 */
public class dHeapTester 
{
	private dHeap<Integer> empty;
	private dHeap<Integer> one;
	private dHeap<Integer> binary;
	private dHeap<Integer> tertiary;
	private dHeap<Integer> quad;
	
	
	/**
	 * sets up the dHeap objects to be tested
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		empty = new dHeap<>(); // set up empty heap
		
		one = new dHeap<>(1); // set up dHeap with one element
		one.add(1);
		
		binary = new dHeap<>(2, 1, true); // set up binary maxHeap
		binary.add(0);
		binary.add(1);
		
		tertiary = new dHeap<>(3, 3, false); // set up tertiary min heap
		tertiary.add(1);
		tertiary.add(0);
		tertiary.add(2);
		
		quad = new dHeap<>(4, 4, true); // set up quarternary max heap
		quad.add(7);
		quad.add(4);
		quad.add(2);
		quad.add(1);
	}

	
	/**
	 * tests the size method for dHeap
	 */
	@Test
	public void testSize() 
	{
		assertEquals("Checking empty size", 0, empty.size()); // size is 0
		assertEquals("Checking one size", 1, one.size()); // size is 1
		assertEquals("Checking quad size", 4, quad.size()); // size is 4
	}

	
	/**
	 * tests the add method for dHeap
	 */
	@Test
	public void testAdd() 
	{
		binary.add(5);
		tertiary.add(-1);
		
		// size should have increased
		assertEquals("Checking add", 3, binary.size()); 
		assertEquals("Checking add", 4, tertiary.size());
		
		// root of heap should be 5
		assertEquals("Checking add", 5, (int)binary.element());
		
		// root of heap should be -1
		assertEquals("Checking add", -1, (int)tertiary.element());
		
		try
		{
			one.add(null); // cannot add null element
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
	}

	
	/**
	 * tests the remove method for dHeap
	 */
	@Test
	public void testRemove() 
	{

		// should remove smallest element (since it is min heap)
		assertEquals("Checking remove", 0, (int)tertiary.remove());

		// size should have decremented
		assertEquals("Checking remove", 2, tertiary.size());
		
		try
		{
			empty.remove(); // cannot remove from empty heap
			fail("Should have generated an exception");
		}
		catch(NoSuchElementException e)
		{
			// normal
		}
	}

	
	/**
	 * tests the clear method for dHeap
	 */
	@Test
	public void testClear() 
	{
		quad.clear();
		binary.clear();
		
		// cleared dHeaps should have sizes of 0
		assertEquals("Checking clear", 0, quad.size());
		assertEquals("Checking clear", 0, binary.size());
	}

	
	/**
	 * tests the element method for dHeap
	 */
	@Test
	public void testElement() 
	{
		// max element in binary dHeap
		assertEquals("Checking element", 1, (int)binary.element());
		
		// min element in tertiary dHeap
		assertEquals("Checking element", 0, (int)tertiary.element());
		
		// max element in quarternary dHeap
		assertEquals("Checking element", 7, (int)quad.element());
		
		try
		{
			empty.element(); // empty heap has no root
			fail("Should have generated an exception");
		}
		catch(NoSuchElementException e)
		{
			// normal
		}
	}

} // end of dHeapTester class
