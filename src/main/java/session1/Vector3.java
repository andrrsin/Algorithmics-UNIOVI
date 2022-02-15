package session1;

public class Vector3 {
	static int[] v;
	
	public static void main(String arg[]) {
		long t1,t2;
		for(int n = 10; n <= Integer.MAX_VALUE;n *=5) {
			v = new int[n];
			Vector1.fillIn(v);//Filling the array v with values from -99 to 99
			
			
			
			t1 = System.currentTimeMillis();
			int sum = Vector1.sum(v);
			t2 = System.currentTimeMillis();
			
			System.out.printf("SIZE = %d - TIME = %d milliseconds SUM = %d \n", n,t2-t1,sum);
		}
		
		
		
		
	}
}
