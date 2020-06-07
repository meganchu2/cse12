package hw5;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class DoubleEndedLL
 *  Description: class for tracking a double ended singly linked list
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T> 
{
	  private int nelems;
	  private Node head;
	  private Node tail;
	  
	  
	  /**
	   * Title: class Node
	   *  Description: class for tracking components of linked list
	   *
	   * @version 1.0
	   * @author  Megan Chu
	   * @since   2017-02-08
	   */
	  protected class Node 
	  {
		  
	    T data; // data in node
	    Node next; // link to next node


	    /** 
	     * Constructor to create singleton Node
	     * Initializes the node with element
	     *
	     * @param element element to add, can be null
	     */
	    public Node(T element)
	    {
	      data = element;
	    }


	    /** Constructor to create singleton link it between previous and next
	     *   Initializes the node with element, prevNode as its predecessor 
	     *   node and nextNode as its successor node
	     *
	     *   @param element Element to add, can be null
	     *   @param nextNode successor Node, can be null 
	     */
	    public Node(T element, Node nextNode)
	    {
	      data = element;
	      next = nextNode;
	    }


	    /** 
	     *  Removes this node from the list. Links this node's 
	     *  predecessor and successor nodes. 
	     */
	    public void remove()
	    {
	    	data = null;
	    	next = null;
	    }


	    /** 
	     * Set n as the next node
	     *
	     *  @param n new next node
	     */
	    public void setNext(Node n)
	    {
	      next = n;
	    }


	    /** 
	     * Set e as the node's element 
	     *
	     *  @param e new element 
	     */
	    public void setElement(T e)
	    {
	      data = e;
	    }


	    /** 
	     * Accessor to get the next Node in the list
	     *
	     * @return the node's successor
	     */
	    public Node getNext()
	    {
	      if(this != tail) // if at tail return null
	      {
	        return next;
	      }
	      return (Node)null;
	    }


	    /** 
	     * Accessor to get the Nodes Element 
	     *
	     * @return the node's data
	     */
	    public T getElement()
	    {
	      return data;
	    }
	 
	  } // end of Node class
	  
	  
	  /**
	   * constructor for DoubleEndedLL, initializes number 
	   * of components to 0
	   */
	  public DoubleEndedLL()
	  {
		  nelems = 0;
	  }
	  
	  
	  /** Checks if the list is empty
	   * @return returns true if the list is empty, false otherwise
	   */
	  public boolean isEmpty()
	  {
		  if(nelems == 0) // if no components return true
		  {
			  return true;
		  }
		  return false;
	  }

	  
	  /** Checks the size of the list
	   * @return returns the number of elements in the list
	   */
	  public int size()
	  {
		  return nelems;
	  }


	  /** Adds a new node to the front of the list
	   * @param newItem - an element to add
	   */
	  public void addFirst(T newItem)
	  {

		  Node temp = new Node(newItem);
		  
		  if(size() == 0) // if this is first add
		  {
			  head = temp;
			  tail = temp;
		  }
		  else // if there are other elements in list
		  {
			  temp.setNext(head);
			  head = temp;
		  }
		  
		  nelems++;
	  }

	  
	  /** Adds a new node to the end of the list
	   * @param newItem - an element to add
	   */
	  public void addLast(T newItem)
	  {
		  Node temp = new Node(newItem);
		  
		  if(size() == 0) // if this is first add
		  {
			  head = temp;
			  tail = temp;
		  }
		  else // if there are other elements in list
		  {
			  getNth(size() - 1).setNext(temp);
			  tail = temp;
		  }
		  
		  nelems++;
	  }

	  
	  /** Removes a node from the beginning of the list
	   * @return element the data found
	   * @throws NullPointerException if the list is empty
	   */
	  public T removeFirst()
	  {
		  if(size() == 0) // if there are no elements
		  {
			  throw new NullPointerException();
		  }
		  
		  T store = getHeadValue(); // element to return
		  
		  if(size() == 1) // if there is only one element
		  {
			  getNth(0).remove();
			  head = null;
			  tail = null;
		  }
		  else // otherwise
		  {
			  Node temp = head.getNext();
		      head.remove();
		      head = temp;
		  }
		  
		  nelems--;
		  return store;
	  }


	  /** Removes a node from the end of the list
	   * @return element the data found
	   * @throws NullPointerException if the list is empty
	   */
	  public T removeLast()
	  {
		  if(size() == 0) // if there are no elements
		  {
			  throw new NullPointerException();
		  }
		  
		  T store = tail.getElement();
		  
		  if(size() == 1) // if there is only one element
		  {
			  tail.remove();
			  head = null;
			  tail = null;
		  }
		  else // otherwise
		  {
			  tail.remove();
			  getNth(size() - 1 - 1).setNext(new Node(null));
			  tail = getNth(size() - 1 - 1);
		  }
		  
		  nelems--;
		  return store;
	  }
	  
	  
	  /**
	   * gets the node at specified index
	   * @param index, specifies index of node
	   * @return node at the index
	   */
	  private Node getNth(int index)
	  {
		  if(index > (size() - 1))
		  {
			  throw new IndexOutOfBoundsException();
		  }
		  Node toGet = head;
		  for(int i =  1; i < index; i++)
		  {
			  toGet = toGet.getNext();
		  }
		  return toGet;
	  }
	  
	  
	  /**
	   * gets value at start of list
	   * @return the element stored in first node
	   */
	  public T getHeadValue()
	  {
		  Node toGet = head;		  
		  return toGet.getElement();
	  }
	  
	  
	  /**
	   * gets value at end of list
	   * @return the elemet stored in last node
	   */
	  public T getTailValue()
	  {
		  Node toGet = tail;
		  return toGet.getElement();
	  }
	  
} // end of DoubleEndedLL class
