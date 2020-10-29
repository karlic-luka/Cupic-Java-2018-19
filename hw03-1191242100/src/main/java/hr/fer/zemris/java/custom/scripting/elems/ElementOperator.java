/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 * operators (+, -, *, /, ^)
 * @author Luka
 */
public class ElementOperator extends Element {

	/**
	 * read-only property
	 */
	private String symbol;

	/**
	 * constructor
	 * @param symbol
	 */
	public ElementOperator(String symbol) {
		
		this.symbol = symbol;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		
		return symbol;
	}	
}
