/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * The copy command expects two arguments: source file name and destination file name (i.e. paths and
 * names). If destination file exists, it asks user if is it allowed to overwrite it.
 * Works only with files (no directories). If the second argument is directory, it is assumed that user wants
 * to copy the original file into that directory using the original file name. 
 * @author Luka
 *
 */
public class CopyShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String sourceFileName = "";
		try {
			sourceFileName = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		int indexAfterSource = UtilShellCommand.actualIndexIncludingEscape;
		
		//these lines are here because of using substring down there - maybe it is index out of bounds exc
		if(indexAfterSource >= arguments.length()) {
			env.writeln("I expected destination file name");
			return ShellStatus.CONTINUE;
		}
		
		String destinationFileName = "";
		try {
			destinationFileName = UtilShellCommand.parsePath(arguments.substring(indexAfterSource).trim());
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		//now we extracted what we need and we need to check if anything left - nothing should be left, not even blanks
		int indexAfterDestination = arguments.indexOf(destinationFileName) + UtilShellCommand.actualIndexIncludingEscape;
		if(indexAfterDestination < arguments.length()) {
			env.writeln("Too many arguments for COPY");
			return ShellStatus.CONTINUE;
		}
	
		Path sourcePath = Paths.get(sourceFileName);
		sourcePath = UtilShellCommand.resolvePath(sourcePath, env.getCurrentDirectory());
		Path destinationPath = Paths.get(destinationFileName);
		destinationPath = UtilShellCommand.resolvePath(destinationPath, env.getCurrentDirectory());
		
		if(Files.isDirectory(destinationPath)) {
			String originalFileName = sourceFileName.substring(sourceFileName.lastIndexOf("\\") + 1);
			destinationPath = Paths.get(destinationFileName + "\\" + originalFileName);
        }
		
		File sourceFile = sourcePath.toFile();
		File destinationFile = destinationPath.toFile();
		
		if(sourceFile.isDirectory()) {
			env.writeln("First argument should not be directory");
			return ShellStatus.CONTINUE;
		}
		
		if(sourceFile.equals(destinationFile)) {
			env.writeln("I will not copy to myself.");
			return ShellStatus.CONTINUE;
		}
		
		if(destinationFile.exists()) {
			env.writeln("Am I allowed to overwrite " + destinationFile.getName() + " ?");
			env.write(env.getPromptSymbol() + " ");
			env.writeln("Write only YES or NO");
			env.write(env.getPromptSymbol() + " ");
			
			String answer = env.readLine();
			if(!answer.equals("YES")) {
				env.writeln("My job here is done. You said that I could not overwrite it and yet you're still here");
				return ShellStatus.CONTINUE;
			}
		}
		//yes was said
		try(InputStream fis = Files.newInputStream(sourcePath, StandardOpenOption.READ);
				OutputStream fos = Files.newOutputStream(destinationPath, StandardOpenOption.CREATE)) {
			byte[] buffer = new byte[1024];
			while(true) {
				int numberOfRead = fis.read(buffer);
				if(numberOfRead < 1) {
					break;
				}
				fos.write(buffer, 0, numberOfRead);
			}
			
		} catch (IOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		env.writeln("Successful copying!");
		return ShellStatus.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		
		return "copy";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The copy command expects two arguments: source file name and destination file name.");
		list.add("If destination file exists, it asks user if is it allowed to overwrite it.");
		list.add("Works only with files (no directories).");
		list.add("It is assumed that user wants to copy the original file into that directory using the original file name.");
		list.add("if the second argument is the directory.");
		
		return list;
	}

}
