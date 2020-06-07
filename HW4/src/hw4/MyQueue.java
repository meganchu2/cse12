package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class MyQueue
 *  Description: class for creating a FIFO stack
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MyQueue<T> implements Stack_QueueInterface<T> 
{
	
	private int size = 5; // initial size of queue
	private int head; // remove from
	private int tail; // add to
	private T[] array; // storage for elements
	private int twoTimes = 2; // to increase array size
	private int counter; // counts size of queue
	
	
	/**
	 * constructor that initializes array to specified size and sets head,
	 * tail, and counter for elements to 0
	 */
	public MyQueue()
	{
		array = (T[]) new Object[size];
		head = 0;
		tail = 0;
		counter = 0;
	}
	
	
	/** Tests if the storage is empty. 
	    * @return true a storage contains no items; false otherwise.
	    */ 
	public boolean isEmpty()
	{
	    if(counter == 0)
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
		if(((tail + 1) % size) == head) // if array full
		{			
			T[] arrayTemp = (T[]) new Object[twoTimes*size];
			// new array of double size
			
			for(int i = 0; i < counter; i++)
			{
				arrayTemp[i] = array[(head + i)%(size)];
				// set head to 0th index on double sized array
			}
			
			size = twoTimes*size; // update size
			array = arrayTemp; // update array
			head = 0; // update head
			tail = counter; // update tail
		}
		
		array[tail] = newItem; // add new element
		tail = (tail + 1) % size; // increment tail
		counter++;
	}

	
	/** Removes the object from the storage and returns
	    * that object as the value of this function.
	    * @return value of the removed object.
	    */  
	public T removeElement()
	{
		T temp = array[head]; // element to return
		
		array[head] = null; // remove element from list
		
		head = (head + 1) % size; // update head
		
		counter--;
		return temp;
	}

	
	/** Returns the number of items in the storage 
	    * @return the number of items in the storage
	    */ 
	public int size()
	{
		return counter;
	}

	
	/** Returns the next item to be removed
	 * @return element to be removed next
	 * @throws NullPointerException if list is empty
	 */
	public T peek()
	{
		if(isEmpty()) // if no elements in array
		{
			throw new NullPointerException();
		}
		
		return array[head];
	}
	
	/**
	 * gets that last element in the array
	 * @return the last element that was added
	 */
	public T getLastAdd()
	{
		return array[tail - 1];
	}

} // end of MyQueue class
