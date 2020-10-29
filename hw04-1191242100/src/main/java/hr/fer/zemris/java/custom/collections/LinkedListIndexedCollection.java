package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
/**
 * Implementation of linked list-backed collection of objects that extends <code>Collection</code>
 * and overrides methods which were empty there. 
 * Null references are not allowed to be stored
 * @author Luka Karlić
 */
public class LinkedListIndexedCollection<T> implements List<T> {
	
	/**
	 * Represents one node in the linked list. It has references to previous and next node and has its own value. 
	 */
	private static class ListNode<T> {
		ListNode<T> previous;
		ListNode<T> next;
		T value;
	}
	/**
	 * 
	 */
	private long modificationCount = 0;
	
	/**
	 * Current size of collection (number of elements actually stored; number of nodes in list)
	 */
	private int size;
	
	/**
	 * Reference to the first node of the linked list
	 */
	private ListNode<T> first;
	
	/**
	 *  reference to the last node of the linked list
	 */
	private ListNode<T> last;
	
	
	/**
	 * Creates new empty linked list.
	 */
	public LinkedListIndexedCollection() {
		first = last = null;
		size = 0;
	}
	
	/**
	 * Creates new linked list and adds all elements from <code>Collection other</code>
	 * @param other <code>Collection</code> whose elements are copied into this newly constructed collection.
	 */
	public LinkedListIndexedCollection(Collection<? extends T> other) {
		this();
		addAll(other);
	}
	
	
	/**
	 * Adds the given object into this collection at the end of collection.
	 * Newly added element becomes the element at the biggest index and it is implemented in constant time.
	 * @throws NullPointerException if <code>value == null</code>
	 */
	@Override
	public void add(T value) {
		if(value == null) {
			throw new NullPointerException("Storage of null references is not allowed!");
		}
		ListNode<T> novi = new ListNode<T>();
		novi.value = value;
		size++;
		
		if(first == null) { //u slucaju da je vezana lista prazna
			first = novi;
			last = novi;
			return;
		}
		novi.previous = last;
		last.next = novi;
		last = novi;
		
	}
	
	/**
	 * Helping method for checking if the given <code>index</code> is valid.
	 * @param index to be checked
	 * @param inserting == <code>true</code> if we are going to use this <code>index</code> for inserting elements,
	 * <code>false</code> otherwise (i.e. for removing object)
	 * @return <code>true</code> if <code>index</code> is in interval <code>0 to size</code> for inserting or in interval
	 * <code>0 to size - 1</code> for removing, <code>false</code> otherwise
	 */
	private boolean validIndex(int index, boolean inserting) { //pomocna da se ne duplicira kod
		//boolean remove = true akko se koristi za ubacivanje, inace 0
		if(inserting) {
			if(index < 0 || index > size) {
				return false;
			}
			return true;
		}
		if(index < 0 || index >= size) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns object on the given position in linked list.
	 * It never has the complexity greater than n/2+1 (<code>n</code> is size of the current linked list)

	 * @param index of the element to return
	 * @return object on the given position
	 * @throws IndexOutOfBoundsException if <code>index</code> is not from interval <code>0 to size - 1</code>
	 */
	public T get(int index) { //paziti na slozenost
		if(!validIndex(index, false)) {
			throw new IndexOutOfBoundsException("Legal indexes are 0 to " + (size - 1));
		}
		
		if((size / 2) < index) { //index je blize kraju
			//int stepsBackwards = size - index;
			ListNode<T> temporary = last;
			for(int i = size - 1; i > index; i--) {
				temporary = temporary.previous;
			}
			return temporary.value; //trenutno se nalazimo na trazenom elementu
		}
		ListNode<T> temporary = first;
		for(int i = 0; i < index; i++) {
			temporary = temporary.next;
		}
		return temporary.value;
	}
	
	@Override
	public void clear() { //nije nuzno sve elemente postaviti na null
		first = last = null;
		size = 0;
	}
	
	/**
	 * Inserts(does not overwrite) <code>value</code>on the position of the <code>index</code>.
	 * Elements starting from this position are shifted one position.
	 * It is not same if object is inserted in the to the beginning of linked list, in the middle or to the end.
	 * @param value to be inserted
	 * @param index where it will be inserted
	 * @throws IndexOutOfBoundsException if index is outside interval 0 to <code>size</code>
	 * @throws NullPointerException if <code>value == null</code>
	 */
	public void insert(T value, int position) {			
		if(value == null) {
			throw new NullPointerException("Storage of null references is not allowed"); 
		}
		
		if(!validIndex(position, true)) {
			throw new IndexOutOfBoundsException("Legal positions are 0 to " + size);
		}
		
		if(size == 0 || size == position) { //ili na kraj, ili u praznu listu
			add(value);
			size++;
			return;
		}
		ListNode<T> element = new ListNode<T>();
		element.value = value;
		if(position == 0) { //ubacivanje na pocetak liste
			ListNode<T> oldFirst = first;
			first = element;
			first.next = oldFirst;
			if(last == null) {
				last = first;
			}
		} else {
			//ubacivanje u sredinu
			ListNode<T> temporary = first;
			for(int i = 0; i < position; i++) {
				temporary = temporary.next;
			}
			element.next = temporary;
			element.previous = temporary.previous;
			temporary.previous.next = element; 
			temporary.previous = element;
		}
		size++;
	}
	
	/**
	 * Searches linked list and returns the index of the first occurrence of the given value or -1 if the value is
	 * not found. Argument can be null and the result must be that this element is not found
	 * @param value value that we want to find
	 * @return -1 if the value is not found, otherwise index of first occurrence
	 */
	public int indexOf(Object value) {
		if(value == null || size == 0) {
			return -1; 
		}
		ListNode<T> temporary = first;
		for(int index = 0; index < size; index++, temporary = temporary.next) {
			if(temporary.value.equals(value)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * Removes value in the linked list stored at given <code>index</code>.
	 * @param index of element that will be removed
	 * @throws IndexOutOfBoundsException if <code>index</code> is outside the correct interval (0 to <code>size</code> -1)
	 */
	public void remove(int index) {
		if(!validIndex(index, false)) {
			throw new IndexOutOfBoundsException("Legal indexes are 0 to " + (size - 1));
		}
		if(index == 0) {
			first = first.next;
			first.previous = null;
		} else if(index == (size - 1)) {
			last = last.previous;
			last.next = null;
		} else {
			ListNode<T> temporary = first;
			for(int i = 0; i < index; i++) { 
				temporary = temporary.next;
			}
			ListNode<T> copyOfTemporaryPrevious = temporary.previous;
			ListNode<T> copyOfTemporaryNext = temporary.next;
			copyOfTemporaryNext.previous = copyOfTemporaryPrevious;
			copyOfTemporaryPrevious.next = copyOfTemporaryNext; 
		}
		--size;
	}
	
	@Override
	public boolean remove(Object value) {
		if(indexOf(value) == -1) {
			return false;
		}
		remove(indexOf(value));
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(Object value) {
		return indexOf(value) != -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] newArray = (T[])new Object[size];
		ListNode<T> temporary = first;
		for(int i = 0; i < size; i++) {
			newArray[i] = temporary.value;
			temporary = temporary.next;
		}
		return newArray;
	}

	private static class LinkedListElementsGetter<T> implements ElementsGetter<T> {
		
		private LinkedListIndexedCollection<T> collection;
		private long savedModificationCount;
		private ListNode<T> currentNode;
		
		public LinkedListElementsGetter(LinkedListIndexedCollection<T> collection) {
			//super();
			this.collection = collection;
			this.savedModificationCount = collection.modificationCount;
			this.currentNode = collection.first;
		}

		@Override
		public boolean hasNextElement() {

			return currentNode.next != null;
		}
		
		@Override
		public T getNextElement() {
			
			if(savedModificationCount != collection.modificationCount) {
				throw new ConcurrentModificationException("Current collection is being structurally modified.");
			}
			
			if(hasNextElement()) {
				T element = currentNode.value;
				currentNode = currentNode.next;
				return element;
			} else {
				throw new NoSuchElementException("All elements have been sent.");
			}
		}
	}

	@Override
	public ElementsGetter<T> createElementsGetter() {
		
		return new LinkedListElementsGetter<T>(this);
	}
}
