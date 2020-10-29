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
public class ScaleCommand implements Command {

	/**
	 * scaler for new effective length
	 */
	private double factor;
	
	public ScaleCommand(double factor) {
		super();
		this.factor = factor;
	}
	
	/**
	 * @return the factor
	 */
	public double getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(double factor) {
		this.factor = factor;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState current = ctx.getCurrentState();
		current.setEffectiveLength(factor * current.getEffectiveLength());
	}
}
