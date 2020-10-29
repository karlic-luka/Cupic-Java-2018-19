/**
 * 
 */
package hr.fer.zemris.java.hw07.observer2;

/**
 * Class that implements IntegerStorageObserver and counts number of changes
 * since constructing.
 * @author Luka
 *
 */
public class ChangeCounter implements IntegerStorageObserver {

	/**
	 * Number of changes
	 */
	private int valueChanges;
	
	/**
	 * Constructor
	 */
	public ChangeCounter() {
		this.valueChanges = 0;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw07.observer1.IntegerStorageObserver#valueChanged(hr.fer.zemris.java.hw07.observer1.IntegerStorage)
	 */
	@Override
	public void valueChanged(IntegerStorage.IntegerStorageChange istorage) {
		
		System.out.format("Number of value changes since tracking: %d%n", ++valueChanges);
	}

}
