/**
 * 
 */
package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * @author Luka
 *
 */
public class ColorCommand implements Command {

	/**
	 * color
	 */
	private Color color;
	
	/**
	 * constructor
	 * @param color
	 */
	public ColorCommand(Color color) {
		super();
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setColor(color);
	}
}
