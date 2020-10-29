/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import hr.fer.zemris.java.hw06.shell.ShellIOException;

/**
 * Helper class that contains implemented 
 * methods for parsing path and name from given arguments.
 * @author Luka
 *
 */
public class UtilShellCommand {

	/**
	 * Represents current index after first argument was extracted.
	 * It is used because there are valid escape sequences and that way
	 * we lose our chars.
	 */
	public static int actualIndexIncludingEscape = 0;
	
	/**
	 * Helper method for parsing path from command arguments
	 * @param arguments 
	 * @return path
	 */
	public static String parsePath(String arguments) {
		
		if(arguments.length() == 0) {
			return "";
		}
		
		if(arguments.startsWith("\"")) {
			//spaces are allowed
			char[] chars = arguments.toCharArray();
			int index = 1;
			StringBuilder builder = new StringBuilder();
			
			while(true) {	
				if(index == arguments.length()) {
					throw new ShellIOException("Invalid quote input");
				}
				
				if(chars[index] == '\\') {
					index++;
					
					if(index > arguments.length()) {
						throw new ShellIOException("Invalid escaping sequence.");
					}
					
					if(chars[index] == '\\' || chars[index] == '\"') {
						builder.append(chars[index++]);
					} else {
						builder.append('\\');
						builder.append(chars[index++]);
					}
					
				} else if(chars[index] == '\"') {
					index++;
					break;
					
				} else {
					builder.append(chars[index++]);
				}
			}
			//only end or space can be left
			if(index == arguments.length() || Character.isSpaceChar(chars[index])) {
			
				actualIndexIncludingEscape = index;
				return builder.toString();
			} else {
				throw new ShellIOException("Wrong quoting. After last quotation mark only space is allowed");
			}
		}
		//if it does not start with quotes
		if(arguments.contains(" ")) {
			//+1 because I want to move to first index after last
			actualIndexIncludingEscape += arguments.substring(0, arguments.indexOf(" ")).length() + 1;
			return arguments.substring(0, arguments.indexOf(" "));
		} else {
			actualIndexIncludingEscape += arguments.length() + 1;
			return arguments;
		}
	}
	/**
	 * Method that resents current index to 0 so that 
	 * commands that use parsePath could be used more than once per shell work.
	 */
	public static void resetCounter() {
		actualIndexIncludingEscape = 0;
	}
	
	/**
	 * Helper method that parses name from command arguments
	 * @param arguments 
	 * @return command name
	 */
	public static String parseCommandName(String arguments) {
		if(arguments.length() == 0) {
			return "";
		}
		if(!arguments.contains(" ")) {
			return arguments;
		}
		return arguments.substring(0, arguments.indexOf(" "));
	}
}
