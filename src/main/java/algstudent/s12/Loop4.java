package algstudent.s12;

import java.util.Random;

public class Loop4 {
	public static long loop4(int n) {
		Random rn = new Random();
		int cont = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					for (int h = 0; h < n; h++)
						cont += rn.nextInt();
		return cont;
	}

	public static void main(String arg[]) {
		long t1, t2;
		long cont = 0;
		int repetitions = Integer.parseInt(arg[0]);

		for (int n = 1; n <= Integer.MAX_VALUE; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repetition = 1; repetition <= repetitions; repetition++) {
				cont = loop4(n);
			}

			t2 = System.currentTimeMillis();
			System.out.println("n=" + n + "\tTIME=" + (t2 - t1) + "\tCONT=" + cont + "\trepetitions=" + repetitions);
		} // for

	} // main
} // class