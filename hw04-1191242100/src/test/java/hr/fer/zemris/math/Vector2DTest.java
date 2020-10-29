package hr.fer.zemris.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hr.fer.zemris.math.Vector2D;

class Vector2DTest {
	
	private double EPSILON = 1E-2;

	@Test
	void ConstructorTest() {
		Vector2D c = new Vector2D(0,0);
		assertEquals(0, c.getX());
		assertEquals(0, c.getY());
	}
	
	@Test
	void testgetX() {
		Vector2D c = new Vector2D(5,5);
		assertEquals(5, c.getX());
	}
	
	@Test
	void testgetY() {
		Vector2D c = new Vector2D(5,5);
		assertEquals(5, c.getY());
	}
	
	@Test
	void testGetMagnitude() {
		Vector2D c = new Vector2D(3,4);
		assertEquals(5, c.getMagnitude());
	}
	
	@Test
	void translateMethodTest() {
		Vector2D c = new Vector2D(1, 1);
		Vector2D d = new Vector2D(2, 3);
		c.translate(d);
		assertEquals(3, c.getX());
		assertEquals(4, c.getY());
	}
	
	@Test
	void rotateMethodTest() {
		Vector2D c = new Vector2D(0, 1);
		c.rotate(Math.PI / 2);
		assertTrue(Math.abs(-1 - c.getX()) < EPSILON);
		assertTrue(Math.abs(0 - c.getY()) < EPSILON);
	}
	
	@Test
	void rotatedVector() {
		Vector2D c = new Vector2D(0, 1);
		Vector2D d = c.rotated(Math.PI / 2);
		assertTrue(Math.abs(-1 - d.getX()) < EPSILON);
		assertTrue(Math.abs(0 - d.getY()) < EPSILON);
	}
	
	@Test
	void translateNullOffset() {
		Vector2D c = new Vector2D(1, 1);
		assertThrows(NullPointerException.class, ()-> c.translate(null));
	}
	
	
	@Test
	void translatedVector() {
		Vector2D c = new Vector2D(1, 1);
		Vector2D d = new Vector2D(2, 3);
		Vector2D result = c.translated(d);
		assertEquals(3, result.getX());
		assertEquals(4, result.getY());
	}
	
	@Test
	void scaleMethodTest() {
		Vector2D c = new Vector2D(1, 1);
		c.scale(2);
		assertEquals(2, c.getX());
		assertEquals(2, c.getY());
	}
	
	@Test
	void scaledVector() {
		Vector2D c = new Vector2D(1, 1);
		Vector2D result = c.scaled(-2);
		assertEquals(-2, result.getX());
		assertEquals(-2, result.getY());
	}
	
	@Test
	void copyVector() {
		Vector2D c = new Vector2D(1, 1);
		Vector2D result = c.copy();
		assertEquals(1, result.getX());
		assertEquals(1, result.getY());
	}

}
