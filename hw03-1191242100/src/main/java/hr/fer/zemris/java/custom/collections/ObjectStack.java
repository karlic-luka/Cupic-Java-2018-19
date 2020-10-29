package hr.fer.zemris.java.custom.collections;

import hr.fer.zemris.java.custom.collections.EmptyStackException;


/**
 * Implementation of stack-like collection with adaptor pattern. ArrayIndexedCollection is the adaptee.
 * @author Luka Karlić
 *
 */
public class ObjectStack {
	
	private ArrayIndexedCollection adaptee;
	
	/**
	 * Constructs an empty stack sized by default size of <code>ArrayIndexedCollection</code>,
	 * which is 16 in this case.
	 */
	public ObjectStack() {
		adaptee = new ArrayIndexedCollection();
	}
	
	/**
	 * Constructs new stack and sets default capacity to <code>initialCapacity</code>
	 * @param initialCapacity initial capacity of the stack
	 */
	public ObjectStack(int initialCapacity) {
		adaptee = new ArrayIndexedCollection(initialCapacity);
	}
	
	/**
	 * /**
	 * Returns true if stack contains no objects and false otherwise.
	 * @return <code>true</code> if collection is empty, <code>false</code> otherwise
	 */
	public boolean isEmpty() {
		return adaptee.isEmpty();
	}
	
	/**
	 * 
	 * @return current size(number of elements) of stack
	 */
	public int size() {
		return adaptee.size();
	}
	
	/**
	 *  pushes given value on the stack. <code>null</code> value must not be allowed to be placed on stack
	 * @param value to be inserted
	 */
	public void push(Object value) {
		if(value == null) {
			throw new NullPointerException("Null references are not allowed to be stored.");
			
		}
		adaptee.add(value);
	}
	
	/**
	 * Removes last value pushed on stack from stack and returns it
	 * @return last element placed on stack
	 * @throws EmptyStackException if the stack is empty and we want to remove element
	 */
	public Object pop() {
		if(adaptee.size() == 0) {
			throw new EmptyStackException("It is not allowed to pop elements when stack is empty");
		}
		Object topElement = adaptee.get(adaptee.size() - 1);
		adaptee.remove(adaptee.size() - 1);
		return topElement;
	}
	
	/**
	 * Returns last element placed on stack but does not delete it from stack. 
	 * @return last element placed on stack
	 */
	public Object peek() {
		if(adaptee.size() == 0) {
			throw new EmptyStackException("Stack is empty.");
		}
		return adaptee.get(adaptee.size() - 1);
	}
	
	/**
	 * removes all elements from stack
	 */
	public void clear() {
		adaptee.clear();
	}
	
	
}
