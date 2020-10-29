package hr.fer.zemris.java.hw06.crypto;

/**
 * Helper class that has two main methods - byte to hex and hex to byte.
 * @author Luka
 *
 */
public class Util {

	/**
	 * Constant that represents invalid character
	 */
	private static final int INVALID_CHARACTER = -1;
	/**
	 * Constant that represents that represents invalid input for hex.
	 */
	private static final char INVALID_INPUT = 'K';
	
	/**
	 * Method takes hex-encoded String and return appropriate byte[].
	 * @param keyText password
	 * @return byte output
	 * @throws IllegalArgumentException if If string is
	 * not valid (odd-sized, has invalid characters...)
	 */
	public static byte[] hextobyte(String keyText) {
		
		char[] characters = keyText.toCharArray();
		
		if(characters.length % 2 == 1) {
			throw new IllegalArgumentException("String should not be odd-numbered.");
		} else if(characters.length == 0) {
			return new byte[0];
		}
		
		byte[] byteArray = new byte[characters.length / 2];
		
		for(int i = 0, length = characters.length; i < length - 1; i += 2) {
			int left = charToHex(characters[i]);
			int right = charToHex(characters[i + 1]);
			
			if(left == INVALID_CHARACTER || right == INVALID_CHARACTER) {
				throw new IllegalArgumentException("Invalid character");
			}
			
			int currentHexValue = (left * 16 + right) % 256;
			byteArray[i / 2] = (byte) currentHexValue;
			
		}
		return byteArray;
	}
		
	/**
	 * Method takes appropriate byte[] and returns hex-encoded String.
	 * It uses lower case letters.
	 * @param byteArray byte input
	 * @return String output of byte input
	 */
	public static String bytetohex(byte[] byteArray) {
		if(byteArray.length == 0) {
			return "";
		}
		StringBuilder stringB = new StringBuilder();
		for(byte b : byteArray) {
			int number = b;
			if(b < 0) {
				number += 256;
			}
			stringB.append(hexToChar(number / 16));
			stringB.append(hexToChar(number % 16));
		}
		return stringB.toString();
	}
	
	/**
	 * Helper method that returns hex chars.
	 * @param b
	 * @return hex char
	 */
	private static char hexToChar(int b) {
		if(b < 10) {
			return Integer.toString(b).toCharArray()[0];
		} else if(b == 10) {
			return 'a';
		} else if(b == 11) {
			return 'b';
		} else if(b == 12) {
			return 'c';
		} else if(b == 13) {
			return 'd';
		} else if(b == 14) {
			return 'e';
		} else if(b == 15) {
			return 'f';
		}
		return INVALID_INPUT;
	}

	/**
	 * Helper method that returns hex chars as integers
	 * @param c hex char
	 * @return integer of given hex char
	 */
	private static int charToHex(char c) {
		if(Character.isDigit(c)) {
			return Character.getNumericValue(c);
		} else if(Character.isLetter(c)) {
			char ch = Character.toLowerCase(c);
			if(ch == 'a') {
				return 10;
			} else if(ch == 'b') {
				return 11;
			} else if(ch == 'c') {
				return 12;
			} else if(ch == 'd') {
				return 13;
			} else if(ch == 'e') {
				return 14;
			} else if(ch == 'f') {
				return 15;
			} else {
				return -1;
			}
		}
		return INVALID_CHARACTER;
	}
}
