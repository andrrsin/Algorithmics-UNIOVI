package algstudent.s32;

public class RunTromino {

	public static void main(String[] args) {
		Tromino tr = new Tromino(8,1,5);
		System.out.println(tr.toString());
		
		tr.solveTromino();
		System.out.println(tr.toString());

	}

}
