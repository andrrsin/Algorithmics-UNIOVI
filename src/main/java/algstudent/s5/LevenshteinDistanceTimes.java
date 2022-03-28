package algstudent.s5;

public class LevenshteinDistanceTimes {

	public static void main(String[] args) {
		String abecedary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String word1 = "";
		String word2 = "";
		LevenshteinDistance lev;
		long before;
		long after;
		int dist = 0;
		int reps = Integer.valueOf(args[0]);
		System.out.println("Total repetitions each time are: "+reps);
		for(int i = 100;i<Integer.MAX_VALUE;i*=2) {
			word1= "";
			word2="";
			for(int j = 0;j<i;j++) {
				word1+=abecedary.charAt((int) (Math.random() * abecedary.length()));
				word2+=abecedary.charAt((int) (Math.random() * abecedary.length()));
			}
			
			lev = new LevenshteinDistance(word1,word2);
			before = System.currentTimeMillis();
			
			for(int n = 0;n < reps;n++)
				dist = lev.levenshtein();
			after = System.currentTimeMillis();
			
			System.out.println("N size: "+i+"Time: "+(after-before)+" ms"+"Levenshtein distance: "+dist);
			
		}
	}

}
