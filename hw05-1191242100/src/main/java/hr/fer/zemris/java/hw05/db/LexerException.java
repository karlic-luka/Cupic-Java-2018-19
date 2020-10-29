/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * New lexer exception used in Query command if something goes wrong.
 * It extends RunTimeException
 */
public class LexerException extends RuntimeException {

	private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructs new <code>LexerException</code> without error message.
	 */
	public LexerException() {
		
	}
	
	/**
	 * Constructs new <code>LexerException</code> provided with error message.
	 * @param message message that will be printed.
	 */
	public LexerException(String message) {
		super(message);
	}
}
