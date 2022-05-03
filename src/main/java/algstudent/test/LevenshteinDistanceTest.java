package algstudent.test;

public class LevenshteinDistanceTest {

	public static void main(String[] args) {
		
		
		System.out.println("=====BARCAZAS/ABRACADABRA=====");
		LevenshteinDistance ld = new LevenshteinDistance("BARCAZAS","ABRACADABRA");
		ld.levenshtein();
		ld.printMatrix();
		ld.traceBack();
		
//		System.out.println("=====a/a=====");
//		ld = new LevenshteinDistance("a","a");
//		ld.levenshtein();
//		ld.printMatrix();
//		
//		System.out.println("=====a/ab=====");
//		ld = new LevenshteinDistance("a","ab");
//		ld.levenshtein();
//		ld.printMatrix();
//		
//		System.out.println("=====hola/adios=====");
//		ld = new LevenshteinDistance("hola","adios");
//		ld.levenshtein();
//		ld.printMatrix();
				
	}

}
