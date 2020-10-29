/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.parser;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;
import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptLexer;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptToken;
import hr.fer.zemris.java.custom.scripting.lexer.ScriptTokenType;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;

/**
 * parser for our "made up" program language
 * @author Luka
 */
public class SmartScriptParser {

	/**
	 * lexer for this parser
	 */
	private ScriptLexer lexer;
	
	/**
	 *node that represents entire document
	 */
	private DocumentNode documentNode;

	/**
	 * constructor sets new lexer for this parser and gives it text to group tokens
	 * @param text
	 */
	public SmartScriptParser(String text) {
		super();
		if(text == null) {
			throw new SmartScriptParserException("Input can not be null");
		}
		lexer = new ScriptLexer(text);
		parse();
	}

	/**
	 * getter
	 * @return the documentNode
	 */
	public DocumentNode getDocumentNode() {
		return documentNode;
	}
	
	/**
	 * helper method to check current token's type
	 * @param type type to be checked
	 * @return true if equal
	 */
	private boolean isTokenOfType(ScriptTokenType type) {
		
		return lexer.getToken().getType() == type;
	}
	
	/**
	 * checks token's value
	 * @param text to be checked
	 * @return true if equal
	 */
	private boolean isTokenValue(String text) {
		return lexer.getToken().getValue().asText().equals(text);
	}
	
	/**
	 * most important method that delegates work to other helper methods
	 */
	private void parse() {
		
		ObjectStack stack = new ObjectStack();
		documentNode = new DocumentNode();
		stack.push(documentNode);
		
		if(lexer.getToken() == null) {
			lexer.nextToken();
		}
		//boolean tagIsOpened = false;
		while(true) {
			//ako smo dosli do kraja
			if(isTokenOfType(ScriptTokenType.EOF)) {
				break;
			}
			//sada treba doci text ili tag
			if(!isTokenOfType(ScriptTokenType.TEXT) && !isTokenOfType(ScriptTokenType.TAG)) {
				throw new SmartScriptParserException("Text or tag expected");
			}
			//try {
			if(isTokenOfType(ScriptTokenType.TEXT)) {
				String text = lexer.getToken().getValue().asText();
				TextNode textNode = new TextNode(text);
				Node topOfStack = (Node)stack.peek();
				topOfStack.addChildNode(textNode);
				lexer.nextToken();
				continue;
			} else {
				lexer.nextToken();
				if(!validTagName()) {
					throw new SmartScriptParserException("Invalid tag name.");
				}
				if(isTokenOfType(ScriptTokenType.SYMBOL)) { //ako je '='
					lexer.nextToken(); //preskoci '='
					parseEmptyTag(stack);
					continue;		
				}
				String currentTokenValueCaps = lexer.getToken().getValue().asText().toUpperCase();
				if(currentTokenValueCaps.equals("FOR")) {
					lexer.nextToken(); //preskacem for
					parseForTag(stack);
					continue;
				} else if(currentTokenValueCaps.equals("END")) {
					lexer.nextToken(); //preskacem end
					parseEndTag(stack);
					continue;
				} else {
					throw new SmartScriptParserException("Parser does not know what to do with " 
							+ currentTokenValueCaps);
				}
			}	
			//} catch (Exception exc){
				//throw new SmartScriptParserException("Something went wrong. Could not parse.");
		}
		if(stack.size() != 1) { //treba biti samo documentNode 
			throw new SmartScriptParserException("Error in the document. It contains more end-tags than opened non-empty tags.");
		}
	}

	/**
	 * helper method that checks if current token has valid tag name (= or variable name)
	 * @return
	 */
	private boolean validTagName() {
	
		if(isTokenOfType(ScriptTokenType.SYMBOL)) {
			if(isTokenValue("=")) {
				return true;
			} else {
				return false;
			}
		} else if(isTokenOfType(ScriptTokenType.VARIABLE)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * helper method to check valid operator. have not been used
	 * @return
	 */
	private boolean validOperator() {
		
		return (isTokenValue("+") || isTokenValue("-") || isTokenValue("*") ||
				isTokenValue("/") || isTokenValue("^"));
	}
	
	/**
	 * helper method that parses in empty tag
	 * @param stack
	 */
	private void parseEmptyTag(ObjectStack stack) {
		
		ObjectStack temporaryStack = new ObjectStack();
		//ne znam koliko ce biti elemenata u tagu pa ne mogu odma znati koliko treba alocirati memorije
		//zato koristim stog kojeg cu zatim napuniti od kraja prema pocetku, tj vrh stoga
		//cu stavljati na kraj polja
		
		while(lexer.getToken() != null && !isTokenOfType(ScriptTokenType.TAG)) {
			
			if(isTokenOfType(ScriptTokenType.EOF)) {
				throw new SmartScriptParserException("Tag must be closed properly.");
			} else if(lexer.getToken().getType() instanceof ScriptTokenType ){
				temporaryStack.push(lexer.getToken().getValue());
			}
			lexer.nextToken();
		}
		Element[] elements = new Element[temporaryStack.size()];
		for(int i = temporaryStack.size() - 1; i >= 0; i--) {
			elements[i] = (Element)temporaryStack.pop();
		}
		Node currentTopOfStack = (Node)stack.peek();
		currentTopOfStack.addChildNode(new EchoNode(elements));
		lexer.nextToken(); //preskociti zavrsni tag
	}
	
	/**
	 * helper method that parses end tag
	 * @param stack
	 */
	private void parseEndTag(ObjectStack stack) {
		//ulazim u metodi tako sto sam prethodno pomaknuo token na sljedeci
		//dakle, trenutni mora biti tag
		if(!isTokenOfType(ScriptTokenType.TAG)) {
			throw new SmartScriptParserException("Tag is not closed properly or it is wrong syntax");
		}
		try {
			stack.pop();
		} catch (EmptyStackException ex) {
			throw new SmartScriptParserException("End tag can not come before for tag");
		}
		if(stack.size() == 0) {
			throw new SmartScriptParserException("There is more end-tags than opened non-empty tags.");
		}
		lexer.nextToken(); //micem se s trenutnog taga - trebao bi ici na text
	}
	
	private void parseForTag(ObjectStack stack) {
		
		//vec sam pomaknut na sljedeci token
		if(!isTokenOfType(ScriptTokenType.VARIABLE)) {
			throw new SmartScriptParserException("Variable was expected.");
		} //sada moraju barem 2 biti string/broj/varijabla
		ElementVariable variable = new ElementVariable(lexer.getToken().getValue().asText());
		
		lexer.nextToken();
		if(!isValidElementInForTag()) {
			throw new SmartScriptParserException("Invalid syntax in for-tag.");
		}
		Element startExpression = (Element)(lexer.getToken().getValue());
		
		lexer.nextToken();
		if(!isValidElementInForTag()) {
			throw new SmartScriptParserException("Invalid syntax in for-tag.");
		}
		Element endExpression = (Element)(lexer.getToken().getValue());
		
		lexer.nextToken();
		boolean hasFour = false;
		
		if(isTokenOfType(ScriptTokenType.TAG)) {
			hasFour = false;
		} else if(isValidElementInForTag()) {
			hasFour = true;
		} else {
			throw new SmartScriptParserException("Invalid syntax in for-tag.");
		}
		
		Element stepExpression = null;
		if(hasFour) {
			stepExpression = (Element)(lexer.getToken().getValue());
			lexer.nextToken(); //ako ga ima, preci cu ga i bit cu trenutno na tagu
		}
		lexer.nextToken(); //prelazim tag
		
		ForLoopNode forNode = new ForLoopNode(variable, startExpression, endExpression, stepExpression);
		Node currentTopOfStack = (Node)stack.peek();
		currentTopOfStack.addChildNode(forNode);
		stack.push(forNode);
	}
	
	/**
	 * helper method to check if it is number/string/variable
	 * @return
	 */
	private boolean isValidElementInForTag() {
		return isTokenOfType(ScriptTokenType.DOUBLE) || isTokenOfType(ScriptTokenType.INT) || isTokenOfType(ScriptTokenType.VARIABLE)
				|| isTokenOfType(ScriptTokenType.STRING);
	}
}
