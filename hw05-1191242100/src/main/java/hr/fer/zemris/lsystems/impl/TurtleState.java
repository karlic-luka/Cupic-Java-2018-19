/**
 * 
 */
package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.math.Vector2D;

/**
 * Represents current turtle's state
 * @author Luka
 */
public class TurtleState {

	/**
	 * Current position
	 */
	private Vector2D currentPosition;
	
	/**
	 * Normalised vector that represents direction
	 */
	private Vector2D direction;
	
	/**
	 * Color
	 */
	private Color color;
	
	/**
	 * Effective length
	 */
	private double effectiveLength;
	
	/**
	 * Constructor
	 * @param currentPosition
	 * @param direction
	 * @param color
	 * @param effectiveLength
	 */
	public TurtleState(Vector2D currentPosition, Vector2D direction, Color color, double effectiveLength) {
//		Objects.requireNonNull(currentPosition);
//		Objects.requireNonNull(direction);
//		Objects.requireNonNull(color);
//		Objects.requireNonNull(effectiveLength);
		this.currentPosition = currentPosition;
		this.direction = direction;
		this.color = color;
		this.effectiveLength = effectiveLength;
	}

	/**
	 * @return the currentPosition
	 */
	public Vector2D getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @return the direction
	 */
	public Vector2D getDirection() {
		return direction;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return the effectiveLength
	 */
	public double getEffectiveLength() {
		return effectiveLength;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(Vector2D currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Vector2D direction) {
		this.direction = direction.normalised();
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @param effectiveLength the effectiveLength to set
	 */
	public void setEffectiveLength(double effectiveLength) {
		this.effectiveLength = effectiveLength;
	}

	/**
	 * @return copy of current state
	 */
	public TurtleState copy() {
		return new TurtleState(currentPosition.copy(), direction.copy(), new Color(color.getRGB()), effectiveLength);
	}
}
