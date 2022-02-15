package session0;

import static org.junit.Assert.*;

import org.junit.Test;

public class MatrixOperationsTest {

	@Test
	public void travelPathTest() {
		MatrixOperations mat = new MatrixOperations("session0.data");
		int count;
		assertEquals(32, (count = mat.travelPath(3, 0) ));
		System.out.println("Number of movements = " + count);
		mat.write();
	}


}
