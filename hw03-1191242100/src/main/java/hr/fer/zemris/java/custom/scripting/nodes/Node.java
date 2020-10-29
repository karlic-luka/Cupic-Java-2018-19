/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

/**
 * Base class for all nodes of our document. 
 * Nodes will be used for representation of structured documents while parsing.
 * @author Luka
 *
 */
public class Node {

	private ArrayIndexedCollection children;
	/**
	 * Adds given child to an internally managed collection of children;
	 * @param child child to be added
	 */
	public void addChildNode(Node child) {
		if(children == null) {
			children = new ArrayIndexedCollection();
		}
		children.add(child);
	}
	
	/**
	 * Returns a number of (direct) children. 
	 * @return number of children
	 */
	public int numberOfChildren() {
		if(children == null) {
			return 0;
		}
		return children.size();
	}
	
	/**
	 *  returns selected child or throws an appropriate exception if the index is invalid.
	 * @param index index of child
	 * @return child on given index
	 */
	public Node getChild(int index) {
		if (children == null) {
			return null;
		}
		return (Node)children.get(index);
	}

	@Override
	public String toString() {
		return "";
	}
}
