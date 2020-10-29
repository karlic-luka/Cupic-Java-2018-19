/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 *  If started with no arguments, lists names of all supported commands.
 *  If started with single argument, prints name and the description of selected command (or prints
 *  appropriate error message if no such command exists)
 * @author Luka
 *
 */
public class HelpShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		boolean selectedCommand = false;
		String commandName = UtilShellCommand.parseCommandName(arguments);
		if(commandName.length() != 0) {
			selectedCommand = true;
		}
		if(selectedCommand) {
			if((env.commands().get(commandName) == null)) {
				env.writeln("Command does not exist");
				return ShellStatus.CONTINUE;
			}
			env.writeln(env.commands().get(commandName).getCommandName());
			List<String> descriptions = env.commands().get(commandName).getCommandDescription();
			for(String description : descriptions) {
				env.writeln(description);
			}
		} else {
			for(String name: env.commands().keySet()) {
				env.writeln(name);
			}
		}
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "help";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add(" If started with no arguments, lists names of all supported commands.");
		list.add("The first argument is path to some file and is mandatory.");
		list.add("If started with single argument, prints name and the description of selected command");
		list.add("prints appropriate error message if no such command exists");
		
		return list;
	}

}
