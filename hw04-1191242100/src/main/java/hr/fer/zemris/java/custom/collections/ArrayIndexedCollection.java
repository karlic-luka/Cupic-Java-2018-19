package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 *Implementation of resizable array-backed collection of objects which extends <code>Collection</code>.
 *Implements a lot of new methods and overrides inherited ones.
 * @author Luka
 *
 */
public class ArrayIndexedCollection<T> implements List<T> {
	
	/**
	 * 
	 */
	private long modificationCount = 0;
	
	/**
	 * Constant that represents default capacity of the collection.
	 */
	private static final int DEFAULT_CAPACITY = 16; //ne zelimo magicni broj
	
	/**
	 *Current size of collection (number of elements actually stored in elements array),
	 */
	private int size;
	
	/**
	 * An array of object references that will be stored. Capacity is length of this array.
	 * Null references are not allowed for storage.
	 */
	private T[] elements;
	
	/**
	 * Creates empty collection with default capacity (16)
	 */
	public ArrayIndexedCollection() { //prvi konstruktor
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Creates empty collection with <code>initialCapacity</code>.
	 * @param initialCapacity capacity of the collection
	 * @throws IllegalArgumentException if <code>initialCapacity</code> is lower than 1
	 */ 
	@SuppressWarnings("unchecked")
	public ArrayIndexedCollection(int initialCapacity) { //drugi konstruktor
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("Array should have at least one element.");
		}
		this.elements = (T[])new Object[initialCapacity];
		this.size = 0;
	}
	
	/**
	 * Creates new collection initially filled with objects from <code>Collection other</code> 
	 * @param other Collection whose elements are copied.
	 * @throws NullPointerException if <code>other</code> is <code>null</code>
	 */
	public ArrayIndexedCollection(Collection<? extends T> other) { //treci konstruktor
		this(other, other.size());
	}
	
	/**
	 * Creates new collection initially filled with objects from <code>Collection other</code> 
	 * and capacity is set to <code>initialCapacity</code>
	 * @param other Collection which elements are copied
	 * @param initialCapacity capacity of new array
	 * @throws NullPointerException if the collection <code>other</code> is <code>null</code>
	 * @throws IllegalArgumentException if the initial capacity is lower than 0.
	 */
	public ArrayIndexedCollection(Collection<? extends T> other, int initialCapacity) { //cetvrti konstruktor
		this(initialCapacity);
		addAll(other); 
	}
	
	/**
	 * Helping method that doubles the capacity and copies old values.
	 * @param elements array to be doubled
	 * @return array of objects <code>elements</code> with doubled capacity than before
	 */
	@SuppressWarnings("unchecked")
	private T[] enlarge(T[] elements) {
		T[] bigger = (T[])new Object[2 * elements.length];
		for(int i = 0; i < elements.length; i++) {
			bigger[i] = elements[i];
		}
		modificationCount++;
		return bigger;
	}
	
	@Override
	public void add(T value) {
		if(value == null) {
			throw new NullPointerException("Storage of null references is not allowed!");
		}
		if(elements.length == size) {
			elements = enlarge(elements);
		}
		modificationCount++;
		elements[size++] = value; //zelimo na pravo mjesto staviti novi element, ali i povecati trenutnu velicinu polja
	}
	
	/**
	 * Helping method that checks if given <code>index</code> is valid for inserting or removing
	 * @param index to be checked
	 * @param inserting flag that is set to <code>true</code> if it is going to be used for inserting in array
	 * (valid indexes are 0 to <code>size</code>), otherwise used for removing i.e. <code>false</code>
	 *  (valid indexes 0 to <code>size</code>-1)
	 * @return <code>true</code> if index is inside interval, <code>false</code> otherwise.
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
	 * Removes value stored at <code>index</code>
	 * @param index of element that will be removed
	 * @throws IndexOutOfBoundsException if <code>index</code> is outside the correct interval (0 to <code>size</code> -1)
	 */
	public void remove(int index) {
		if(!validIndex(index, false)) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		modificationCount++;
		size--;
	}
	
	@Override
	public boolean remove(Object value) {
		if(-1 == indexOf(value)) {
			return false;
		}
		remove(indexOf(value));
		modificationCount++;
		return true;
	}
	
	/**
	 * Returns object on the given position in array
	 * @param index of the element to return
	 * @return object on the given position
	 * @throws IndexOutOfBoundsException if <code>index</code> is not from interval <code>0 to size - 1</code>
	 */
	public T get(int index) {
		if(!validIndex(index, false)) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}
	
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
		modificationCount++;
	}
	
	/**
	 * Inserts(does not overwrite) <code>value</code> on the position of the <code>index</code>.
	 * Elements starting from this position are shifted one position.
	 * @param value to be inserted
	 * @param index where it will be inserted
	 * @throws IndexOutOfBoundsException if index is outside interval 0 to <code>size</code>
	 * @throws NullPointerException if <code>value == null</code>
	 */
	public void insert(T value, int index) {
		if(!validIndex(index, true)) {
			throw new IndexOutOfBoundsException();
		}
		if(value == null) {
			throw new NullPointerException("Storage of null-references is not allowed.");
		}
		if(size + 1 >= elements.length) {
			elements = enlarge(elements);
		}
		for(int i = size - 1 ; i >= index; i--) { 
			elements[i + 1] = elements[i];
		}
		elements[index] = value;
		size++;
		modificationCount++;
	}
	
	/**
	 * Searches the collection and returns the index of the first occurrence of the given value or -1 if the value is
	 * not found. Argument can be null and the result must be that this element is not found
	 * @param value
	 * @return -1 if the value is not found, otherwise index of first occurrence
	 */
	public int indexOf(Object value) {
		if(value == null) {
			return -1;
		}
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) {
				return i;
			}
		}
		return -1;
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
		return -1 != indexOf(value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] novi = (T[])new Object[size];
		for(int i = 0; i < size; i++) {
			novi[i] = elements[i];
		}
		return novi;
	}

	private static class ArrayIndexedElementsGetter<T> implements ElementsGetter<T> {

		private ArrayIndexedCollection<T> collection;
		private long savedModificationCount;
		private int sentElements;
		private int collectionSize;

		public ArrayIndexedElementsGetter(ArrayIndexedCollection<T> collection) {
			//super(); - TREBA LI MI?
			this.collection = collection;
			this.sentElements = 0;
			this.collectionSize = collection.size();
			this.savedModificationCount = collection.modificationCount;
		}

		@Override
		public boolean hasNextElement() {

			return sentElements < collectionSize;
		}

		@Override
		public T getNextElement() {
			
			if(savedModificationCount != collection.modificationCount) {
				throw new ConcurrentModificationException("Current collection is being structurally modified.");
			}
			
			if(hasNextElement()) {//zapravo korektnije je mozda this.hasNextElement();
				T element = collection.get(sentElements++);
				return element;
			} else {
				throw new NoSuchElementException("All elements have been sent.");
			}
		}
	}

	@Override
	public ElementsGetter<T> createElementsGetter() {

		return new ArrayIndexedElementsGetter<T>(this);
	}
	
}
