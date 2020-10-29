/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * @author Luka
 *
 */
public class PushdShellCommand implements ShellCommand {

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
			env.writeln("Invalid number of arguments for PUSHD command. It should only have one path.");
			return ShellStatus.CONTINUE;
		}
		Path pathToNewDirectory = Paths.get(newWorkingDirectory);
		pathToNewDirectory = UtilShellCommand.resolvePath(pathToNewDirectory, env.getCurrentDirectory());
		
		if(!(Files.exists(pathToNewDirectory) && Files.isDirectory(pathToNewDirectory))) {
			env.writeln("Given path is not directory or it does not exist.");
			return ShellStatus.CONTINUE;
		}
		if(env.getSharedData("cdstack") == null) {
			Stack<Path> stack = new Stack<>();
			stack.push(env.getCurrentDirectory());
		} else {
			env.setSharedData("cdstack", new Stack<Path>().push(env.getCurrentDirectory()));
		}

		env.setCurrentDirectory(pathToNewDirectory);
		
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "pushd";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		
		List<String> list = new ArrayList<>();
		list.add("Command takes one argument - path.");
		list.add("Given argument must be directory and it becomes current working directory.");
		list.add("It throws error if argument is invalid.");
		
		return list;
	}

}
