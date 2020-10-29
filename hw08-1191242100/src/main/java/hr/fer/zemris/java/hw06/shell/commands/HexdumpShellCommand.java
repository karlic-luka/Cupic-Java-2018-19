/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Hexdump command expects a single argument: file name, and produces hex-output.
 * It also replaces all bytes whose value is less than 32 or greater than 127 with '.'
 * @author Luka
 *
 */
public class HexdumpShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		String fileName = "";

		try {
			fileName = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		
		if(UtilShellCommand.actualIndexIncludingEscape < arguments.length()) {
			env.writeln("Invalid number of arguments for HEXDUMP");
			return ShellStatus.CONTINUE;
		}
		Path filePath = Paths.get(fileName);
		filePath = UtilShellCommand.resolvePath(filePath, env.getCurrentDirectory());
		
		File ourFile = filePath.toFile();
		if(!ourFile.isFile()) {
			env.writeln("You did not provide me file.");
			return ShellStatus.CONTINUE;
		}
		try(InputStream fis = Files.newInputStream(filePath, StandardOpenOption.READ)) {
			
			StringBuilder builder = new StringBuilder();
			byte[] buffer = new byte[16];
			int position = 0x0;
			
			while(true) {
				int numberOfRead = fis.read(buffer);
				if(numberOfRead < 1) {
					break;
				}
				builder.append(String.format("%08x", position)).append(": ");
				for(int i = 0; i < 16; i++) {
					if(i >= numberOfRead) {
						builder.append("  ");
					} else {
						builder.append(byteToHex(buffer[i]));
					}
					if(i != 7) {
						builder.append(" ");
					} else {
						builder.append("|");
					}
				}
				builder.append("| ");
				for(int i = 0; i < numberOfRead; i++) {
					if(buffer[i] >= 32 && buffer[i] <= 127) {
						builder.append((char) buffer[i]);
					} else {
						builder.append(".");
					}
				}
		
				position += 0x10;
				env.writeln(builder.toString());
				builder.setLength(0);
			}
			
		} catch (IOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		return ShellStatus.CONTINUE;
	}

	/**
	 * Helper method that evaluates byte to hex.
	 * @param number number to be evaluated
	 * @return hex output
	 */
	//As you can see this method is much different than one in Util.
	//I googled better way to implement this and it is so much more elegant than mine.
	private String byteToHex(byte number) {
		
	    char[] hexDigits = new char[2];
	    hexDigits[0] = Character.forDigit((number >> 4) & 0xF, 16);
	    hexDigits[1] = Character.forDigit((number & 0xF), 16);
	    
	    return new String(hexDigits).toUpperCase();
	}
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "hexdum";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Hexdump command expects a single argument: file name, and produces hex-output.");
		list.add("It also replaces all bytes whose value is less than 32 or greater than 127 with '.'");
		
		return list;
	}

}
