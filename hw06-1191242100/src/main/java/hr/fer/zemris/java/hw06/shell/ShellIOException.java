/**
 * 
 */
package hr.fer.zemris.java.hw06.shell;

/**
 * Exception for my shell that occurs if anything went wrong.
 * @author Luka
 *
 */
public class ShellIOException extends RuntimeException {

private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructs new <code>ShellIOException</code> without error message.
	 */
	public ShellIOException() {
		
	}
	
	/**
	 * /**
	 * Constructs new <code>ShellIOException</code> provided with error message.
	 * @param message message that will be printed.
	 */
	public ShellIOException(String message) {
		super(message);
	}
}
