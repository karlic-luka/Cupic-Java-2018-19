package hr.fer.zemris.java.custom.scripting.demo;

import hr.fer.zemris.java.custom.scripting.exec.ValueWrapper;

/**
 * @author Luka
 *
 */
public class ValueWrapperDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		System.out.println(v1.numCompare(v2));
		v1.add(v2.getValue()); // v1 now stores Integer(0); v2 still stores null.
//		System.out.println(v1.getValue());
//		System.out.println(v2.getValue());
		
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.add(v4.getValue()); // v3 now stores Double(13); v4 still stores Integer(1).
		System.out.println(v4.numCompare(v3));
//		System.out.println(v3.getValue());
//		System.out.println(v4.getValue());
		
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(1));
		v5.add(v6.getValue()); // v5 now stores Integer(13); v6 still stores Integer(1).
		System.out.println(v5.numCompare(v6));
//		System.out.println(v5.getValue());
//		System.out.println(v6.getValue());
		
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
//		System.out.println(v7.numCompare(v8));
//		System.out.println(v7.getValue());
//		System.out.println(v8.getValue());
//		v7.add(v8.getValue()); // throws RuntimeException
		
//		ValueWrapper vv1 = new ValueWrapper(Boolean.valueOf(true));
//		vv1.add(Integer.valueOf(5)); // ==> throws, since current value is boolean
		
		ValueWrapper vv2 = new ValueWrapper(Integer.valueOf(5));
		vv2.add(Boolean.valueOf(true)); // ==> throws, since the argument value is boolean
	}
}
