package algstudent.s5;

public class LevenshteinDistanceTest {

	public static void main(String[] args) {
		
		
		System.out.println("=====BARGES/ABRACA=====");
		LevenshteinDistance ld = new LevenshteinDistance("BARGES","ABRACA");
		ld.printMatrix();
		
		
		System.out.println("=====hola/hola=====");
		ld = new LevenshteinDistance("hola","hola");
		ld.printMatrix();
		
		System.out.println();
		System.out.println("=====abcde/hijkl=====");
		ld = new LevenshteinDistance("abcde","hijkl");
		ld.printMatrix();
		
		System.out.println();
		System.out.println("=====bacde/abcde=====");
		ld = new LevenshteinDistance("bacde","abcde");
		ld.printMatrix();
		
		System.out.println();
		System.out.println("=====puerta/porton=====");
		ld = new LevenshteinDistance("puerta","porton");
		ld.printMatrix();
		
		
		
		
	}

}
