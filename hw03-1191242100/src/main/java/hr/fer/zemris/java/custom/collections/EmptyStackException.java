package hr.fer.zemris.java.custom.collections;

/**
 * New exception that is thrown by <code>ObjectStack</code> class if the stack is empty.
 * @author Luka
 *
 */
public class EmptyStackException extends RuntimeException {
	
	private static final long serialVersionUID = 1L; 	
	
	/**
	 * Constructs new <code>EmptyStackException</code> without error message.
	 */
	public EmptyStackException() {
		
	}
	
	/**
	 * /**
	 * Constructs new <code>EmptyStackException</code> provided with error message.
	 * @param message message that will be printed.
	 */
	public EmptyStackException(String message) {
		super(message);
	}
	
}
