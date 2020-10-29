/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.exec;

/**
 * Exception if anything went wrong in Multistack
 * @author Luka
 *
 */
public class MultistackException extends RuntimeException {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructs new <code>MultistackException</code> without error message.
	 */
	public MultistackException() {
		
	}
	
	/**
	 * /**
	 * Constructs new <code>MultistackException</code> provided with error message.
	 * @param message message that will be printed.
	 */
	public MultistackException(String message) {
		super(message);
	}
	
}