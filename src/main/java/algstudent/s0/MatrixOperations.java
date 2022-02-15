package algstudent.s0;

import java.util.Random;

import util.FileUtil;

public class MatrixOperations {
	private int[][] matrix;
	private int size;

	/**
	 * MatrixOperations(int n, int min, int max). Creates a new matrix of size n x n
	 * and fills it with random values. These random values must be parameterizable
	 * between a maximum (max) and a minimum (min) value.
	 */
	public MatrixOperations(int n, int min, int max) {
		this.matrix = new int[n][n];
		fillMatrix(min, max);
		size = matrix.length;
	}

	/**
	 * • MatrixOperations(String fileName). Creates a matrix using data of the file
	 * provided as parameter. This file must have 1 integer number as the first
	 * line. Following lines contain n values to represent every element of the
	 * matrix rows. Each of the values will be separated by a tabulator. Example:
	 */
	public MatrixOperations(String fileName) {
		matrix = FileUtil.loadMatrix(fileName);
		size = matrix.length;
	}

	/**
	 * • getSize(). Returns the matrix size (n).
	 */
	public int getSize() {
		return size;
	}

	/**
	 * • write(). Prints in the console all the matrix elements.
	 */
	public void write() {
		System.out.printf(toString());
	}
	
	public String toString() {
		String aux = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++)
				aux += matrix[i][j] + "\t";
			aux+= "\n";
			
		}
		return aux;
	}
	/**
	 * • sumDiagonal1(). Computes the summation of all the elements of the matrix
	 * diagonal. This implementation must iterate over all the matrix elements, but
	 * only sums appropriate elements. So, the complexity is quadratic.
	 */
	public int sumDiagonal1() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix.length; j++)
				if (i == j)
					sum += matrix[i][j];
		return sum;
	}

	/**
	 * • sumDiagonal2(). Computes the summation of all the elements of the matrix
	 * diagonal. This second version should only consider the elements of the main
	 * diagonal. So, the complexity is linear.
	 */
	public int sumDiagonal2() {
		int sum = 0;
		for (int i = 0; i < matrix.length; i++)
			sum += matrix[i][i];
		return sum;
	}

	/**
	 * • travelPath(int i, int j). Given a matrix with integer numbers between 1 and
	 * 4, this method iterates through the matrix starting at position (i, j)
	 * according to the following number meanings: 1 – move up; 2 – move right; 3 –
	 * move down; 4 – move left. Traversed elements would be set to -1 value. The
	 * process will finish if it goes beyond the limits of the matrix or an already
	 * traversed position is reached. To make sure your code works, create a text
	 * file with the previous example indicated in MatrixOperations(String fileName)
	 * and test it. For that file, the final output for a call travelPath(3, 0)
	 * should be something like the following:
	 * 
	 */
	public int travelPath(int i, int j) {
		int count = 0;
		while (i >= 0 && j >= 0 && i < matrix.length && j < matrix.length && matrix[i][j] != -1) {
			
			if (matrix[i][j] == 1) {
				matrix[i][j] = -1;
				i--;
				
			}
			else if (matrix[i][j] == 2) {
				matrix[i][j] = -1;
				j++;
			}
			else if (matrix[i][j] == 3) {
				matrix[i][j] = -1;
				i++;
			}
			else if (matrix[i][j] == 4) {
				matrix[i][j] = -1;
				j--;
			}
			count++;
			write();
		}
		return count;

	}

	public void fillMatrix(int min, int max) {
		Random rand = new Random();
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix.length; j++)
				matrix[i][j] = rand.nextInt(max + 1) + min;

	}
}
