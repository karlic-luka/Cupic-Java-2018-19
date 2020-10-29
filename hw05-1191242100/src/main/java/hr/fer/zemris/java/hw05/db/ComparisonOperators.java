/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

/**
 * Contains 7 public static final implementations of {@link IComparisonOperator} interface.
 */
public class ComparisonOperators {
	
	/**
	 * Operator <
	 */
	public static final IComparisonOperator LESS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) < 0;
		}
	};
	
	/**
	 * Operator <=
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) <= 0;
		}
	};
	
	/**
	 * Operator >
	 */
	public static final IComparisonOperator GREATER = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) > 0;
		}
	};
	
	/**
	 * Operator >=
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) >= 0;
		}
	};
	
	/**
	 * Operator =
	 */
	public static final IComparisonOperator EQUALS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) == 0;
		}
	};
	
	/**
	 * Operator !=
	 */
	public static final IComparisonOperator NOT_EQUALS = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			return value1.compareTo(value2) != 0;
		}
	};
	
	/**
	 * Operator LIKE
	 * It can contain up to one wildcard '*'
	 * i.e. "AAAA" LIKE "AA*AA" returns true, but
	 * "AAA" LIKE "AA*AA" returns false
	 */
	public static final IComparisonOperator LIKE = new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			if(value2.indexOf('*') >= 0) {
				//'*' exists . but it should be the only one
				if(value2.indexOf('*') != value2.lastIndexOf('*')) {
					throw new IllegalArgumentException("Only one wildcard is allowed");
				}
				String firstPart;
				String secondPart;
				
				String[] separatedByWildcard = value2.split("\\*");
				
				if(value2.indexOf('*') == (value2.length() - 1)) {
					secondPart = "";
					firstPart = separatedByWildcard[0];
				} else if(value2.indexOf('*') == 0) {
					secondPart = separatedByWildcard[0];
					firstPart="";
				} else {
					firstPart = separatedByWildcard[0];
					secondPart = separatedByWildcard[1];
				}
				
				
				//if first and second part have something in common (intersection) 
				int indexAfterFirstPart = firstPart.length();
				
				if(!value1.startsWith(firstPart) || !value1.substring(indexAfterFirstPart).endsWith(secondPart)) {
					return false;
				}
				return true;
			} 
			return value1.equals(value2);
		}
	};
	
}
