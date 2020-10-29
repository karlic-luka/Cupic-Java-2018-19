/**
 * 
 */
package hr.fer.zemris.java.hw06.shell.commands;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw06.shell.Environment;
import hr.fer.zemris.java.hw06.shell.ShellCommand;
import hr.fer.zemris.java.hw06.shell.ShellIOException;
import hr.fer.zemris.java.hw06.shell.ShellStatus;

/**
 * The tree command expects a single argument: directory name and prints a tree (each directory level shifts
 * output two characters to the right).
 * @author Luka
 *
 */
public class TreeShellCommand implements ShellCommand {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#executeCommand(hr.fer.zemris.java.hw06.shell.Environment, java.lang.String)
	 */
	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String directoryName = "";
		
		try {
			directoryName = UtilShellCommand.parsePath(arguments);
		} catch(ShellIOException e) {
			env.writeln(e.getMessage());
			return ShellStatus.CONTINUE;
		}
		
		if(UtilShellCommand.actualIndexIncludingEscape < arguments.length()) {
			env.writeln("Invalid number of arguments for TREE");
			return ShellStatus.CONTINUE;
		}
		Path directoryPath = Paths.get(directoryName);
		if(!directoryPath.toFile().isDirectory()) {
			env.writeln("You did not provide directory!");
			return ShellStatus.CONTINUE;
		}
		//I used lambda so I could use environment...and it is "easier"
		try {
			Files.walkFileTree(directoryPath, new FileVisitor<>() {
				
				private int level = 0;
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					env.writeln("  ".repeat(level) + dir.getFileName());
					level++;
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					env.writeln("  ".repeat(level) + file.getFileName());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					level--;
					return FileVisitResult.CONTINUE;
				}
			});
			
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
		
		return "tree";
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw06.shell.ShellCommand#getCommandDescription()
	 */
	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<>();
		list.add("The tree command expects a single argument: directory name and prints a tree.");
		list.add("each directory level shifts output two characters to the right");
		
		return list;
	}

}
