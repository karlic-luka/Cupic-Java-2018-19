package hr.fer.zemris.java.hw02;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ComplexNumberTest {
	
	private double EPSILON = 1E-2;

	@Test
	void ConstructorTest() {
		ComplexNumber c = new ComplexNumber(0,0);
		assertEquals(0, c.getReal());
		assertEquals(0, c.getImaginary());
	}
	
	@Test
	void ConstructorTestNegative() {
		ComplexNumber c = new ComplexNumber(-5,4);
		assertEquals(-5, c.getReal());
		assertEquals(4, c.getImaginary());
	}
	
	@Test
	void testFromReal() {
		ComplexNumber c = new ComplexNumber(0,0);
		c = ComplexNumber.fromReal(5);
		assertEquals(5, c.getReal());
	}
	
	@Test
	void testFromImaginary() {
		ComplexNumber c = new ComplexNumber(0,0);
		c = ComplexNumber.fromImaginary(5);
		assertEquals(5, c.getImaginary());
	}
	
	@Test
	void testFromMagnitudeAndAngle() {
		ComplexNumber c = new ComplexNumber(0,0);
		ComplexNumber d = new ComplexNumber(5,2);
		c = ComplexNumber.fromMagnitudeAndAngle(Math.sqrt(29), 0.38);
		assertEquals(true, c.equals(d));
	}
	
	
	@Test
	void parseValid() {
		ComplexNumber c = new ComplexNumber(0, 0);
		c = ComplexNumber.parse("-2.71+3.5i");
		ComplexNumber d = new ComplexNumber(-2.71, 3.5);
		assertEquals(true, c.equals(d));
	}
	
	@Test
	void parsePureImaginary() {
		ComplexNumber c = new ComplexNumber(0, 0);
		c = ComplexNumber.parse("i");
		ComplexNumber d = new ComplexNumber(0, 1);
		assertEquals(true, c.equals(d));
	}
	
	@Test
	void parsePureReal() {
		ComplexNumber c = new ComplexNumber(0, 0);
		c = ComplexNumber.parse("5");
		ComplexNumber d = new ComplexNumber(5, 0);
		assertEquals(true, c.equals(d));
	}
	
	@Test
	void parseIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, ()-> {
			ComplexNumber.parse("5+-5i");
		});
	}
	
	@Test
	void testGetReal() {
		ComplexNumber c = new ComplexNumber(5,5);
		assertEquals(5, c.getReal());
	}
	
	@Test
	void testGetImaginary() {
		ComplexNumber c = new ComplexNumber(5,5);
		assertEquals(5, c.getImaginary());
	}
	
	@Test
	void testGetMagnitude() {
		ComplexNumber c = new ComplexNumber(3,4);
		assertEquals(5, c.getMagnitude());
	}
	
	@Test
	void testGetAngle() {
		ComplexNumber c = new ComplexNumber(5,2);
		assertEquals(true, Math.abs(0.38 - c.getAngle()) < EPSILON);
	}
	
	@Test
	void getNegativeAngleAdd2Pi() {
		ComplexNumber c = new ComplexNumber(-3,4);
		assertEquals(true, Math.abs(2.214297435588181 - c.getAngle()) < EPSILON); //-38.stupnjeva
	}
	
	@Test
	void addComplex() {
		ComplexNumber c = new ComplexNumber(1, 1);
		ComplexNumber d = new ComplexNumber(2, 3);
		ComplexNumber result = new ComplexNumber(3, 4);
		assertEquals(true, result.equals(c.add(d)));
	}
	
	@Test
	void subComplex() {
		ComplexNumber c = new ComplexNumber(1, 1);
		ComplexNumber d = new ComplexNumber(2, 3);
		ComplexNumber result = new ComplexNumber(-1, -2);
		assertEquals(true, result.equals(c.sub(d)));
	}
	
	@Test
	void mulComplex() {
		ComplexNumber c = new ComplexNumber(5, 6);
		ComplexNumber d = new ComplexNumber(-3, 4);
		ComplexNumber result = new ComplexNumber(-39, 2);
		assertEquals(true, result.equals(c.mul(d)));
	}
	
	@Test
	void divComplex() {
		ComplexNumber c = new ComplexNumber(5, 6);
		ComplexNumber d = new ComplexNumber(-3, 4);
		ComplexNumber result = new ComplexNumber(0.36, -1.52);
		assertEquals(true, result.equals(c.div(d)));
	}
	
	@Test
	void powComplex() {
		ComplexNumber c = new ComplexNumber(1, 2);
		ComplexNumber result = new ComplexNumber(-11, -2);
		assertEquals(true, result.equals(c.power(3)));
	}
	
	@Test
	void rootComplex() {
		ComplexNumber c = new ComplexNumber(8,-6);
		ComplexNumber[] roots = new ComplexNumber[2];
		ComplexNumber[] result = new ComplexNumber[2];
		result = c.root(2);
		roots[0] = new ComplexNumber(-3,1);
		roots[1] = new ComplexNumber(3,-1);
		
		for(int i = 0; i < 2; i++) {
			assertEquals(true, result[i].equals(roots[i]));
		}
	}
	
	@Test
	void testStringToString() {
		ComplexNumber c = new ComplexNumber(1,1);
		
		assertEquals(true, c.toString().equals("1.0+1.0i"));
	}

}
