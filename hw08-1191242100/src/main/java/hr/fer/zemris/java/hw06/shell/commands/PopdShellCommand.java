/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.file.Path;
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
public class PopdShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		if(arguments.length() != 0) {
			env.writeln("Popd command takes no arguments.");
			return ShellStatus.CONTINUE;
		}
		if(env.getSharedData("cdstack") == null) {
			env.writeln("Stack is empty");
		} else {
			Stack<Path> stack = (Stack<Path>)env.getSharedData("cdstack");
			if(stack.isEmpty()) {
				env.writeln("Stack is empty.");
			}
			try {
				env.setCurrentDirectory(stack.pop());
			} catch(ShellIOException e) {
				env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
				env.writeln("Directory does not exist.");
			}
		}
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "popd";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command pwd takes no arguments.");
		list.add("It pops directory from stack and sets it as new current directory.");
		return list;
	}

}
