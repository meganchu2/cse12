package hw6;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.util.List;
import java.util.LinkedList;


/**
 * Title: class Course
 *  Description: class that represents each course people enroll in
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-20
 */
public class Course implements Course_Interface 
{
	
	private String courseName;
	private String courseCode;
	private MyPriorityQueue<Registration> waitlistQueue;
	private List<Student> roster;
	private int maxCapacity;
	
	public Course(String name, String code, int capacity) 
	{
		courseName = name;
		courseCode = code;
		maxCapacity = capacity;
		// instance variables are initialized

		// initialize list/queue
		waitlistQueue = new MyPriorityQueue<>(0);
		roster = new LinkedList<>();
	}
	
	
	/**
	 * Getter for course name
	 * @return course name
	 */
	public String getCourseName()
	{
		return courseName;
	}

	
	/**
	 * Getter for course code
	 * @return course code
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	
	/**
	 * Getter for course capacity
	 * @return course capacity
	 */
	public int getCourseCapacity()
	{
		return maxCapacity;
	}
	

	/**
	 * Getter for Course Roster
	 * @return course roster
	 */
	public List<Student> getCourseRoster()
	{
		return roster;
	}


	/**
 	 * getter for course waitlist
 	 * @return waitlist
 	 */
	public MyPriorityQueue<Registration> getWaitlist()
	{
		return waitlistQueue;
	}

	
	/**
	 * Checks whether the course enrollment has reached its capacity
	 * @return Returns true if the number of enrolled students is equal to 
	 * capacity, false otherwise
	 */
	public boolean isFull()
	{
		if(roster.size() == maxCapacity) // if course full
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	/**
	 * Enqueues the student to the waitlist for this course
	 * @param Registration r to be waitlisted
	 */
	public void addToWaitlist(Registration r)
	{
		r.setTimestamp();
		waitlistQueue.offer(r);
	}

	
	/**
	 * Enrolls the next student in the waitlist to the course.
	 * Does nothing if the waitlist is empty
	 */
	public Registration processWaitlist()
	{
		Registration store = waitlistQueue.poll(); 
		// get highest priority on waitlist
		
		if(store == null) // if waitlist is empty
		{
			return null;
		}
		else //if waitlist has students
		{
			roster.add(store.getStudent());
			store.getStudent().enrollCourse(this);
			return store;
		}
	}

	
	/**
 	* converts class into string representation
 	* @return String, representation of this Course
 	*/
	@Override
	public String toString() 
	{
		return courseCode;
	}
	
} // end of Course class
