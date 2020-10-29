/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Interface for getters
 */
public interface IFieldValueGetter {

	/**
	 * getter for certain field name
	 * @param record record from which it will be gotten
	 * @return field value
	 */
	public String get(StudentRecord record);
}
