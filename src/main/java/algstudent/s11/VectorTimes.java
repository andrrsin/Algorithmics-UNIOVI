package algstudent.s11;

public class VectorTimes {
	public static void main(String arg[]) {
		int repetitions = Integer.parseInt(arg[0]);
//		System.out.println("Sum");
//		try {
//			testSum(repetitions);
//		} catch (Exception e) {
//		}
//		System.out.println("fillIn");
//		try {
//			testFillIn(repetitions);
//		} catch (Exception e) {
//		}
		System.out.println("Maximum");
		try {
			testMaximum(repetitions);
		} catch (Exception e) {
		}

	}

	public static void testMaximum(int repetitions) {
		long t1, t2;
		int[] v;
		int[] m;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			m = new int[] {0,0};
			
			Vector1.fillIn(v);
			
			
			t1 = System.currentTimeMillis();

			for (int repetition = 1; repetition <= repetitions; repetition++) {
				Vector1.maximum(v,m);// Filling the array v with values from -99 to 99
			}

			t2 = System.currentTimeMillis();

			System.out.printf("SIZE = %d - TOTAL TIME = %d milliseconds - TIME/TASK = %f milliseconds \n", n,
					t2 - t1, (t2 - t1) / (float) repetitions);
		}
		
	}

	public static void testFillIn(int repetitions) {
		long t1, t2;
		int[] v;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			

			
			t1 = System.currentTimeMillis();

			for (int repetition = 1; repetition <= repetitions; repetition++) {
				Vector1.fillIn(v);// Filling the array v with values from -99 to 99
			}

			t2 = System.currentTimeMillis();

			System.out.printf("SIZE = %d - TOTAL TIME = %d milliseconds - TIME/TASK = %f milliseconds \n", n,
					t2 - t1, (t2 - t1) / (float) repetitions);
		}
		
	}

	public static void testSum(int repetitions) {
		long t1, t2;
		int[] v;
		for (int n = 10; n <= Integer.MAX_VALUE; n *= 3) {
			v = new int[n];
			Vector1.fillIn(v);// Filling the array v with values from -99 to 99

			int sum = 0;
			t1 = System.currentTimeMillis();

			for (int repetition = 1; repetition <= repetitions; repetition++) {
				sum += Vector1.sum(v);
			}

			t2 = System.currentTimeMillis();

			System.out.printf("SIZE = %d - TOTAL TIME = %d milliseconds - TIME/TASK = %f milliseconds SUM = %d \n", n,
					t2 - t1, (t2 - t1) / (float) repetitions, sum);
		}
	}
}
