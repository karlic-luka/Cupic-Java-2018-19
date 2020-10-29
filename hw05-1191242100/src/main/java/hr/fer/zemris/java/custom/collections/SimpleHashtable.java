/**
 * 
 */
package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Luka
 *
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {

	/**
	 * default number of slots
	 */
	private static final int DEFAULT_CAPACITY = 16;
	
	/**
	 * constant double that will be used for checking if 
	 * occupancy is over that number in percentage 
	 */
	private static final double OCCUPANCY = 0.75;
	
	/**
	 * slots table
	 */
	private TableEntry<K, V>[] table;
	
	/**
	 * current size of hash table
	 */
	private int size;
	
	private int filledSlots = 0;
	
	/**
	 * Flag that increments if inside structure is changed(i.e. new node is added)
	 */
	private long modificationCount = 0;
	
	/**
	 * constructor with default number of slots
	 */
	public SimpleHashtable() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructor with desired number of slots - <code>capacity</code>
	 * actual capacity will be the first power of 2 greater than <code>capacity</code>
	 * @param capacity desired capacity
	 * @throws IllegalArgumentException if capacity < 0
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity) {
		if(capacity < 0) {
			throw new IllegalArgumentException("Capacity can not be negative number.");
		}
		size = 0;
		table = (TableEntry<K, V>[])new TableEntry[evaluateNewCapacity(capacity)];
	}
	
	/**
	 * Returns first power of 2 greater than parameter <code>size</code>
	 * @param size wanted size
	 * @return first power of 2 greater than <code>size</code>
	 */
	private int evaluateNewCapacity(int size) {
		int copySize = size;
		int newSize = 1;
		copySize /= 2;
		while(copySize > 0) {
			copySize /= 2;
			newSize *= 2;
		}
		return size == newSize ? newSize : newSize * 2;
	}

	/**
	 * Returns index of slot where <code>key</code> will be put.
	 * index = key.hashCode % numberOfSlots
	 * @param key key to be put in table
	 * @return index of slot
	 */
	private int decideSlot(Object key) {
		return Math.abs(key.hashCode()) % table.length;
	}
	
	@SuppressWarnings("unchecked")
	private void realocateTable() {
		TableEntry<K, V>[] copyOfOldTable = table;
		int oldSize = size;
		table = (TableEntry<K, V>[])new TableEntry[copyOfOldTable.length * 2];
		for(int i = 0, oldLength = copyOfOldTable.length; i < oldLength; i++) {
			TableEntry<K, V> slot = copyOfOldTable[i];
			while(slot != null) {
				put(slot.key, slot.value);
				slot = slot.next;
			}	
		}
		copyOfOldTable = null;
		size = oldSize;
		//do not need to increment modificationCount because this method
		//calls "put" that increments it
	}
	
	public void clear() {
		for(int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		size = 0;
		modificationCount++;
		filledSlots = 0;
	}
	
	/**
	 * Adds new pair (key, value) into the table on the last place in
	 * the belonging slot.
	 * If adding new element means that our table is at least 75% full,
	 * it will be resized to doubled size.
	 * @param key
	 * @param value
	 * @throws NullPointerException if <code>key == null</code>
	 */
	public void put(K key, V value) {
		if(key == null) {
			throw new NullPointerException("Key can not be null");
		}
		if(filledSlots >= OCCUPANCY * table.length ) {
			realocateTable();
		}
		
		int slotIndex = decideSlot(key);
		TableEntry<K, V> slot = table[slotIndex];
		if(slot == null) {
			table[slotIndex] = new TableEntry<K, V>(key, value, null);
			size++;
			modificationCount++;
			filledSlots++;
			return;
		}
		//slot was not empty
		while(slot != null) {
			if(slot.key.equals(key)) {
				slot.setValue(value);
				return;
			} else if(slot.next == null) {
				slot.next = new TableEntry<K, V>(key, value, null);
				size++;
				modificationCount++;
				return;
			}
			slot = slot.next;
		}
	}
	
	/**
	 * returns value that is paired up with <code>key</code>
	 * @param key
	 * @return value paired up with <code>key</code>
	 */
	public V get(Object key) {
		if(key == null || !containsKey(key)) {
			return null;
		}
		int slotIndex = decideSlot(key);
		TableEntry<K, V> slot = table[slotIndex];
		while(slot != null) {
			if(slot.key.equals(key)) {
				return slot.value;
			}
			slot = slot.next;
		}
		return null;
	}
	
	/**
	 * returns <code>true</code> if table contains <code>key</code>,
	 * <code>false</code> otherwise
	 * @param key key to be checked
	 * @return <code>true</code> if table contains <code>key</code>,
	 * <code>false</code> otherwise
	 */
	public boolean containsKey(Object key) {
		if(key == null) {
			return false;
		}
		int slotIndex = decideSlot(key);
		TableEntry<K, V> slot = table[slotIndex];
		while(slot != null) {
			if(slot.key.equals(key)) {
				return true;
			}
			slot = slot.next;
		}
		return false;
	}
	
	/**
	 * returns <code>true</code> if table contains <code>value</code>,
	 * <code>false</code> otherwise
	 * @param key key to be checked
	 * @return <code>true</code> if table contains <code>value</code>,
	 * <code>false</code> otherwise
	 */
	public boolean containsValue(Object value) {
		for(TableEntry<K, V> slot : table) {
			while(slot != null) {
				if(slot.value.equals(value)) {
					return true;
				}
				slot = slot.next;
			}
		}
		return false;
	}
	
	/**
	 * removes pair that belongs to <code>key</code>
	 * @param key key to be removed
	 */
	public void remove(Object key) {
		if(key == null || !containsKey(key)) {
			return;
		}
		int slotIndex = decideSlot(key);
		//1.if first node has our key
		if(table[slotIndex].key.equals(key)) {
			table[slotIndex] = table[slotIndex].next;
			size--;
			modificationCount++;
			filledSlots--;
			return;
		}
		//it is not first node
		TableEntry<K, V> slot = table[slotIndex];
		while(slot.next != null) {
			if(slot.next.key.equals(key)) {
				slot.next = slot.next.next;
				size--;
				modificationCount++;
				return;
			}
			slot = slot.next;
		}
	}

	@Override
	public String toString() {
		if(isEmpty()) {
			return "[]";
		}
		String string = new String("[");
		for(TableEntry<K, V> slot : table) {
			while(slot != null) {
				string += slot.toString() + ", ";
				slot = slot.next;
			}
		}
		//I need to remove last ", ";
		return string.substring(0, string.length() - 2) + "]";
	}
	
	/**
	 * returns current size of the table
	 * @return size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 
	 * @return <code>true</code> if table is empty, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * returns capacity of slots table
	 * @return capacity of slots table
	 */
	public int tableCapacity() {
		return table.length;
	}
	
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}
	
	/**
	 * implements iterator for our SimpleHashTable
	 * @author Luka
	 *
	 * @param <K> type for key
	 * @param <V> type for value
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>> {

		/**
		 * current pair
		 */
		private TableEntry<K, V> currentPair;
		/**
		 * next pair
		 */
		private TableEntry<K, V> nextPair;
		/**
		 * current index of slot table
		 */
		private int currentIndexOfSlot;
		/**
		 * true if we can remove current element, false otherwise
		 */
		private boolean canRemove = false;
		/**
		 * saved modification count
		 */
		private long savedModificationCount;
		
		/**
		 * constructor
		 */
		public IteratorImpl() {
			//super();
			currentIndexOfSlot = 0;
			currentPair = null;
			nextPair = null;
			savedModificationCount = modificationCount;
			//I need to call this method in constructor
			//because I put everything to null reference
			if(size > 0) {
				goToNextSlot();
			}
		}

		/**
		 * goes to the next non-empty slot in table slot
		 */
		private void goToNextSlot() {
			while(currentIndexOfSlot < table.length && nextPair == null) {
				nextPair = table[currentIndexOfSlot++];
			}
		}
		
		@Override
		public boolean hasNext() {
			return nextPair != null;
		}

		@Override
		public TableEntry<K, V> next() {
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException("You can not iterate while hash table is being restructured.");
			}
			
			if(!hasNext()) {
				throw new NoSuchElementException("I gave you everything I have!");
			}
			currentPair = nextPair;
			nextPair = currentPair.next;
			if(nextPair == null) {
				//we need to change slot 
				goToNextSlot();
			}
			canRemove = true;
			return currentPair;
		}
		
		/**
		 * removes current pair
		 * @throws ConcurrentModificationException if table is currently being restructured
		 * @throws IllegalStateException if we try to remove element more than one time in a row
		 * 			or we try to remove before calling next
		 */
		public void remove() {
			if(savedModificationCount != modificationCount) {
				throw new ConcurrentModificationException("You can not iterate while hash table is being restructured.");
			}
			
			if(!canRemove) {
				throw new IllegalStateException("It can not be removed before next is called");
			}
			SimpleHashtable.this.remove(currentPair.key);
			//it needs to be updated!
			savedModificationCount = modificationCount;
			canRemove = false;
		}
	}
	
	/**
	 * represents one node in our table (key, value, nextPointer)
	 * @author Luka
	 *
	 * @param <K> type for key
	 * @param <V> type for value
	 */
	public static class TableEntry<K, V> {
		
		/**
		 * key
		 */
		private K key;
		/**
		 * value
		 */
		private V value;
		/**
		 * pointer to next node in the same slot
		 */
		private TableEntry<K, V> next;
		
		/**
		 * constructor for node
		 * @param key
		 * @param value
		 * @param next
		 * @throws NullPointerException if <code>key == null</code>
		 */
		public TableEntry(K key, V value, TableEntry<K, V> next) {
			super();
			if(key == null) {
				throw new NullPointerException("Key can not be null");
			}
			this.key = key;
			this.value = value;
			this.next = next;
		}

		/**
		 * @return the value
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * @param value the value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * @return the key
		 */
		public K getKey() {
			return key;
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}
	}
}
