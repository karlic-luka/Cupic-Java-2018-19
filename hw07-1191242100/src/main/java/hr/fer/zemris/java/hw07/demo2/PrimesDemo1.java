/**
 * 
 */
package hr.fer.zemris.java.hw07.demo2;

/**
 * Demo class for PrimesCollection
 * @author Luka
 *
 */
public class PrimesDemo1 {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		PrimesCollection primesCollection = new PrimesCollection(5); // 5: how many of them
		for(Integer prime : primesCollection) {
			System.out.println("Got prime: "+prime);
		}
	}
}
