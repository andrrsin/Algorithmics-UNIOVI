package algstudent.s32;

public class Tromino {
	private String[][] board;
	private final static String DOT = "#";
	private int elem;
	private static final String EMPTY = " ";
	private int dotPosx;
	private int dotPosy;

	public Tromino(int size, int x, int y) {
		initializeBoard(size);
		placeDot(x, y);

	}

	private void placeDot(int x, int y) {
		board[x][y] = DOT;
		dotPosx = x;
		dotPosy = y;
	}

	public void solveTromino() {
		fillinTable(0, 0, board.length, dotPosx, dotPosy);
	}

	private void fillinTable(int startx, int starty, int n, int blankx, int blanky) {
		if (n == 1) {

		} else {
			elem++;
			
			switch (findQuadrant(startx, starty, n, blankx, blanky)) {
			case (1):
				board[startx + (n / 2) - 1][starty + (n / 2) - 1] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2)] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2) - 1] = String.valueOf(elem);

				fillinTable(startx, starty + n / 2, n / 2, blankx, blanky);
				fillinTable(startx, starty, n / 2, startx + (n / 2) - 1, starty + (n / 2) - 1);
				fillinTable(startx + n / 2, starty + n / 2, n / 2, startx + n / 2, starty + n / 2);
				fillinTable(startx + n / 2, starty, n / 2, startx + n / 2, starty + (n / 2) - 1);

				break;
			case (2):
				board[startx + (n / 2) - 1][starty + (n / 2)] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2)] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2) - 1] = String.valueOf(elem);

				fillinTable(startx, starty + n / 2, n / 2, startx + (n / 2) - 1, starty + (n / 2));
				fillinTable(startx, starty, n / 2, blankx, blanky);
				fillinTable(startx + n / 2, starty + n / 2, n / 2, startx + n / 2, starty + n / 2);
				fillinTable(startx + n / 2, starty, n / 2, startx + n / 2, starty + (n / 2) - 1);

				break;
			case (3):
				board[startx + (n / 2) - 1][starty + (n / 2) - 1] = String.valueOf(elem);
				board[startx + (n / 2) - 1][starty + (n / 2)] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2)] = String.valueOf(elem);

				fillinTable(startx, starty + n / 2, n / 2, startx + (n / 2) - 1, starty + (n / 2));
				fillinTable(startx, starty, n / 2, startx + (n / 2) - 1, starty + (n / 2) - 1);
				fillinTable(startx + n / 2, starty + n / 2, n / 2, startx + n / 2, starty + n / 2);
				fillinTable(startx + n / 2, starty, n / 2, blankx, blanky);

				break;
			default:
				board[startx + (n / 2) - 1][starty + (n / 2) - 1] = String.valueOf(elem);
				board[startx + (n / 2) - 1][starty + (n / 2)] = String.valueOf(elem);
				board[startx + (n / 2)][starty + (n / 2) - 1] = String.valueOf(elem);

				fillinTable(startx, starty + n / 2, n / 2, startx + (n / 2) - 1, starty + (n / 2));
				fillinTable(startx, starty, n / 2, startx + (n / 2) - 1, starty + (n / 2) - 1);
				fillinTable(startx + n / 2, starty + n / 2, n / 2, blankx, blanky);
				fillinTable(startx + n / 2, starty, n / 2, startx + n / 2, starty + (n / 2) - 1);

				break;
			}
		}
	}

	public int findQuadrant(int refx, int refy, int n, int x, int y) {
		if (x < refx + n / 2 && y >= refy + n / 2)
			return 1;
		if (x >= refx + n / 2 && y >= refy + n / 2)
			return 4;
		if (x >= refx + n / 2 && y < refy + n / 2)
			return 3;
		return 2;

	}

	private void initializeBoard(int size) {
		elem = 0;
		board = new String[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				board[i][j] = EMPTY;

	}

	public String toString() {
		String aux = "";
		for (int i = 0; i < board.length; i++) {
			aux += "________________________________________\n";
			for (int j = 0; j < board.length; j++) {
				aux += "| " + board[i][j] + " |";
			}
			aux += "\n________________________________________ \n";
		}
		return aux;
	}

}
