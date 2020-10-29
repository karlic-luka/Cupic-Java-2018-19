/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.custom.scripting.elems.ElementConstantDouble;
import hr.fer.zemris.java.custom.scripting.elems.ElementConstantInteger;
import hr.fer.zemris.java.custom.scripting.elems.ElementFunction;
import hr.fer.zemris.java.custom.scripting.elems.ElementOperator;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.hw03.prob1.LexerException;


/**
 * Lexer for our "made up" program language.
 * @author Luka
 */
public class ScriptLexer {

	/**
	 * Program code as char array.
	 */
	private char[] data;
	/**
	 * current token
	 */
	private ScriptToken token;
	/**
	 * current index
	 */
	private int currentIndex;
	/**
	 * current state of lexer - there is <code>TEXT</code> and <code>TAG</code>
	 * TAG is everything between {$ ... $}, TEXT is everything else.
	 */
	private ScriptLexerState state;
	
	/**
	 * constructor 
	 * @param input
	 */
	public ScriptLexer(String text) {
		//super();
		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.state = ScriptLexerState.TEXT;
		//extractNextToken();
	}
	
	/**
	 * setter 
	 * @param state
	 */
	public void setState(ScriptLexerState state) {
		if(state == null || !(state instanceof ScriptLexerState)) {
			throw new IllegalArgumentException("Invalid state. It can not be null and it must be from ScriptLexerState enumeration");
		}
		this.state = state;
	}
	
	/**
	 * Getter for current state 
	 * @return the state
	 */
	public ScriptLexerState getState() {
		return state;
	}

	/**
	 * method that extracts next token via helper method 
	 * @return
	 */
	public ScriptToken nextToken() {
		extractNextToken();
		return getToken();
	}

	/**
	 * getter
	 * @return the token
	 */
	public ScriptToken getToken() {
		return token;
	}
	
	/**
	 * helper method that skips next blanks
	 */
	private void skipNextBlanks() {
		
		while(currentIndex < data.length) {
			char character = data[currentIndex];
			if(character == ' ' || character == '\t' || character == '\r' || character == '\n') {
				currentIndex++;
				continue;
			}
			break; //ako nije blank - izlazi iz petlje
		}
	}
	
	/**
	 * helper method whose work is delegated by <code>nextToken</code>
	 */
	public void extractNextToken() {
		
		//ako je vec prije utvrden kraj
		if (token != null && token.getType() == ScriptTokenType.EOF) {
			throw new LexerException("No tokens available.");
		} else if(currentIndex >= data.length) {
			token = new ScriptToken(ScriptTokenType.EOF, new ElementString("KRAJ DOKUMENTA"));
			return;
		}
		if(getState() == ScriptLexerState.TEXT) {
			workInTextMode();
		} else {
			workInTagMode();
		}
		return;
	}
	
	/**
	 * helper method when state is set to <code>TEXT</code> 
	 */
	private void workInTextMode() {
		try {
			if (data[currentIndex] == '{' && data[currentIndex + 1] == '$') {
				setState(ScriptLexerState.TAG);
				currentIndex += 2;
				token = new ScriptToken(ScriptTokenType.TAG, new ElementString("open"));
			} else {
				token = extractText();
			}
		} catch (Exception ex) {
			throw new LexerException("Invalid input");
		}
		return;
	}
	
	/**
	 * helper method when state is set to <code>TAG</code> 
	 */
	private void workInTagMode() {
		
		skipNextBlanks();
		if(currentIndex +1 > data.length) {
			throw new LexerException("Invalid document");
		}
		if(data[currentIndex] == '$' && data[currentIndex + 1] == '}') {
			setState(ScriptLexerState.TEXT);
			currentIndex += 2;
			token = new ScriptToken(ScriptTokenType.TAG, new ElementString("close"));
			return;
		}
		char currentChar = data[currentIndex];
		
		if(currentChar == '\"') {
			token = extractString();
			return;
		} else if(currentChar == '@') {
			token = extractFunction();
			return;
		} else if(currentChar == '-') {
			if((currentIndex + 1 <= data.length) && Character.isDigit(data[currentIndex + 1])) {
				currentIndex++;
				token = extractNumber(false);
				return;
			} else {
				token = new ScriptToken(ScriptTokenType.SYMBOL, new ElementOperator("-"));
				currentIndex++;
				return;
			} 
		} else if(Character.isDigit(currentChar)) {
			token = extractNumber(true);
			return;
		} else if(Character.isLetter(currentChar)) {
			token = extractVariable();
			return;
		} else {
			token = new ScriptToken(ScriptTokenType.SYMBOL, new ElementOperator(Character.toString(currentChar)));
			currentIndex++;
			return;
		}
	}
	
	/**
	 * helper method that returns true if it is valid escape sequence
	 * @return true if it is valid escape sequence (depends on current state)
	 */
	private boolean validEscapingSequence() {
		
		if (currentIndex + 1 >= data.length) {
			throw new LexerException("Invalid escaping sequence");
		}
		char afterEscape = data[currentIndex + 1];
		if(getState() == ScriptLexerState.TAG) {

			if(afterEscape == '"' || data[currentIndex + 1] == '\\') {
				return true;
			} else {
				throw new LexerException("Invalid escaping sequence.");
			}
		} else {
			if(data[currentIndex + 1] == '\\' || data[currentIndex + 1] == '{') {
				return true;
			} else {
				throw new LexerException("Invalid escaping sequence.");
			}
		}
	}
	
	/**
	 * helper method that extracts text tokens
	 * @return text token
	 */
	private ScriptToken extractText() {
		StringBuilder text = new StringBuilder();
		
		while(currentIndex < data.length && data[currentIndex] != '{') {
			if(data[currentIndex] == '\\') {
				if(validEscapingSequence()) {
					text.append(data[++currentIndex]);
					currentIndex++;
					continue;
				}
			}
			text.append(data[currentIndex++]);
		}
		return new ScriptToken(ScriptTokenType.TEXT, new ElementString(text.toString()));
	}
	
	/**
	 * helper method that extracts positive or negative integer or double
	 * @param positive flag if the number is positive
	 * @return number token 
	 */
	private ScriptToken extractNumber(boolean positive) { 
		
		StringBuilder number = new StringBuilder();
		if(!positive) {
			number.append('-');
		}
		int numberOfDots = 0;
		
		while(currentIndex < data.length && (Character.isDigit(data[currentIndex])) || data[currentIndex] == '.') {
			
			if(data[currentIndex] == '.') {
				numberOfDots++;
			}
			number.append(data[currentIndex++]);
		}
		if(numberOfDots > 1) {
			throw new LexerException("Invalid input. Too many dots for number");
		} else if(numberOfDots == 0) {
			try {
				return new ScriptToken(ScriptTokenType.INT, new ElementConstantInteger(Integer.parseInt(number.toString())));
			} catch (Exception ex) {
				throw new LexerException("Too big number for integer.");
			}
		} else { // 1 tocka 
			try {
				return new ScriptToken(ScriptTokenType.DOUBLE, new ElementConstantDouble(Double.parseDouble(number.toString())));
			} catch (Exception ex) {
				throw new LexerException("Too big number for double.");
			}
		}	
	}
	
	/**
	 * helper method that extracts functions (@ + letter(s))
	 * @return function token
	 */
	private ScriptToken extractFunction() {
		
		if((currentIndex + 1) >= data.length) {
			throw new LexerException("Invalid input, letter was expected.");
		}
		char nextCharacter = data[currentIndex + 1];
		StringBuilder function = new StringBuilder();
		
		if(!Character.isLetter(nextCharacter)) {
			throw new LexerException("Invalid input. Letter was expected.");
		}
		currentIndex++; //slovo je, mogu preskočiti @
		while(currentIndex < data.length && (Character.isLetter(data[currentIndex])) || Character.isDigit(data[currentIndex])
				|| data[currentIndex] == '_') {
			function.append(data[currentIndex++]);
		}
		return new ScriptToken(ScriptTokenType.FUNCTION, new ElementFunction(function.toString()));
	}
	
	/**
	 * helper method that extracts string
	 * @return string token
	 */
	private ScriptToken extractString() {
		currentIndex++; //preskakanje "
		StringBuilder string = new StringBuilder();
		
		for(int i = currentIndex; i < data.length; i++) {
			
			if(data[currentIndex] == '"') {
				break;
			} else if(data[currentIndex] == '\\') {
				if(validEscapingSequence()) {
					string.append(data[++currentIndex]);
					currentIndex++;
					continue;
				}
			}
			string.append(data[currentIndex++]);
		}
		
		if(data[currentIndex] != '"') { //ako to nije razlog izlaska iz petlje
			throw new LexerException("String should end with quotation marks.");
		}
		currentIndex++; //treba maknuti zavrsne navodnike
		return new ScriptToken(ScriptTokenType.STRING, new ElementString(string.toString()));
	}
	
	/**
	 * helper method that extracts variables (letter + more letters/digits/underscores)
	 * @return
	 */
	private ScriptToken extractVariable() {
		
		StringBuilder id = new StringBuilder();
		id.append(data[currentIndex++]);
		for(int i = currentIndex; i < data.length; i++) {
			
			char character = data[currentIndex];
			if(Character.isLetterOrDigit(character)  || character == '_') {
				id.append(data[currentIndex++]);
			} else {
				break;
			}
		}
		return new ScriptToken(ScriptTokenType.VARIABLE, new ElementVariable(id.toString()));
	}
	
}
