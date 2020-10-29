/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Interface for different operators.
 */
public interface IComparisonOperator {

	/**
	 * Checks if two given strings satisfy IComparisonOperator
	 * @param value1 first string
	 * @param value2 second string
	 * @return <code>true</code> if they satisfy, <code>false</code> otherwise
	 */
	public boolean satisfied(String value1, String value2);
}
