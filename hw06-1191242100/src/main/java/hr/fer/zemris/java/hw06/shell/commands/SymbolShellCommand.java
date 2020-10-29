/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Method that gets or sets 3 special symbols - prompt, multiline or moreline.
 * If it sets new symbol, it has 2 parameters, otherwise only one.
 * @author Luka
 *
 */
public class SymbolShellCommand implements ShellCommand {
	
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		String[] splitArguments = arguments.split("\\s+");
		if(splitArguments.length != 1 && splitArguments.length !=2) {
			env.writeln("Invalid number of arguments for symbol shell command");
			return ShellStatus.CONTINUE;
		}
		try {
			switch(splitArguments[0]) {
			
			case "PROMPT": 
				if(splitArguments.length == 2) {
					if(splitArguments[1].length() != 1) {
						env.writeln("Character was expected here.");
					} else {
						Character oldPrompt = env.getPromptSymbol();
						env.setPromptSymbol(splitArguments[1].charAt(0));
					env.writeln("Symbol for PROMPT changed from '" + oldPrompt + "' to '" + splitArguments[1].charAt(0) + "'");
					}
				} else if(splitArguments.length == 1) {
					env.writeln("Symbol for PROMPT is '" + env.getPromptSymbol() + "'");
				} else {
					env.writeln("Invalid number of arguments for PROMPT symbol shell command");
				}
				break;
				
			case "MULTILINE":
				if(splitArguments.length == 2) {
					if(splitArguments[1].length() != 1) {
						env.writeln("Character was expected here.");
					} else {
						Character oldMultiline = env.getMultilineSymbol();
						env.setMultilineSymbol(splitArguments[1].charAt(0));
						env.writeln("Symbol for MULTILINE changed from '" + oldMultiline + "' to '" + splitArguments[1].charAt(0) + "'");
					}
				} else if(splitArguments.length == 1) {
					env.writeln("Symbol for MULTILINE is '" + env.getMultilineSymbol() + "'");
				} else {
					env.writeln("Invalid number of arguments for MULTILINE symbol shell command");
				}
				break;
				
			case "MORELINES":
				if(splitArguments.length == 2) {
					if(splitArguments[1].length() != 1) {
						env.writeln("Character was expected here.");
					} else {
						Character oldMorelines = env.getMorelinesSymbol();
						env.setMorelinesSymbol(splitArguments[1].charAt(0));
						env.writeln("Symbol for MORELINES changed from '" + oldMorelines + "' to '" + splitArguments[1].charAt(0) + "'");
					}
				} else if(splitArguments.length == 1) {
					env.writeln("Symbol for MORELINES is '" + env.getMorelinesSymbol() + "'");
				} else {
					env.writeln("Invalid number of arguments for MORELINES symbol shell command");
				}
				break;
			default:
				env.writeln("I do not recognize that command");
			}
		} catch(ShellIOException e) {
			env.writeln(e.getMessage());
		}
		
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
	
		return "symbol";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Method that gets or sets 3 special symbols - prompt, multiline or moreline.");
		list.add("If it sets new symbol, it has 2 parameters, otherwise only one.");
		
		return list;
	}


}
