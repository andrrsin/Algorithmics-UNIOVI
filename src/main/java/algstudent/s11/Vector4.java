package algstudent.s11;

public class Vector4 {
	static int[] v;
	
	public static void main(String arg[]) {
		long t1,t2;
		int repetitions = Integer.parseInt(arg[0]);
		for(int n = 10; n <= Integer.MAX_VALUE;n *=5) {
			v = new int[n];
			Vector1.fillIn(v);//Filling the array v with values from -99 to 99
			
			
			int sum = 0;
			t1 = System.currentTimeMillis();
			
			for(int repetition =1; repetition <=repetitions;repetition++) {
				sum += Vector1.sum(v);
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.printf("SIZE = %d - TOTAL TIME = %d milliseconds - TIME/TASK = %f milliseconds SUM = %d \n", n,t2-t1,(t2-t1)/(float) repetitions ,sum);
		}
		
		
		
		
	}
}
