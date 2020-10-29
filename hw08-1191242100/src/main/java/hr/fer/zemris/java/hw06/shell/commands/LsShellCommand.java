/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * Command ls takes a single argument – directory – and writes a directory listing (not recursive).
 * Output format:
 * drwx size yyyy-MM-dd HH:mm:ss name
 * d - directory, r - readable, w - writable, x - executable (- if not)

 * @author Luka
 *
 */
public class LsShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		String directoryName = "";

		try {
			directoryName = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
			return ShellStatus.CONTINUE;
		}
		
		if(UtilShellCommand.actualIndexIncludingEscape < arguments.length()) {
			env.writeln("Invalid number of arguments for LS");
			return ShellStatus.CONTINUE;
		}
		Path directoryPath = Paths.get(directoryName);
		directoryPath = UtilShellCommand.resolvePath(directoryPath, env.getCurrentDirectory());
		
		if(!directoryPath.toFile().isDirectory()) {
			env.writeln("You did not provide directory!");
			return ShellStatus.CONTINUE;
		}
		File directory = directoryPath.toFile();
		File[] kids = directory.listFiles();
		
		for(File kid : kids) {
			try {
				env.writeln(lsOutput(kid));
			} catch (IOException e) {
				env.writeln(e.getClass().getSimpleName().toString() + " " + e.getMessage());
				return ShellStatus.CONTINUE;
			}
		}
		return ShellStatus.CONTINUE;
	}
	/**
	 * Helper method that returns string in particular format.
	 * @param kid current file 
	 * @return String of expected output
	 * @throws IOException if something went wrong while getting file attribute view
	 */
	private String lsOutput(File kid) throws IOException {
		
		boolean directory = kid.isDirectory();
		boolean readable = kid.canRead();
		boolean writable = kid.canWrite();
		boolean executable = kid.canExecute();
		
		long size = kid.length();
		String sizeString = Long.toString(size);
		
		StringBuilder output = new StringBuilder();
		output.append(directory ? "d" : "-");
		output.append(readable ? "r" : "-");
		output.append(writable ? "w" : "-");
		output.append(executable ? "x" : "-");
		for(int i = 0, length = 10 - sizeString.length(); i < length; i++) {
			output.append(" ");
		}
		output.append(sizeString);
		output.append(" ");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Path path = kid.toPath();
		BasicFileAttributeView faView = Files.getFileAttributeView(path, BasicFileAttributeView.class, 
				LinkOption.NOFOLLOW_LINKS);
		BasicFileAttributes attributes = faView.readAttributes();
		FileTime fileTime = attributes.creationTime();
		String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
		
		output.append(formattedDateTime);
		output.append(" ");
		output.append(kid.getName());
		return output.toString();
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "ls";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("Command ls takes a single argument – directory – and writes a directory listing (not recursive).");
		list.add("Output format: drwx size yyyy-MM-dd HH:mm:ss name");
		list.add("d - directory, r - readable, w - writable, x - executable (- if not)");
		
		return list;
	}

}
