/**
 * 
 */
package hr.fer.zemris.java.hw05.db.demo;

import hr.fer.zemris.java.hw05.db.ComparisonOperators;

/**
 * Demo class for testing LIKE operator
 */
public class LikeDemo {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		String[] value = "ABCD*".split("\\*");
        System.out.println(value[0]);
        
        String value2 = "ABCD*";
        System.out.println(value2.indexOf("*"));
        
        System.out.println(ComparisonOperators.LIKE.satisfied("Zagreb", "Aba*"));
        System.out.println(ComparisonOperators.LIKE.satisfied("AAA", "AA*AA"));
        System.out.println(ComparisonOperators.LIKE.satisfied("AAAA", "AA*AA"));
	}

}
