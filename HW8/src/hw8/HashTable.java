package hw8;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot 
*/


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


/**
 * Title: class HashTable
 *  Description: class that holds and operates a HashTable
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-03-08
 */
public class HashTable implements IHashTable 
{	
	//You will need a HashTable of LinkedLists. 
	LinkedList<String>[] hashT;
	
	private int nelems; //Number of element stored in the hash table
	private int expand; //Number of times that the table has been expanded
	private int collision; //Number of collisions since last expansion
	//FilePath for the file to write statistics upon every rehash
	private String statsFileName; 
	//Boolean that decides to  write statistics to file or not after rehash
	private boolean printStats = false; 
	private int size;
	private final int twice = 2;
	private double loadFac = 0;
	private int numFilled = 0;
	private int longestChain = 0;
	private final double twoThirds = (double)2/(double)3;
	private final int hashFuncVal = 27;
	private final String rounding = "%.2f";
	
	//You are allowed to add more :)
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 */
	public HashTable(int size) 
	{
		hashT = new LinkedList[size];
		
		for(int i = 0; i < hashT.length; i++)
		{
			hashT[i] = new LinkedList<>();
			// make new linked list at every index in table
		}
		
		nelems = 0;
		expand = 0;
		collision = 0;
		this.size = size;
	}
	
	
	/**
	 * Constructor for hash table
	 * @param Initial size of the hash table
	 * @param File path to write statistics
	 */
	public HashTable(int size, String fileName)
	{	
		hashT = new LinkedList[size];
		
		for(int i = 0; i < hashT.length; i++)
		{
			hashT[i] = new LinkedList<>();
			// make new linked list at every index in table
		}
		
		nelems = 0;
		expand = 0;
		collision = 0;
		this.size = size;
		
		//Set printStats to true and statsFileName to fileName
		printStats = true;
		statsFileName = fileName;
	}

	
	/**
	 * Insert the string value into the hash table
	 * @param value value to insert
	 * @throws NullPointerException if value is null
	 * @return true if the value was inserted, false if the value was 
	 *         already present
	 */
	@Override
	public boolean insert(String value) 
	{
		if(value == null) // cannot insert null element in table
		{
			throw new NullPointerException();
		}
				
		int index = hashFunc(value); // index to insert item
		
		if(hashT[index].contains(value)) 
		// if item has already been inserted, we cannot insert
		{
			return false;
		}
		else // item has not yet been inserted
		{
			if(hashT[index].size() == 0) 
			// if there are no other values at this index
			{
				numFilled++;
				loadFac = (double)numFilled/(double)size;
			}
			else // if there is at least one value at this index
			{
				collision++;
			}
			
			hashT[index].add(value); // add value to the list at index
			
			if(hashT[index].size() > longestChain)
			// if chain of items is the longest
			{
				longestChain = hashT[index].size(); 
				// store longest chain length
			}
			
			nelems++;
			
			if(loadFac > twoThirds) 
			// if load factor > 2/3 need to rehash
			{
				expand++;
				rehash();
			}
			
			return true;
		}
	}

	
	/**
	 * Delete the given value from the hash table
	 * @param value value to delete
	 * @throws NullPointerException if value is null
	 * @return true if the value was deleted, false if the value was 
	 *         not found
	 */
	@Override
	public boolean delete(String value) 
	{
		if(value == null) // connot delete null value from table
		{
			throw new NullPointerException();
		}
		
		int index = hashFunc(value); //get index of item to delete
		if(hashT[index].contains(value))
		// if item to delete is contained in list at specified index
		{
			hashT[index].remove(value); // delete item
			nelems--;
			return true;
		}
		return false;
	}

	
	/**
	 * Check if the given value is present in the hash table
	 * @param value value to look up
	 * @throws NullPointerException if value is null
	 * @return true if the value was found, false if the value was not found
	 */
	@Override
	public boolean contains(String value) 
	{
		if(value == null) // hash table cannot contain a null value
		{
			throw new NullPointerException();
		}
		for(int i = 0; i < hashT.length; i++) // loop thought every list
		{
			if(hashT[i].contains(value)) // if the hash table contains value
			{
				return true;
			}
		}
		return false; // if hash table does not contain value
	}

	
	/**
	 * Print the contents of the hash table. Print nothing if table is empty
	 */
	@Override
	public void printTable() 
	{
		for(int i = 0; i < hashT.length; i++)
		{
			System.out.print(i + ": "); // print index and a colon
			
			LinkedList<String> temp = hashT[i];
			
			if(!temp.isEmpty()) // if list is not empty at this index
			{
				System.out.print(temp.get(0)); // print first int in list
			}
			
			for(int j = 1; j < temp.size(); j++) 
			// for elements in list after first one
			{
				System.out.print(", " + temp.get(j)); 
				// print out rest of elements
			}
			System.out.println(); // go to next line for next index
		}
		System.out.println();
	}
	
	
	/**
	 * Return the number of elements currently stored in the hashtable
	 * @return nelems
	 */
	@Override
	public int getSize() 
	{
		return nelems;
	}
	
	
	/**
	 * helper method to find the index of which element is stored
	 * @param value, the element we are placing/searching for
	 * @return int, index of where element is stored
	 */
	public int hashFunc(String value)
	{
		int hashVal = 0;
		for(int i = 0; i < value.length(); i++) // left to right of string
		{
			int letter = value.charAt(i); // get char code
			hashVal = (hashVal*hashFuncVal + letter) % size; 
			// multiply, add and mod
		}
		return hashVal;
	}
	
	
	/**
	 * private helper method to double size of hash table and put values
	 * in table accordingly
	 */
	private void rehash()
	{
		if(printStats)
		{
			printStatistics();
		}
		
		size = size*twice; // double size of table
		LinkedList<String>[] temp = new LinkedList[size];
		
		numFilled = 0;
		collision = 0;
		loadFac = 0;
		longestChain = 0;
		
		for(int i = 0; i < temp.length; i++)
		{
			temp[i] = new LinkedList<>(); 
			// make new linked list at every index in table
		}
		
		for(int i = 0; i < hashT.length; i++) // loop through current table
		{
			for(int j = 0; j < hashT[i].size(); j++)
			// loop through list at every index
			{
				String s = hashT[i].get(j); // get element in list
				int index = hashFunc(s); // get new index for element
				if(temp[index].size() == 0)
				{
					numFilled++;
				}
				temp[index].add(s); 
				// add every element to bigger table at new index
				
				if(temp[index].size() > longestChain)
				{
					longestChain = temp[index].size(); // update longestChain
				}
			}
		}
		loadFac = (double)numFilled/(double)size;
		hashT = temp; // set current table to bigger table
	}
	
	
	/**
	 * method to print statistics after each expansion
	 */
	public void printStatistics()
	{
		// prints text to specified stored file
		try(FileWriter file = new FileWriter(statsFileName, true);
			BufferedWriter bw = new BufferedWriter(file);)
		{
			bw.write(expand + " resizes, load factor ");
			
			bw.write(String.format(rounding, loadFac)); 
			// load factor with 2 dec places
			
			bw.write(", " + collision + " collisions, " 
			           + longestChain + " longest chain\n");
			bw.close();
		} 
		catch (IOException e) 
		{
			System.out.println("\nFile not found!!");
		}
	}

} // end of HashTable class
