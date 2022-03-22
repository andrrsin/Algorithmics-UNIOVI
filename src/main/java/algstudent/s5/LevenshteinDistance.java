package algstudent.s5;

public class LevenshteinDistance {
	private int[][] mat;
	private char[] str1;
	private char[] str2;

	public LevenshteinDistance(String str1, String str2) {
		this.str1 = str1.toCharArray();
		this.str2 = str2.toCharArray();

	}

	public int levenshtein() {
		if (mat == null)
			createTable();
		return mat[str1.length][str1.length];
	}

	public void printMatrix() {
		if (mat == null)
			createTable();

		System.out.print("  ");
		for (int i = 0; i < mat.length; i++) {
			System.out.print(" " + i + " ");
		}

		System.out.println();
		for (int i = 0; i < mat.length * 3 + 2; i++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < mat.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(" " + mat[i][j] + " ");
			}
			System.out.println();
		}

	}

	private void createTable() {

		mat = new int[str1.length + 1][str2.length + 1];

		for (int i = 0; i < mat.length; i++) {
			mat[i][0] = i;
			mat[0][i] = i;
		}

		d(str1.length - 1, str2.length - 1);

	}

	private int d(int i, int j) {
		if (i == 0 || j == 0)
			return mat[i][j];
		if (str1[i] == str2[j]) {
			int aux = d(i - 1, j - 1);
			mat[i][j] = aux;
			return aux;
		} else {
			int aux = 1 + min(d(i - 1, j - 1), d(i, j - 1), d(i - 1, j));
			mat[i][j] = aux;
			return aux;
		}

	}

	private int min(int a, int b, int c) {
		if (a >= b && a >= c)
			return a;
		if (b >= c && b >= a)
			return b;
		return c;
	}

}
