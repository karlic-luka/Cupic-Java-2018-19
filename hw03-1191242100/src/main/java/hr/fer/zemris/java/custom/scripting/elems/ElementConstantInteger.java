/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 * represents integers
 * @author Luka
 *
 */
public class ElementConstantInteger extends Element {

	/**
	 * Integer value of element
	 * read-only
	 */
	private int value;

	/**
	 * constructor
	 * @param number
	 */
	public ElementConstantInteger(int number) {
		
		super();
		this.value = number;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
	
		return Integer.toString(value);
	}

	/**
	 * getter
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}
