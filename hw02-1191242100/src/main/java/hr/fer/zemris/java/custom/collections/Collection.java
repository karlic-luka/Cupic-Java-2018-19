package hr.fer.zemris.java.custom.collections;


/**
 * This class represents general collection of objects and it will be our interface later. 
 * @author Luka Karlić
 *
 */
public class Collection {
	
	/**
	 * Protected default constructor that does nothing here.
	 */
	protected Collection() {
	
	}
	
	/**
	 * Returns true if collection contains no objects and false otherwise. Here it always returns false through the size()
	 * method, but it is supposed to be overridden.
	 * @return <code>true</code> if collection is empty, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		if(this.size() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of currently stored objects in this collections. Here it always returns 0, but
	 * it is supposed to be overridden.
	 * @return always 0
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * Adds the given object into this collection. Here it does nothing, but it is supposed to be overridden.
	 * @param value to be added
	 */
	public void add(Object value) {
		
	}
	
	/**
	 * 
	 * @param value that will be checked
	 * @return true if collection contains <code>value</code> as determined by equals() method,
	 *  <code>false</code> otherwise. Here it always returns false, but it will be overridden
	 */
	public boolean contains(Object value) {
		return false;
	}
	
	/**
	 * 
	 * @param value to be removed
	 * @return <code>true</code> only if the collection contains given value as determined by equals method and removes 
	 * one occurrence of it (in this class it is not specified which one). Here it always returns <code>false</code>, but
	 * it will be overridden.
	 */
	public boolean remove(Object value) {
		return false;
	}
	
	/**
	 * Allocates new array with size equals to the size of this collections, fills it with collection content and 
	 * returns the array. This method never returns null. It is supposed to be overridden later.
	 * @return array of objects in collection
	 * @throws UnsupportedOperationException always
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Method calls processor.process(.) for each element of this collection. The order in which elements
	 * will be sent is undefined in this class. It will be overridden for each Collection
	 * @param processor whose process will be executed
	 */
	public void forEach(Processor processor) {

	}
	
	/**
	 * Method adds into the current collection all elements from the given collection. This other collection
	 * remains unchanged. Here it is implemented to define a local processor class whose method process will add each
	 * item into the current collection by calling method add, and then call forEach on the other collection with this
	 * processor as argument. You must define this new class directly in 
	 * the method addAll (such classes are called local classes). 
	 * 
	 * @param other Collection that will be fully added.
	 */
	public void addAll(Collection other) {
		
		class LokalniProcesor extends Processor{
			
			@Override
			public void process(Object value) {
				Collection.this.add(value);
			}
		}
		LokalniProcesor lokalni = new LokalniProcesor();
		other.forEach(lokalni); 
	}
	
	/**
	 * Removes all elements from this collection. Here it is empty method, but it will be overridden.
	 */
	public void clear() {
		
	}
}
