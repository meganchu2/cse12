
package hw6;

public class MyPriorityQueue<T extends Comparable<? super T>> {
    
    
    public MyPriorityQueue( int initialSize ) {
        
       //TODO
        
    }
    
    /**
     * Inserts an element into the Priority Queue. The element received cannot
     * be null.
     *
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer( T element ) throws NullPointerException {
        
       //TODO
        
    }
    
    /**
     * Retrieves the head of this Priority Queue (smallest element), or null 
     * if the queue is empty.
     *
     * @return The head of the queue (smallest element), or null if queue is
     *           empty.
     */
    public T poll() {
        return null; //XXX-CHANGE-XXX
       //TODO
            
    }
    
    /**
     * Clears the contents of the queue
     */
    public void clear() {
    	//TODO
    }
    
    /**
     * Retrieves, but does not remove, the head of this queue, or
     *  returns null if this queue is empty.
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() {
    	return null; //XXX-CHANGE-XXX
    }

}
