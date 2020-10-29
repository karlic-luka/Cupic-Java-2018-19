package hr.fer.zemris.java.hw07.demo2;

import java.util.Iterator;
import java.util.Objects;

/**
 * Class that works with prime numbers. It iterates through
 * first n primes with complexity O(n**2).
 * @author Luka
 *
 */
public class PrimesCollection implements Iterable<Integer> {
	
	/**
	 * Number of first primes we want to extract.
	 */
	private int numberOfPrimes;
	
	public PrimesCollection(int numberOfPrimes) {
		Objects.requireNonNull(numberOfPrimes);
		if(numberOfPrimes == 0) {
			throw new IllegalArgumentException("You should provide me positive number.");
		}
		this.numberOfPrimes = numberOfPrimes;
	}
	
	/**
	 * Helper method that returns n-th prime number
	 * @param n 
	 * @return n-th prime number
	 */
	private int getNthPrime(int n) {
		if(n < 1) {
			throw new IllegalArgumentException("You should provide me positive number.");
		}
		if(n == 1) {
			return 2;
		}
		//it is not first, so I want to skip 2
		n--;
		for(int i = 3; ; i += 2) {
			if(isPrime(i)) {
				n--;
				if(n == 0) {
					return i;
				}
			}
		}
	}
	
	/**
	 * Helper method that checks whether <code>number</code> is prime number.
	 * @param number number to be checked
	 * @return <code>true</code> if prime, <code>false</code> otherwise.
	 */
	private boolean isPrime(int number) {
		if(number < 1) {
			throw new IllegalArgumentException("Number can not be < 1.");
		}
		
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new IteratorImplementation();
	}
	
	/**
	 * Implementation of iterator for primary class.
	 * @author Luka
	 */
	private class IteratorImplementation implements Iterator<Integer> {

		/**
		 * Number of primes I provided
		 */
		private int numberOfPrimesIGave;

		/**
		 * Constructor
		 */
		public IteratorImplementation() {
			numberOfPrimesIGave = 0;
		}

		@Override
		public boolean hasNext() {
			
			return numberOfPrimesIGave < numberOfPrimes;
		}

		@Override
		public Integer next() {
			
			return getNthPrime(++numberOfPrimesIGave);
		}	
	}
	
}
