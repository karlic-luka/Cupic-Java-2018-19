/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command cat takes one or two arguments. The first argument is path to some file and is mandatory. The
 * second argument is charset name that should be used to interpret chars from bytes. If not provided, a default
 * platform charset should be used. This command opens given file and writes its content to console.
 * @author Luka
 *
 */
public class CatShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		Charset charset = Charset.defaultCharset();
		
		String directoryPath = UtilShellCommand.parsePath(arguments);
		Path path = Paths.get(directoryPath);
		
		int charsetNameIndex = UtilShellCommand.actualIndexIncludingEscape;
		
		//it is easier to extract it from arguments when I know real index than to call parseName from Util
		if(charsetNameIndex < arguments.length()) {
			String charsetName = arguments.substring(charsetNameIndex).trim();
			if(charsetName.length() != 0) {
				try {
					charset = Charset.forName(charsetName);
				} catch(IllegalCharsetNameException | UnsupportedCharsetException e) {
					env.writeln(e.getMessage());
					return ShellStatus.CONTINUE;
				}
			}
			
		}
		try(InputStream fis = Files.newInputStream(path, StandardOpenOption.READ)) {
			
			byte[] buffer = new byte[1024];
			
			while(true) {
				int numberOfRead = fis.read(buffer);
				if(numberOfRead < 1) {
					break;
				}
				env.write(new String(buffer, 0, numberOfRead, charset));
			}
			env.writeln("");
			
			
		} catch (IOException e) {
			env.writeln(e.getMessage());
			return ShellStatus.CONTINUE;
		}
		
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "cat";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command cat takes one or two arguments.");
		list.add("The first argument is path to some file and is mandatory.");
		list.add("The second argument is charset name that should be used to interpret chars from bytes.");
		list.add("This command opens given file and writes its content to console");
		
		return list;
	}

}
