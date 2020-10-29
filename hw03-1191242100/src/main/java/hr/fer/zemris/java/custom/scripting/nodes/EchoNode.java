/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementString;

/**
 *  a node representing a command which generates some textual output dynamically. It inherits
 *  from Node class
 * @author Luka
 */
public class EchoNode extends Node {

	/**
	 * elements inside empty tag
	 */
	Element[] elements;

	public EchoNode(Element[] elements) {
		//super();
		this.elements = elements;
	}

	/**
	 * Getter
	 * @return the elements
	 */
	public Element[] getElements() {
		return elements;
	}
	
	@Override
	public String toString() {
		String string = "{$=";
		for(Element element : elements) {
			if(element instanceof ElementString) {
				string += "\"" + element.asText() + "\" ";
				continue;
			}
			string += element.asText() + " ";
		}
		string += "$}";
		return string;
	}
}
