package hw4;

public class MazeSolverQueue extends MazeSolver {

	MyQueue<Square> queue = new MyQueue<>();
	
	MazeSolverQueue(Maze maze){
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
	
	public MyQueue<Square> getQueue() {
		return null; //XXX-CHANGE-XXX
	}

	public static void main( String[] args )
	  {
		Maze myMaze = new Maze();
		boolean load = myMaze.loadMaze(args[0]);
		if(!load) {
			System.out.println("Oops!! Could not load the Maze");
		} else {
			MazeSolverQueue queueSolver = new MazeSolverQueue(myMaze);
			queueSolver.solve();
			System.out.println(queueSolver.getPath() +"\n");
			System.out.println(queueSolver.getMaze().toString());
			System.out.println("Number of squares remaining in the worklist = "+ queueSolver.getQueue().size() );
		}
	  }

}

