package hr.fer.zemris.java.custom.collections;

/**
 * Interface that will be used for getting elements in various collections.
 * @author Luka Karlić
 */
public interface ElementsGetter {
	
	/**
	 * Method that returns <code>true</code> if collection still contains elements that have not been sent 
	 * to user, <code>false</code> otherwise.
	 * @return <code>true</code> if there are still unsent elements from collection, <code>false</code> otherwise
	 */
	boolean hasNextElement();
	
	/**
	 * Sends user next element from collection that has not been sent yet.
	 * @return
	 */
	Object getNextElement();
	
	/**
	 * Method that will call method <code>process</code> on every element that still
	 * has not been sent to user.
	 * @param p Processor that will execute processing
	 */
	default void processRemaining(Processor p) {
		
		ElementsGetter getter = this;
		while(getter.hasNextElement()) {
			p.process(getter.getNextElement());
		}
	}
}
