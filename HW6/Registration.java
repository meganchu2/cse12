package hw6;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class Registration
 *  Description: class that represents a request to enroll in a course
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-20
 */
public class Registration implements Comparable<Registration> 
{
	
	private Student student;
	private Course course;
	private int coins;
	private	long timestamp;
	

	/**
 	 * constructor for class that initializes instance variables
 	 * @param student, the student that wants to enroll
 	 * @param course, the course they want to enroll in
 	 * @param coins, the number of coins they are giving to this course
 	 */
	public Registration(Student student, Course course, int coins) 
	{
		this.student = student;
		this.course = course;
		this.coins = coins;
	}
	
	
	/**
 	 * gives the student that made the request
 	 * @return Student, student that wants to enroll
 	 */
	public Student getStudent() 
	{
		return student;
	}
	

	/**
 	 * gets the course this request wants to enroll in
 	 * @return Course, course requested to be enrolled in
 	 */
	public Course getCourse() 
	{
		return course;
	}


	/**
 	 * gets the number of coins allotted in request
 	 * @return int, number of coins student is willing to give
 	 */
	public int getCoins() 
	{
		return coins;
	}
	

	/**
 	 * sets the time that this registration request was made
 	 */
	public void setTimestamp()
	{
		timestamp = System.nanoTime();
	}


	/**
 	 * gets the time that thie registration request was made
 	 * @return long, time of creation of request
 	 */
	public long getTimestamp()
	{
		return timestamp;
	}


	/**
	 * Compares this Student with another Student, by comparing their 
	 * course coins
	 * If the coins of this is less, returns a negative integer. If the
	 * coins of the Student received is less, returns a positive integer.
	 * If both Students have the same number of coins, returns zero.
	 * @param o Student to be compared with
	 * @return Result of the comparison
	 */
	@Override
	public int compareTo( Registration o ) 
	{
		if(coins < o.getCoins() || (coins == o.getCoins() 
		   && timestamp > o.getTimestamp()) 
                // if same coins but later time then this has less priority
		{
			return -1;
		}
		else if (coins > o.getCoins() || (coins == o.getCoins() 
		         && timestamp < o.getTimestamp())
		// if same coins but earlier time then this has more priority
		{
			return 1;
		}
		else
		{
			return 0;
		}	    
	}

} // end of Registration class
