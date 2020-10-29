/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 *  Base class for all elements.
 *  Elements will be used as representation of expressions while lexer works.
 * @author Luka
 *
 */
public class Element {

	/**
	 * String representation of element
	 * @return element interpreted as string
	 */
	public String asText() {
		return "";
	}
}
