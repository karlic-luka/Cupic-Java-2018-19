/**
 * 
 */
package hr.fer.zemris.java.hw05.db;


/**
 * Query parser exception.
 */
public class QueryParserException extends RuntimeException {

	private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructor
	 */
	public QueryParserException() {
		
	}

	/**
	 * Constructor with message
	 * @param message
	 */
	public QueryParserException(String message) {
		super(message);
	}

	/**
	 * Constructor with cause
	 * @param cause
	 */
	public QueryParserException(Throwable cause) {
		super(cause);
	}

}
