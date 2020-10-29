package hr.fer.zemris.java.custom.collections;

import java.util.Objects;

/**
 * Interface for different kind of storage collections.
 * @author Luka Karlić
 *
 */
public interface Collection<T> {
	
	/**
	 * Returns true if collection contains no objects and false otherwise. 
	 * @return <code>true</code> if collection is empty, <code>false</code> otherwise
	 */
	default boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the number of currently stored objects in this collection.
	 * @return number of stored elements in collection
	 */
	int size();
	
	/**
	 * Adds the given object into this collection.
	 * @param value to be added
	 */
	void add(T value);
	
	/**
	 * 
	 * @param value that will be checked
	 * @return true if collection contains <code>value</code> as determined by equals() method,
	 *  <code>false</code> otherwise. Here it always returns false, but it will be overridden
	 */
	boolean contains(Object value);
	
	/**
	 * 
	 * @param value to be removed
	 * @return <code>true</code> only if the collection contains given value as determined by equals method and removes 
	 * one occurrence of it (in this class it is not specified which one).
	 */
	boolean remove(Object value);
	
	/**
	 * Allocates new array with size equals to the size of this collections, fills it with collection content and 
	 * returns the array. This method never returns null.
	 * @return array of objects in collection
	 * @throws UnsupportedOperationException always
	 */
	T[] toArray();
	
	/**
	 * Method calls processor.process(.) for each element of this collection. The order in which elements
	 * will be sent is undefined in this class. 
	 * @param processor whose process will be executed
	 */
	default void forEach(Processor<? super T> processor) {
		
		ElementsGetter<T> getter = this.createElementsGetter();
		
		while(getter.hasNextElement()) {
			processor.process(getter.getNextElement());
		}
	}
	
	/**
	 * Method adds into the current collection all elements from the given collection. This other collection
	 * remains unchanged. Here it is implemented to define a local processor class whose method process will add each
	 * item into the current collection by calling method add, and then call forEach on the other collection with this
	 * processor as argument.
	 * @param other Collection that will be fully added.
	 */
	default void addAll(Collection<? extends T> other) {
		Objects.requireNonNull(other);
		class LokalniProcesor implements Processor<T>{
			
			@Override
			public void process(T value) {
				Collection.this.add(value);
			}
		}
		LokalniProcesor lokalni = new LokalniProcesor();
		other.forEach(lokalni); 
	}
	
	/**
	 * Removes all elements from this collection. Here it is empty method, but it will be overridden.
	 */
	void clear();
	
	/**
	 * 	/**
	 * Method that creates <code>ElementsGetter</code>.
	 * @return getter for elements of collection
	 */
	ElementsGetter<T> createElementsGetter();
	
	default void addAllSatisfying(Collection<T> col, Tester<T> tester) {
		Objects.requireNonNull(col);
        Objects.requireNonNull(tester);
		ElementsGetter<T> getter = col.createElementsGetter();
		while(getter.hasNextElement()) {
			
			T element = getter.getNextElement();
			if(tester.test(element)) {
				this.add(element);
			}
		}
	}
}
