package hw6;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.util.*;


/**
 * Title: class dHeap
 *  Description: class that implements a max or min heap
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-16
 */
public class dHeap<T extends Comparable<? super T>> 
        implements dHeapInterface<T> 
{    
	
    private T[] heap; //heap array
    private int d; //branching factor
    private int nelems;
    private boolean isMaxHeap; //boolean to indicate min or max heap
    public static final int capacity = 5;
    public static final int binary = 2;
		
    
	/**
	 * Initializes a binary max heap with capacity = 5
	 */
	public dHeap() 
	{
		// downcasting array to type T[]
		heap = (T[])new Comparable[capacity];
		d = binary;
		nelems = 0;
	}
	
	
    /**
     * Initializes a binary max heap with a given initial capacity.
     * 
     * @param heapSize The initial capacity of the heap.
     */
	public dHeap( int heapSize ) 
	{
		// downcasting array to type T[]
		heap = (T[])new Comparable[heapSize];
		d = binary;
		nelems = 0;
	}

	
	/**
	 * Initializes a d-ary heap (with a given value for d), with a given
	 * initial capacity.
	 * 
	 * @param d The number of child nodes each node in the heap should have.
	 * @param heapSize The initial capacity of the heap.
	 * @param isMaxHeap indicates whether the heap should be max or min
	 * @throws IllegalArgumentException if d is less than one.
	 */
	@SuppressWarnings( "unchecked" )
    public dHeap( int d, int heapSize, boolean isMaxHeap) 
    		throws IllegalArgumentException 
	{
		if(d < 1) // cannot have d-ary heap of d < 1
		{
			throw new IllegalArgumentException();
		}
		
		// downcasting array to type T[]
		heap = (T[])new Comparable[heapSize];
		this.d = d;
		this.isMaxHeap = isMaxHeap;
		nelems = 0;
	}

	
	/**
     * Returns the number of elements stored in the heap.
     * 
     * @return The number of elements stored in the heap.
     */
	@Override
	public int size() 
	{
	   return nelems; // return number of elements in heap
	}
	
	
	/**
     * Adds the specified element to the heap; o cannot be null. 
     * Resizes the storage if full.
     * 
     * @param data The element to add.
     * @throws NullPointerException if o is null.
     */
	@Override
	public void add( T data ) throws NullPointerException 
	{
		if(data == null) // data cannot be null
		{
			throw new NullPointerException();
		}
		
	    if(size() == heap.length) // if array is full
	    {
	    	resize();
	    }
	    
	    heap[size()] = data; // add data
	    bubbleUp(size());
	    nelems++; // increment number elements
	        
	}

	
	/**
     * Removes and returns the element at the root. If the 
     * heap is empty, then this method throws a NoSuchElementException.
     * 
     * @return The element at the root stored in the heap.
     * @throws java.util.NoSuchElementException if the heap is empty
     */
	@Override
	public T remove() throws NoSuchElementException 
	{
		if(size() == 0) // if heap is empty
		{
			throw new NoSuchElementException();
		}
		
		T toReturn = heap[0]; // store element at root
		heap[0] = heap[size() - 1]; // remove element at root
		trickleDown(0);
		nelems--; // decrement number elements	
		
	    return toReturn;	    
	}
	
	
	/**
     * Clears all the items in the heap
     * Heap will be empty after this call returns
     */
	@Override
	public void clear() 
	{
		for(int i = 0; i < heap.length; i++) // loop through heap
		{
			if(heap[i] == null) // if last item in heap is cleared exit loop
			{
				break;
			}
			
			heap[i] = null; // clear element at this index
			nelems--; // decrement number elements
		}
	}
	
	
	/**
     * Retrieves, but does not remove, the element at the root.
     * @return item at the root of the heap
     * @throws NoSuchElementException - if this heap is empty
     */
	@Override
	public T element() 
	{
		if(size() == 0)
		{
			throw new NoSuchElementException();
		}
		
		return heap[0]; // return element at root
	}
	
	
	/**
	 * reconfigures the heap after removing root after last element is 
	 * put at root
	 * @param index array index of element that needs to be put in 
	 *        correct place
	 */
	private void trickleDown(int index)
	{
		if(isMaxHeap) // for max heap
		{
			while(true) // loop until break
			{
				int start = d*index + 1; // starting index of children
				int end = d*index + d; // ending index of children
				int indexMaxChild = index; // index of maximum child if any
				
				for(int i = start; i <= end; i++) // loop through children
				{
					if(heap[i].compareTo(heap[indexMaxChild]) == 1)
					{
						indexMaxChild = i; // gets max child index if any
					}
				}
				if(indexMaxChild != index) // if there is greater child
				{
					T temp = heap[index];
					heap[index] = heap[indexMaxChild];
					heap[indexMaxChild] = temp;
					index = indexMaxChild;
					// current element switched iwth max child
				}
				else // if this element greater than children
				{
					break;
				}				
			}
		}
		else // for min heap
		{
			while(true) // loop until breka
			{
				int start = d*index + 1; // children starting index
				int end = d*index + d; // children ending index
				int indexMinChild = index; // index of smallest child if any
				
				for(int i = start; i <= end; i++) // loop through children
				{
					if(size() > i && 
							heap[i].compareTo(heap[indexMinChild]) == -1)
					{
						indexMinChild = i; // get index of min child if any
					}
				}
				if(indexMinChild != index) // if there is min child
				{
					T temp = heap[index];
					heap[index] = heap[indexMinChild];
					heap[indexMinChild] = temp;
					index = indexMinChild;
					// switch this element with min child
				}
				else // if element smaller than children
				{
					break;
				}				
			}
		}
	}
	
	
	/**
	 * method for putting added element in right place
	 * @param index array index of added element
	 */
	private void bubbleUp(int index)
	{
		if(isMaxHeap) // for max heap
		{
			while(heap[index].compareTo(heap[parent(index)]) == 1)
			{
				T temp = heap[index];
				heap[index] = heap[parent(index)];
				heap[parent(index)] = temp;
				index = parent(index);
				// switch element and parent if element > parent
			}
		}
		else // for min heap
		{
			while(heap[index].compareTo(heap[parent(index)]) == -1)
			{
				T temp = heap[index];
				heap[index] = heap[parent(index)];
				heap[parent(index)] = temp;
				index = parent(index);
				// switch element and parent if element < parent
			}
		}
	}
	
	
	/**
	 * makes the array for heap bigger if no more space
	 */
	private void resize()
	{
		T[] temp;
		if(size() == 0) // if size of array is 0
		{
			temp = (T[])new Comparable[1];
		}
		else // if size of array is not zero
			
		{
			temp = (T[])new Comparable[size()*binary];
		}
		
		for(int i = 0; i < heap.length; i++) // copy heap to bigger array
		{
			temp[i] = heap[i];
		}
		heap = temp; // set heap to bigger array
	}
	
	
	/**
	 * gives the index of the parent vertex/node
	 * @param index array index of element whose parent index will be found
	 * @return index of parent element
	 */
	private int parent(int index)
	{
		int i = (int)Math.floor((index - 1) / d);
		return i;
	}
	
	
	/**
	 * finds the min value in a max-heap in dHeap class
	 * @return T, the min value in the heap
	 */
	public T findMaxinMin()
	{
		int indexMin = heap.length - 1; // index of min value in heap
		
		// find height of tree
		int height = (int) Math.ceil(Math.log(heap.length)/Math.log(d));
		
		// use height to calculate number of leaves, because min value
		// must be among leaves
		int numLeaves = (int) Math.pow(d,height);
		
		// start from last element in array
		int run = heap.length - 1;
		
		for(int i = 0; i <= numLeaves; i++) // loop through leaves
		{
			if(heap[indexMin].compareTo(heap[run]) == 1)
			// can change 1 to -1 if looking for max in min heap instead
			{
				indexMin = run; // store smallest value out of leaves
			}
			run--;
		}
		return heap[indexMin]; // return min value
	}
	
	
	/**
	 * returns all the occurrences of the values greater than a parameter 
	 * 'k' in a max-heap
	 * @param k threshold value to find elements greater than it
	 * @return LinkedList<T> of elements greater than k
	 */
	public LinkedList<T> findGreaterThanK(T k)
	{
		LinkedList<T> toReturn = new LinkedList<>();
		T[] temp = heap; // store heap so we don't change it
		
		T add = remove(); // remove root max value
		while(add.compareTo(k) == 1) // if max value in heap greater than k
		{
			toReturn.addLast(add);
			// we can also use addFirst if we wanted the returned list in 
			// increasing order
			add = remove(); // get next greatest value
		}
		
		heap = temp; // restore original heap
		return toReturn; // return values greater than k
	}
	
} // End of public class dHeap<T extends Comparable<? super T>> 
  // implements dHeapInterface<T>.
