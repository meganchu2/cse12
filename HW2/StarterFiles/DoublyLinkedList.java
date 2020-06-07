/**
 * TODO - your comments here
 */

package hw2;

import java.util.*;

public class DoublyLinkedList<E> extends AbstractList<E> {

	private int nelems;   //No. of items in the list
	private Node head;
	private Node tail;

	protected class Node {

		E data;
		Node next;
		Node prev;

		/** Constructor to create singleton Node */
		public Node(E element)
		{
		}
		/** Constructor to create singleton link it between previous and next 
		 *   @param element Element to add, can be null
		 *   @param prevNode predecessor Node, can be null
		 *   @param nextNode successor Node, can be null 
		 */
		public Node(E element, Node prevNode, Node nextNode)
		{
		}
		/** Remove this node from the list. Update previous and next nodes */
		public void remove()
		{
		}
		/** Set the previous node in the list
		 *  @param p new previous node
		 */
		public void setPrev(Node p)
		{
		}
		/** Set the next node in the list
		 *  @param n new next node
		 */
		public void setNext(Node n)
		{
		}

		/** Set the element 
		 *  @param e new element 
		 */
		public void setElement(E e)
		{
		}
		/** Accessor to get the next Node in the list */
		public Node getNext()
		{
			return (Node) null; // XXX-CHANGE-XXX
		}
		/** Accessor to get the prev Node in the list */
		public Node getPrev()
		{
			return (Node) null; // XXX-CHANGE-XXX
		} 
		/** Accessor to get the Nodes Element */
		public E getElement()
		{
			return (E) null; // XXX-CHANGE-XXX
		} 
	}

	/** ListIterator implementation */ 

	protected class MyListIterator implements ListIterator<E> {

		private boolean forward;
		private boolean canRemove;
		private Node left,right; // Cursor sits between these two nodes
		private int index;        // Tracks current position. what next() would return

		public MyListIterator()
		{
		}

		@Override
		public void add(E e) throws  NullPointerException
		{
		}
		@Override
		public boolean hasNext()
		{
			return false; // XXX-CHANGE-XXX
		}

		@Override
		public boolean hasPrevious()
		{
			return false; // XXX-CHANGE-XXX
		}
		@Override
		public E next() throws NoSuchElementException
		{
			return (E) null;  // XXX-CHANGE-XXX
		}
		@Override
		public int nextIndex()
		{
			return 0; // XXX-CHANGE-XXX
		}
		@Override
		public E previous() throws NoSuchElementException
		{
			return (E) null; // XXX-CHANGE-XXX
		}

		@Override
		public int previousIndex()
		{
			return 0;  // XXX-CHANGE-XXX
		}
		@Override
		public void remove() throws IllegalStateException
		{
		}
		@Override
		public void set(E e) 
				throws NullPointerException,IllegalStateException
		{
		}

	}

	//  Implementation of the DoublyLinkedList Class


	/** Only 0-argument constructor is define */
	/**
	 * Creates a new, empty doubly-linked list.
	 */
	public DoublyLinkedList()
	{
	}

	/**
	 * Retrieves the amount of elements that are currently on the list.
	 * 
	 * @return Number of elements currently on the list
	 */
	@Override
	public int size()
	{
		return 0; // XXX-CHANGE-XXX 
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
	}

	@Override
	public boolean add(E data) throws NullPointerException
	{
		return true; // XXX-CHANGE-XXX
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
		return (E) null;  // XXX-CHANGE-XXX
	}

	/**
	 * Retrieves the index of the item passed as a parameter
	 * 
	 * @param Item whose index is to be retrieved
	 * @return index The index of the desired item, -1 if the item is not found.
	 * @throws NullPointerException if item passed is null
	 */
	@Override
	public int indexOf(Object o) throws NullPointerException {
		return -1; //XXX-CHANGE-XXX
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
		return (E) null; // XXX-CHANGE-XXX
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		return (E) null; // XXX-CHANGE-XXX
	}

	/**Returns true if this list contains the specified element,
	 * false otherwise.
	 * @param data to be searched in the list
	 * @return true if the data is in the list, false otherwise
	 * @throws NullPointerException if the data is null
	 */
	@Override
	public boolean contains(Object o) throws NullPointerException {

		return false; //XXX-CHANGE-XXX
	}
	/**Removes the last occurrence of the specified element in this list,
	 * (when traversing the list from head to tail). 
	 * If the list does not contain the element, it is unchanged.
	 * @param data to be removed from the list
	 * @return true if the data is in the list, false otherwise
	 * @throws NullPointerException if the data is null
	 */
	public boolean removeLastOccurrence(Object o) throws NullPointerException {
		return false; //XXX-CHANGE-XXX
	}
	/** Clear the linked list */
	public void clear()
	{
	}

	/** Determine if the list empty 
	 * 
	 *  @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return true;  // XXX-CHANGE-XXX
	}

	public Iterator<E> QQQiterator()
	{
		return new MyListIterator();
	}
	public ListIterator<E> QQQlistIterator()
	{
		return new MyListIterator();
	}

	// Helper method to get the Node at the Nth index
	private Node getNth(int index) 
	{
		return (Node) null;  // XXX-CHANGE-XXX
	}




	/*  UNCOMMENT the following when you believe your MyListIterator class is
   functioning correctly
   public Iterator<E> iterator()
   {
   return new MyListIterator();
   }
   public ListIterator<E> listIterator()
   {
   return new MyListIterator();
   }
	 */
}

