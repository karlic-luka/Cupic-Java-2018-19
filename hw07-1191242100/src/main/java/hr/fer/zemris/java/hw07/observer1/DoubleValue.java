/**
 * 
 */
package hr.fer.zemris.java.hw07.observer1;

/**
 * Class that represents implementation of IntegerStorageObserver
 * which just doubles the current value that is stored.
 * It can only be called n times.
 * @author Luka
 *
 */
public class DoubleValue implements IntegerStorageObserver {

	/**
	 * Number of times the method valueChanged can be called
	 */
	private int n;

	public DoubleValue(int n) {
		super();
		this.n = n;
	}
	
	/**
	 * Method that represents if the method valueChanged can still be called.
	 * @return <code>true</code> if it can be called, <code>false</false> otherwise
	 */
	private boolean canBeCalled() {
		return n > 0;
	}

	/* (non-Javadoc)
	 * @see hr.fer.zemris.java.hw07.observer1.IntegerStorageObserver#valueChanged(hr.fer.zemris.java.hw07.observer1.IntegerStorage)
	 */
	@Override
	public void valueChanged(IntegerStorage istorage) {
		
		if(canBeCalled()) {
			System.out.println(istorage.getValue() * 2);
			n--;
			return;
		}
		istorage.removeObserver(this);
	}

}
