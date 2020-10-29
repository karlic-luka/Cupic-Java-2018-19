/**
 * 
 */
package hr.fer.zemris.java.hw07.observer1;

/**
 * Implementation of IntegerStorageObserver that squares current value
 * stored.
 * @author Luka
 *
 */
public class SquareValue implements IntegerStorageObserver {

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw07.observer1.IntegerStorageObserver#valueChanged(hr.fer.zemris.java.hw07.observer1.IntegerStorage)
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		
		System.out.format("Provided new value: %d, square is %d%n", istorage.getValue(), istorage.getValue() * istorage.getValue());
	}

}
