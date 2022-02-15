package algstudent.s11;

public class Vector2 {
	static int[] v;
	
	public static void main(String arg[]) {
		int n = Integer.parseInt(arg[0]);
		v = new int[n];
		Vector1.fillIn(v);//Filling the array v with values from -99 to 99
		long t1,t2;
		
		t1 = System.currentTimeMillis();
		int sum = Vector1.sum(v);
		t2 = System.currentTimeMillis();
		
		System.out.printf("SIZE = %d - TIME = %d milliseconds SUM = %d \n", n,t2-t1,sum);
		
		
	}
}
