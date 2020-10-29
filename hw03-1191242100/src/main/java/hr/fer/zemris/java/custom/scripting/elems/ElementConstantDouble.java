package hr.fer.zemris.java.custom.scripting.elems;

/**
 * represents double number
 * @author Luka
 *
 */
public class ElementConstantDouble extends Element {

	/**
	 * double value of element
	 * read-only
	 */
	private double value;

	/**
	 * Constructor
	 * @param number
	 */
	public ElementConstantDouble(double number) {
		
		this.value = number;
	}

	/**
	 * Getter
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		
		return Double.toString(value);
	}	
}
