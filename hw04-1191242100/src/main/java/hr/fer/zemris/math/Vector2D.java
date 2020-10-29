/**
 * 
 */
package hr.fer.zemris.math;

import java.util.Objects;

/**
 * My simple implementation of 2-dimensional vector. It contains methods
 * such as rotate, translate, scale. Everything you need while working
 * with vectors.
 * @author Luka
 */
public class Vector2D {
	
	/**
	 * X-coordinate
	 */
	private double x;
	/**
	 * Y-coordinate
	 */
	private double y;
	
	/**
	 * Precision for identifying if two vectors are equal.
	 */
	private double EPSILON = 1E-3;
	
	/**
	 * getter
	 * @return the x-coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * @return the y-coordinate
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * constructor
	 * @param x x-coordinate
 	 * @param y
	 */
	public Vector2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for magnitude 
	 * @return magnitude of vector
	 */
	public double getMagnitude() {
		return Math.hypot(x, y);
	}
		
	/**
	 * Method that translates current vector by offset
	 * @param offset
	 */
	public void translate(Vector2D offset) {
		x += offset.x;
		y += offset.y;
	}
	
	/**
	 * Method that returns translated vector
	 * @param offset 
	 * @return new translated vector
	 */
	public Vector2D translated(Vector2D offset) {
		if(offset == null) {
			throw new NullPointerException("Offset can not be null");
		}
		return new Vector2D(x + offset.x, y + offset.y);
	}
	
	/**
	 * Method that rotates current vector by <code>angle</code> over (0, 0)
	 * @param angle rotation angle
	 */
	public void rotate(double angle) {
		//this is conversion from polar to cartesian coordinates
		//new angle is added to current one
		double copyX = x;
		double copyY = y;
		x = copyX * Math.cos(angle) - copyY * Math.sin(angle);
		y = copyX * Math.sin(angle) + copyY * Math.cos(angle);
	}
	
	/**
	 * Method that returns new vector rotated by <code>angle</code> over (0, 0)
	 * @param angle rotation angle
	 * @return new rotated angle
	 */
	public Vector2D rotated(double angle) {
		//same as rotate, but with new vector
		return new Vector2D(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
	}
	
	/**
	 * Method that scales current vector 
	 * @param scaler
	 */
	public void scale(double scaler) {
		x *= scaler;
		y *= scaler;
	}
	
	/**
	 * Method that returns new vector scaled by <code>scaler</code>
	 * @param scaler
	 * @return new scaled vector
	 */
	public Vector2D scaled(double scaler) {
		return new Vector2D(x * scaler, y * scaler);
	}
	
	/**
	 * Method that returns copy of current vector
	 * @return copy vector
	 */
	public Vector2D copy() {
		return new Vector2D(x, y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector2D))
			return false;
		Vector2D other = (Vector2D) obj;
		return Math.abs(x - other.x) < EPSILON && Math.abs(y - other.y) < EPSILON;
	}
}
