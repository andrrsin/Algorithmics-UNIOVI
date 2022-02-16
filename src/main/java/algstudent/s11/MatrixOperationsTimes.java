package algstudent.s11;

import java.nio.file.Paths;

import algstudent.s0.MatrixOperations;

public class MatrixOperationsTimes {
	public static void main(String[] arg) {
		int repetitions = Integer.valueOf(arg[0]);

		timeOfSumDiagonal1(repetitions);
		//timeOfSumDiagonal2(repetitions);

	}

	public static void timeOfSumDiagonal1(int repetitions) {
		String filename = Paths.get("").toAbsolutePath().toString()+"/src/main/java/algstudent/s0/data/matrix01.txt";
		MatrixOperations mat = new MatrixOperations(filename);
		/*
		 * It is used for the table in this case it starts in 10 and increases by 3 each
		 * time
		 */
		
		long before, after;
		System.out.println("TEST SUMDIAGONAL1() WITH " + repetitions + " FOR EACH METHOD");
		System.out.println("-----------------------------------------");
		System.out.println();
		int sum = 0;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			sum = 0;
			
			/*
			 * TESTING THE sumDiagonal1() method using a power of ten to obtain a valid
			 * result
			 */
			mat = new MatrixOperations(n,0,99);
			before = System.currentTimeMillis();
			for (int repetition = 1; repetition <= repetitions; repetition++) {
				sum += mat.sumDiagonal1();
			}
			after = System.currentTimeMillis();

			// Printing results
			System.out.printf("SIZE = %15d - TOTAL TIME = %8d ms TIME / TASK = %7f ms - SUM = %d \n", n, after - before,
					(after - before) / (float) repetitions, sum);
			System.out.println("-----------------------------------------");
			System.out.println();
		}
	}
	
	private static void timeOfSumDiagonal2(int repetitions) {
		String filename = Paths.get("").toAbsolutePath().toString()+"/src/main/java/algstudent/s0/data/matrix01.txt";
		MatrixOperations mat = new MatrixOperations(filename);
		/*
		 * It is used for the table in this case it starts in 10 and increases by 3 each
		 * time
		 */
		long before, after;
		System.out.println("TEST SUMDIAGONAL2() WITH " + repetitions + " FOR EACH METHOD");
		System.out.println("-----------------------------------------");
		System.out.println();
		int sum = 0;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			sum = 0;

			before = System.currentTimeMillis();
			/*
			 * TESTING THE sumDiagonal2() method using a power of ten to obtain a valid
			 * result
			 */
			mat = new MatrixOperations(n,0,99);
			before = System.currentTimeMillis();
			for (int repetition = 1; repetition <= repetitions; repetition++) {
				sum += mat.sumDiagonal2();
			}
			after = System.currentTimeMillis();

			// Printing results
			System.out.printf("SIZE = %15d - TOTAL TIME = %8d ms TIME / TASK = %7f ms \n", n, after - before,
					(after - before) / (float) repetitions);
			System.out.println("-----------------------------------------");
			System.out.println();
		}
	}
}
