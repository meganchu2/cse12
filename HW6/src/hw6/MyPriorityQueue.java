package hw6;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class MyPriorityQueue
 *  Description: class that implements a queue with priority
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-16
 */
public class MyPriorityQueue<T extends Comparable<? super T>> 
{
    private dHeap<T> pQ;
    
    public MyPriorityQueue( int initialSize ) 
    {
    	pQ = new dHeap<T>(initialSize);    	
    }
    
    
    /**
     * Inserts an element into the Priority Queue. The element received 
     * cannot be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer( T element ) throws NullPointerException 
    {
    	if(element == null) // element to add cannot be null
    	{
    		throw new NullPointerException();
    	}
    	
    	pQ.add(element); // adds element to heap
    	return true;        
    }
    
    
    /**
     * Retrieves the head of this Priority Queue (smallest element), or null 
     * if the queue is empty.
     *
     * @return The head of the queue (smallest element), or null if queue is
     *           empty.
     */
    public T poll() 
    {
    	if(pQ.size() != 0) // if the queue is not empty
    	{
    		return pQ.remove(); // return smallest element (root)
    	}
    	else // if queue is empty
    	{
    		return null;
    	}            
    }
    
    
    /**
     * Clears the contents of the queue
     */
    public void clear() 
    {
    	pQ.clear();
    }
    
    
    /**
     * Retrieves, but does not remove, the head of this queue, or
     *  returns null if this queue is empty.
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() 
    {
    	if(pQ.size() != 0) // if the queue is not empty
    	{
    		return pQ.element(); // return smallest element (root)
    	}
    	else // if queue is empty
    	{
    		return null;
    	}
    }

} // end of MyPriorityQueue class
