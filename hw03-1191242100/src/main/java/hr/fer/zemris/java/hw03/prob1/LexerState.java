/**
 * 
 */
package hr.fer.zemris.java.hw03.prob1;

/**
 * @author Luka Karlić
 *
 */
public enum LexerState {

	/**
	 * Default state in which it works "as usual." It groups tokens into "WORD", "NUMBER", "SYMBOL" and "EOF",
	 * which is explained in <code>TokenType</code> enumeration.
	 * @see TokenType
	 * @see EOF
	 * @see NUMBER
	 * @see SYMBOL
	 * @see WORD
	 * 
	 */
	BASIC,
	
	/**
	 * Mode that is activated when first '#' occurs and lasts until second one. 
	 * It works like that periodically.
	 * When lexer is in this mode, everything between two symbols '#' is one big <code>WORD</code> token with 
	 * included blanks. Lexer can not extract <code>NUMBER</code> while in this mode. 
	 */
	EXTENDED
}
