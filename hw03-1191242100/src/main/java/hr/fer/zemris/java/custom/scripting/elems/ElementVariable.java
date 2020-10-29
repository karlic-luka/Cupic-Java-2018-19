/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 * Variable representation (letter + letters/digits/underscores)
 * @author Luka
 */
public class ElementVariable extends Element {

	/**
	 * read-only property - variable name
	 */
	private String name;

	/**
	 * constructor
	 * @param text
	 */
	public ElementVariable(String text) {
		name = text;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.custom.scripting.elems.Element#asText()
	 */
	@Override
	public String asText() {
		
		return name;
	}	
}
