/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Parser for query command
 * @author Luka
 */
public class QueryParser {

	/**
	 * Lexer
	 */
	private QueryLexer lexer;
	
	/**
	 * List of conditional expressions
	 */
	private ArrayList<ConditionalExpression> expressions;

	/**
	 * Constructor
	 * @param input text input
	 */
	public QueryParser(String input) {
		Objects.requireNonNull(input);
		lexer = new QueryLexer(input);
		expressions = new ArrayList<>();
		parse();
	}
	
	/**
	 * Checks if direct query(jmbag getter and "=" operator)
	 * @return <code>true</code> if direct query, <code>false</code> otherwise
	 */
	public boolean isDirectQuery() {
		if(expressions.size() != 1) {
			return false;
		}
		ConditionalExpression currentExpression = expressions.get(0);
		return currentExpression.getFieldGetter() == FieldValueGetters.JMBAG 
				&& currentExpression.getComparisonOperator() == ComparisonOperators.EQUALS;
	}
	
	/**
	 * Getter for string literal, but works only with direct query
	 * @throws IllegalStateException if it is called on non-direct queries
	 * @return string literal
	 */
	public String getQueriedJMBAG() {
		if(isDirectQuery()) {
			return expressions.get(0).getStringLiteral();
		}
		throw new IllegalStateException("It was not direct query");
	}
	
	/**
	 * Getter
	 * @return
	 */
	public List<ConditionalExpression> getQuery() {
		return expressions;
	}
	
	/**
	 * helper method to check current token's type
	 * @param type type to be checked
	 * @return true if equal
	 */
	private boolean isTokenOfType(QueryTokenType type) {
		
		return lexer.getToken().getTokenType() == type;
	}
	
	/**
	 * checks token's value
	 * @param text to be checked
	 * @return true if equal
	 */
	private boolean isTokenValue(String text) {
		return lexer.getToken().getValue().equals(text);
	}
	
	/**
	 * Main method that does all the hard work and delegates work to helper methods
	 */
	public void parse() {
		
		//it is null on first call
		lexer.nextToken();
		while(true) {
			
			if(!isTokenOfType(QueryTokenType.TEXT)) {
				throw new QueryParserException("Text was expected");
			}
			
			IFieldValueGetter getter = parseFieldName();
			lexer.nextToken();
			//-----------------------------------------------------------
			if(!isTokenOfType(QueryTokenType.SYMBOL) && !isLike()) {
				throw new QueryParserException("Symbol or LIKE was expected");
			}
			
			IComparisonOperator operator = parseOperator();
			//parseOperator will extract next token (in my implementation
			//there is no operator token --> '<=' are two symbols in a row, so 
			//I need to check that
			//-----------------------------------------------------------
			if(!isTokenOfType(QueryTokenType.STRING)) {
				throw new QueryParserException("String was expected");
			}
			String literal = lexer.getToken().getValue();
			lexer.nextToken();
			
			expressions.add(new ConditionalExpression(getter, literal, operator));
			
			//------------------------------------------------------------
			//maybe there is an and
			if(isAnd(lexer.getToken().getValue())) {
				lexer.nextToken();
				continue;
			} else if(isTokenOfType(QueryTokenType.EOF)) {
				break;
			} else {
				throw new QueryParserException("Something went wrong. It is invalid syntax.");
			}
		}
	}
	
	/**
	 * Parses operator (7 mentioned)
	 * @return operator
	 * @throws QueryParserException if invalid operator
	 */
	private IComparisonOperator parseOperator() {
		String operator = lexer.getToken().getValue();
		lexer.nextToken();
		
		if(isTokenOfType(QueryTokenType.SYMBOL)) {
			operator += lexer.getToken().getValue();
			lexer.nextToken();
		}
		
		if(operator.equals("<")) {
			return ComparisonOperators.LESS;
		} else if(operator.equals("<=")) {
			return ComparisonOperators.LESS_OR_EQUALS;
		} else if(operator.equals(">")) {
			return ComparisonOperators.GREATER;
		} else if(operator.equals(">=")) {
			return ComparisonOperators.GREATER_OR_EQUALS;
		} else if(operator.equals("=")) {
			return ComparisonOperators.EQUALS;
		} else if(operator.equals("!=")) {
			return ComparisonOperators.NOT_EQUALS;
		} else if(operator.equals("LIKE")) {
			return ComparisonOperators.LIKE;
		} else {
			throw new QueryParserException("Invalid operator");
		}
	}
	
	/**
	 * Parses field name (jmbag, firstName, lastName)
	 * @return getter for certain field name
	 * @throws QueryParserException if invalid field name
	 */
	private IFieldValueGetter parseFieldName() {
		
		switch(lexer.getToken().getValue()) {
		
		case "jmbag":
			return FieldValueGetters.JMBAG;
		
		case "firstName":
			return FieldValueGetters.FIRST_NAME;
			
		case "lastName":
			return FieldValueGetters.LAST_NAME;
			
		default:
			throw new QueryParserException("Invalid field name.");
		}
	}
	
	/**
	 * Checks if it is "and"
	 * @param value value to be checked
	 * @return <code>true</code> if it is "and", <code>false</code> otherwise
	 */
	private boolean isAnd(String value) {
		
		return value.toLowerCase().equals("and");
	}
	
	/**
	 * Checks if it is "LIKE" operator
	 * @return <code>true</code> if it is "LIKE", <code>false</code> otherwise
	 */
	private boolean isLike() {
		
		return isTokenValue("LIKE");
	}
}
