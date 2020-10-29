/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;
import hr.fer.zemris.java.custom.scripting.elems.ElementVariable;

/**
 *a node representing a single for-loop construct. It inherits from Node class
 * @author Luka
 */
public class ForLoopNode extends Node {

	/**
	 * variable - must be ElementVariable type
	 */
	private ElementVariable variable;
	/**
	 * start expression (number, string or variable)
	 */
	private Element startExpression;
	/**
	 * end expression (number, string or variable)
	 */
	private Element endExpression;
	/**
	 * step expression (can be null)
	 */
	private Element stepExpression; //can be null
	
	/**
	 * constructor
	 * @param variable
	 * @param startExpression
	 * @param endExpression
	 * @param stepExpression can be null
	 */
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression,
			Element stepExpression) {
		super();
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		if(stepExpression == null) {
			this.stepExpression = null;
		}	
	}
	/**
	 * @return the variable
	 */
	public ElementVariable getVariable() {
		return variable;
	}
	/**
	 * @return the startExpression
	 */
	public Element getStartExpression() {
		return startExpression;
	}
	/**
	 * @return the endExpression
	 */
	public Element getEndExpression() {
		return endExpression;
	}
	/**
	 * @return the stepExpression
	 */
	public Element getStepExpression() {
		return stepExpression;
	}
	
	@Override
	public String toString() {
		String string = "{$ FOR " + variable.asText() + " "
				+ startExpression.asText() + " " + endExpression.asText();
		if (stepExpression != null) {
			string += " " + stepExpression.asText();
		}
		string +=" $} ";
		return string;
	}
}
