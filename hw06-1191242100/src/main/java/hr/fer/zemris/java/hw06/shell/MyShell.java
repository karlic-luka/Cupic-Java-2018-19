/**
 * 
 */
package hr.fer.zemris.java.hw06.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import hr.fer.zemris.java.hw06.shell.commands.CatShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.CharsetsShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.CopyShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.ExitShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.HelpShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.HexdumpShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.LsShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.MkdirShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.SymbolShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.TreeShellCommand;
import hr.fer.zemris.java.hw06.shell.commands.UtilShellCommand;

/**
 * My implementation of shell. Currently it has elementary commands
 * such as  charsets, cat, ls, tree, copy, mkdir, hexdump.
 *  When started, my program writes a greeting message to user (Welcome to MyShell v 1.0), writes a 
 *  prompt symbol and waits for the user to enter a command. The command can span across multiple lines. 
 * @author Luka
 *
 */
public class MyShell {
	
	/**
	 * Map of currently available commands
	 */
	private static SortedMap<String, ShellCommand> commands = EnvironmentImplementation.buildCommands();
	/**
	 * Instance of environment.
	 */
	public static Environment environment = new EnvironmentImplementation();
	/**
	 * Static implementation of environment that will be used in my shell.
	 * @author Luka
	 *
	 */
	public static class EnvironmentImplementation implements Environment {

		/**
		 * Buffered reader
		 */
		private BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in)); 
		//nisam znao koji je path od system.in pri koristenju Files.newBufferedReader(path, charset)
		/**
		 * Buffered writer.
		 */
		private BufferedWriter buffWriter =  new BufferedWriter(new OutputStreamWriter(System.out));
		/**
		 * Prompt symbol
		 */
		private Character promptSymbol = '>';
		/**
		 * More lines symbol
		 */
		private Character moreLinesSymbol = '\\';
		/**
		 * Multi line symbol
		 */
		private Character multiLineSymbol = '|';
	
		@Override
		public String readLine() throws ShellIOException {
			try {
				return buffReader.readLine();
			} catch (IOException e) {
				throw new ShellIOException("Could not read line");
			}
		}

		@Override
		public void write(String text) throws ShellIOException {
			try {
				buffWriter.write(text);
				buffWriter.flush();
			} catch (IOException e) {
				throw new ShellIOException("Could not write");
			}
		}

		@Override
		public void writeln(String text) throws ShellIOException {
			try {
				buffWriter.write(text);
				buffWriter.newLine();
				buffWriter.flush();
			} catch (IOException e) {
				throw new ShellIOException("Could not write new line");
			}
		}

		@Override
		public SortedMap<String, ShellCommand> commands() {
			buildCommands();
			return Collections.unmodifiableSortedMap(commands);
		}
		
		/**
		 * Helper method that puts all available commands in map.
		 * @return
		 */
		private static SortedMap<String, ShellCommand> buildCommands() {
			TreeMap<String, ShellCommand> commands = new TreeMap<>();
	        commands.put("charsets", new CharsetsShellCommand());
	        commands.put("cat", new CatShellCommand());
	        commands.put("ls", new LsShellCommand());
	        commands.put("tree", new TreeShellCommand());
	        commands.put("copy", new CopyShellCommand());
	        commands.put("mkdir", new MkdirShellCommand());
	        commands.put("hexdump", new HexdumpShellCommand());
	        commands.put("exit", new ExitShellCommand());
	        commands.put("symbol", new SymbolShellCommand());
	        commands.put("help", new HelpShellCommand());
	        
	        return commands;
		}
		
		@Override
		public Character getMultilineSymbol() {
			return multiLineSymbol;
		}

		@Override
		public void setMultilineSymbol(Character symbol) {
			multiLineSymbol = symbol;
		}

		@Override
		public Character getPromptSymbol() {
			return promptSymbol;
		}

		@Override
		public void setPromptSymbol(Character symbol) {
			promptSymbol = symbol;
		}

		@Override
		public Character getMorelinesSymbol() {
			return moreLinesSymbol;
		}

		@Override
		public void setMorelinesSymbol(Character symbol) {
			moreLinesSymbol = symbol;
		}
		
	}
	
	
	/**
	 * Entry point to the program
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		environment.writeln("Welcome to MyShell v 1.0");
		
		while(true) {
			environment.write(environment.getPromptSymbol().toString() + " ");
			String curLine = environment.readLine();
			//if it ends with moreline symbol
			while(curLine.endsWith(environment.getMorelinesSymbol().toString())) {
				environment.write(environment.getMultilineSymbol().toString() + " ");
				//I want to get rid of multiline symbol
				curLine = curLine.substring(0, curLine.length() - 1);
				curLine += environment.readLine();
			}
			//now we have our line
			String commandName = "";
			String arguments = "";
			if(curLine.contains(" ")) {
				String[] splitted = curLine.split(" ", 2);
				commandName = splitted[0];
				arguments = splitted[1];
				
			} else {
				commandName = curLine;
			}
			
			ShellCommand command = commands.get(commandName.toLowerCase());
			if(command == null) {
				environment.writeln("I do not recognize given command");
				continue;
			}
			ShellStatus status = command.executeCommand(environment, arguments);
			//so I could call parseString more times - counter should be 0
			UtilShellCommand.resetCounter();
			if(status.equals(ShellStatus.TERMINATE)) {
				break; 
			}
		}
	}
}

