/**
 * 
 */
package hr.fer.zemris.java.hw03.prob1;

/**
 * Enumeration of types of tokens in our document. There are four kinds of tokens - EOF, WORD, NUMBER, SYMBOL
 * which are explained down.
 * @author Luka Karlić
 */
public enum TokenType {
	
	/**
	 * Signalises that there is no more tokens.
	 */
	EOF,
	
	/**
	 * String of one or more characters on which <code>Character.isLetter</code> returns <code>true</code>
	 */
	WORD,
	
	/**
	 * String of one or more characters that can be interpreted as <code>long</code>
	 */
	NUMBER,
	
	/**
	 * Every "individual" character that stays when we move every <code>WORD</code>, <code>NUMBER</code> 
	 * and blank (<code>'\r', '\n', '\t', ' ')</code>
	 */
	SYMBOL
}
