package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/

import hw4.DoubleEndedLL;


/**
 * Title: class MyStack
 *  Description: class for creating a LIFO stack
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MyStack<T> implements Stack_QueueInterface<T> 
{
	private DoubleEndedLL<T> stack;
	
	
	/**
	 * constructor for MyStack class, initializes new DoubleEndedLL
	 */
	public MyStack()
	{
		stack = new DoubleEndedLL<>();
	}
	
	
	/** Tests if the storage is empty. 
	    * @return true a storage contains no items; false otherwise.
	    */ 
	public boolean isEmpty()
	{
		if(stack.isEmpty()) // if no elements in stack
	    {
			return true;
	    }
		return false;
	}

	
	/** Adds an element to a storage 
	    * @param newItem - item to be added
	    */  
	public void addElement(T newItem)
	{
		stack.addFirst(newItem);
	}

	
	/** Removes the object from the storage and returns
	    * that object as the value of this function.
	    * @return value of the removed object.
	    */  
	public T removeElement()
	{
		return stack.removeFirst();
	}

	
	/** Returns the number of items in the storage 
	    * @return the number of items in the storage
	    */ 
	public int size()
	{
		return stack.size();
	}

	
	/** Returns the next item to be removed
	 * @return element to be removed next
	 * @throws NullPointerException if list is empty
	 */
	public T peek()
	{
		if(size() == 0) // if stack is empty
		{
			throw new NullPointerException();
		}
		
		T toReturn = stack.getHeadValue();
		return toReturn;	
	}

} // end of MyStack class
