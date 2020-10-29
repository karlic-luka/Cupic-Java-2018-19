/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Enumeration of query token types
 * @author Luka
 *
 */
public enum QueryTokenType {

	/**
	 * Everything inside " "
	 */
	STRING,
	
	SYMBOL,
	
	TEXT,
	
	/**
	 * End of file
	 */
	EOF
}
