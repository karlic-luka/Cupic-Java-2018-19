/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.parser;

/**
 * @author Luka
 *
 */
public class SmartScriptParserException extends RuntimeException {

	private static final long serialVersionUID = 1L; 	
	
	/**
	 * 
	 */
	public SmartScriptParserException() {
		
	}

	/**
	 * @param message
	 */
	public SmartScriptParserException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public SmartScriptParserException(Throwable cause) {
		super(cause);
		
	}

}
