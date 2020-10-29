/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * Lexer for query parser.
 * @author Luka
 */
public class QueryLexer {

	/**
	 * Data input
	 */
	private char[] data;
	
	/**
	 * Current token
	 */
	private QueryToken token;
	
	/**
	 * Current index
	 */
	private int index;
	
	/**
	 * Constructor 
	 * @param text input
	 */
	public QueryLexer(String text) {
		Objects.requireNonNull(text);
		this.data = text.toCharArray();
		this.index = 0;
	}

	/**
	 * @return the token
	 */
	public QueryToken getToken() {
		return token;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Extracts next token, but delgates work to <code>extractNextToken</code> method
	 * @return
	 */
	public QueryToken nextToken() {
		extractNextToken();
		return getToken();
	}
	
	/**
	 * Helper method that does all the hard work of extracting next token.
	 */
	private void extractNextToken() {
		
		if (token != null && token.getTokenType() == QueryTokenType.EOF) {
			throw new LexerException("No tokens available.");
		} else if(index >= data.length) {
			token = new QueryToken(QueryTokenType.EOF, new String("EOF"));
			return;
		}
		
		skipBlanks();
		if(index >= data.length) {
			token = new QueryToken(QueryTokenType.EOF, new String("EOF"));
			return;
		}
		
		char currentChar = data[index];
		if(Character.isLetter(currentChar)) {
			token = extractText();
			return;
		} else if(currentChar == '"') {
			index++;
			token = extractString();
			return;
		} else {
			token = new QueryToken(QueryTokenType.SYMBOL, Character.toString(currentChar));
			index++;
			return;
		}
	}
	
	/**
	 * Extracts text token type
	 * @return
	 */
	private QueryToken extractText() {
		//current char is letter
		StringBuilder text = new StringBuilder();
		while(index < data.length && Character.isLetter(data[index])) {
			text.append(data[index++]);
		}
		return new QueryToken(QueryTokenType.TEXT, text.toString());
	}
	
	/**
	 * Extracts string token type
	 * @return
	 */
	private QueryToken extractString() {
		//I already skipped '"'
		StringBuilder string = new StringBuilder();
		while(index < data.length && data[index] != '"') {
			string.append(data[index++]);
		}
		index++; //last '"'
		return new QueryToken(QueryTokenType.STRING, string.toString());
	}
	
	/**
	 * Helper method that skips next blanks
	 */
	private void skipBlanks() {
		
		while(index < data.length) {
			
			if(Character.isWhitespace(data[index])) {
				index++;
				continue;
			}
			break;
		}
	}
}
