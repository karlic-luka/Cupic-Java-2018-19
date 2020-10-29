/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * @author Luka
 *
 */
public class CdShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		String newWorkingDirectory = "";
		try {
			newWorkingDirectory = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		if(UtilShellCommand.actualIndexIncludingEscape < arguments.length()) {
			env.writeln("Invalid number of arguments for CD command. It should only have one path.");
			return ShellStatus.CONTINUE;
		}
		Path pathToNewDirectory = Paths.get(newWorkingDirectory);
		pathToNewDirectory = UtilShellCommand.resolvePath(pathToNewDirectory, env.getCurrentDirectory());
		env.setCurrentDirectory(pathToNewDirectory);
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "cd";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command cd takes one argument - path.");
		list.add("Given path is directory which will be set as new current working directory.");
		list.add("If provided path is not absolute, it will be resolved agains current working directory.");
		
		return list;
	}

}
