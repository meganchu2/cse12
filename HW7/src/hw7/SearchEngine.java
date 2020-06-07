package hw7;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Title: class SearchEngine
 *  Description: class that generates a BST to search from
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-26
 */
public class SearchEngine 
{

	/*Populate a BST from a file
	 * @param searchTree - BST to be populated
	 * @param fileName - name of the input file
	 * @returns false if file not found, true otherwise
	 */
	public static boolean populateSearchTree(BSTree<String> searchTree, 
			String fileName) 
	{
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				//read two lines - one for document and the next line 
				// for the list of keywords
				String document = scanner.nextLine().trim();
				String keywords[] = scanner.nextLine().split(" ");
				
				for(int i = 0; i < keywords.length; i++) // loop through keys
				{
					String key = keywords[i].toLowerCase();
					// make sure keyword is lower case
					
					if(!searchTree.findKey(key)) 
					// if key is not in binary search tree
					{
						searchTree.insert(key); 
						// add key
					}
					searchTree.insertInformation(key, document);
					// add document to info in node
				}				
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			System.out.println("\nFile not found!!");
			return false;
		}
		return true;
	}
	
	
	/*Search a query in a BST
	 * @param searchTree - BST to be searched
	 * @param fileName - query string
	 * @returns LinkedList of documents in which the query string is found
	 */
	public static void searchMyQuery(BSTree<String> searchTree, String query) 
	{
		query = query.toLowerCase(); // make sure all keys lower case
		String key[] = query.split(" "); // splits query into separate words

		if(key.length == 1) // if there is only one word
		{
			if(searchTree.findKey(key[0])) // if this key is in tree
			{
				print(key[0], searchTree.findMoreInformation(key[0]));
			}
			else // if key is not in tree
			{
				print(key[0], null);
			}
		}
		else // if there are multiple queries
		{
			LinkedList<String> overlap = new LinkedList<String>();		 
			// documents that will be narrowed down to fit all queries
			
			LinkedList<String> printed = new LinkedList<String>();
			// documents that have been printed

			if(searchTree.findKey(key[0])) // if this key is in tree
			{
				// store info for first key
				LinkedList<String> str = 
						searchTree.findMoreInformation(key[0]);
				
				for(String i: str)
				{
					overlap.add(i);
					// copy info from first key to overlap list so we 
					// don't modify original list
				}
			}
			
			for(int index = 1; index < key.length; index++)
			// loop through second to last queries
			{
				if(searchTree.findKey(key[index])) // if key in tree
				{
					// list of documents matching query
					LinkedList<String> documents2 = 
							searchTree.findMoreInformation(key[index]);
					for(int i = overlap.size() - 1; i >= 0; i--)
					// loop through overlap documents
					{
						if(documents2.contains(overlap.get(i)))
						// if key contains document in overlap
						{
							// good, keep document
						}
						else // if key does not contain document in overlap
						{
							overlap.remove(i); 
							// document should not be in overlap
						}
					}
				}
				else // if key not in tree, cannot have overlap documents
				{
					overlap = new LinkedList<String>();
				}
			} // end of looping through queries
			
			print(query, overlap); // print overlap documents		
			printed.addAll(overlap); // add printed documents to printed list

			for(int i = 0; i < key.length; i++)
			// loop through queries
			{
				if(searchTree.findKey(key[i])) // if key in tree
				{
					// documents for query
					LinkedList<String> documents = 
							searchTree.findMoreInformation(key[i]);
					
					for(int j = 0; j < printed.size(); j++)
					// loop through all printed documents
					{
						if(documents.contains(printed.get(j)))
						// if a document has been printed
						{
							documents.remove(printed.get(j));
							// remove that document from list to print
						}
					}
					if(documents.isEmpty())
					{
						// print nothing
					}
					else
					{
						print(key[i], documents);
						printed.addAll(documents);
						// add printed documents to printed list
					}
				}
				else // if this key is not in tree
				{
					print(key[i], null);
				}
			} // end of second loop through queries
		} // end of else statement for multiple queries
	}
	
	
	/*Print method 
	 * @param query input
	 * @param documents - result of SearchMyQuery
	 */	 
	public static void print(String query, LinkedList<String> documents) 
	{
		if(documents==null || documents.isEmpty())
			System.out.println("The search yielded no results for "+query);
		else {
			Object[] converted = documents.toArray();
			Arrays.sort(converted);
			System.out.println("Documents related to "+ query +" are: "
			                   +Arrays.toString(converted));
		}
	}
	
	
	/**
	 * main method to run query search
	 * @param args, String user input to define documents and keywords, and
	 *        to define keywords inputted in search
	 */
	public static void main( String[] args ) 
	{
		
		if(args.length < 2) {
			System.err.println("Invalid number of arguments passed");
			return;
		}
		
		BSTree<String> searchTree = new BSTree<>();
		
		String fileName = args[0];
		String query = args[1];
		
		//Create my BST from file
		boolean check = populateSearchTree(searchTree, fileName);
		
		if(check == false) {
			System.out.println("\nUnable to create search tree");
		}
		
		searchMyQuery(searchTree, query);
	}
}
