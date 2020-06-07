package hw7;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;


/**
 * Title: class BSTreeTester
 *  Description: tester class for BSTree
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-26
 */
public class BSTreeTester 
{
	BSTree<Integer> empty;
	BSTree<Integer> one;
	BSTree<Integer> several;
	BSTree<String> tester;
	
	
	/**
	 * sets up tester class
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		empty = new BSTree<>(); // empty bst, height -1
		
		one = new BSTree<>(); // bst with one node, height 0, leaves 1
		one.insert(0);
				
		several = new BSTree<>(); // bst with 5 nodes, height 2, leaves 2
		several.insert(3);
		several.insert(1);
		several.insert(2);
		several.insert(5);
		several.insert(4);
		
		tester = new BSTree<>(); // string bst
		tester.insert("Hi");
		tester.insert("Bye");
		tester.insert("Apple");
		tester.insert("We");
		tester.insert("Ya");
	}

	
	/**
	 * tests getRoot method
	 */
	@Test
	public void testGetRoot() 
	{
		// key of root should be key of first inserted node
		assertEquals("Checking get root", 3, 
				(int)several.getRoot().getKey());
		assertEquals("Checking get root", 0, (int)one.getRoot().getKey());
		assertEquals("Checking get root", null, empty.getRoot());
	}

	
	/**
	 * tests getSize method
	 */
	@Test
	public void testGetSize() 
	{
		// size should be number of inserted nodes in bst
		assertEquals("Checking get size", 5, several.getSize());
		assertEquals("Checking get size", 1, one.getSize());
		assertEquals("Checking get size", 0, empty.getSize());
	}

	
	/**
	 * tests insert method
	 */
	@Test
	public void testInsert() 
	{
		
		empty.insert(0);
		one.insert(5);
		several.insert(0);

		// inserting a new node in bst should increase size by one
		assertEquals("Checking insert", 1, empty.getSize());
		assertEquals("Checking insert", 2, one.getSize());
		assertEquals("Checking insert", 6, several.getSize());
		
		// several's number of leaves should increase by one
		assertEquals("Checking insert", 3, several.leafCount());
		
		try
		{
			empty.insert(null); // cannot insert node with null key
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
	}

	
	/**
	 * tests findKey method
	 */
	@Test
	public void testFindKey() 
	{
		// should return true if nodes with specified keys are in bst
		assertTrue("Checking find key", several.findKey(5));
		assertTrue("Checking find key", several.findKey(2));
		assertTrue("Checking find key", !several.findKey(7));
	}

	
	/**
	 * tests insertInformation method
	 */
	@Test
	public void testInsertInformation() 
	{
		one.insertInformation(0, 0);
		
		// last element in info list should be the one just inserted
		assertEquals("Checking insert information", 0, 
				(int)one.findMoreInformation(0).getLast());
		
		several.insertInformation(2, 7);
		
		// last element in info list should be the one just inserted
		assertEquals("Checking insert information", 7, 
				(int)several.findMoreInformation(2).getLast());
				
		try
		{
			empty.insertInformation(null, 3); 
			// cannot insert info to a null key node
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
		
		try
		{
			empty.insertInformation(3, null); 
			// cannot insert null info to a node
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
		
		try
		{
			empty.insertInformation(3, 3); 
			// cannot insert info in key that is not in tree
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
			// normal
		}
	}

	
	/**
	 * tests findMoreInformation method
	 */
	@Test
	public void testFindMoreInformation() 
	{
		one.insertInformation(0, 0);
		LinkedList<Integer> temp = new LinkedList<Integer>();
		temp.add(0);
		
		// should return list with info we just added
		assertEquals("Checking insert information", temp, 
				one.findMoreInformation(0));
		
		several.insertInformation(2, 7);
		several.insertInformation(2, 6);
		temp.remove(0);
		temp.add(7);
		temp.add(6);
		
		// should return list with info we just added
		assertEquals("Checking insert information", temp, 
				several.findMoreInformation(2));
		
		try
		{
			empty.findMoreInformation(null); 
			// cannot find info of null key node
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
		
		try
		{
			empty.findMoreInformation(1); 
			// cannot find info of node with key that is not in bst
			fail("Should have generated an exception");
		}
		catch(IllegalArgumentException e)
		{
			// normal
		}
	}

	
	/**
	 * tester for find height method
	 */
	@Test
	public void testFindHeight() 
	{
		// heights should equal what was specified in setup
		assertEquals("Checking height", 2, several.findHeight());
		assertEquals("Checking height", 0, one.findHeight());
		assertEquals("Checking height", -1, empty.findHeight());
		
		several.insert(0);
		several.insert(-1);
		
		// several height should increase by 1
		assertEquals("Checking height", 3, several.findHeight());
	}

	
	/**
	 * tester for leafCount method
	 */
	@Test
	public void testLeafCount() 
	{
		// number of leaves should equal what was specified in setup
		assertEquals("Checking leaf", 2, several.leafCount());
		assertEquals("Checking leaf", 1, one.leafCount());
		assertEquals("Checking leaf", 0, empty.leafCount());
	}

	
	/**
	 * tester for BSTree iterator
	 */
	@Test
	public void testIterator() 
	{
		int counter = 0 ;
	    Iterator<Integer> iter;
	    for (iter = empty.iterator() ; iter.hasNext(); ) 
	    // hasNext should never return true for empty list
	    {
	      fail("Iterating empty list and found element") ;
	    }

	    counter = 0 ;
	    for (iter = several.iterator() ; iter.hasNext(); iter.next())
	    {
	      counter++; // should add up to length of several
	    }
	    assertEquals("Iterator several count", 5, counter);
	}
	
	
	/**
	 * tester for BSTTree iterator's hasNext method
	 */
	@Test
	public void testIteratorHasNext()
	{
		Iterator<Integer> iter = several.iterator();
		assertTrue("Iterator several", iter.hasNext());
		iter.next(); // 1
		assertTrue("Iterator several", iter.hasNext());
		iter.next(); // 2
		assertTrue("Iterator several", iter.hasNext());
		iter.next(); // 3
		assertTrue("Iterator several", iter.hasNext());
		iter.next(); // 4
		assertTrue("Iterator several", iter.hasNext());
		iter.next(); // 5
		
		// iterator should not have more elements to pop from stack, 
		// since we have iterated through all nodes in several
		assertTrue("Iterator several", !iter.hasNext());
	}
	
	
	/**
	 * tester for BSTTree iterator's next method
	 */
	@Test
	public void testIteratorNext()
	{
		// iterator should return keys of nodes in specified order
		Iterator<Integer> iter = several.iterator();
		assertEquals("Iterator several", 1, (int)iter.next());
		assertEquals("Iterator several", 2, (int)iter.next());
		assertEquals("Iterator several", 3, (int)iter.next());
		assertEquals("Iterator several", 4, (int)iter.next());
		assertEquals("Iterator several", 5, (int)iter.next());
	}
	
	
	/**
	 * tester for intersection method
	 */
	@Test
	public void testIntersection()
	{
		several.insert(0); // will have intersection with the bst "one"
		
		Iterator<Integer> iter1 = one.iterator();
		Iterator<Integer> iter2 = several.iterator();
		ArrayList<Integer> test = several.intersection(iter1, iter2);
		// get the intersection of the two bsts
		
		// the intersection list should contain 0
		assertEquals("Checking intersection", 0, (int)test.get(0));
		
		one.insert(3); // will have intersection with the bst "several"
		
		iter1 = one.iterator();
		iter2 = several.iterator();
		test = several.intersection(iter1, iter2);
		// get the intersection of the two bsts
		
		// number of overlapping elements should be 2
		assertEquals("Checking intersection", 2, test.size());
		
		// the intersection list should contain 0 and 3 in that order
		assertEquals("Checking intersection", 0, (int)test.get(0));
		assertEquals("Checking itersection", 3, (int)test.get(1));		
	}
	
	
	/**
	 * tester for levelCount method
	 */
	@Test
	public void testLevelCount()
	{
		// count in 1 for root level
		assertEquals("Check level count", 1, one.levelCount(0));
		assertEquals("Check level count", 1, several.levelCount(0));
		
		// several has 2 nodes in levels 1 and 2
		assertEquals("Check level count", 2, several.levelCount(1));
		assertEquals("Check level count", 2, several.levelCount(2));
		
		several.insert(0);
		
		// now several has 3 nodes in level 2
		assertEquals("Check level count", 3, several.levelCount(2));
		
		// several has no nodes in level 3 (there is no level 3 in several)
		assertEquals("Check level count", -1, several.levelCount(3));
		
		several.insert(-1);
		
		// several now has 1 node in level 3
		assertEquals("Check level count", 1, several.levelCount(3));
	}
	
	
	/**
	 * tester for addChildToLeaves method
	 */
	@Test
	public void testAddChildToLeaves()
	{
		tester.addChildToLeaves();
		
		// size should increase by 2 for two leaf nodes
		assertEquals("Check add child", 7, tester.getSize());
		
		// height should increase by 1 for any bst
		assertEquals("Check add child", 3, tester.findHeight());
		
		// keys of node should be returned my iterator as specified
		Iterator<String> iter = tester.iterator();
		assertEquals("Check add child", "Apple", iter.next());
		assertEquals("Check add child", "Apple", iter.next());
		assertEquals("Check add child", "Bye", iter.next());
		assertEquals("Check add child", "Hi", iter.next());
		assertEquals("Check add child", "We", iter.next());
		assertEquals("Check add child", "Ya", iter.next());
		assertEquals("Check add child", "Ya", iter.next());
	}
	
	
	/**
	 * tester for isFullBST method
	 */
	@Test
	public void testIsFullBST()
	{
		assertTrue("Check full", one.isFullBST()); // no children so yes
		assertTrue("Check full", !several.isFullBST()); 
		// no because some nodes have only 1 child
		
		several.insert(0);
		several.insert(6);
		// several is now full BST
		
		assertTrue("Check full", several.isFullBST());
	}
} // end of BSTreeTester class
