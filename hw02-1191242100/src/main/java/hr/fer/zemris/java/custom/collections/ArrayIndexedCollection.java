package hr.fer.zemris.java.custom.collections;

/**
 *Implementation of resizable array-backed collection of objects which extends <code>Collection</code>.
 *Implements a lot of new methods and overrides inherited ones.
 * @author Luka
 *
 */
public class ArrayIndexedCollection extends Collection {
	
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
	private Object[] elements;
	
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
	public ArrayIndexedCollection(int initialCapacity) { //drugi konstruktor
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("Array should have at least one element.");
		}
		this.elements = new Object[initialCapacity];
		this.size = 0;
	}
	
	/**
	 * Creates new collection initially filled with objects from <code>Collection other</code> 
	 * @param other Collection whose elements are copied.
	 * @throws NullPointerException if <code>other</code> is <code>null</code>
	 */
	public ArrayIndexedCollection(Collection other) { //treci konstruktor
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
	public ArrayIndexedCollection(Collection other, int initialCapacity) { //cetvrti konstruktor
		this(initialCapacity);
		addAll(other); 
	}
	
	/**
	 * Helping method that doubles the capacity and copies old values.
	 * @param elements array to be doubled
	 * @return array of objects <code>elements</code> with doubled capacity than before
	 */
	private Object[] enlarge(Object[] elements) {
		Object[] bigger = new Object[2 * elements.length];
		for(int i = 0; i < elements.length; i++) {
			bigger[i] = elements[i];
		}
		return bigger;
	}
	
	@Override
	public void add(Object value) {
		if(value == null) {
			throw new NullPointerException("Storage of null references is not allowed!");
		}
		if(elements.length == size) {
			elements = enlarge(elements);
		}
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
		size--;
	}
	
	@Override
	public boolean remove(Object value) {
		if(-1 == indexOf(value)) {
			return false;
		}
		remove(indexOf(value));
		return true;
	}
	
	/**
	 * Returns object on the given position in array
	 * @param index of the element to return
	 * @return object on the given position
	 * @throws IndexOutOfBoundsException if <code>index</code> is not from interval <code>0 to size - 1</code>
	 */
	public Object get(int index) {
		if(!validIndex(index, false)) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}
	
	@Override
	public void forEach(Processor processor) {
		for(int i = 0; i < size; i++) {
			processor.process(elements[i]);
		}
	}
	
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	/**
	 * Inserts(does not overwrite) <code>value</code> on the position of the <code>index</code>.
	 * Elements starting from this position are shifted one position.
	 * @param value to be inserted
	 * @param index where it will be inserted
	 * @throws IndexOutOfBoundsException if index is outside interval 0 to <code>size</code>
	 * @throws NullPointerException if <code>value == null</code>
	 */
	public void insert(Object value, int index) {
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
	
	@Override
	public Object[] toArray() {
		Object[] novi = new Object[size];
		for(int i = 0; i < size; i++) {
			novi[i] = elements[i];
		}
		return novi;
	}
	
}
