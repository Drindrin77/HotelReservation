package triangle;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleTest {
	   
	
	@Test
	public void testClassify() {
		assertTrue(Triangle.classify(1, 1, 1).equals(TriangleType.EQUILATERAL));
		assertTrue(Triangle.classify(2, 2, 1).equals(TriangleType.ISOCELE));
		assertTrue(Triangle.classify(1, 2, 3).equals(TriangleType.SCALENE));
		assertTrue(Triangle.classify(-1, 1, 1).equals(TriangleType.INVALID));
		assertTrue(Triangle.classify(1, 0, 1).equals(TriangleType.INVALID));
		assertTrue(Triangle.classify(-1, 0, 1).equals(TriangleType.INVALID));
		//Ne fonctionne pas
		assertFalse(Triangle.classify(1, 3, 1).equals(TriangleType.INVALID));

	}

}
