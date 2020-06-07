package hw8;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot 
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Title: class SpellChecker
 *  Description: class that checks spelling of words in dictionary
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-03-08
 */
public class SpellChecker 
{
	public static final int numArgs = 2;
	
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
				
				if(!hT.contains(word)) 
				// if hashtable does not already contain this word
				{
					hT.insert(word);
				}				
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
	 * inserts word in given file into arraylist
	 * @param inputName, name of input file
	 * @param input, arraylist to place words in file into
	 * @return boolean, true if file was found, false otherwise
	 */
	public static boolean populateInput(String inputName, 
			ArrayList<String> input)
	{
		File file = new File(inputName); // path to input file
		try 
		{
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) // while file has next line
			{
				String word = scanner.nextLine().trim().toLowerCase(); 
				// get word in next line in lower case				
				
				input.add(word); // add word to arraylist
			}
			scanner.close();
		} 
		catch(FileNotFoundException e) // given file not found
		{
			System.out.println("\nFile not found!!");
			return false;
		}
		return true; // given file was found
	}
	
	
	/**
	 * checks spelling of input file against words in dictionary
	 * @param words, arraylist of strings to find in dictionary
	 * @param dictionary, hashtable of words from dictionary file
	 */
	public static void spellCheck(ArrayList<String> words, 
			HashTable dictionary)
	{
		for(int i = 0; i < words.size(); i++) // loop through input words
		{
			ArrayList<String> toPrint = new ArrayList<>();
			// arraylist of what to print for each input
			
			String word = words.get(i);
			if(dictionary.contains(word)) // if word is in dictionary
			{
				toPrint.add("ok");
			}
			else // if word is not in dictionary
			{
				char[] letters = word.toCharArray(); 
				// put word into char array
				
				// find possible errors and word suggestions
				toPrint.addAll(wrongLetter(letters, dictionary));
				toPrint.addAll(insertedLetter(letters, dictionary));
				toPrint.addAll(deletedLetter(letters, dictionary));
				toPrint.addAll(transposedLetter(letters, dictionary));
				toPrint.addAll(pairLetter(letters, dictionary));
				
				if(toPrint.size() == 0) // if no word suggestions
				{
					toPrint.add("not found");
				}
			}
			printCheck(word, toPrint); // print results for this word
		}
	}
	
	
	/**
	 * looks for possible error of a wrong letter
	 * @param letters, char array for word
	 * @param dictionary, hashtable of words in dictionary
	 * @return ArrayList<String>, list of possible corrected words
	 */
	public static ArrayList<String> wrongLetter(char[] letters, 
			HashTable dictionary)
	{
		ArrayList<String> possible = new ArrayList<>(); 
		// list of possible errors
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		// array of characters in alphabet
		
		for(int i = 0; i < letters.length; i++) 
		// loop through characters in word
		{
			char[] alter = new char[letters.length];
			// new char array so original array doesn't get altered
			for(int j = 0; j < letters.length; j++)
			{
				alter[j] = letters[j]; // copy original to new char array
			}
			
			for(int j = 0; j < alphabet.length; j++)
			// loop through alphabet
			{
				alter[i] = alphabet[j]; 
				// switch current character with another one in alphabet
				
				String word = new String(alter);
				if(dictionary.contains(word))
				// if dictionary contains word with wrong letter
				{
					possible.add(word);
				}
			}
		}
		return possible;
	}
	
	
	/**
	 * looks for possible error of extra inserted letter
	 * @param letters, char array for word
	 * @param dictionary, hashtable of words in dictionary
	 * @return ArrayList<String>, list of possible corrected words
	 */
	public static ArrayList<String> insertedLetter(char[] letters, 
			HashTable dictionary)
	{
		ArrayList<String> possible = new ArrayList<>(); 
		// list of possible errors
		
		char[] removed = new char[letters.length - 1];
		for(int i = 0; i < letters.length; i++)
		// loop through characters in word
		{
			for(int j = 0; j < removed.length; j++)
			// loop through spaces in possible error word
			{
				if(j < i) // copies characters to error word if index is 
				// less than index to remove
				{
					removed[j] = letters[j];
				}
				if(j >= i) // copies characters one space ahead to error 
				// word if index is greater than or equal to index to remove
				{
					removed[j] = letters[j + 1];
				}
			}
			String word = new String(removed);
			if(dictionary.contains(word))
			// dictionary contains word with removed letter
			{
				possible.add(word);
			}
		}
		return possible;
	}
		
	
	/**
	 * looks for possible error of a missing letter
	 * @param letters, char array for word
	 * @param dictionary, hashtable of words in dictionary
	 * @return ArrayList<String>, list of possible corrected words
	 */
	public static ArrayList<String> deletedLetter(char[] letters, 
			HashTable dictionary)
	{
		ArrayList<String> possible = new ArrayList<>(); 
		// list of possible errors
		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		// array of characters in alphabet
	
		char[] added = new char[letters.length + 1];
		for(int i = 0; i < added.length; i++)
		// loop through characters in word
		{
			for(int j = 0; j < added.length; j++)
			// loop through spaces in possible error word
			{
				if(j < i) // copies characters to error word if index is
				// less that space to add extra letter
				{
					added[j] = letters[j];
				}
				if(j > i) // copies characters on space behind to error word
				// if index is greater than space to add extra letter
				{
					added[j] = letters[j - 1];
				}
			}
			for(int a = 0; a < alphabet.length; a++)
			// loops through alphabet
			{
				added[i] = alphabet[a]; // adds extra letter to given space
				String word = new String(added);
				if(dictionary.contains(word) && !possible.contains(word))
				// dictionary contains word with added letter
				// word has not yet been added to suggestion list
				{
					possible.add(word);
				}
			}	
		}
		return possible;
	}
	
	
	/**
	 * looks for possible error of switched letters
	 * @param letters, char array for word
	 * @param dictionary, hashtable of words in dictionary
	 * @return ArrayList<String>, list of possible corrected words
	 */
	public static ArrayList<String> transposedLetter(char[] letters, 
			HashTable dictionary)
	{
		ArrayList<String> possible = new ArrayList<>(); 
		// list of possible errors
		
		for(int i = 0; i < (letters.length - 1); i++)
		// loop through characters in word except last one
		{
			char[] alter = new char[letters.length];
			// new char array so original array doesn't get altered
			for(int j = 0; j < letters.length; j++)
			{
				alter[j] = letters[j]; // copy original to new char array
			}
			
			// transpose letter at this index with letter at next index
			char temp = alter[i];
			alter[i] = alter[i + 1];
			alter[i + 1] = temp;
			
			String word = new String(alter);
			if(dictionary.contains(word))
			// dictionary contains word with transposed letters
			{
				possible.add(word);
			}
		}
		return possible;
	}
	
	
	/**
	 * looks for possible error of two connected strings
	 * @param letters, char array for word
	 * @param dictionary, hashtable of words in dictionary
	 * @return ArrayList<String>, list of possible corrected words
	 */
	public static ArrayList<String> pairLetter(char[] letters, 
			HashTable dictionary)
	{
		ArrayList<String> possible = new ArrayList<>(); 
		// list of possible errors
		
		for(int i = 1; i < (letters.length - 1); i++)
		// loop through characters in word excluding letters on end
		{
			// two char arrays to split original array to
			char[] first = new char[i];
			char[] second = new char[letters.length - i];
			
			for(int j = 0; j < letters.length; j++)
			// loop through characters in original word
			{
				if(j < i) // puts first i items in first array
				{
					first[j] = letters[j];
				}
				else // puts last length - i items in second array
				{
					second[j - i] = letters[j];
				}
			}

			String word = new String(first);
			String word2 = new String(second);
			if(dictionary.contains(word) && dictionary.contains(word2))
			// dictionary contains both sub-words
			{
				// place both sub-words in one string with space in between
				String together = new String(word + " " + word2);
				possible.add(together);
			}				
		}
		return possible;
	}
	
	
	/**
	 * prints the word and its results
	 * @param word, inputted word to print and print results for
	 * @param toPrint, results for word after comparing to dictionary
	 */
	public static void printCheck(String word, ArrayList<String> toPrint)
	{
		// print word, and first element in results (ok, not found, 
		// or a word suggestion)
		System.out.print(word + ": " + toPrint.get(0));
		
		// if there are more elements in results, print the rest
		for(int i = 1; i < toPrint.size(); i++)
		{
			System.out.print(", " + toPrint.get(i));
		}
		
		System.out.println(); // go to next line for next word
	}

	
	
	/**
	 * main method that runs the code
	 * @param args, String array of user input for filenames needed
	 */
	public static void main(String args[])
	{		
		if(args.length < numArgs) // need 2 arguments
		{
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		HashTable dictionary = new HashTable(1); // to store first arg
		ArrayList<String> input = new ArrayList<>(); // to store second arg
		
		String dName = args[0]; // name of dictionary file
		String words = args[1]; // name of input file
		
		// put dictionary into HashTable and input into ArrayList
		boolean check = populateDictionary(dName, dictionary);
		boolean check2 = populateInput(words, input);
		
		if(!check) // if dictionary was not loaded
		{
			System.out.println("\nUnable to load dictionary");
		}		
		if(!check2) // if input was not loaded
		{
			System.out.println("\nUnable to load input");
		}
		
		spellCheck(input, dictionary);
		// check spelling of input against dictionary
		
	}
} // end of SpellChecker class
