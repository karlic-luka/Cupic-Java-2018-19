package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.*;

/**
 * Demonstration program for my implementation of stack. 
 * Also evaluates postfix expression from command line. Expression must be enclosed in quotations in order to work.
 * @author Luka Karlić
 *
 */
public class StackDemo {

	/**
	 * Entry point to the program that gets just one expression enclosed in quotations.
	 * It checks if it is regular postfix expression and prints result to the screen.
	 * @param args one command line argument
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("You should write only one expression enclosed in quotes.");
			System.exit(0);
		}
		String expression = args[0];
		evaluatePostfix(expression);
	}
	
	/**
	 * Helping method that evaluates postfix expression.
	 * If something went wrong( i.e. postfix expression is not valid), program stops and writes error on the screen.
	 * @param expression expression that will be evaluated
	 * 
	 */
	public static void evaluatePostfix (String expression) {
		String[] newExpression = expression.trim().split("\\s+");
		ObjectStack stack = new ObjectStack();
		
		for(String str : newExpression) {
			if(isInteger(str)) {
				stack.push(Integer.parseInt(str));
				continue;
			} else if(validCharacter(str)) {
				if(stack.size() < 2) {
					System.err.println("Error: at least 2 numbers should be before operator.");
					return;
				}
				int rightNumber = (int)stack.pop();
				int leftNumber = (int)stack.pop();
				try {
					stack.push((int)operation(str, leftNumber, rightNumber));
					continue;
				} catch (ArithmeticException e) {
					System.err.println("Division by zero is not allowed");
					return;
				}
				
			} else {
				System.err.println("Expression is not valid.");
				return;
			}
		}
		if(stack.size() != 1) {
			System.err.println("Error: stack should not be in this state.");
			return;
		} else {
			System.out.println("Expression evaluates to " + stack.pop());
		}
	}
	
	private static boolean isInteger(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	/**
	 * Helping method that checks whether character is either <code> +, -, /, * or %</code>
	 * @param str string of characters to be checked. It will actually always be just one character as string
	 * @return <code>true</code> if it one of upper operations, <code>false</code> otherwise
	 */
	private static boolean validCharacter(String str) {
		if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("%")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Helping method that performs given operation on top 2 elements on stack
	 * @param str operation 
	 * @param first first operand
	 * @param second second operand
	 * @return result of the operation
	 * @throws ArithmeticException if user tried to divide by zero.
	 */
	private static int operation(String str, int first, int second) {
		if(str.equals("%")) {
			if(second == 0) {
				throw new ArithmeticException("Division by zero is not allowed");
			}
			return first + second;
		} else if(str.equals("-")) {
			return first - second;
		} else if(str.equals("*")) {
			return first * second;
		} else if(str.equals("/")) {
			if(second == 0) {
				throw new ArithmeticException("Division by zero is not allowed");
			}
			return first / second;
		}
		return first + second;
	}
}
