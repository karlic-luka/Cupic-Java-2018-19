package hr.fer.zemris.java.custom.scripting.exec;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  ObjectMultistack allows the user to
 *  store multiple values for same key and it must provides a stack-like abstraction.
 *  Keys for ObjectMultistack will be instances of the class String.
 *  Values that are associated with keys are instances of class ValueWrapper.
 * @author Luka
 *
 */
public class ObjectMultistack {
	
	/**
	 * Map that represents my Multistack
	 */
	private Map<String, MultistackEntry> innerMap = new LinkedHashMap<>();
	
	/**
	 * Pushes given wrapper on the top of the stack in map with given key.
	 * @param keyName key
	 * @param valueWrapper wrapper to be put
	 */
	public void push(String keyName, ValueWrapper valueWrapper) {
		Objects.requireNonNull(keyName, "Key can not be null");
		
		//it will be next if it is not null
		//it means I always push nodes to the front
		MultistackEntry currentList = innerMap.get(keyName);
		if(currentList == null) {
			innerMap.put(keyName, new MultistackEntry(valueWrapper, null));
		} else {
			innerMap.remove(keyName);
			innerMap.put(keyName, new MultistackEntry(valueWrapper, currentList));
		}
		
	}
	/**
	 * Pops wrapper on the top of the stack with given key.
	 * @param keyName
	 * @return wrapper that was popped
	 * @throws MultistackException if it is called upon empty stack
	 */
	public ValueWrapper pop(String keyName) {
		Objects.requireNonNull(keyName, "Key can not be null");
		MultistackEntry currentList = innerMap.get(keyName);
		
		if(currentList == null) {
			throw new MultistackException("I can not pop empty stack.");
		} else {
			innerMap.remove(keyName);
			innerMap.put(keyName, currentList.next);
		}
		return currentList.currentWrapper;
		
	}
	/**
	 * Returns wrapper on the top of the stack with given key.
	 * @param keyName
	 * @return wrapper that was popped
	 * @throws MultistackException if it is called upon empty stack
	 */
	public ValueWrapper peek(String keyName) {
		MultistackEntry currentList = innerMap.get(keyName);
		
		if(currentList == null) {
			throw new MultistackException("Empty stack.");
		}
		return currentList.currentWrapper;
	}
	/**
	 * Checks whether stack with given key is empty.
	 * @param keyName
	 * @return <code>true</code> if empty, <code>false</code> otherwise.
	 */
	public boolean isEmpty(String keyName) {
		return innerMap.get(keyName) == null;
	}
	
	/**
	 * Represents one node for values of our multistack.
	 * It has its own wrapper and reference to the next entry in node.
	 * @author Luka
	 *
	 */
	private static class MultistackEntry {
		
		/**
		 * Current wrapper
		 */
		private ValueWrapper currentWrapper;
		/**
		 * Reference to the next entry in node
		 */
		private MultistackEntry next;
		
		/**
		 * Constructor
		 * @param currentWrapper
		 * @param next
		 */
		public MultistackEntry(ValueWrapper currentWrapper, MultistackEntry next) {
			Objects.requireNonNull(currentWrapper, "Wrapper can not be null. Howewer, his value can be null. Try that.");
			this.currentWrapper = currentWrapper;
			this.next = next;
		}
	}
}
