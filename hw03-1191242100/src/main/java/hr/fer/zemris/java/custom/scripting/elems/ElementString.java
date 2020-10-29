/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 * string representation
 * @author Luka
 *
 */
public class ElementString extends Element {

	/**
	 * read-only property - string value
	 */
	private String value;

	public ElementString(String text) {
		value = text;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
	
		return value;
	}
}
