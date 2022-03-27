package algstudent.s5;

public class LevenshteinDistance {
	private int[][] mat;
	private char[] str1;
	private char[] str2;

	public LevenshteinDistance(String str1, String str2) {
		this.str1 = str1.toCharArray();
		this.str2 = str2.toCharArray();
		this.mat = new int[str2.length() + 1][str1.length() + 1];
	}

	public int levenshtein() {
		for (int i = 0; i < mat.length; i++) {
			mat[i][0]=i;
		}

		for (int i = 0; i < mat[0].length; i++) {
			mat[0][i]=i;
		}
		for(int i = 1; i<mat.length;i++) {
			for(int j = 1;j<mat[0].length;j++) {
				if (str2[i-1] == str1[j-1]) 
					mat[i][j]=mat[i-1][j-1];
				else
					mat[i][j] = 1+ Math.min(mat[i-1][j-1],Math.min(mat[i][j-1],mat[i-1][j]));
			}
		}
		return mat[str2.length-1][str1.length-1];
	}

	public void printMatrix() {
		
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

	
}
