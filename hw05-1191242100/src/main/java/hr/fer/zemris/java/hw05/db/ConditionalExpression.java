/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * One expression that contains getter for certain field name,
 * one operator and string value.
 */
public class ConditionalExpression {

	/**
	 * Field name getter
	 */
	private IFieldValueGetter getter;
	
	/**
	 * Operator
	 */
	private IComparisonOperator operator;
	
	/**
	 * String value
	 */
	private String string;

	/**
	 * Constructor
	 * @param getter
	 * @param string
	 * @param operator
	 */
	public ConditionalExpression(IFieldValueGetter getter, String string, IComparisonOperator operator) {
		super();
		this.getter = getter;
		this.operator = operator;
		this.string = string;
	}

	/**
	 * @return the getter
	 */
	public IFieldValueGetter getFieldGetter() {
		return getter;
	}

	/**
	 * @return the operator
	 */
	public IComparisonOperator getComparisonOperator() {
		return operator;
	}

	/**
	 * @return the string
	 */
	public String getStringLiteral() {
		return string;
	}
	
	
}
