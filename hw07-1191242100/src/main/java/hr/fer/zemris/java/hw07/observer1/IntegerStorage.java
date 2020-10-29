/**
 * 
 */
package hr.fer.zemris.java.hw07.observer1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Luka
 *
 */
public class IntegerStorage {

	/**
	 * Value that is stored
	 */
	private int value;
	/**
	 * List of observers
	 */
	private List<IntegerStorageObserver> observers; 
	
	/**
	 * Constructor
	 * @param initialValue value to be stored
	 */
	public IntegerStorage(int initialValue) {
		Objects.requireNonNull(initialValue);
		this.value = initialValue;
		observers = new ArrayList<>();
	}
	/**
	 * Adds new observer
	 * @param observer observer to be added
	 */
	public void addObserver(IntegerStorageObserver observer) {
		// add the observer in observers if not already there ...
		Objects.requireNonNull(observer);
		if(!observers.contains(observer)) {
			observers.add(observer);
		} 
	}
	/**
	 * Removes given observer
	 * @param observer observer to be removed
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		// remove the observer from observers if present ...
		if(observers.contains(observer)) {
			observers.remove(observer);
		}
	}
	/**
	 * Removes all observers
	 */
	public void clearObservers() {
		// remove all observers from observers list ...
		observers.clear();
	}
	/**
	 * Getter for current value
	 * @return
	 */
	public int getValue() {
		return value;
	}
	/**
	 * Setter for current value. It also notifies all the observers
	 * that change occurred.
	 * @param value
	 */
	public void setValue(int value) {
		// Only if new value is different than the current value:
		if(this.value != value) {
			// Update current value
			this.value = value;
			// Notify all registered observers
			if(observers != null) {
				//first implementation
//				for(IntegerStorageObserver observer : observers) {
//					observer.valueChanged(this);
//					
//				}
				//I do not want concurrent modification
				int firstSize = observers.size();
				for(int i = 0; i < firstSize; ) {
					observers.get(i).valueChanged(this);
					if(firstSize != observers.size()) {
						firstSize--;
					} else {
						i++;
					}
				}
			}
		}
	}

}
