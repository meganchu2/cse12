package hw8;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot 
*/


import hw8.EC1_AnagramChecker.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Title: class EC2_HashSort
 *  Description: class that implements bucket sort in a hashtable
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-03-08
 */
public class EC2_HashSort 
{
	public HashTable buckets;
	
	/**
	 * constructor for anagram checker that initializes hashtable
	 */
	public EC2_HashSort(int size, int hashFuncVal)
	{
		buckets = new HashTable(size, hashFuncVal);
	}
	
	/**
	 * getter for the hashtable
	 * @return
	 */
	public HashTable getHashT()
	{
		return buckets;
	}
	
	
	/**
	 * Title: class HashTable
	 *  Description: class that stores elements according to hash value
	 *
	 * @version 1.0
	 * @author  Megan Chu
	 * @since   2017-03-08
	 */
	protected class HashTable
	{
		//an array of LinkedLists of String ArrayLists to store strings
		LinkedList<Integer>[] hashT;
		private int nelems; //Number of element stored in the hash table
		private int size;
		private int hashFuncVal;
				
		/**
		 * Constructor for hash table
		 * @param Initial size of the hash table
		 */
		public HashTable(int size, int hashFuncVal) 
		{
			hashT = new LinkedList[size];
			
			for(int i = 0; i < hashT.length; i++)
			{
				hashT[i] = new LinkedList<>();
				// make new linked list at every index in table
			}
			
			nelems = 0;
			this.size = size;
			this.hashFuncVal = hashFuncVal;
		}
		
		
		/**
		 * getter for the array storing values
		 * @return
		 */
		public LinkedList<Integer>[] getStored()
		{
			return hashT;
		}
		
		
		/**
		 * Insert the string value into the hash table
		 * @param value value to insert
		 * @return true if the value was inserted, false if the value was 
		 *         already present
		 */
		public boolean insert(int value) 
		{
			int index = hashFunc(value); // index to insert item
			
			for(int i = 0; i < hashT[index].size(); i ++)
			// loops through list at index
			{
				if(hashT[index].get(i) > value)
				{
					hashT[index].add(i, value); 
					// add new value while maintaining order
				}
			}
			if(!hashT[index].contains(value))
			// list was empty or all elements were smaller than value
			{
				hashT[index].addLast(value); 
				// add new value while maintaining order
			}
					
			nelems++;
			
			return true;
		}
		
		
		/**
		 * helper method to find the index of which element is stored
		 * @param value, the element we are placing/searching for
		 * @return int, index of where element is stored
		 */
		public int hashFunc(int value)
		{
			return (int)((double)value/(double)hashFuncVal);
		}

	} // end of HashTable class

	
	public static boolean fillBuckets(String fileName, HashTable hT)
	{
		File file = new File(fileName); // new path to file
		try 
		{
			Scanner scanner = new Scanner(file);
			// go through first 3 lines of info
			scanner.nextInt();
			scanner.nextInt();
			scanner.nextInt();
			while(scanner.hasNextInt()) // while there is next int in file
			{
				int i = scanner.nextInt();
				// get integer in next line
				hT.insert(i); // put integer in hashtable
			}
			scanner.close();
		} 
		catch(FileNotFoundException e) // given file was not found
		{
			System.out.println("\nFile not found!!");
			return false;
		}
		return true; // given file was found
	}
	
	
	/**
	 * main method that runs the code
	 * @param args, String array of user input needed
	 */
	public static void main(String[] args) 
	{
		if(args.length != 1)
		{
			System.err.println("Invalid number of arguments passed");
			return;
		}
				
		String fileName = args[0];
		int max = 0;
		int inputs = 0;
		
		File file = new File(fileName); // new path to file
		try 
		{
			Scanner scanner = new Scanner(file);
			// first three inputs
			inputs = scanner.nextInt();
			scanner.nextInt();
			max = scanner.nextInt();
			
			scanner.close();
		} 
		catch(FileNotFoundException e) // given file was not found
		{
			System.out.println("\nFile not found!!");
			return;
		}
		
		int hashFuncVal = (int)(inputs);
		EC2_HashSort sorter = new EC2_HashSort(
				(int)((double)max/(double)hashFuncVal + 1), hashFuncVal);
		
		HashTable buckets = sorter.getHashT();
		
		boolean check = fillBuckets(fileName, buckets);
		
		if(!check) // if buckets not loaded
		{
			System.out.println("\nUnable to load buckets");
		}	
		
		LinkedList<Integer>[] hT = buckets.getStored();
		
		System.out.println("Output");
		for(int i = 0; i < hT.length; i++)
		{
			for(int j = 0; j < hT[i].size(); j++)
			{
				System.out.println(hT[i].get(j));
			}
		}

	}

}
