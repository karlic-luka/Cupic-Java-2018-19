package hr.fer.zemris.java.hw02;


/**
 * Class represents my implementation of complex numbers with various methods to calculate and work with complex numbers such as
 * division, multiplication, powering, etc.
 * Parsing complex number from string is really interesting actually.
 * @author Luka Karlić
 *
 */
public class ComplexNumber {
	
	/**
	 * real part of complex number
	 */
	private final double real;
	
	/**
	 * imaginary part of complex number
	 */
	private final double imaginary;
	
	/**
	 * Precision for identifying if two complex numbers are equal.
	 */
	private double EPSILON = 1E-2;
	
	/**
	 * Constructor that creates new instance of <code>ComplexNumber</code> 
	 * with given parameters <code>real and imaginary</code>
	 * @param real real part of new complex number
	 * @param imaginary imaginary part of new complex number
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * Static factory method that creates new instance of <code>ComplexNumber</code> without imaginary part.
	 * @param real real part of new complex number
	 * @return new <code>ComplexNumber</code> without imaginary part
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0.0);
	}
	
	/**
	 * Static factory method that creates new instance of <code>ComplexNumber</code> without real part.
	 * @param imaginary imaginary part of new complex number
	 * @return new <code>ComplexNumber</code> without real part
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0.0, imaginary);
	}
	
	/**
	 * Static factory method that creates new instance of <code>ComplexNumber</code> from given <code>magnitude and angle</code>
	 * in polar coordinates.
	 * @param magnitude of new complex number
	 * @param angle in polar coordinates of new complex number
	 * @return new <code>ComplexNumber</code> created by given parameters
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		if(magnitude < 0) {
			throw new IllegalArgumentException("Magnitude can not be negative");
		}
		return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
	}
	
	/**
	 * Parses <code>String s</code> into <code>ComplexNumber</code> as a + bi
	 * 
	 * @param s string input that needs to be parsed 
	 * @return parsed complex number
	 * @throws IllegalArgumentException if <code>string</code> doesn't represent valid complex number
	 */
	public static ComplexNumber parse(String s) {
		if(s.length() == 0) {
			throw new IllegalArgumentException("Empty string is not valid expression");
		}
		s = s.replace(" ", "");
		ComplexNumber parsed = null; 
		//boolean negativeRealPart = false;
		//boolean negativeImaginaryPart = false;
		
		if(s.indexOf('+') == 0) { //zelim zanemariti prvi +
			s = s.substring(1, s.length());
			if(s.indexOf('+') == 0 || s.indexOf('-') == 0) { //napravio sam novi string i ne zelim da je opet prvi znak + ili -
				throw new IllegalArgumentException("You put illegal expression that has two signs of + or - in a row.");
			} 
		}
		
		String copyOfS = new String();
		copyOfS = copyOfS.concat(s);
		int lengthBefore = copyOfS.length();
		if(copyOfS.contains(String.valueOf("+")) || copyOfS.contains(String.valueOf("-"))) {
			copyOfS = copyOfS.replaceAll("\\+", "");
			copyOfS = copyOfS.replaceAll("\\-", "");
			int lengthAfter = copyOfS.length();
			if((s.indexOf('-') == 0)) {
				if((lengthBefore - lengthAfter) > 2) { //sada smiju biti dva znaka +-
					throw new IllegalArgumentException("You didn't put valid expression.");
				}
			} else {
				if((lengthBefore - lengthAfter) > 1) { //sada smiju biti jos samo potencijalno + ili -
					throw new IllegalArgumentException("You didn't put valid expression.");
				}
			}
		}
		if (s.contains(String.valueOf("+")) || (s.contains(String.valueOf("-")) && s.lastIndexOf('-') > 0)) {
			String realPart = "";
			String imaginaryPart = "";
			s = s.replace("i", "");
			if (s.indexOf('+') > 0) {
				realPart = s.substring(0, s.indexOf('+'));
				imaginaryPart = s.substring(s.indexOf('+') + 1, s.length());
				parsed = new ComplexNumber(Double.parseDouble(realPart), Double.parseDouble(imaginaryPart));
			} else if (s.lastIndexOf('-') > 0) {
				realPart = s.substring(0, s.lastIndexOf('-'));
				imaginaryPart = s.substring(s.lastIndexOf('-') + 1, s.length());
				parsed = new ComplexNumber(Double.parseDouble(realPart), - Double.parseDouble(imaginaryPart));
			}
		} else {
			if (s.endsWith("i")) {
				s = s.replace("i", "");
				s = s.trim().length() == 0 ? "1" : s;
				parsed = fromImaginary(Double.parseDouble(s));
			} else {
					parsed = fromReal(Double.parseDouble(s));
				}
			}
		return parsed;
	}
	
	
	/**
	 * Getter for real part
	 * @return real part
	 */
	public double getReal() {
		return real;
	}

	/** 
	 * Getter for imaginary part
	 * @return imaginary part
	 */
	public double getImaginary() {
		return imaginary;
	}
	
	/**
	 * Getter for magnitude 
	 * @return magnitude of complex number
	 */
	public double getMagnitude() {
		return Math.hypot(real, imaginary);
	}
	
	/**
	 * Getter for angle in polar coordinates
	 * @return polar coordinate's angle from interval [0, 2Pi]
	 */
	public double getAngle() {
		double angle = Math.atan2(imaginary, real);
		if(angle <= 0) {
			angle += 2 * Math.PI;
		}
		return angle;
	}
	
	/**
	 * Adds <code>ComplexNumber c</code> to the complex number on which the method is called.
	 * @param c complex number to be added
	 * @return new <code>ComplexNumber</code> as a result of adding.
	 */
	public ComplexNumber add(ComplexNumber c) {
		return new ComplexNumber(real + c.getReal(), imaginary + c.getImaginary());
	}
	
	/**
	 * Subtracts complex number on which the method called with <code>ComplexNumber c</code> .
	 * @param c subtrahend
	 * @return new <code>ComplexNumber</code> as a result of subtraction.
	 */
	public ComplexNumber sub(ComplexNumber c) {
		return new ComplexNumber(real - c.getReal(), imaginary - c.getImaginary());
	}
	
	/**
	 * Multiplies complex number on which the method is called with <code>ComplexNumber c</code> .
	 * @param c factor
	 * @return new <code>ComplexNumber</code> as a result of multiplication.
	 */
	public ComplexNumber mul(ComplexNumber c) {
		if (c == null) {
			throw new NullPointerException("Argument cannot be null");
		}
		return new ComplexNumber(real * c.getReal() - imaginary * c.getImaginary(), real * c.getImaginary() + imaginary * c.getReal());
	}
	
	/**
	 * Divides complex number on which the method is called with divisor <code>ComplexNumber c</code> .
	 * @param c divisor
	 * @return new <code>ComplexNumber</code> as a result of divisipm
	 */
	public ComplexNumber div(ComplexNumber c) {
		if (c == null) {
			throw new NullPointerException("Argument cannot be null");
		}
		double realPart = (real * c.getReal() + imaginary * c.getImaginary()) / Math.pow(c.getMagnitude(), 2);
		double imaginaryPart = (imaginary * c.getReal() - real * c.getImaginary()) / Math.pow(c.getMagnitude(), 2);
		return new ComplexNumber(realPart, imaginaryPart);
	}
	
	/**
	 * calculates the <code>ComplexNumber</code> to the passed positive integer power.
	 * @param n positive integer power
	 * @return result of powering
	 * @throws IllegalArgumentException if power is negative
	 */
	public ComplexNumber power(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Exponent should be >= 0");
		}
		return new ComplexNumber(Math.pow(getMagnitude(), n) * Math.cos(n * getAngle()), Math.pow(getMagnitude(), n) * Math.sin(n * getAngle()));
	}
	
	/**
	 * Calculates the n-th root of complex number
	 * @param n degree of root
	 * @return list of all n-th roots 
	 * @throws IllegalArgumentException if root is negative
	 */
	public ComplexNumber[] root(int n) {
		if(n <= 0) {
			throw new IllegalArgumentException("Root should be natural number");
		}
		ComplexNumber[] roots = new ComplexNumber[n];
		double angleRoot = getAngle() / n;
		double magnitudeRoot = Math.pow(getMagnitude(), 1. / n);
		for(int i = 0; i < n; i++) {
			roots[i] = fromMagnitudeAndAngle(magnitudeRoot, angleRoot);
			angleRoot += 2 * Math.PI / n;
		}
		return roots;
	}
	
	@Override
	public String toString() {
		if(imaginary == 0) {
			return "" + real;
		} else if(real == 0) {
			return imaginary +"i";
		} else if(imaginary < 0) {
			return real + "-" + (-imaginary) + "i";
		}
		return "" + real + "+" + imaginary + "i";
	}

	/**
	 * Checks whether two complex numbers are same (if and only if they have exact real and imaginary part)
	 * it uses epsilon as precision because of computer's arithmetic
	 * @param c complex number 
	 * @return <code>true</code> if they are same, <code>false</code> otherwise
	 */
	public boolean equals(ComplexNumber c) {
		if(Math.abs(real - c.getReal()) >= EPSILON || Math.abs(imaginary - c.getImaginary()) >= EPSILON) {
			return false;
		}
		return true;
	}
	
} 
