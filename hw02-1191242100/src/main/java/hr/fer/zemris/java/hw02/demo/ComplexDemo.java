package hr.fer.zemris.java.hw02.demo;



import hr.fer.zemris.java.hw02.*;


/**
 * Demo class with examples of working with my implementation of Complex Numbers.
 * @author Luka Karlić
 */
public class ComplexDemo {
	
	/**
	 * @param args arguments of command line, but it won't be used
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
		ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57)).div(c2).power(3).root(2)[1];
		System.out.println(c3);
		
		/*String s = "abc";
		String copyOfS = new String();
		copyOfS = copyOfS.concat(s);
		System.out.print(s);
		System.out.print(copyOfS);
		System.out.print(s.length());
		s = s.replaceAll("a", "");
		System.out.print(s);
		System.out.print(s.length());*/
		/*ComplexNumber c1 = new ComplexNumber(0, 0);
		
		
		c1 = ComplexNumber.parse("-3+4i");
		System.out.print(c1.getAngle());*/

	}
}
