package hw4;

public class MazeSolverStack extends MazeSolver {

	private MyStack<Square> stack = new MyStack<>();

	MazeSolverStack(Maze maze){
		super(maze);
	}

	public void makeEmpty(){
		//TODO

	}

	public boolean isEmpty(){
		return false; //XXX-CHANGE-XXX
	}

	public void add(Square sq) {
		//TODO
	}

	public Square next() {
		return null; //XXX-CHANGE-XXX
	}

	public MyStack<Square> getStack() {
		return null; //XXX-CHANGE-XXX
	}

	public static void main( String[] args )
	{
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverStack stackSolver = new MazeSolverStack(myMaze);
			stackSolver.solve();
			System.out.println(stackSolver.getPath() +"\n");
			System.out.println(stackSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ stackSolver.getStack().size() );
		}
	}
}
