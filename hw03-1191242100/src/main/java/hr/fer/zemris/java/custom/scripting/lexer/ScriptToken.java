/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.custom.scripting.elems.Element;

/**
 * token paired up with his type and value
 * @author Luka
 */
public class ScriptToken {

	/**
	 * token type
	 */
	private ScriptTokenType type;
	
	/**
	 * value of token (can be null)
	 */
	private Element value;
	
	/**
	 * Construcotr 
	 * @param type type of token
	 * @param value value of token
	 */
	public ScriptToken(ScriptTokenType type, Element value) {
		super();
		if(type == null) {
			throw new IllegalArgumentException("Token can not be null!");
		}
		this.type = type;
		this.value = value;
	}

	/**
	 * @return the type
	 */
	public ScriptTokenType getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	public Element getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "(" + type + ", " + value.asText() + ")";
	}
	
}
