package algstudent.s7;

import java.util.ArrayList;
import java.util.UUID;

import algstudent.s6.ImageAverager;


/**
 * To solve a reduced version of the Pyramid Puzzle
 * Instructions at http://www2.stetson.edu/~efriedma/puzzle/pyramid/
 */
public class PyramidPuzzle extends BranchAndBound {	
	/**
	 * Constructor for Pyramid Puzzle objects
	 * @param board Representation of the board for playing the puzzle
	 */
    public PyramidPuzzle(PyramidBoard board) {
    	rootNode = board; //we create the puzzle to start playing
    }
}
/***************************************************/


/***************************************************/
class PyramidBoard extends Node {
	private int[][] board; //board for playing
	private int row; //current row of this board
	private int column; //current column of this board
	private static int n; //size of the side of the board to save the pyramid

	/**
	 * Constructor for Pyramid puzzle objects (root node)
	 * @param n Size of the board
	 */
	public PyramidBoard(int n) { //Generates an empty board
		PyramidBoard.n = n;	 	
		board = new int[n][n];
		row = n-1;
		column = n-1;
	}
	
	/**
	 * Inserts the values of a line from the pyramid 
	 * It is call once per every row of the pyramid to initialize all the values
	 * @param values Values of a row of the pyramid
	 * @param row Number of the current row
	 */
	public void insertValues(String[] values, int row) {
		for (int i=0; i<row+1; i++) { //in row 0, 1 value; in row 1, 2 values...
			if (values[i].equals("*"))
				board[row][i] = 0;
			else
				board[row][i] = Integer.parseInt(values[i]);
		}
	}
		
    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) { 
			for(int k=i; k<n; k++) {
				sb.append(" ");
			}
			for (int j=0; j<=i; j++){			
				if (board[i][j] == 0) //empty
					sb.append("* ");
				else 
					sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
    }

    /**
     * Counts the number of blanks that are not yet filled
     */
    @Override
    public void calculateHeuristicValue() {
    	int counter=0;
    	if (prune()) {
    		heuristicValue=Integer.MAX_VALUE;
    	}
    	else {
    		for (int i=0; i<n; i++) {
    			for (int j=0; j<=i; j++) { //go only through i (more 0 than expected)
    				if (board[i][j]<1) counter++;
    			}
    		}
    		heuristicValue=counter;
    	}
    }
    
	/**
	 * Checks if we should prune when the conditions are not longer met
	 * @return True if we should prune. False otherwise
	 */
	private boolean prune() {
		for (int i=0; i<n-1 ; i++) {  //n-1 porque no hay mas filas tras la ultima
			for (int j=0; j<=i ; j++) {
				if (board[i][j]>0 && board[i+1][j]>0 && board[i+1][j+1]>0) {
					boolean valid=false;
					if (board[i][j]==board[i+1][j] + board[i+1][j+1]) valid = true;
					else if (board[i][j]==board[i+1][j] - board[i+1][j+1]) valid = true;
					else if (board[i][j]==board[i+1][j+1] - board[i+1][j]) valid = true;
					if(!valid) return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isSolution() {
		return heuristicValue==0;
	}
    
	/**
	 * To get the children of the current node. They 
     * point to their parent through the parentID
	 */
	@Override
	public ArrayList<Node> expand() {
		ArrayList<Node> res = new ArrayList<Node>();
		int[][] newBoard;
		PyramidBoard temp;
		
		while(board[row][column]!=0) {
			if(column>0) column--;
			else {
				row--;
				column=row;
			}
		}
		for (int k=1; k<10; k++) {
			newBoard=copyBoard(row,column,k);
			temp=new PyramidBoard(newBoard, depth+1, this.getID(), row, column);
			res.add(temp);
		}
		
		return res;
	}
	
	private int[][] copyBoard(int row, int column, int k) {
		int[][] newBoard = new int[n][n];
		
		for (int i=0; i<n; i++) 
			for (int j=0; j<=i; j++)
				newBoard[i][j] = board[i][j];				      
		
		newBoard[row][column] = k;	
		return newBoard;
	}
	
	/**
	 * Constructor for Pyramid puzzle objects (children of the root node)
	 * @param board
	 * @param depth
	 * @param parentID
	 */
    public PyramidBoard(int[][] board, int depth, UUID parentID, int row, int column) {
    	this.board=board;
    	this.depth=depth;
    	this.parentID=parentID;
    	this.row=row;
    	this.column=column;
    	calculateHeuristicValue();
    }

	@Override
	protected ImageAverager getManager() {
		return null;
	}

} 


