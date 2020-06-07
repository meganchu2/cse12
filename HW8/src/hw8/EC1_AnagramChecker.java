package hw8;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot 
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Title: class EC1_AnagramChecker
 *  Description: class that checks for anagrams in dictionary
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-03-08
 */
public class EC1_AnagramChecker 
{
	public HashTable dictionary;
	
	/**
	 * constructor for anagram checker that initializes hashtable
	 */
	public EC1_AnagramChecker()
	{
		dictionary = new HashTable(1);
	}
	
	/**
	 * getter for the hashtable
	 * @return
	 */
	public HashTable getHashT()
	{
		return dictionary;
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
		LinkedList<ArrayList<String>>[] hashT;
		private int nelems; //Number of element stored in the hash table
		private int size;
		private final int twice = 2;
		private double loadFac = 0;
		private int numFilled = 0;
		private final double twoThirds = (double)2/(double)3;
		private final int hashFuncVal = 27;
				
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
			this.size = size;
		}
		
		
		/**
		 * getter for the array storing values
		 * @return
		 */
		public LinkedList<ArrayList<String>>[] getStored()
		{
			return hashT;
		}
		
		
		/**
		 * Insert the string value into the hash table
		 * @param value value to insert
		 * @throws NullPointerException if value is null
		 * @return true if the value was inserted, false if the value was 
		 *         already present
		 */
		public boolean insert(String value) 
		{
			if(value == null) // cannot insert null element in table
			{
				throw new NullPointerException();
			}

			char[] toSort = value.toCharArray(); // char array of given word
			
			// bubble sorts the char array
			for(int i = toSort.length - 1; i > 0; i--)
			{
				for(int j = 0; j < i; j++)
				{
					if(toSort[j] > toSort[j + 1])
					{
						char temp = toSort[j];
						toSort[j] = toSort[j + 1];
						toSort[j + 1] = temp;
					}
				}
			}
			
			// turns sorted array back to string
			String sorted = new String(toSort); 
			
			int index = hashFunc(sorted); // index to insert item
			
			if(hashT[index].size() == 0) 
			// if there are no other values at this index
			{
				numFilled++;
				loadFac = (double)numFilled/(double)size;
			}
			
			// pair of strings to add at index (original word and key)
			ArrayList<String> toAdd = new ArrayList<>();
			toAdd.add(value);
			toAdd.add(sorted);
			
			hashT[index].add(toAdd); // add values to list at index
					
			nelems++;
			
			if(loadFac > twoThirds) 
			// if load factor > 2/3 need to rehash
			{
				rehash();
			}
			return true;
		}
		
		
		/**
		 * helper method to find the index of which element is stored
		 * @param value, the element we are placing/searching for
		 * @return int, index of where element is stored
		 */
		public int hashFunc(String value)
		{
			int hashVal = 0;
			for(int i = 0; i < value.length(); i++) 
			// parse characters from left to right of string
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
			size = size*twice; // double size of table
			LinkedList<ArrayList<String>>[] temp = new LinkedList[size];
			
			numFilled = 0;
			loadFac = 0;
			
			for(int i = 0; i < temp.length; i++)
			{
				temp[i] = new LinkedList<>(); 
				// make new linked list at every index in table
			}
			
			for(int i = 0; i < hashT.length; i++) 
			// loop through current table
			{
				for(int j = 0; j < hashT[i].size(); j++)
				// loop through list at every index
				{
					// get element in list
					ArrayList<String> s = hashT[i].get(j);
					
					// get new index for element using key
					int index = hashFunc(s.get(1));
					if(temp[index].size() == 0)
					{
						numFilled++;
					}
					temp[index].add(s); 
					// add every element to bigger table at new index
				}
			}
			loadFac = (double)numFilled/(double)size;
			hashT = temp; // set current table to bigger table
		}
	} // end of HashTable class
	
	
	/**
	 * inserts words in given file into a hashtable
	 * @param fileName, name of the dictionary file
	 * @param hT, hashtable to insert words in
	 * @return boolean, true if file was found, false otherwise
	 */
	public static boolean populateDictionary(String fileName, HashTable hT)
	{
		File file = new File(fileName); // new path to dictionary file
		try 
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) // while there is next line in file
			{
				String word = scanner.nextLine().trim(); 
				// get word in next line
				hT.insert(word); // put word in hashtable
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
	 * method to look for anagrams of a word
	 * @param word, string whose anagrams we are looking for
	 * @param dictionary, the Hashtable of words we are looking through
	 * @return ArrayList<String>, list of anagrams of word
	 */
	public static ArrayList<String> searchAnagrams(String word, 
			                                       HashTable dictionary)
	{
		ArrayList<String> toReturn = new ArrayList<>();
		
		char[] toSort = word.toCharArray();	// turn string into a char array
		
		// bubble sort the char array
		for(int i = toSort.length - 1; i > 0; i--)
		{
			for(int j = 0; j < i; j++)
			{
				if(toSort[j] > toSort[j + 1])
				{
					char temp = toSort[j];
					toSort[j] = toSort[j + 1];
					toSort[j + 1] = temp;
				}
			}
		}
		String sorted = new String(toSort); // turn sorted array into string
		
		int index = dictionary.hashFunc(sorted); // index of anagrams
		
		LinkedList<ArrayList<String>>[] hT = dictionary.getStored();
		// get the array for the dictionary hashTable
		
		for(int i = 0; i < hT[index].size(); i++)
		// for every arraylist in the linked list at the index of anagrams
		{
			if(hT[index].get(i).get(1).equals(sorted))
			// if the keys are the same
			{
				toReturn.add(hT[index].get(i).get(0));
				// add word to list of anagrams (includes original word)
			}
		}		
		return toReturn;
	}
	
	
	/**
	 * main method that runs the code
	 * @param args, String array of user input needed
	 */
	public static void main(String[] args) 
	{
		if(args.length < 2) // need at least 2 arguments
		{
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		// make new anagram checker and a hashtable for the dictionary
		EC1_AnagramChecker searcher = new EC1_AnagramChecker();	
		HashTable dictionary = searcher.getHashT();
		
		String dName = args[0]; // name of dictionary file
		
		boolean check = populateDictionary(dName, dictionary); // add words from dictionary 
		// to hashtable using key derived from alphabatizing letters of
		// each word	
		
		if(!check) // if dictionary was not loaded
		{
			System.out.println("\nUnable to load dictionary");
		}	
		
		// loop through remaining arguments (words to look for anagrams)
		for(int i = 1; i < args.length; i++)
		{
			// get list of anagrams that we need to alphabatize
			ArrayList<String> toSort = new ArrayList<>();
			toSort.addAll(searchAnagrams(args[i], dictionary));
			
			// bubble sort the list of anagrams
			for(int j = toSort.size() - 1; j > 0; j--)
			{
				for(int k = 0; k < j; k++)
				{
					if(toSort.get(k).compareTo(toSort.get(k + 1)) > 1)
					{
						String temp = toSort.get(k);
						toSort.set(k, toSort.get(j + 1));
						toSort.set(j + 1, temp);
					}
				}
			}
			
			// if there are no anagrams
			if(toSort.size() == 0)
			{
				System.out.println("Anagram(s) of " + args[i] + ":");
				System.out.println("No anagrams found");
			}
			else // if there is at least 1 anagram
			{
				System.out.println("Anagram(s) of " + args[i] + 
						           "(in alphabetical order):");
				// print each anagram on a new line
				for(int p = 0; p < toSort.size(); p++)
				{
					System.out.println(toSort.get(p));
				}
			}
		} // end of loop through arguments
	}
} // end of EC1_AnagramCecker class
