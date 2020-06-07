package hw6;


/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


/**
 * Title: class CourseScheduling
 *  Description: class that handles the enrollment of student registrations
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-20
 */
public class CourseScheduling 
{
	
	public static List<Course> courseList = new LinkedList<>();
	public static List<Student> studentList = new LinkedList<>();
	

	/**
 	 * reads the user input and creates registration requests and 
 	 * processes enrollment
 	 * @param fname, file that is taken as user input
 	 */
	public static void populateCourseandStudents(String fname) 
	{
		File file = new File(fname);
		try 
		{
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			boolean loadCourse = true;
			while(scanner.hasNextLine()) 
			{
				String nextLine = scanner.nextLine();	
				if(nextLine.isEmpty()) 
				{
					loadCourse = false;
					scanner.nextLine();
					continue;
				}
				Scanner wordscan = new Scanner(nextLine);
				if(loadCourse) 
				{
					String id = wordscan.next();
					String cname = wordscan.next();
					int capacity = wordscan.nextInt();
					Course course = new Course(cname,id,capacity);
					courseList.add(course);
				} 
				else 
				{
					String sname = wordscan.next();
					String pid = wordscan.next();
					int coins = wordscan.nextInt();
					Student student = new Student(pid,sname,coins);
					studentList.add(student);
				}
				wordscan.close();
			}
			scanner.close();
		} 
		catch(FileNotFoundException e) 
		{
			
		}		
	}
	

	/**
 	 * prints out each registration and whether it is enrolled or 
 	 * waitlisted
 	 * @param s, Student to be enrolled or waitlisted
 	 * @param c, course to enroll or waitlist in
 	 * @param coins, coins student has allotted
 	 * @param enroll, whether student is enrolled or waitlisted
 	 */
	public static void print(Student s, Course c, int coins, 
	                         boolean enroll)
 	{
		if(enroll)
			System.out.println("Enrolling "+ s.toString() + 
			                   " to Course " + c.toString() + 
			                   " with coins " + coins);
		else
			System.out.println("Waitlisting " + s.toString() + 
			                   " to Course " + c.toString() + 
			                   " with coins " + coins);
	}
	

	/**
 	 * prints if student cannot be enrolled for some reason 
 	 * @param s, Student to be enrolled or waitlisted
 	 * @param c, course to enroll or waitlist in
  	 * @param enroll, whether student is enrolled or waitlisted
  */
	public static void printFail(Student s, Course c, boolean enroll) 
	{
		if(enroll)
			System.out.println(s.toString() + 
                                           " is already enrolled in Course "
					   + c.toString());
		else
			System.out.println( s.toString() 
					+ " is already waitlisted in Course "
					+ c.toString());
	}
	

	/**
 	 * prints if a course is full
 	 * @param c, course to enroll or waitlist in
 	 */
	public static void printCapacity(Course c) 
	{
		System.out.println(c.toString() + "has already reached " + 
		                   "maximum capacity. There are no more " + 
		                   "seats left");
	}
	

	/**
 	 * prints if a student doesn't have enough coins
 	 * @param s, Student to be enrolled or waitlisted
 	 * @param c, course to enroll or waitlist in
  	 */
	public static void printNoCoins(Student s, Course c) 
	{
		System.out.println("Insufficient course coins. " + 
		                   "Cannot waitlist " + s.toString() + 
		                   " to " + c.toString());
	}

	/**
 	 * prints out final result of enrollment processes
 	 */
	public static void generateOutput() 
	{		
		System.out.println("\n\n########COURSE INFORMATION######");
		ListIterator<Course> iter = courseList.listIterator();
		while(iter.hasNext()) 
		{
			Course temp = iter.next();
			System.out.println(temp.getCourseCode()+"  "+ 
                                           temp.getCourseName());
			System.out.println("\tRoster: "+temp.getCourseRoster());
		}
		
		System.out.println("\n\n########STUDENT INFORMATION######");
		ListIterator<Student> iterS = studentList.listIterator();
		while(iterS.hasNext()) {
			Student temp = iterS.next();
			System.out.println(temp.toString());
			System.out.println("\t Enrolled courses: " + 
                                           temp.getmyEnrolledCourses());
			System.out.println("\t Waitlisted courses: " + 
                                           temp.getmyWaitlist());
		}
		
	}
	
	
	//**********************YOUR WORK STARTS HERE***************************
	/**
	 * Returns a reference to the Course object whose courseCode is code
	 * @param code Course code of the Course to be returned
	 * @return Course Course with the course code passed as a parameter
	 */
	public static Course getCourse(String code) 
	{
		ListIterator<Course> iter = courseList.listIterator();
		
		while(iter.hasNext()) // if we have not reached end of list
		{
			if(iter.next().getCourseCode().equals(code))
			// if this element matches the parameter
			{
				break;
			}
		}
		int i = iter.previousIndex();
		return courseList.get(i); // return found element
	}
	

	/**
	 * Returns a reference to the Student object whose StudentID is pid
	 * @param pid  StudentID of the Student to be returned
	 * @return Student student with the id passed as a parameter
	 */
	public static Student getStudent(String pid) 
	{
		ListIterator<Student> iter = studentList.listIterator();

		while(iter.hasNext()) // if we have not reached end of list
		{
			if(iter.next().getStudentID().equals(pid))
			// if this element matches the parameter
			{
				break;				
			}
		}
		int i = iter.previousIndex();
		return studentList.get(i); // return found element
	}
	

	/**
 	 * main method to start course scheduling
 	 * @param args, string input if needed when running the code
 	 */
	public static void main(String[] args) 
	{
		populateCourseandStudents(args[0]);
		File file = new File( args[1]);
		
		try
		{
		  Scanner scLine = new Scanner(file);	//scan lines in file
		  System.out.println("####START COURSE SCHEDULING####\n");

		  while (scLine.hasNextLine()) //if the file has next line
		  {
		    Scanner scWord = new Scanner(scLine.nextLine());
		    String property;
				
		    if(scWord.hasNext()) //if the file has next word
		    {
		      property = scWord.next();
		    }else break;

		    if(property.equals("register"))
		    {
		      String pid = scWord.next(); // get input info
		      String code = scWord.next();
		      int coins = scWord.nextInt();
		      
		      Course c = getCourse(code); // reference to specific course
		      Student s = getStudent(pid); // reference to specific student
		      
		      List<Course> enrolled = s.getmyEnrolledCourses();
		      List<Course> waitlist = s.getmyWaitlist();
		      
		      if(enrolled.contains(c)) // if student already enrolled
		      {
		    	  printFail(s, c, true);
		      }
		      else if(waitlist.contains(c)) // if student already waitlisted
		      {
		    	  printFail(s, c, false);
		      }
		      else if(s.getCoins() < coins) // if not enough coins
		      {
		    	  printNoCoins(s, c);
		      }
		      else // if registration request is cleared
		      {
		    	  Registration toAdd = new Registration(s, c, coins);
		    	  c.addToWaitlist(toAdd); // add to course waitlist
		    	  s.waitlistCourse(c); // add to student's waitlist
		    	  s.deductCoins(coins);
		    	  print(s, c, coins, false);
		      }
		        	
		    }
		    else if(property.equals("enroll"))
		    {
		      //process registrations in the waitlist
		      System.out.println("\n####STARTING BATCH ENROLLMENT####");
		        	
		      int numEnroll = scWord.nextInt(); 
		      // get how many students too enroll per course
		      
		      for(int i = 1; i <= numEnroll; i++) 
		      // loop through number of students to enroll
		      {
		    	  for(int j = 0; j < courseList.size(); j++)
		    	  // loop through all courses
		    	  {
		    		  Course enroller = courseList.get(j);
		    		  if(enroller.isFull()) // if course full
		    		  {
		    			  printCapacity(enroller);
		    		  }
		    		  else // if course not full
		    		  {
		    			  Registration r = enroller.processWaitlist();
		    			  if(r != null) // if there are students on waitlist
		    			  {
		    				  print(r.getStudent(), r.getCourse(), 
		    						  r.getCoins(), true);
		    			  }
		    		  }
		    	  }
		      }		    
		        	
		      System.out.println("####ENDING BATCH ENROLLMENT####\n");
		    }else break;

	            scWord.close();
		  }
		  scLine.close();
		  System.out.println("\n####END COURSE SCHEDULING####");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Failed to open "+file);
			System.exit(1);
		}
		
		generateOutput();
	}//end of main method

} // end of CourseScheduling class
