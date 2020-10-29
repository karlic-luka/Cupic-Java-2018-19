/**
 * 
 */
package hr.fer.zemris.java.hw06.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Program that allows the user to encrypt/decrypt given
 * file using the AES cryptoalgorithm and the 128-bit encryption key or calculate 
 * and check the SHA-256 file digest.
 * @author Luka Karlić
 *
 */
public class Crypto {

	/**
	 * Password for crpyting
	 */
	public static String keyText;
	/**
	 * Initialization vector
	 */
	public static String ivText;
	/**
	 * Flag that represents encryption or decryption
	 */
	public static boolean encryptFlag = false;
	
	/**
	 * Entry point to the program.
	 * @param args command line parameters
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String string = "01aE22";
//		Util.hextobyte(string);
//		Util.bytetohex(new byte[] {1, -82, 34});
		
		if(args.length != 2 && args.length != 3) {
			throw new IllegalArgumentException("Invalid number of arguments in command line");
		}
		try(Scanner sc = new Scanner(System.in)) {
			String userCommand = args[0];
			if(userCommand.equals("encrypt") || userCommand.equals("decrypt")) {
				
				if(userCommand.equals("encrypt")) {
					encryptFlag = true;
				}
				
				System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):");
				System.out.print("> ");
				keyText = sc.nextLine().trim();
				
				System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
				System.out.print("> ");
				ivText = sc.nextLine().trim();
				
				cryption(args[1], args[2]);
				
			} else if(userCommand.equals("checksha")) {
				System.out.println("Please provide expected sha-256 digest for hw06.test.bin:");
				System.out.print("> ");
				String expected = sc.nextLine().trim();
				checksha(args[1], expected);
			} else {
				throw new IllegalArgumentException("I do not recognize given command.");
			}
		}
	}
	
	/**
	 * Method that checks the SHA-256 file digest.
	 * Message digest is a fixed-size binary digest which is calculated from arbitrary long data.
	 * Digests are used to verify if the data you have received (for example, when downloading the data from
	 * the Internet) arrived unchanged
	 * @param inputFilePath string that represents path of given file
	 * @param expected expected path 
	 */
	private static void checksha(String inputFilePath, String expected) {
		
		try(InputStream fis = Files.newInputStream(Paths.get(inputFilePath), StandardOpenOption.READ);) {
			
			byte[] buffer = new byte[4096];
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			while(true) {
				int numberOfRead = fis.read(buffer);
				if(numberOfRead < 1) {
					break;
				}
				sha.update(buffer, 0, numberOfRead);
			}
			byte[] digestedBytes = sha.digest();
			String digested = Util.bytetohex(digestedBytes);
			if(digested.equals(expected)) {
				System.out.println("Digesting completed. Digest of " + inputFilePath + " matches expected digest.");
			} else {
				System.out.println("Digesting completed. Digest of " + inputFilePath + " does not match the expected digit. "
						+ "Digest was " + digested);
			}
			
		} catch (IOException | NoSuchAlgorithmException e) {
			System.out.println("Something went wrong during digesting");
			
		}
	}

	/**
	 * Helper method that encrypts/decrypts given file to output
	 * @param inputFilePath path of input file
	 * @param outputFilePath path of output file
	 */
	private static void cryption(String inputFilePath, String outputFilePath) {
		try(InputStream fis = Files.newInputStream(Paths.get(inputFilePath), StandardOpenOption.READ);
			OutputStream fos = Files.newOutputStream(Paths.get(outputFilePath), StandardOpenOption.CREATE)) {
				
			byte[] buffer = new byte[4096];
			SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hextobyte(ivText));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(encryptFlag ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);
			
			while(true) {
				int numberOfRead = fis.read(buffer);
				if(numberOfRead < 1) {
					break;
				}
				fos.write(cipher.update(buffer, 0, numberOfRead));
			}
			fos.write(cipher.doFinal());
			
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException
				| IllegalBlockSizeException | BadPaddingException e) {
			System.out.println("Something went wrong during encrypting or decrypting.");
			System.out.println(e.getCause());
			System.out.println(e.toString());
			System.exit(2);
		}
		
		if(encryptFlag) {
			System.out.println("Encryption completed. Generated file " + outputFilePath + " based on file " + inputFilePath);
		} else {
			System.out.println("Decryption completed. Generated file " + outputFilePath + " based on file " + inputFilePath);
		}
		
	}
		
}