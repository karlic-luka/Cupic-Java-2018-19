/**
 * 
 */
package searching.algorithms;

/**
 * Node that stores all relevant informations - parent node,
 * current state and price we paid to get to current node.
 * @author Luka
 */
public class Node<S> {

	/**
	 * Reference to the node that was one move before current node
	 */
	private Node<S> parent;
	/**
	 * Current state
	 */
	private S state;
	/**
	 * Price we paid to get to current node
	 */
	private double cost;
	
	/**
	 * Constructor
	 * @param parent
	 * @param state
	 * @param cost
	 */
	public Node(Node<S> parent, S state, double cost) {
		super();
		this.parent = parent;
		this.state = state;
		this.cost = cost;
	}
	/**
	 * @return the parent
	 */
	public Node<S> getParent() {
		return parent;
	}
	/**
	 * @return the state
	 */
	public S getState() {
		return state;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	
}
