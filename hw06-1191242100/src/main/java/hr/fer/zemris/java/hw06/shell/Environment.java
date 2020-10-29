/**
 * 
 */
package hr.fer.zemris.java.hw06.shell;

import java.util.SortedMap;

/**
 * This is an abstraction which will be passed to each defined command. The each implemented command
 * communicates with user (reads user input and writes response) only through this interface.
 * It contains methods: readLine, write, writeln, commands, getMultilineSymbol, etc...
 * @author Luka Karlić
 *
 */
public interface Environment {

	/**
	 * Reads one line from console
	 * @return
	 * @throws ShellIOException if could not read
	 */
	String readLine() throws ShellIOException;
	/**
	 * Writes <code>text</code> to console.
	 * @param text text to be written
	 * @throws ShellIOException if could not write
	 */
	void write(String text) throws ShellIOException;
	/**
	 * Same as <code>write</code> but enters new line
	 * @see write
	 * @param text text to be written
	 * @throws ShellIOException
	 */
	void writeln(String text) throws ShellIOException;
	/**
	 * Sorted map of every currently available command in our shell.
	 * @return
	 */
	SortedMap<String, ShellCommand> commands();
	/**
	 * Getter for symbol that represents multiline indicator.
	 * @return
	 */
	Character getMultilineSymbol();
	/**
	 * Setter for multiline symbol
	 * @param symbol
	 */
	void setMultilineSymbol(Character symbol);
	/**
	 * Getter for prompt symbol
	 * @return
	 */
	Character getPromptSymbol();
	/**
	 * Setter for prompt symbol.
	 * @param symbol
	 */
	void setPromptSymbol(Character symbol);
	/**
	 * Getter for more lines symbol that represents more line indicator.
	 * @return
	 */
	Character getMorelinesSymbol();
	/**
	 * Setter for more lines symbol.
	 * @param symbol
	 */
	void setMorelinesSymbol(Character symbol);
}
