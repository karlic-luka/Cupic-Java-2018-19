/**
 * 
 */
package hr.fer.zemris.java.custom.collections;
/**
 * Type of collection that has ordered pair of objects (key, value).
 * Key is not allowed to be null, while value can be.
 * @author Luka Karlić
 */
public class Dictionary<K, V> {

	/**
	 * Adapter that will contain our ordered pairs.
	 */
	private ArrayIndexedCollection<OrderedPair<K, V>> adaptee;

	public Dictionary() {
		adaptee = new ArrayIndexedCollection<OrderedPair<K,V>>();
	}
	
	/**
	 * Method that checks if dictionary is empty
	 * @return <code>true</code> if dictionary is empty, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		return adaptee.size() == 0;
	}
	
	/**
	 * @return current number of ordered pairs in dictionary
	 */
	public int size() {
		return adaptee.size();
	}
	
	/**
	 * deletes all pairs in dictionary
	 */
	public void clear() {
		adaptee.clear();
	}
	
	/**
	 * Returns index of <code>key</code> or -1 if it does not exist
	 * @param key key to be checked
	 * @return index of key or -1 if it does not exist in dictionary
	 */
	private int getKey(Object key) {
		ElementsGetter<OrderedPair<K, V>> getter = adaptee.createElementsGetter();
		int index = 0;
		while(getter.hasNextElement()) {
			if(getter.getNextElement().key.equals(key)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	/**
	 * Puts ordered pair (key, value) into dictionary. Key can not be null, but
	 * value can.
	 * @param key
	 * @param value
	 * @throws IllegalArgumentException if key is null
	 */
	public void put(K key, V value) {
		if(key == null) {
			throw new NullPointerException("Key can not be null.");
		}
		int index = getKey(key);
		OrderedPair<K, V> newPair = new OrderedPair<>(key, value);
		try {
			//maybe key is in dictionary
			//getKey returns -1 if it does notgetKey(key)) exist
			adaptee.insert(newPair, index);
			//now we need to remove pair with one index later
			//because "insert" does not overwrite, but shifts
			//everything 
			adaptee.remove(index + 1);
		} catch(IndexOutOfBoundsException ex) {
			//if it got here, it means that K key does not exist in our dictionary
			//so we can just add it
			adaptee.add(newPair);
		}
	}
	
	/**
	 * Gets value paired up with given <code>key</code>
	 * @param key
	 * @return value paired up with 
	 */
	public V get(Object key) {
		int index = getKey(key);
		if(index != -1) { 
			return adaptee.get(index).value; //value can be null
		}
		//key does not exist
		return null;
	}
	
	/**
	 * Parameterised class that represents one ordered pair in our dictionary.
	 * It looks like this: (key, value)
	 * @author Luka Karlić
	 * @param <K> type of key
	 * @param <V> type of value
	 */
	private static class OrderedPair<K, V> {
		
		/**
		 * Key in one pair in dictionary
		 */
		private K key;
		
		/**
		 * Value of one pair in dictionary
		 */
		private V value;
		
		/**
		 * Constructor
		 * @param key
		 * @param value
		 */
		public OrderedPair(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}

		/**
		 * Getter
		 * @return the value
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * Setter for value
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
			return "(" + key + ", " + value +")";
		}
	}
}
