package algstudent.s32;

public class TrominoTimes {

	public static void main(String[] args) {
		int repetitions = Integer.valueOf(args[0]);
		long before,after;
		for (int n = 8; n <= Integer.MAX_VALUE; n *= 2) {
			Tromino tr = new Tromino(n, 1, 5);
			
			before = System.currentTimeMillis();
			for (int reps = 0; reps < repetitions; reps++) {
				tr.solveTromino();
			}
			after = System.currentTimeMillis();
			
			System.out.println("N: "+ n + " Repetitions: "+repetitions+" Time in ms: "+ (after-before));
		}

	}

}
