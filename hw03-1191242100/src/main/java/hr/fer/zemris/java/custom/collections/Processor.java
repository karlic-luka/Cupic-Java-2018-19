package hr.fer.zemris.java.custom.collections;


/**
 * Class <code>Processor</code> here represents a conceptual contract between clients which will have objects to be processed, 
 * and each concrete <code>Processor</code> which knows how to perform the selected operation.
 * Each concrete <code>Processor</code> will be defined as a new
 * class which inherits from the class <code>Processor</code>
 * @author Luka Karlić
 *
 */
public interface Processor {
	
	/**
	 * Operation which will be processed on given object
	 * @param value that will be processed
	 */
	void process(Object value);
}
