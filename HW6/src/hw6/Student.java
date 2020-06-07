package hw6;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.util.List;
import java.util.LinkedList;


/**
 * Title: class Student
 *  Description: class that represents each student trying to enroll
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-20
 */
public class Student implements Student_Interface {
	
	private String studentID;
	private String name;
	private List<Course> myEnrolledCourses;
	private List<Course> myWaitlist;
	private int courseCoins;
	
	/**
	 * Constructor that populates instance variables with parameters passed
	 * @param id StudentID
	 * @param name Name of the student
	 * @param coins Course Coins
	 */
	public Student(String id, String name, int coins) 
	{
		studentID = id;
		this.name = name;
		courseCoins = coins;
		// instance variables are populated

		// initialize lists
		myEnrolledCourses = new LinkedList<>();
		myWaitlist = new LinkedList<>();
	}
	
	
	/**
	 * Adds course c to the list of enrolled courses
	 * Also removes c from the waitlisted courses
	 * @param c Course to be enrolled in
	 */
	public void enrollCourse(Course c)
	{
		myEnrolledCourses.add(c); // add to end of list
		myWaitlist.remove(c); // remove course that was enrolled in
	}


	/**
	 * Adds course c to the waitlist
	 * @param c course to be waitlisted
	 */
	
	public void waitlistCourse(Course c)
	{
		myWaitlist.add(c);
	}
	

	/**
	 * Getter for name
	 * @return name - Name of the student
	 */
	public String getStudentName()
	{
		return name;
	}
	

	/**
	 * Getter for Student ID
	 * @return studentID - Student ID
	 */
	public String getStudentID()
	{
		return studentID;
	}


	/**
	 * Returns a list of all enrolled courses
	 * @return List of enrolled courses
	 */
	public List<Course> getmyEnrolledCourses()
	{
		return myEnrolledCourses;
	}


	/**
	 * Returns a list of all waitlisted courses
	 * @return List of waitlisted courses
	 */
	public List<Course> getmyWaitlist()
	{
		return myWaitlist;
	}


	/**
	 * Getter for course coins
	 * @return course coins
	 */
	public int getCoins()
	{
		return courseCoins;
	}


	/**
	 * Deducts numCoins from coursecoins
	 * @param numCoins Number of coins to be deducted
	 */
	public void deductCoins(int numCoins)
	{
		courseCoins -= numCoins;
	}

	
	/**
	 * Returns a string representation of the Student that includes
	 * the name and the studentID
	 * @return String representation of the student
	 */
	@Override
	public String toString() 
	{
		return this.name + "("+this.studentID+")";
	}

} // end of Student class
