/**
 * 
 */
package hr.fer.zemris.java.hw07.observer1;

/**
 * Observer interface.
 * @author Luka
 */
public interface IntegerStorageObserver {

	/**
	 * Method that does something with the value that is stored
	 * in provided storage.
	 * @param istorage storage
	 */
	public void valueChanged(IntegerStorage istorage);
}
