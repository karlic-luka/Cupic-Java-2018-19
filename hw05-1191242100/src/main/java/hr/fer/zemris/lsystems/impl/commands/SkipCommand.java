/**
 * 
 */
package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * @author Luka
 *
 */
public class SkipCommand implements Command {

	/**
	 * step to be skipped
	 */
	private double step;
	
	/**
	 * constructor
	 * @param step
	 */
	public SkipCommand(double step) {
		super();
		this.step = step;
	}
	
	/**
	 * @return the step
	 */
	public double getStep() {
		return step;
	}
	
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState currentState = ctx.getCurrentState();
		Vector2D currentPosition = currentState.getCurrentPosition();
		Vector2D currentDirection = currentState.getDirection();
		Vector2D whereTo = currentPosition.translated(currentDirection.scaled(step * currentState.getEffectiveLength()));
		currentState.setCurrentPosition(whereTo);
	}
}
