/**
 * 
 */
package searching.algorithms;

/**
 * @author Luka
 *
 */
public class Transition<S> {

	/**
	 * Current state
	 */
	private S state;
	/**
	 * Cost of getting to this state
	 */
	private double cost;
	/**
	 * Constructor
	 * @param state
	 * @param cost
	 */
	public Transition(S state, double cost) {
		super();
		this.state = state;
		this.cost = cost;
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
