/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * @author Luka
 *
 */
public class ListdShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		if(arguments.length() != 0) {
			env.writeln("Listd command takes no arguments.");
			return ShellStatus.CONTINUE;
		}
		if(env.getSharedData("cdstack") == null) {
			env.writeln("Stack is empty");
		} else {
			Stack<Path> stack = (Stack<Path>)env.getSharedData("cdstack");
			if(stack.isEmpty()) {
				env.writeln("There is no stored paths.");
				return ShellStatus.CONTINUE;
			}
			List<Path> stackList = new ArrayList<>(stack);
			Collections.reverse(stackList);
			if(stack.isEmpty()) {
				env.writeln("Stack is empty.");
			}
			for(Path path : stackList) {
				env.writeln(path.toString());
			}
		}
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "listd";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command listd takes no arguments.");
		list.add("It lists all directories from stack. Each in new line.");
		return list;
	}

}
