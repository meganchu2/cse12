package hw6;

public class Registration implements Comparable<Registration> {
	
	private Student student;
	private Course course;
	private int coins;
	
	public Registration(Student student, Course course, int coins) {
		//TODO
	}
	
	public Student getStudent() {
		return null; //XXX-CHANGE-XXX
	}
	
	public Course getCourse() {
		return null; //XXX-CHANGE-XXX
	}
	
	public int getCoins() {
		return -1; //XXX-CHANGE-XXX
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
    public int compareTo( Registration o ) {
	    
		return -1; //XXX-CHANGE-XXX
	    
    }

}
