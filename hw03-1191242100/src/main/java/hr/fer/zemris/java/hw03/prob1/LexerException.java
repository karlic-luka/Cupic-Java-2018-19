/**
 * 
 */
package hr.fer.zemris.java.hw03.prob1;

/**
 * New lexer exception if something goes wrong. It extends RunTimeException
 * @author Luka
 *
 */
public class LexerException extends RuntimeException {

	private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructs new <code>LexerException</code> without error message.
	 */
	public LexerException() {
		
	}
	
	/**
	 * /**
	 * Constructs new <code>LexerException</code> provided with error message.
	 * @param message message that will be printed.
	 */
	public LexerException(String message) {
		super(message);
	}
}
