/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * One query token.
 * @author Luka
 *
 */
public class QueryToken {

	/**
	 * Token type
	 */
	private QueryTokenType tokenType;
	
	/**
	 * Token value
	 */
	private String value;

	/**
	 * Constructor
	 * @param tokenType
	 * @param value
	 */
	public QueryToken(QueryTokenType tokenType, String value) {
		Objects.requireNonNull(tokenType);
		this.tokenType = tokenType;
		this.value = value;
	}

	/**
	 * @return the tokenType
	 */
	public QueryTokenType getTokenType() {
		return tokenType;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QueryToken [tokenType=" + tokenType + ", value=" + value + "]";
	}
}
