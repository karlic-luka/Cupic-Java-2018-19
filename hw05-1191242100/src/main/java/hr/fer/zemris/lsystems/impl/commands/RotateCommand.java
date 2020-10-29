/**
 * 
 */
package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * @author Luka
 *
 */
public class RotateCommand implements Command {

	/**
	 * angle in degrees
	 */
	private double angle;
	
	/**
	 * constructor
	 * @param angle
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}

	/**
	 * @return the angle in degrees
	 */
	public double getAngle() {
		return angle;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState current = ctx.getCurrentState();
		current.setDirection(current.getDirection().rotated(Math.toRadians(angle)).normalised());
	}

}
