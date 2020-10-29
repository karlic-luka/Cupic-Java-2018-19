package hr.fer.zemris.java.custom.scripting.exec;

/**
 * Class that wraps any kind of class. 
 * Furthermore, it can execute basic arithmetic methods
 * with Integers and Doubles that could also be parsed 
 * from String. If it is null, we use it as integer 0.
 * @author Luka
 *
 */
public class ValueWrapper {

	private static final int ADD = 1;
	private static final int SUBTRACT = 2;
	private static final int MULTIPLY = 3;
	private static final int DIVIDE = 4;
	private boolean isDoubleFlag = false;

	/**
	 * Value that is stored.
	 */
	private Object value;

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	public ValueWrapper(Object initialValue) {
		this.value = initialValue;
	}
	
	/**
	 * Arithmetic operation - addition.
	 * @param incValue
	 */
	public void add(Object incValue) {
		executeCommand(ADD, incValue);
	}
	
	/**
	 * Arithmetic operation - subtraction.
	 * @param decValue
	 */
	public void subtract(Object decValue) {
		executeCommand(SUBTRACT, decValue);
	}
	
	/**
	 * Arithmetic operation - multiplication.
	 * @param mulValue
	 */
	public void multiply(Object mulValue) {
		executeCommand(MULTIPLY, mulValue);
	}
	
	/**
	 * Arithmetic operation - division.
	 * @param divValue
	 */
	public void divide(Object divValue) {
		executeCommand(DIVIDE, divValue);
	}
	
	/**
	 * Compares value of the wrapper on which it is called
	 * with given value.
	 * @param withValue
	 * @return The method returns an integer less than zero if the currently stored value is smaller than the
	 * argument, an integer greater than zero if the currently stored value is larger than the argument, or an integer
	 * 0 if they are equal
	 */
	public int numCompare(Object withValue) {
		double first = parseArgument(value);
		double second = parseArgument(withValue);
		return Double.compare(first, second);
	}
	
	/**
	 * Helper method that prepares argument for arithmetic operation.
	 * i.e. returns integer 0 if null is given
	 * @param argument
	 * @return argument
	 */
	private Object prepareArguments(Object argument) {
		if(argument == null) {
			return Integer.valueOf(0);
		} else if(argument instanceof ValueWrapper) {
			//this is for calling method numCompare
			return ((ValueWrapper) argument).parseArgument(((ValueWrapper) argument).getValue());
		}
		
		if(!(checkInstanceOfElements(argument))) {
			throw new RuntimeException("Argument can not be instance of " + argument.getClass());
		} else {
			return argument;
		}
		
	}
	/**
	 * Checks whether given element is instance of string, double or integer
	 * It can also be null as said.
	 * @param element
	 * @return <code>true</code> if valid instance, <code>false</code> otherwise.
	 */
	private boolean checkInstanceOfElements(Object element) {
		if(element == null || element instanceof Integer || element instanceof String || element instanceof Double) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that parses number from string or returns double value
	 * of current value. It is because of casting problems.
	 * @param argument argument to be returned as double
	 * @return double value of argument
	 * @throws Runtime exception if string could not be parsed into number
	 */
	private double parseArgument(Object argument) {
		argument = prepareArguments(argument);
		if(argument instanceof String) {
			String copyOfArgument = (String)argument;
			try {
				if(copyOfArgument.contains(".")) {
					argument = Double.parseDouble(copyOfArgument);
					isDoubleFlag = true;
				} else if(copyOfArgument.toUpperCase().contains("E")) {
					String[] splitArgument = copyOfArgument.split("E");
					argument = Double.parseDouble(splitArgument[0]) * Math.pow(10, Double.parseDouble(splitArgument[1]));
					isDoubleFlag = true;
				} else {
					argument = (double)Integer.parseInt(copyOfArgument);
				}
			} catch (NumberFormatException e) {
				throw new RuntimeException("You provided invalid argument." + e.getMessage());
			}
		} else if(argument instanceof Double) {
			argument = Double.valueOf((double)argument);
			isDoubleFlag = true;
		} else {
			argument = (double)Integer.valueOf((int)argument);
		}
		return (double)argument;
	}
	
	/**
	 * Helper method that does all the arithmetic operations.
	 * @param operation
	 * @param argument
	 */
	private void executeCommand(int operation, Object argument) {
		boolean returnValueIsDouble = false;
		value = parseArgument(value);
		if(isDoubleFlag) {
			returnValueIsDouble = true;
		}
		argument = parseArgument(argument);
		//moram dvaput koristiti zato sto se mijenja pozivom funkcije parseArgument
		//pa bi bila greska ako je prvi poziv double, a drugi integer
		if(isDoubleFlag) {
			returnValueIsDouble = true;
		}
		
		double returnValue;
		double copyValue = Double.valueOf((double)value);
		double copyArgument = Double.valueOf((double)argument);
		switch(operation) {
		case 1:
			returnValue = copyValue + copyArgument;
			break;
		case 2:
			returnValue = copyValue - copyArgument;
			break;
		case 3:
			returnValue = copyValue * copyArgument;
			break;
		case 4:
			returnValue = copyValue / copyArgument;
			break;
		default: 
			throw new IllegalArgumentException("I do not recognize given operation.");
		}
		if(returnValueIsDouble) {
			value = Double.valueOf((double)returnValue);
		} else {
			value = Integer.valueOf((int)returnValue);
		}
	}
}
