/* 
* NAME: Megan Chu
* PID: A12814536
* LOGIN: cs12waot
*/

package hw2;

import java.util.*;


/**
 * Title: class DoublyLinkedList
 *  Description: class for creating DoublyLinkedList
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-01-25
 */
public class DoublyLinkedList<E> extends AbstractList<E> 
{
  private int nelems;   //No. of items in the list
  private Node head;
  private Node tail;


  protected class Node 
  {
    E data;
    Node next;
    Node prev;


    /** 
     * Constructor to create singleton Node
     * Initializes the node with element
     *
     * @param element element to add, can be null
     */
    public Node(E element)
    {
      data = element;
    }


    /** Constructor to create singleton link it between previous and next 
     *   Initializes the node with element, prevNode as its predecessor node 
     *   and nextNode as its successor node
     *
     *   @param element Element to add, can be null
     *   @param prevNode predecessor Node, can be null
     *   @param nextNode successor Node, can be null 
     */
    public Node(E element, Node prevNode, Node nextNode)
    {
      data = element;
      prev = prevNode;
      next = nextNode;
    }


    /** 
     *  Removes this node from the list. Links this node's 
     *  predecessor and successor nodes. 
     */
    public void remove()
    {
      if(next != null) // if not at tail
      {
        next.setPrev(prev);
      }
      if(prev != null) // if not at head
      {
        prev.setNext(next);
      }
      if(this == head) // if at head
      {
        head = next;
      }
      if(this == tail) // if at tail
      {
        tail = prev;
      }
    }


    /** 
     * Set p as the predecessor node
     *
     *  @param p new previous node
     */
    public void setPrev(Node p)
    {		  
      prev = p;
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
    public void setElement(E e)
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
     * Accessor to get the prev Node in the list 
     *
     * @return the node's predecessor
     */
    public Node getPrev()
    {
      if(this != head) // if at head return null
      {
        return prev;
      }
      return (Node)null;
    } 


    /** 
     * Accessor to get the Nodes Element 
     *
     * @return the node's data
     */
    public E getElement()
    {
      return data;
    }
 
  } // end of Node class



  /** 
   * ListIterator implementation 
   */ 
  protected class MyListIterator implements ListIterator<E> 
  {
    private boolean forward;
    private boolean canRemove;
    private Node left,right; // Cursor sits between these two nodes
    private int index; // Tracks current position. what next() would return


    /**
     * Constructor that is used to initialize the iterator
     */
    public MyListIterator()
    {
      forward = true;
      canRemove = false;
      index = -1;
      left = head; // dummy head
      right = left.getNext(); // node next to dummy head
    }

    
    /**
     * Adds an element to the list between left and right nodes, and
     * moves cursor after new node
     *
     * @param e the element to add
     * @throws NullPointerException if the data is null
     */
    @Override
    public void add(E e) throws  NullPointerException
    {
      if(e == null)
      {
        throw new NullPointerException();
      }
      else
      {
        Node newNode = new Node(e);
        Node p = left;
        Node n = right;

        newNode.setNext(n); // linking newNode to n and p
        newNode.setPrev(p);
        p.setNext(newNode); // linking p to newNode
        n.setPrev(newNode); // linking n to newNode

        left = newNode; // move cursor to after newNode
        index++;
        canRemove = false;
        nelems++;
      }
    }


    /**
     * Checks if there is another element to be retrieved by calling next
     *
     * @return true if there is another element to the right
     */
    @Override
    public boolean hasNext()
    {
      if(right != tail) // if right is a node with data
      {
        return true;
      }
      return false;
    }


    /**
     * Checks if there is another element to be retrieved by calling previous
     *
     * @return true if there is another element to the left
     */
    @Override
    public boolean hasPrevious()
    {
      if(left != head) // if left is a node with data
      {
        return true;
      }
      return false;
    }


    /**
     * Advances through the list by one index, and retrieves the next element
     *
     * @return the element currently stored in right
     * @throws NoSuchElementException if there is no next element to return
     */
    @Override
    public E next() throws NoSuchElementException
    {
      if(!hasNext())
      {
        throw new NoSuchElementException();
      }
      else // if there is a node with data to the right
      {
        forward = true;
        canRemove = true;
        index++;
        left = right; // shift left one to right
        right = left.getNext(); // shift right one to right
      }
      return left.getElement(); // return element in node traversed over
    }


    /**
     * Retrieves the index of the next element (that would be retrieved 
     * by next() call)
     *
     * @return the index of element in right
     */
    @Override
    public int nextIndex()
    {
      int returner = index + 1;
      return returner;
    }


    /**
     * Retreats through the list by one index, and retrieves the previous 
     * element
     *
     * @return the element currently stored in left
     * @throws NoSuchElementException if there is no next element to return
     */
    @Override
    public E previous() throws NoSuchElementException
    {
      if(!hasPrevious())
      {
        throw new NoSuchElementException();
      }

      forward = false;
      canRemove = true;
      index--;

      left = getNth(index); // shift left one to left
      right = left.getNext(); // shift right one to left

      return right.getElement(); // element in node traversed over
    }


    /**
     * Retrieves the index of the previous element (that would be 
     * retrieved by previous() call)
     *
     * @return the index of element in left
     */
    @Override
    public int previousIndex()
    {
      int returner = index;
      return returner;
    }


    /**
     * Removes from the list the last element that was returned by next() 
     * or previous()
     *
     * @throws IllegalStateException next or previous was not called first
     */
    @Override
    public void remove() throws IllegalStateException
    {
      if(canRemove) // if next or previous was just called
      {
        if(forward) // if traversing forward
        {
          left.remove();
          index--;
        }
        else // if traversing backward
        {
          right.remove();
        }
        canRemove = false;
        nelems--;
      }
      else
      {
        throw new IllegalStateException();
      }
    }


    /**
     * Replaces the last element returned by next() or previous() with 
     * a given element
     *
     * @param e new data to replace old with
     * @throws NullPointerException if the data is null
     * @throws IllegalStateException next or previous was not called first
     */
    @Override
    public void set(E e) throws NullPointerException,IllegalStateException
    {
      if(e == null)
      {
        throw new NullPointerException();
      }
      if(canRemove) // if next or previous just called
      {
        if(forward) // if traversing forward
        {
          left.setElement(e);
        }
        else // if traversing backward
        {
          right.setElement(e);
        }
        canRemove = false;
      }
      else
      {
        throw new IllegalStateException();
      }
    }

} // end of MyListIterator class



  //  Implementation of the DoublyLinkedList Class
  /**
   * Creates a new, empty doubly-linked list.
   */
  public DoublyLinkedList()
  {
    head = new Node(null); // dummy head
    tail = new Node(null); // dummy tail
    head.setNext(tail);
    tail.setPrev(head);
    nelems = 0;
  }


  /**
   * Retrieves the amount of elements that are currently on the list.
   * 
   * @return Number of elements currently on the list
   */
  @Override
  public int size()
  {
    return nelems; 
  }


  /**
   * Adds an element to a certain index in the list, shifting exist elements
   * create room. Does not accept null values.
   * 
   * @param index Where in the list to add the element.
   * @param data Data to be added.
   * @throws IndexOutOfBoundsException if index received is out of bounds for 
   *             the current list. 
   * @throws NullPointerException if data received is null.
   */
  @Override
  public void add(int index, E data) 
              throws IndexOutOfBoundsException,NullPointerException
  {
    if(data == null || index > size() || index < 0)
    {
      if(index < 0 || index > size())
      {
        throw new IndexOutOfBoundsException();
      }
      if(data == null)
      {
        throw new NullPointerException();
      }
    }
    else
    {
      Node newNode = new Node(data);

      newNode.setPrev(getNth(index - 1)); // linking newNode
      newNode.setNext(getNth(index));

      getNth(index - 1).setNext(newNode); // linking previous node to newNode
      getNth(index).setPrev(newNode); // linking next node to newNode
      nelems++;
    }
  }


  /**
   * Add an element to the end of the list
   *
   * @param data to be put in new node to add
   * @return always returns true
   * @throws NullPointerException if the data is null
   */ 
  @Override
  public boolean add(E data) throws NullPointerException
  {
    if(data == null)
    {
      throw new NullPointerException();
    }
    else
    {
      Node newNode = new Node(data);

      newNode.setPrev(getNth(size() - 1)); 
      // linking previous node and newNode to each other
      (getNth(size() - 1)).setNext(newNode);

      newNode.setNext(tail); // linking next node and newNode to each other
      tail.setPrev(newNode);				
      nelems++;
    }
    return true;
  }


  /**
   * Retrieves the element stored with a given index on the list.
   * 
   * @param index The index of the desired element.
   * @return The element stored in the Node with the desired index.
   * @throws IndexOutOfBoundsException if index received is out of bounds for 
   *             the current list. 
   */
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
    if(index < 0 || index >= size())
    {
      throw new IndexOutOfBoundsException();
    }

    E e = getNth(index).getElement();
    return (E)e;
  } 


  /**
   * Retrieves the index of the item passed as a parameter
   * 
   * @param Item whose index is to be retrieved
   * @return index The index of the desired item, -1 if the item is not found.
   * @throws NullPointerException if item passed is null
   */
  @Override
  public int indexOf(Object o) throws NullPointerException 
  {
    if(o == null)
    {
      throw new NullPointerException();
    }
    else
    {
      for(int i = 0; i <= size(); i++)
      {
        if(getNth(i).getElement() == o)
        {
          return i; // returns index if element matching o is found
        }
      }
    }
    return -1;
  }


  /**
   * Sets the value of an element at a certain index in the list.
   * 
   * @param index Where in the list the data should be added.
   * @param data Data to add.
   * @return Element that was previously at this index.
   * @throws IndexOutOfBoundsException if index received is out of bounds for 
   *             the current list. 
   * @throws NullPointerException if data received is null.
   */
  @Override
  public E set(int index, E data) 
           throws IndexOutOfBoundsException,NullPointerException
  {
     if(data == null || index > size() || index < 0)
    {
      if(index < 0 || index > size())
      {
        throw new IndexOutOfBoundsException();
      }
      if(data == null)
      {
        throw new NullPointerException();
      }
    }

    E dataOld = getNth(index).getElement();
    getNth(index).setElement(data); // changes element
    return dataOld; // return old element
  }


  /**
   * remove the element from position i in the list
   *
   * @param index position of element to remove
   * @return element previously at the specified position
   * @throws IndexOutOfBoundsException if index received is out of bounds for 
   *             the current list.
   */
  @Override
  public E remove(int index) throws IndexOutOfBoundsException
  {
    if(index < 0 || index >= size())
    {
      throw new IndexOutOfBoundsException();
    }

    E returner = getNth(index).getElement();
    getNth(index).remove(); // remove this node
    nelems--;
    
    return returner;
  }


  /**Returns true if this list contains the specified element,
   * false otherwise.
   *
   * @param data to be searched in the list
   * @return true if the data is in the list, false otherwise
   * @throws NullPointerException if the data is null
   */
  @Override
  public boolean contains(Object o) throws NullPointerException 
  {
    if(o == null)
    {
      throw new NullPointerException();
    }
    else
    {
      for(int i = 0; i <= size(); i++)
      {
        if(getNth(i).getElement() == o)
        {
          return true; // returns true if element matching to o is found
        }
      }
    }
    return false;
  }


  /**Removes the last occurrence of the specified element in this list,
   * (when traversing the list from head to tail). 
   * If the list does not contain the element, it is unchanged.
   *
   * @param data to be removed from the list
   * @return true if the data is in the list, false otherwise
   * @throws NullPointerException if the data is null
   */
  public boolean removeLastOccurrence(Object o) throws NullPointerException 
  {
    int n = -1;

    if(o == null)
    {
      throw new NullPointerException();
    }

    for(int i = 0; i <= size(); i++)
    {
      if(getNth(i).getElement() == o)
      {
        n = i; // gets index of element matching o if any
        // keeps changing until last index is found
      }
    }
    
    if(n != -1) // if we found an element that matches
    {
      getNth(n).remove(); // remove that node containing matching element
      nelems--;
      return true;
    }
    return false;
  }


  /** 
   * Clear the linked list 
   */
  public void clear()
  {
    for(int i = size()-1; i >= 0; i--) // start from end of list
    {
      getNth(i).remove(); // remove every node
    }
    nelems = 0;
  }


  /** 
   * Determine if the list empty 
   * 
   *  @return true if empty, false otherwise
   */
  public boolean isEmpty()
  {
    if(size() != 0) // if there is a element containing node in list
    {
      return false;
    }
    return true;
  }


  /**
   *  Helper method to get the Node at the Nth index
   *
   *  @param index the index of node to return
   *  @return the node at the specified index
   */
  private Node getNth(int index) 
  {
    Node atIndex = head; // dummy head
    for(int counter = 0; counter <= index; counter++)
    {
      atIndex = atIndex.getNext(); 
      // loops until atIndex equals the node at index
    }
    return atIndex;
  }

  
  /**
   * method used to construct a new MyListIterator for the DoublyLinkedList
   * 
   * @return an Iterator to traverse through the list
   */
  public Iterator<E> iterator()
  {
    return new MyListIterator(); // create new MyListIterator to traverse list
  }


  /**
   * method used to constuct a new MyListIterator for the DoublyLinkedList
   *
   * @return a ListIterator to traverse through the list
   */
  public ListIterator<E> listIterator()
  {
    return new MyListIterator(); // create new MyListIterator to travers list
  }

} // end of DoublyLinkedList class


