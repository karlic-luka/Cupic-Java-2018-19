/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer.demo;

import hr.fer.zemris.java.custom.scripting.lexer.ScriptLexer;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptToken;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptTokenType;

/**
 * Demo class for testing ScriptLexer
 * @author Luka
 */
public class ScriptLexerDemo {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		String text = "This is sample text.\r\n" + 
				"{$ FOR i 1 10 1 $}\r\n" + 
				" This is {$= i $}-th time this message is generated.\r\n" + 
				"{$END$}\r\n" + 
				"{$FOR i 0 10 2 $}\r\n" + 
				" sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n" + 
				"{$END$}\r\n" + 
				"";
		
		ScriptLexer lexer = new ScriptLexer(text);
		while(true) {
			ScriptToken token = lexer.getToken();
			if(token == null) { 
				token = lexer.nextToken();
			}
			System.out.println(token.toString());
			if(lexer.getToken().getType() == ScriptTokenType.EOF) break;
			token = lexer.nextToken();
		}
	}
}
