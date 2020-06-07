package hw7;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;


/**
 * Title: class BSTree
 *  Description: class that holds a binary search tree
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-26
 */
public class BSTree<T extends Comparable<? super T>>
{
	
	private int nelems;
	private BSTNode root;

	
	/**
	 * Title: class BSTNode
	 *  Description: class that represents nodes of a binary search tree
	 *
	 * @version 1.0
	 * @author  Megan Chu
	 * @since   2017-02-26
	 */
	protected class BSTNode
	{
		T key;
		LinkedList<T> relatedInfo;
		BSTNode left;
		BSTNode right;
		
		//TODO BSTNode constructors and methods here
		
		/**
		 * A constructor that initializes the BSTNode instance variables
		 * @param left, left child node of this node
		 * @param right, right child node of this node
		 * @param relatedInfo, contents contained in this node
		 * @param key, value used to order this node
		 */
		public BSTNode(BSTNode left, BSTNode right, LinkedList<T> 
		               relatedInfo, T key)
		{
			this.left = left;
			this.right = right;
			this.key = key;
			this.relatedInfo = relatedInfo;
		}
		
		
		/**
		 * A constructor that initializes the BSTNode instance variables 
		 * and creates an empty LinkedList for the node
		 * @param left, left child node of this node
		 * @param right, right child node of this node
		 * @param key, value used to order this node
		 */
		public BSTNode(BSTNode left, BSTNode right, T key)
		{
			this.left = left;
			this.right = right;
			this.key = key;
			this.relatedInfo = new LinkedList<T>();
		}
		
		
		/**
		 * getter for the key
		 * @return T, the key
		 */
		public T getKey()
		{
			return key;
		}
		
		
		/**
		 * getter for left child of node
		 * @return BSTNode, the left child
		 */
		public BSTNode getLeft()
		{
			return left;
		}
		
		
		/**
		 * getter for right child of node
		 * @return BSTNode, the right child
		 */
		public BSTNode getRight()
		{
			return right;
		}
		
		
		/**
		 * getter for the LinkedList in the node
		 * @return LinkedList<T>, the LinkedList of the node
		 */
		public LinkedList<T> getRelatedInfo()
		{
			return relatedInfo;
		}
		
		
		/**
		 * setter for the left pointer of the node
		 * @param newLeft, new left child of the node
		 */
		public void setLeft(BSTNode newLeft)
		{
			left = newLeft;
		}
		
		
		/**
		 * setter for the right pointer of the node
		 * @param newRight, new right child of the node
		 */
		public void setRight(BSTNode newRight)
		{
			right = newRight;
		}
		
		
		/**
		 * setter for the LinkedList of the node
		 * @param newInfo, new relatedInfo of the node
		 */
		public void setRelatedInfo(LinkedList<T> newInfo)
		{
			relatedInfo = newInfo;
		}
		
		
		/**
		 * append new info to the end of the existing LinkedList of the node
		 * @param info, new info to append to end of relatedInfo list
		 */
		public void addNewInfo(T info)
		{
			relatedInfo.add(info);
		}
		
		
		/**
		 * remove 'info' from the LinkedList of the node and return true,
		 * if the LinkedList does not contain the value 'info', return false
		 * @param info, the info to rmove from relatedInfo list if it exists
		 */
		public boolean removeInfo(T info)
		{
			return relatedInfo.remove(info);
		}
	} // end of BSTNode class

	
	//BSTree_Iterator here
	
	/**
	 * Title: class BSTree_Iterator
	 *  Description: iterator for the BSTree class
	 *
	 * @version 1.0
	 * @author  Megan Chu
	 * @since   2017-02-26
	 */
	public class BSTree_Iterator implements Iterator<T>
	{
		
		Stack<BSTNode> tree;
		
		/**
		 * constructor that initializes the Stack with the leftPath of the 
		 * root
		 */
		public BSTree_Iterator()
		{
			tree = new Stack<>();
			BSTNode store = root;
			
			while(store != null) 
			// populates stack with leftmost path of tree with root at bottom
			{
				tree.push(store);
				store = store.getLeft();
			}
		}
		
		
		/**
		 * tells if the stack has a next element to pop
		 * @return boolean, false if stack is empty, otherwise true
		 */
		public boolean hasNext()
		{
			return !tree.empty();
		}
		
		
		/**
		 * gets the next item in the binary search tree
		 * @return T, the top of the stack
		 * @throws NoSuchElementException if there is no next item
		 */
		public T next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			BSTNode store = tree.pop(); // pop top of stack
			BSTNode toAdd = store.getRight();
			
			if(toAdd != null) // if there is a right child to popped item
			{
				tree.push(toAdd); // put right child in stack
				toAdd = toAdd.getLeft();
				while(toAdd != null)
				// put leftmost path under right child in stack
				{
					tree.push(toAdd);
					toAdd = toAdd.getLeft();
				}
			}
			
			return store.getKey(); // return popped item
		}
	} // end of BSTree_Iterator class
	
	
	//BSTree methods here
	
	/**
	 * A 0-arg constructor that initializes root to null and nelems to 0
	 */
	public BSTree()
	{
		root = null;
		nelems = 0;
	}
	
	
	/**
	 * getter for the root of the BSTree
	 * @return BSTNode, root of BSTree
	 */
	public BSTNode getRoot()
	{
		if(nelems == 0)
		{
			return null;
		}
		else
		{
			return root;
		}
	}
	
	
	/**
	 * getter for the size of the BSTree
	 */
	public int getSize()
	{
		return nelems;
	}
	
	
	/**
	 * inserts a key into the BST
	 * @param key, the node to insert
	 * @throws NullPointerException if key is null
	 */
	public void insert(T key)
	{
		if(key == null)
		{
			throw new NullPointerException();
		}
		if(root == null) // if we are inserting first node
		{
			root = new BSTNode(null, null, key); // make new node the root
			nelems++;
		}
		else
		{
			if(addHelper(root, key)) // add new node in correct spot
			{
				nelems++;
			}
		}
	}
	
	
	/**
	 * helper method to add to the binary search tree
	 * @param currRoot, node to compare new node key to
	 * @param toAdd, key of new node to add to binary search tree
	 */
	private boolean addHelper(BSTNode currRoot, T toAdd)
	{
		int value = toAdd.compareTo(currRoot.getKey());
		if(value == 0) // there is already a node with the same key
		{
			return false;
		}
		if(value < 0) // this key is smaller than the current node
		{
			if(currRoot.getLeft() == null) // current node has no left child
			{
				currRoot.setLeft(new BSTNode(null, null, toAdd));
				// make new node left child
			}
			else // if current node has left child
			{
				addHelper(currRoot.getLeft(), toAdd);
				// need to compare new node to left child
			}
		}
		else // value > 0, this key is greater than the current node
		{
			if(currRoot.getRight() == null) // currNode has no right child
			{
				currRoot.setRight(new BSTNode(null, null, toAdd));
				// make new node right child
			}
			else // if current nod has right child
			{
				addHelper(currRoot.getRight(), toAdd);
				// need to compare new node to right child
			}
		}
		return true;
	}
	
	
	/**
	 * finds the given key in the binary search tree
	 * @param key, the given key to find
	 * @return boolean, true if key is found, false otherwise
	 * @throws NullPointerException if the key is null
	 */
	public boolean findKey(T key)
	{
		return contains(root, key);
	}
	
	
	/**
	 * helper method to find key in the binary search tree
	 * @param currRoot, current node we are comparing to
	 * @param toFind, key we are looking for
	 * @return boolean, true if toFind is in binary search tree rooted at 
	 *         root, false otherwise
	 */
	private boolean contains(BSTNode currRoot, T toFind)
	{
		if(currRoot == null) // we have reached end of tree w/o finding key
		{
			return false;
		}
		if(toFind.compareTo(currRoot.getKey()) < 0) 
		// key less than current node
		{
			return contains(currRoot.getLeft(), toFind);
			// look for key in left subtree
		}
		else if(toFind.compareTo(currRoot.getKey()) > 0)
		// key greater than current node
		{
			return contains(currRoot.getRight(), toFind);
			// look for key in right subtree
		}
		else // key is equal to key of current node
		{
			return true; // we have found key
		}
	}
	
	
	/**
	 * inserts 'info' into the LinkedList of the node whose key is 'key'
	 * @param key, the key of node whose list we are adding to
	 * @param info, the element we are adding to the list
	 * @throws NullPointerException if 'key' or 'info' is null
	 * @throws IllegalArgumentException if 'key' is not found in the BST
	 */
	public void insertInformation(T key, T info)
	{
		if(key == null || info == null)
		{
			throw new NullPointerException();
		}
		if(!findKey(key))
		{
			throw new IllegalArgumentException();
		}
		
		findNode(root, key).addNewInfo(info);
	}
	
	
	/**
	 * helper to find the node of a certain key
	 * @param currRoot, current node we are comparing to
	 * @param toFind, key of the node we are looking for
	 * @return BSTNode, node corresponding to the given key
	 */
	private BSTNode findNode(BSTNode currRoot, T toFind)
	{
		if(toFind.compareTo(currRoot.getKey()) < 0) 
		// key less than current node
		{
			return findNode(currRoot.getLeft(), toFind);
			// look for key in left subtree
		}
		else if(toFind.compareTo(currRoot.getKey()) > 0)
		// key greater than current node
		{
			return findNode(currRoot.getRight(), toFind);
			// look for key in right subtree
		}
		else // key is equal to key of current node
		{
			return currRoot; // we have found key
		}
	}
	
	
	/**
	 * gets the LinkedList of the node with key value 'key'
	 * @param key, the key of the node whose LinkedList is returned
	 * @return LinkedList<T>, list contained in node corresponding to key
	 * @throws NullPointerException, if key is null
	 * @throws IllegalArgumentException, if 'key' is not found in the BST
	 */
	public LinkedList<T> findMoreInformation(T key)
	{
		if(key == null)
		{
			throw new NullPointerException();
		}
		if(!findKey(key))
		{
			throw new IllegalArgumentException();
		}
		
		return findNode(root, key).getRelatedInfo();
	}
	
	
	/**
	 * gets the height of the tree.  The height of a tree is the length of 
	 * the longest downward path to a leaf from the root.  By convention, 
	 * height of an empty tree is -1 and the height of the root is 0.
	 * @return int, the height of the tree
	 */
	public int findHeight()
	{
		if(getSize() == 0)
		{
			return -1;
		}
		
		LinkedList<Integer> heights = trace(root);
		
		int max = 0; // smallest possible tree height
		
		for(int i = 0; i < heights.size(); i++)
		// loop through lengths of all paths from root to every leaf
		{
			if(heights.get(i) > max)
			{
				max = heights.get(i);
			}
		}
		return max-1; // get path with longest length
	}
	
	
	/**
	 * helper method to find height of binary search tree
	 * @param currNode, current node we have traced to
	 * @return
	 */
	private LinkedList<Integer> trace(BSTNode currNode)
	{
		LinkedList<Integer> toReturn = new LinkedList<Integer>();
		
		if(currNode.getRight() == null && currNode.getLeft() == null)
		// if this is a leaf node
		{
			toReturn.add(1);
			return toReturn; // returns list of 1
		}
		
		if(currNode.getRight() == null) // if only a left child
		{
			LinkedList<Integer> store = trace(currNode.getLeft());
			// get length of paths for left subtree
			
			for(int i = 0; i < store.size(); i++)
			{
				int j = store.get(i) + 1;
				toReturn.add(j);
			} // add 1 to all paths, and add to final list
		}
		else if(currNode.getLeft() == null) // if only a right child
		{
			LinkedList<Integer> store = trace(currNode.getRight());
			// get length of paths for right subtree
			
			for(int i = 0; i < store.size(); i++)
			{
				int j = store.get(i) + 1;
				toReturn.add(j);
			} // add 1 to all paths, and add to final list
		}
		else // if have left and right child
		{
			LinkedList<Integer> store = trace(currNode.getRight());
			LinkedList<Integer> store2 = trace(currNode.getLeft());
			// get length of paths for both subtrees
			
			for(int i = 0; i < store.size(); i++) 
			// loop through right subtree
			{
				int j = store.get(i) + 1;
				toReturn.add(j);
			} // add 1 to all paths, and add to final list
			
			for(int i = 0; i < store2.size(); i++)
			// loop through left subtree
			{
				int j = store2.get(i) + 1;
				toReturn.add(j);
			} // add 1 to all paths, and add to final list
		}
		return toReturn;
	}
	
	
	/**
	 * gets the number of leaf nodes in the tree
	 */
	public int leafCount()
	{
		if(getSize() == 0)
		{
			return 0;
		}
		return trace(root).size();
	}
	
	
	/**
	 * iterator for BSTree class
	 */
	public Iterator<T> iterator()
	{
		return new BSTree_Iterator();
	}
	
	
	/**
	 * method that returns elements that can be found in both bsts
	 * @param iter1, iterator for the first bst
	 * @param iter1, iterator for the second bst
	 * @return ArrayList<T>, arraylist of elements in both bsts
	 */
	public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2)
	{
		ArrayList<T> array1 = new ArrayList<>(); // store first bst
		ArrayList<T> array2 = new ArrayList<>(); // store second bst
		ArrayList<T> toReturn = new ArrayList<>(); // store intersection
		
		while(iter1.hasNext())
		{
			array1.add(iter1.next()); // populate first bst with iterator
		}
		while(iter2.hasNext())
		{
			array2.add(iter2.next()); // populate second bst with iterator
		}
		
		for(T i: array1) // for every element in first bst
		{
			if(array2.contains(i)) // if second bst contains element
			{
				toReturn.add(i); // add element to list to return
			}
		}
		
		return toReturn;
	}
	
	
	/**
	 * method that counts number of nodes at a given level
	 * @param level, the level of bst to count nodes from
	 * @return int, the number of nodes in spcified level of bst
	 */
	public int levelCount(int level)
	{
		ArrayList<Integer> temp = levelHelper(root, level);
		// contains a 1 for every node in level
		
		int nodes = 0; // store nodes in level
		
		for(int i: temp) // for every element in temp
		{
			if(i == 1) // if the element is 1
			{
				nodes++; // we have to count one more node in level
			}
		}
		
		if(nodes == 0) // if there were no nodes in level
		{
			return -1; // level is not in bst
		}
		
		return nodes; // return total number of nodes in level
	}
	
	
	/**
	 * helper method for finding nodes in a level
	 * @param currNode, the node we are currently traversing through
	 * @param counter, keeps track of how far from level we are
	 * @return ArrayList<Intege>, contains list of 1's for every node in 
	 *         level, otherwise it is empty
	 */
	public ArrayList<Integer> levelHelper(BSTNode currNode, int counter)
	{
		ArrayList<Integer> temp = new ArrayList<>(); // store nodes in level
		
		if(currNode == null) // if current node is null
		// in case the root of tree was null
		{
			return temp; // no recursion
		}
		
		if(currNode != null && counter == 0) 
		// if we are at the level, and the current node is not null
		{
			temp.add(1); // add a 1 to returning list to count this node
			return temp;
		}
		else if(currNode.getRight() == null && currNode.getLeft() == null)
		// if this is a leaf and we have not reached the level
		{
			// do nothing
		}
		else if(currNode.getRight() == null) // if no right child
		{
			temp.addAll(levelHelper(currNode.getLeft(), counter - 1));
			// add all counted nodes in recursive call to left child
		}
		else if(currNode.getLeft() == null) // if no left child
		{
			temp.addAll(levelHelper(currNode.getRight(), counter - 1));
			// add all counted nodes in recursive call to right child
		}
		else // if there is left and right child
		{
			temp.addAll(levelHelper(currNode.getLeft(), counter - 1));
			// add all counted nodes in recursive call to left child
			
			temp.addAll(levelHelper(currNode.getRight(), counter - 1));
			// add all counted nodes in recursive call to right child
		}
		return temp;
	}
	
	
	/**
	 * adds a child of same data as parent to every leaf, right child if
	 * the parent data length is odd, and left child if length is even
	 */
	public void addChildToLeaves()
	{
		childHelper(root);
	}
	
	
	/**
	 * helper class to add a child to every leaf in bst
	 * @param currNode, current root we are looking at to see if it is a leaf
	 */
	public void childHelper(BSTNode currNode)
	{
		if(currNode.getLeft() == null && currNode.getRight() == null)
		// if this is a leaf node
		{
			if(((String)currNode.getKey()).length() % 2 == 0)
			// if the length of the key is even
			{
				currNode.setLeft(new BSTNode(null, null, currNode.getKey()));
				// add left child
				nelems++;
			}
			else // if length of key is odd
			{
				currNode.setRight(new BSTNode(null, null, currNode.getKey()));
				// add right child
				nelems++;
			}
		}
		else if(currNode.getLeft() == null) // if only a right child
		{
			childHelper(currNode.getRight()); 
			// continue looking for leaf from right subtree
		}
		else if(currNode.getRight() == null) // if only a left child
		{
			childHelper(currNode.getLeft());
			// continue looking of leaf from left subtree
		}
		else // if two children
		{
			childHelper(currNode.getRight());
			childHelper(currNode.getLeft());
			// continue looking for leaf in both subtrees
		}
	}
	
	
	/**
	 * method that checks for full BST
	 * @return boolean, true if every node has no children 2 children,
	 *         otherwise false
	 */
	public boolean isFullBST()
	{
		return fullHelper(root);
	}
	
	
	/**
	 * helper method to see if BST is full
	 * @param currNode, node who's children we are checking
	 * @return boolean, true if node has 2 or no children, false otherwise
	 */
	public boolean fullHelper(BSTNode currNode)
	{
		if(currNode.getRight() == null && currNode.getLeft() == null)
		{
			return true; // node is leaf node
		}
		else if(currNode.getRight() == null || currNode.getLeft() == null)
		{
			return false; // node has 1 child only
		}
		else // node has 2 children
		{
			// check left and right subtrees for full bst
			boolean left = fullHelper(currNode.getLeft());
			boolean right = fullHelper(currNode.getRight());
			
			if(!left || !right) // if we have even one subchild with 1 child
			{
				return false; // everything is false
			}
			else // if all subchildren have one or two children
			{
				return true;
			}
		}
	}
} // end of BSTree class
