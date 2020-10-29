/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * The mkdir command takes a single argument: directory name, and creates the appropriate directory
 * structure.
 * @author Luka
 *
 */
public class MkdirShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String directoryName = "";
		
		try {
			directoryName = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		
		if(UtilShellCommand.actualIndexIncludingEscape < arguments.length()) {
			env.writeln("Invalid number of arguments for MKDIR");
			return ShellStatus.CONTINUE;
		}
		Path directoryPath = Paths.get(directoryName);
		directoryPath = UtilShellCommand.resolvePath(directoryPath, env.getCurrentDirectory());
		
		try {
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		env.writeln("Successful creating!");
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()(
	 */
	@Override
	public String getCommandName() {
		
		return "mkdir";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The mkdir command takes a single argument: directory name.");
		list.add("Creates the appropriate directory structure");
		
		return list;
	}

}
