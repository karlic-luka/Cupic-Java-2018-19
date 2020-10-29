/**
 * 
 */
package hr.fer.zemris.java.hw03.prob1;

/**
 * @author Luka
 *
 */
public class Lexer {

	/**
	 * Program code as char array.
	 */
	private char[] data;
	/**
	 * current token
	 */
	private Token token;
	/**
	 * current index
	 */
	private int currentIndex;
	/**
	 * current state (BASIC or EXTENDED) - explained in enumeration 
	 */
	private LexerState state;
	
	/**
	 * constructor with text as input
	 * @param text
	 */
	public Lexer(String text) {
		//super();
		text = text.trim();
		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.state = LexerState.BASIC;
		//extractNextToken();
		
	}
	/**
	 * setter
	 * @param state to be set
	 */
	public void setState(LexerState state) {
		if(state == null) {
			throw new NullPointerException("Null reference can not be passed as parameter.");
		}
		this.state = state;
	}
	
	
	/**
	 * Getter for current state 
	 * @return the state
	 */
	public LexerState getState() {
		return state;
	}

	/**
	 * returns next token via <code>extractNextToken</code>
	 * @return next token
	 */
	public Token nextToken() {
		extractNextToken();
		return getToken();
	}

	/**
	 * getter
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	
	/**
	 * extracts next token
	 */
	public void extractNextToken() {
		
		//ako je vec prije utvrden kraj
		if (token != null && token.getType() == TokenType.EOF) {
			throw new LexerException("No tokens available.");
		} else if(currentIndex >= data.length) {
			currentIndex++;
			token = new Token(TokenType.EOF, null);
			return;
		}
		
		skipNextBlanks();
		
		if(currentIndex >= data.length) {
			 token = new Token(TokenType.EOF, null);
			 return;
		}
		
		if(data[currentIndex] == '#') {
			currentIndex++;
			token = new Token(TokenType.SYMBOL, '#');
			setState(state == LexerState.BASIC ? LexerState.EXTENDED : LexerState.BASIC);
			return;
		}
		if(state == LexerState.BASIC) {
			
			if(Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\') {
				String word = extractNextWord(LexerState.BASIC);
				token = new Token(TokenType.WORD, word);
				return;
			} else if(Character.isDigit(data[currentIndex])) {
				long number = extractNextNumber();
				token = new Token(TokenType.NUMBER, number);
				return;
			} else { //nije blank, nije word i nije number - dakle simbol je
				char symbol = data[currentIndex];
				currentIndex++;
				token = new Token(TokenType.SYMBOL, symbol);
				return;
			}
		} else { //nalazi se u extendedu pa moze zapoceti na bilokojem znaku, ne mora nuzno biti slovo
			//zato su razdvojeni uvjeti
			String word = extractNextWord(LexerState.EXTENDED);
			token = new Token(TokenType.WORD, word);
			return;
		}
	}
	
	/**
	 * skips next blanks
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
	 * returns true if current char is blank
	 * @param c character to be checked
	 * @return true if it is blank
	 */
	private boolean isBlank(char c) {
		if(c != ' ' && c != '\t' && c != '\n' && c != '\r') {
			return false;
		}
		return true;
	}
	
	/**
	 * extracts next word
	 * @param state current state
	 * @return next word
	 */
	private String extractNextWord(LexerState state) {
		
		StringBuilder string = new StringBuilder();
		if(state == LexerState.BASIC) {
			
			for(; currentIndex < data.length; currentIndex++ ) {
				char character = data[currentIndex];
				if(character == '#') {
					break;
				}
				if(character == '\\') {
					if((currentIndex + 1) >= data.length) {
						throw new LexerException("Something is expected after escaping sign.");
					}
					
					if((data[currentIndex + 1] == '\\') || (Character.isDigit(data[currentIndex + 1]))) {
						string.append(data[++currentIndex]);
						continue;
					}  else {
						throw new LexerException("Invalid escaping sequence.");
					}
				}
				
				if(!Character.isLetter(data[currentIndex]) || data[currentIndex] == ' ') {
					break;
				}
				string.append(data[currentIndex]);
			}
			
		} else {
			while(currentIndex < data.length && data[currentIndex] != '#' && !isBlank(data[currentIndex])) {
				string.append(data[currentIndex]);
				currentIndex++;
			}
		}
		return string.toString();
	}
	
	/**
	 * extracts next number
	 * @return next number (long type)
	 */
	private long extractNextNumber() {
		
		int startIndex = currentIndex;
		while(currentIndex < data.length && Character.isDigit(data[currentIndex])) {
			currentIndex++;
		}
		
		int endIndex = currentIndex;
		String stringNumber = new String(data, startIndex, endIndex - startIndex);
		try {
			long number = Long.parseLong(stringNumber);
			return number;
		} catch(NumberFormatException ex) {
			throw new LexerException("Input is invalid. " + stringNumber + " is higher than "
					+ "the highest long number.");
		}
	}
}
