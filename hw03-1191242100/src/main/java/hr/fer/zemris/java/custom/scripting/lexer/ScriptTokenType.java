/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Enumeration for our "made up" program.
 * @author Luka
 */
public enum ScriptTokenType {
	
	/**
	 * end of document
	 */
	EOF,
	/**
	 * starts with letter then 0 or more letters, digits or underscores
	 */
	VARIABLE,
	/**
	 * @ + letter(s)
	 */
	FUNCTION,
	/**
	 * integer
	 */
	INT,
	/**
	 * double
	 */
	DOUBLE,
	/**
	 * string
	 */
	STRING,
	/**
	 * single symbol
	 */
	SYMBOL,
	/**
	 * {$ or $}
	 */
	TAG,
	/**
	 * everything in text mode (state)
	 */
	TEXT
}
