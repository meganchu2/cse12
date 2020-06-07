package hw7;

import java.util.LinkedList;

public class BSTree<T extends Comparable<? super T>>{

	private int nelems;
	private BSTNode root;

	 protected class BSTNode{

		T key;
		LinkedList<T> relatedInfo;
		BSTNode left;
		BSTNode right;
		
		//TODO BSTNode constructors and methods here
	}

	//BSTree methods here
	//BSTree_Iterator here
}
