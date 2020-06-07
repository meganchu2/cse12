package hw8;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot 
*/


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Title: class HashTableTester
 *  Description: tester class for the HashTable class
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-03-08
 */
public class HashTableTester 
{
	HashTable tester; //table to test

	/**
	 * sets up HashTables to test method with
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		// initialize tester table with size 1 and one variable
		tester = new HashTable(1);
		tester.insert("Hi");
	}

	
	/**
	 * tests insert method
	 */
	@Test
	public void testInsert() 
	{
		// initial size is 1
		assertEquals("Checking insert", 1, tester.getSize());
		
		// we should be able to insert new string
		assertTrue("Checking insert", tester.insert("Bye"));
		
		// we cannot insert "Hi" since it is already in the table
		assertTrue("Checking insert", !tester.insert("Hi"));
		
		// size should be 2 from inserting "Bye"
		assertEquals("Checking insert", 2, tester.getSize());
		
		// "Bye" should be in the table
		assertTrue("Checking insert", tester.contains("Bye"));
		
		try
		{
			tester.insert(null); // cannot insert a null string
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal;
		}
	}

	
	/**
	 * tests delete method
	 */
	@Test
	public void testDelete() 
	{
		// size should be 1
		assertEquals("Checking delete", 1, tester.getSize());
		
		// we cannot delete a word not in table
		assertTrue("Checking delete", !tester.delete("Bye"));
		
		// we can delete a word in table
		assertTrue("Checking delete", tester.delete("Hi"));
		
		// size should be 0 from deleting "Hi"
		assertEquals("Checking delete", 0, tester.getSize());
		
		try
		{
			tester.delete(null); // cannot delete a null string from table
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
		
	}

	
	/**
	 * tests contains method
	 */
	@Test
	public void testContains() 
	{
		// table contains "Hi" but not "Bye"
		assertTrue("Checking contains", tester.contains("Hi"));
		assertTrue("Checking contains", !tester.contains("Bye"));
		
		try
		{
			tester.contains(null);
			fail("Should have generated an exception");
		}
		catch(NullPointerException e)
		{
			// normal
		}
	}

	
	/**
	 * tests getSize method
	 */
	@Test
	public void testGetSize() 
	{
		// size is 1
		assertEquals("Checking size", 1, tester.getSize());
		
		// size is 2 after 1 insert
		tester.insert("Bye");
		assertEquals("Checking size", 2, tester.getSize());
		
		// size is 3 after 2 inserts
		tester.insert("Hello");
		assertEquals("Checking size", 3, tester.getSize());
	}
	
	
	/**
	 * tests printStats and printTable methods
	 */
	/*@Test
	public void testPrint()
	{
		// testing printing stats to file
		HashTable tester2 = new HashTable(1, "tester");
		
		tester2.insert("apple");
		tester2.printTable();
		tester2.insert("banana");
		tester2.printTable();
		tester2.insert("orange"); // collision
		
		tester2.printTable();
		tester2.insert("potato");
		tester2.printTable();
		
		// rehash
		
		tester2.insert("cherry"); //collision
		tester2.printTable();
		tester2.insert("strawberry"); //collision
		tester2.printTable();
		tester2.insert("blueberry");
		tester2.printTable();
		tester2.insert("appo");
		
		tester2.printTable();
		tester2.insert("e");
		tester2.printTable();
	}*/
} // end of HashTableTester class
