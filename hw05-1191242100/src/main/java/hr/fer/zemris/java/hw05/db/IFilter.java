/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Interface that has only one method which
 * "accepts" certain record if it satisfies tests.
 */
public interface IFilter {

	public boolean accepts(StudentRecord record);
}
