/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Contains 3 public static final implementations of {@link IFieldValueGetter} interface.
 */
public class FieldValueGetters {

	/**
	 * First name getter
	 */
	public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;
	
	/**
	 * Last name getter
	 */
	public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;
	
	/**
	 * Jmbag getter
	 */
	public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;

}
