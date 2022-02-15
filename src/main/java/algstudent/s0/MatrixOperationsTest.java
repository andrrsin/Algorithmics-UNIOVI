package algstudent.s0;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

public class MatrixOperationsTest {

	@Test
	public void travelPathTest() {
		String filename = Paths.get("").toAbsolutePath().toString()+"/src/main/java/algstudent/s0/data/matrix02.txt";
		MatrixOperations mat = new MatrixOperations(filename);
		int count;
		assertEquals(32, (count = mat.travelPath(3, 0) ));
		System.out.println("Number of movements = " + count);
		mat.write();
	}


}
