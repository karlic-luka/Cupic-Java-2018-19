/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.nodes;

/**
 *  a node representing a piece of textual data. It inherits from Node class.
 * @author Luka
 *
 */
public class TextNode extends Node {

	/**
	 * node text
	 */
	private String text;

	/**
	 * getter
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * constructor
	 * @param text
	 */
	public TextNode(String text) {
		super();
		this.text = text;
	}
}
