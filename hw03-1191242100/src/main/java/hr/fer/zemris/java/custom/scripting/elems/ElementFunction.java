/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.elems;

/**
 * represents functions (@ + letter(s))
 * @author Luka
 */
public class ElementFunction extends Element {

	/**
	 * read only property - name of function
	 */
	private String name;

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
		
		return "@" + name;
	}

	/**
	 * constructor
	 * @param name
	 */
	public ElementFunction(String name) {
		//super();
		this.name = name;
	}
	
	
	
}
