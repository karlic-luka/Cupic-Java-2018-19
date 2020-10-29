/**
 * 
 */
package hr.fer.zemris.java.custom.collections;

/**
 * Interface for collections that can be interpreted as lists (i.e. <code>ArrayIndexedCollection, LinkedListIndexedCollection</code>)
 * @author Luka Karlić
 */
public interface List<T> extends Collection<T> {

	/**
	 * Returns the object that is stored in list at position index. Valid indexes are 0 to size-1. If index
	 * is invalid, the implementation should throw the appropriate exception (IndexOutOfBoundsException). 
	 * @param index index of element that we want to get
	 * @return <code>Object</code> on given index
	 * @throws IndexOutOfBoundsException if index is not in valid interval
	 */
	T get(int index);
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in list (observe that before actual
	 * insertion elements at position and at greater positions must be shifted one place toward the end, so that an 
	 * empty place is created at position). The legal positions are 0 to size (both are included). 
	 * @param value value to be inserted
	 * @param position position where it will be inserted
	 * @throws IndexOutOfBoundsException if given position was invalid
	 */
	void insert(T value,  int position);
	
	/**
	 * Searches the list and returns the index of the first occurrence of the given value or -1 if the value is not found.
	 * @param value value to be checked
	 * @return index of given value or -1 if it does not exist in list
	 */
	int indexOf(Object value);
	
	/**
	 * Removes element at specified index from collection. Element that was previously at location index+1 
	 * after this operation is on location index, etc. Legal indexes are 0 to size-1. 
	 * @param index index of element that will be removed
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	void remove(int index);
	
}
