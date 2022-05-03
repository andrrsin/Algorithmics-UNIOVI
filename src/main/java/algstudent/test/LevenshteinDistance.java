package algstudent.test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class LevenshteinDistance {
	private int[][] mat;
	private char[] str1;
	private char[] str2;
	private ArrayList<Changes> trace;
	private ArrayList<Integer> tracePosI, tracePosJ;

	public LevenshteinDistance(String str1, String str2) {
		this.str1 = str1.toCharArray();
		this.str2 = str2.toCharArray();
		this.mat = new int[str2.length() + 1][str1.length() + 1];
		trace = new ArrayList<Changes>();
		tracePosI = new ArrayList<Integer>();
		tracePosJ = new ArrayList<Integer>();
	}

	public int levenshtein() {

		for (int i = 0; i < mat.length; i++) {
			mat[i][0] = i;
		}

		for (int i = 0; i < mat[0].length; i++) {
			mat[0][i] = i;
		}
		int min;
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				if (str2[i - 1] == str1[j - 1]) {
					mat[i][j] = mat[i - 1][j - 1];

				} else {
					min = Math.min(mat[i - 1][j - 1], Math.min(mat[i][j - 1], mat[i - 1][j]));
					mat[i][j] = 1 + min;

				}
			}
		}
		return mat[str2.length - 1][str1.length - 1];
	}

	private enum Changes {
		DELETE, INSERT, KEEP, CHANGE
	}

	public void traceBack() {
		trace = new ArrayList<Changes>();
		tracePosI = new ArrayList<Integer>();
		tracePosJ = new ArrayList<Integer>();
		levenshtein();
		recTraceBack(str2.length, str1.length);

		System.out.println("* Trajectory: <" + String.copyValueOf(str1) + "> to <" + String.copyValueOf(str2) + ">");
		System.out.println("0)= ");
		
		for (int i = trace.size() - 1; i >= 0; i--)
			printTrace(i);
			
	}

	private void printTrace(int i) {
		Changes change = trace.get(i);
		if(change == Changes.CHANGE) {
			System.out.println(mat[tracePosI.get(i)][tracePosJ.get(i)]+
					")Change '"+str1[tracePosJ.get(i)-1]+"' to '"+str2[tracePosI.get(i)-1]+"'");
		}else if(change == Changes.DELETE) {
			System.out.println(mat[tracePosI.get(i)][tracePosJ.get(i)]+
					")Delete '"+str2[tracePosI.get(i)-1]+"'");
		}else if(change == Changes.INSERT) {
			System.out.println(mat[tracePosI.get(i)][tracePosJ.get(i)]+
					")Insert '"+str2[tracePosI.get(i)-1]+"'");
		}else {
			System.out.println(mat[tracePosI.get(i)][tracePosJ.get(i)]+
					")Keep '"+str2[tracePosI.get(i)-1]+"'");
		}
		
	}

	private void recTraceBack(int i, int j) {
		if (i != 0 || j != 0) {
			if (j == 0) {
				tracePosI.add(i);
				tracePosJ.add(j);
				trace.add(Changes.INSERT);
				recTraceBack(i - 1, j);
			}else if (i == 0) {
				tracePosI.add(i);
				tracePosJ.add(j);
				trace.add(Changes.DELETE);
				recTraceBack(i, j - 1);
			} else {
			int min = Math.min(mat[i - 1][j - 1], Math.min(mat[i][j - 1], mat[i - 1][j]));
			tracePosI.add(i);
			tracePosJ.add(j);
			if (mat[i][j] == min) {
				trace.add(Changes.KEEP);
				recTraceBack(i - 1, j - 1);
			} else if (min == mat[i - 1][j - 1]) {
				trace.add(Changes.CHANGE);
				recTraceBack(i - 1, j - 1);
			} else if (min == mat[i][j - 1]) {
				trace.add(Changes.DELETE);
				recTraceBack(i, j - 1);
			} else if (min == mat[i - 1][j]) {
				trace.add(Changes.INSERT);
				recTraceBack(i - 1, j);
			}

		}
	}
	}

	public void printMatrix() {

		System.out.print("  ");
		for (int i = 0; i < mat[0].length; i++) {
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
