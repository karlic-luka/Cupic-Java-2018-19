/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * enumeration of states for our "made up" program
 * @author Luka
 */
public enum ScriptLexerState {

	/**
	 * everything besides {$ ... $}
	 */
	TEXT,
	
	/**
	 * everythinf inside {$ ... $}
	 */
	TAG
}
