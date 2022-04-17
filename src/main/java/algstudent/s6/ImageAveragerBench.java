package algstudent.s6;

import java.nio.file.Paths;

public class ImageAveragerBench {

	// Benchmarking settings
	private static String REAL_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String BAD_IMG = "src/main/java/algstudent/s6/einstein_1_256.png";
	private static String OUT_DIR_G = "src/main/java/algstudent/s6/out_g/";
	private static String OUT_DIR_B = "src/main/java/algstudent/s6/out_bt";
	private static int N_IMGS = 8;
	private static double PERCENTAGE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Noise level - Gaussian sigma

	public static void main(String[] args) {

		int n_real, n_bad;
		ImageAverager img_avger;
		System.out.println(REAL_IMG);
		// Generating and testing a single dataset instance
//		n_bad = (int) ((PERCENTAGE_BAD / 100.) * N_IMGS);
//		n_real = N_IMGS - n_bad;
//		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);

//		System.out.print("TESTING GREEDY:\n");
//		img_avger.splitSubsetsGreedy(N_IMGS);
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_G);
//
//		System.out.print("TESTING BACKTRACKING UNBALANCED:\n");
//		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
//		img_avger.splitSubsetsBacktracking();
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);
//		
//		System.out.print("TESTING BACKTRACKING BALANCING:\n");
//		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
//		img_avger.splitSubsetsBacktracking(1);
//		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
//		System.out.printf("  -Counter: %d\n",  img_avger.getCounter());
//		img_avger.saveResults(OUT_DIR_B);
		
		long before, after;
		for(int n = 2; n<Integer.MAX_VALUE;n++) {
			System.out.println("---------------------------------------------");
			n_bad = (int) ((PERCENTAGE_BAD / 100.) * n);
			n_real = n - n_bad;
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			
			img_avger.splitSubsetsGreedy(n);
			System.out.println("N: "+n+" ZNCC of greedy: "+ img_avger.zncc()+ " counter: "+img_avger.getCounter());
			
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			before = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking();
			after = System.currentTimeMillis();
			System.out.println("N: "+n+" Time:"+(after-before)+" ZNCC of unbalanced: "+ img_avger.zncc()+ " counter: "+img_avger.getCounter());
			
			img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			before = System.currentTimeMillis();
			img_avger.splitSubsetsBacktracking(1);
			after = System.currentTimeMillis();
			System.out.println("N: "+n+" Time:"+(after-before)+" ZNCC of balanced: "+ img_avger.zncc()+ " counter: "+img_avger.getCounter());
			
			
		}
		
		// Measurements
		// TODO
	}

}