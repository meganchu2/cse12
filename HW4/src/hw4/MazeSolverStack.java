package hw4;

/*
* NAME: Megan Chu
* ID: A12814536
* LOGIN: cs12waot
*/


/**
 * Title: class MazeSolverStack
 *  Description: class that solves maze with a stack worklist
 *
 * @version 1.0
 * @author  Megan Chu
 * @since   2017-02-08
 */
public class MazeSolverStack extends MazeSolver 
{

	private MyStack<Square> stack = new MyStack<>();

	
	/**
	 * constructor for MazeSolverStack, calls super
	 * @param maze the maze to solve
	 */
	MazeSolverStack(Maze maze)
	{
		super(maze);
	}

	
	/**
	 * clears this stack worklist
	 */
	public void makeEmpty()
	{
		for(int i = stack.size() - 1; i >= 0; i--)
		{
			stack.removeElement().setOnWorkList(false);
		}
	}

	
	/**
	 * tells if this stack is empty
	 */
	public boolean isEmpty()
	{
		if(stack.isEmpty())
		{
			return true;
		}
		return false; 
	}

	
	/**
	 * adds a square to the stack
	 */
	public void add(Square sq) 
	{
		stack.addElement(sq);
		sq.setOnWorkList(true);
	}

	
	/**
	 * gets the next element to explore
	 * @return Square the next square in the stack
	 */
	public Square next() 
	{
		return stack.removeElement();
	}

	
	/**
	 * getter for the entire stack
	 * @return MyStack of squares to be explored
	 */
	public MyStack<Square> getStack() 
	{
		return stack; //XXX-CHANGE-XXX
	}

	
	/**
	 * main method that runs the solver
	 * @param args, string user input the user can put when running the code
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
			MazeSolverStack stackSolver = new MazeSolverStack(myMaze);
			stackSolver.solve();
			System.out.println(stackSolver.getPath() +"\n");
			System.out.println(stackSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist "
					           + "= "+ stackSolver.getStack().size() );
		}
	}
	
} // end of MazeSolverStack class
