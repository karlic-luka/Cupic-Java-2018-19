/**
 * 
 */
package hr.fer.zemris.java.hw06.shell;

import java.util.List;

/**
 * Public interface that represents one command for my shell.
 * It has 3 methods. Execute, get command name and get command description.
 * @author Luka
 *
 */
public interface ShellCommand {

	/**
	 * Method that executes certain shell command. 
	 * @param env environment
	 * @param arguments command's arguments (i.e. path to the directory or file)
	 * @return CONTINUE or TERMINATE
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	/**
	 * Getter for command name.
	 * @return name of command
	 */
	String getCommandName();
	/**
	 * Getter for command's description. Since it could go through more lines,
	 * it is represented by List
	 * @return command's description
	 */
	List<String> getCommandDescription();
}
