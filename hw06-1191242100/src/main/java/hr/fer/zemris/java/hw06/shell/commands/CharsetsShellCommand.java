/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command charsets takes no arguments and lists names of supported charsets for your Java platform. 
 * A single charset name is written per line.
 * @author Luka
 *
 */
public class CharsetsShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		if(arguments.length() != 0) {
			env.writeln("Charsets command should not have any arguments");
		}
		SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
		for(String name : charsetsMap.keySet()) {
			env.writeln(name);
		}
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "charsets";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command charsets takes no arguments and lists names of supported charsets for your Java platform. ");
		list.add("A single charset name is written per line.");

		return list;
	}

}
