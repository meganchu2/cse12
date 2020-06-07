package hw5;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

import java.util.Stack;


/**
 * Title: class InsertionSort
 *  Description: class for sorting stacks of integers
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-13
 */
public class InsertionSort 
{
	public static Stack<Integer> stack1; // random
	public static Stack<Integer> stack2; // reverse
	public static Stack<Integer> stack3; // in order
	
	/**
	 * constructor that initializes stacks to be sorted
	 */
	public InsertionSort()
	{
		stack1 = new Stack<>(); // top to bottom order:
		// 1, 10, 2, 7, 5, 1
		stack1.push(1);
		stack1.push(5);
		stack1.push(7);
		stack1.push(2);
		stack1.push(10);
		stack1.push(1);
		
		stack2 = new Stack<>(); // top to bottom order:
		// 31, 27, 22, 15, 7
		stack2.push(7);
		stack2.push(15);
		stack2.push(22);
		stack2.push(27);
		stack2.push(31);
		
		stack3 = new Stack<>(); // top to bottom order:
		// 6, 9, 18, 24
		stack3.push(24);
		stack3.push(18);
		stack3.push(9);
		stack3.push(6);
	}
	
	
	/**
	 * pops the stack recursively, then sorts all the values starting 
	 * with the one popped in the last recursive call
	 * @param e Stack of integers to sort
	 */
	public void insertionSort(Stack<Integer> e)
	{
		if(!e.empty())
		{
			int popped = e.pop(); // pops value
			insertionSort(e); // pops all values in stack
			sortAll(e, popped); // sorts each individual value
		}
	}
		
	
	/**
	 * sorts stack recursively
	 * @param e Stack of integers to sort
	 */
    public void sortAll(Stack<Integer> e, int i)
	{
		if(e.empty() || i <= e.peek()) // if this is the first element we 
		// are putting back in stack, or if this element is smaller than 
		// or equal to the top element
		{
			e.push(i);			
		}
		
		else // this integer is bigger than the top one
		{
			int store = e.pop(); // pop top element
			
			sortAll(e, i); // see if  stack is empty or see if this integer 
			// smaller than next item in stack or continue popping
			
			e.push(store); // then put back all popped items when we have 
			// found correct spot for this one
		}
	}

    /**
     * main method to run and print out sorted stacks
     * @param args, any user input we might need when running the code
     */
	public static void main(String[] args) 
	{
		InsertionSort sorter = new InsertionSort(); // new sorter
				
		sorter.insertionSort(stack1); // sort stack
		System.out.println("Sorted stack 1:");
		while(!stack1.empty()) // print out sorted stack, top to bottom
		{			
			System.out.println(stack1.pop());
		}
				
		System.out.println();
		
		sorter.insertionSort(stack2); // sort stack
		System.out.println("Sorted stack 2:");
		while(!stack2.empty()) // print out sorted stack, top to bottom
		{
			System.out.println(stack2.pop());
		}
				
		System.out.println();
				
		sorter.insertionSort(stack3); // sort stack
		System.out.println("Sorted stack 3:");
		while(!stack3.empty()) // print out sorted stack, top to bottom
		{
			System.out.println(stack3.pop());
		}
	}

} // end of InsertionSort class
