package hr.fer.zemris.java.hw03.prob1.demo;

import hr.fer.zemris.java.hw03.prob1.Lexer;

/**
 * Class for demo testing simple lexer.
 * @author Luka
 *
 */
public class LexerDemo {

	public static void main(String[] args) {
		
		Lexer lexer = new Lexer("Janko 3#Ivana26\\a 463abc#zzz");
		lexer.nextToken();
		lexer.nextToken();
		lexer.nextToken();
		
		
		lexer.nextToken();
		lexer.nextToken();
		lexer.nextToken();
		lexer.nextToken();
		
		
		
//		String string = "  a  b   c ";
//		string = string.trim().replaceAll(" ", "");
//		char[] data = string.toCharArray();
//		for(int i = 0; i < data.length; i++) {
//			System.out.println(data[i]);
//		}
	}
}
