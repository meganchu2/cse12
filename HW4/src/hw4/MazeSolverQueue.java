package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class MazeSolverQueue
 *  Description: class solves maze with a queue
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MazeSolverQueue extends MazeSolver 
{

	MyQueue<Square> queue = new MyQueue<>();
	
	
	/**
	 * constructor for MazeSolverQueue, calls super class
	 * @param maze, maze to solve
	 */
	MazeSolverQueue(Maze maze)
	{
		super(maze);
	}
	
	
	/**
	 * clears the queue, and makes it not on worklist
	 */
	public void makeEmpty()
	{
		for(int i = queue.size() - 1; i >= 0; i--)
		{
			queue.removeElement().setOnWorkList(false);
		}
	}
	
	
	/**
	 * tells if queue is empty or not
	 * @return true is queue is empty
	 */
	public boolean isEmpty()
	{
		if(queue.isEmpty())
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * adds square to the queue
	 * @param sq, the Square to add
	 */
	public void add(Square sq) 
	{
		queue.addElement(sq);
		sq.setOnWorkList(true);
	}
	
	
	/**
	 * gets the next square to explore in queue
	 * @return Square to be explored next
	 */
	public Square next() 
	{
		return queue.removeElement();
	}
	
	
	/**
	 * gets the entire queue of squares to be explored
	 * @return MyQueue of squares that are in worklist
	 */
	public MyQueue<Square> getQueue() 
	{
		return queue;
	}

	
	/**
	 * main method for MazeSolverQueue calss
	 * @param args, String that user can input when running this class
	 */
	public static void main( String[] args )
	  {
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		
		if(!load) 
		{
			System.out.println("Oops!! Could not load the Maze");
		} 
		else 
		{
			MazeSolverQueue queueSolver = new MazeSolverQueue(myMaze);
			queueSolver.solve();
			System.out.println(queueSolver.getPath() +"\n");
			System.out.println(queueSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist "
					           + "= "+ queueSolver.getQueue().size() );
		}
	  }

} // end of MazeSolverQueue class

