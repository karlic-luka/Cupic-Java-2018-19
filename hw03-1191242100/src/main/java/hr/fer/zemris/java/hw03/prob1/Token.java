/**
 * 
 */
package hr.fer.zemris.java.hw03.prob1;

/**
 * Class that models one <code>Token</code> from program.
 * @author Luka Karlić
 */
public class Token {

	/**
	 * Type of token
	 */
	private TokenType type;
	
	/**
	 * Value of token
	 */
	private Object value;
	
	/**
	 * Constructor
	 * @param type type of token
	 * @param value value of token
	 */
	public Token(TokenType type, Object value) {
		super();
		if(type == null) {
			throw new IllegalArgumentException("Token can not be null");
		}
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Getter of <code>TokenType</code>
	 * @return type of token
	 */
	public TokenType getType() {
		return type;
	}
	
	/**
	 * Getter of value
	 * @return value of token
	 */
	public Object getValue() {
		return value;
	}

}
