/**
 * 
 */
package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * This class provides us functionality of drawing fractals.
 * It has one stack object on which it storages current states
 * of turtle. Current state is on the top of the stack.
 * @author Luka
 */
public class Context {

	/**
	 * Stack of turtle states
	 */
	private ObjectStack<TurtleState> turtleStates;
	
	/**
	 * Constructor
	 */
	public Context() {
		//Objects.requireNonNull(turtleStates);
		turtleStates = new ObjectStack<TurtleState>();
	}

	/**
	 * Getter for current state
	 * @return
	 */
	public TurtleState getCurrentState() {
		return turtleStates.peek();
	}
	
	/**
	 * Pushes current state to stack
	 * @param state
	 */
	public void pushState(TurtleState state) {
		//Objects.requireNonNull(state);
		turtleStates.push(state);
	}
	
	/**
	 * Pops current state
	 */
	public void popState() {
		turtleStates.pop();
	}
}
