/**
 * 
 */
package hr.fer.zemris.java.hw05.db.demo;

import hr.fer.zemris.java.hw05.db.QueryLexer;
import hr.fer.zemris.java.hw05.db.QueryToken;
import hr.fer.zemris.java.hw05.db.QueryTokenType;

/**
 * @author Luka
 *
 */
public class QueryLexerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String text = "jmbag=\"0000000003\"";
//		String text = " lastName = \"Blažić\"";
//		String text = " firstName>\"A\" and lastName LIKE \"B*ć\"\r\n" + 
//				"";
//		String text = " firstName>\"A\" and firstName<\"C\" and lastName LIKE \"B*ć\" "
//				+ "and jmbag>\"0000000002\"";
		
		String text = " jmbag =\"0123456789\" ";
		QueryLexer lexer = new QueryLexer(text);
		QueryToken token = lexer.nextToken();

		while(true) {
			
			System.out.println(token.toString());
			if(lexer.getToken().getTokenType() == QueryTokenType.EOF) break;
			token = lexer.nextToken();
		}
	}
}
