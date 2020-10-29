/**
 * 
 */
package hr.fer.zemris.java.hw07.demo2;

/**
 * Demo class for PrimesCollection
 * @author Luka
 *
 */
public class PrimesDemo2 {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		PrimesCollection primesCollection = new PrimesCollection(5);
		for(Integer prime : primesCollection) {
		 for(Integer prime2 : primesCollection) {
		 System.out.println("Got prime pair: "+prime+", "+prime2);
		 }
		}
	}
}
