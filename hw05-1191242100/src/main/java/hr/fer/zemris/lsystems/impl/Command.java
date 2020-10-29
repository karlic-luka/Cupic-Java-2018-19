package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Interface that represents one command
 */
public interface Command {

	/**
	 * Executes command
	 * @param ctx context
	 * @param painter painter
	 */
	void execute(Context ctx, Painter painter);
}
