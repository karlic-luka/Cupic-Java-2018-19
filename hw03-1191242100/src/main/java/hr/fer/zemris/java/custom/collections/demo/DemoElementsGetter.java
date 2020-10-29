package hr.fer.zemris.java.custom.collections.demo;


import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;
import hr.fer.zemris.java.custom.collections.Collection;
import hr.fer.zemris.java.custom.collections.ElementsGetter;
import hr.fer.zemris.java.custom.collections.LinkedListIndexedCollection;
import hr.fer.zemris.java.custom.collections.List;

/**
 * Demo class with examples of using ElementsGetter
 * @author Luka
 *
 */
public class DemoElementsGetter {
	/**
	 * 
	 * @param args not in use
	 */
	public static void main(String[] args) {
		
		Collection col = new LinkedListIndexedCollection(); // npr. new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		ElementsGetter getter = col.createElementsGetter();
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Jedan element: " + getter.getNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Jedan element: " + getter.getNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Jedan element: " + getter.getNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
		System.out.println("Ima nepredanih elemenata: " + getter.hasNextElement());
////		
//		Collection col2 = new ArrayIndexedCollection(); // npr. new ArrayIndexedCollection();
//		col2.add("Ivo");
//		col2.add("Ana");
//		col2.add("Jasna");
//		ElementsGetter getter2 = col2.createElementsGetter();
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
		
//		Collection col = new ArrayIndexedCollection(); // npr. new ArrayIndexedCollection();
//		col.add("Ivo");
//		col.add("Ana");
//		col.add("Jasna");
//		ElementsGetter getter1 = col.createElementsGetter();
//		ElementsGetter getter2 = col.createElementsGetter();
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
		
//		Collection col1 = new ArrayIndexedCollection();
//		Collection col2 = new ArrayIndexedCollection();
//		col1.add("Ivo");
//		col1.add("Ana");
//		col1.add("Jasna");
//		col2.add("Jasmina");
//		col2.add("Štefanija");
//		col2.add("Karmela");
//		ElementsGetter getter1 = col1.createElementsGetter();
//		ElementsGetter getter2 = col1.createElementsGetter();
//		ElementsGetter getter3 = col2.createElementsGetter();
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter1.getNextElement());
//		System.out.println("Jedan element: " + getter2.getNextElement());
//		System.out.println("Jedan element: " + getter3.getNextElement());
//		System.out.println("Jedan element: " + getter3.getNextElement());
		
//		Collection col = new ArrayIndexedCollection();
//		col.add("Ivo");
//		col.add("Ana");
//		col.add("Jasna");
//		ElementsGetter getter = col.createElementsGetter();
//		System.out.println("Jedan element: " + getter.getNextElement());
//		System.out.println("Jedan element: " + getter.getNextElement());
//		col.clear();
//		System.out.println("Jedan element: " + getter.getNextElement());
		
//		List col1 = new ArrayIndexedCollection();
//		List col2 = new LinkedListIndexedCollection();
//		col1.add("Ivana");
//		col2.add("Jasna");
//		Collection col3 = col1;
//		Collection col4 = col2;
//		col1.get(0);
//		col2.get(0);
//		//col3.get(0); // neće se prevesti! Razumijete li zašto?
//		//col4.get(0); // neće se prevesti! Razumijete li zašto? collection nema get?
//		col1.forEach(System.out::println); // Ivana
//		col2.forEach(System.out::println); // Jasna
//		col3.forEach(System.out::println); // Ivana
//		col4.forEach(System.out::println); // Jasna

//		Collection col = new ArrayIndexedCollection();
//		col.add("Ivo");
//		col.add("Ana");
//		col.add("Jasna");
//		ElementsGetter getter = col.createElementsGetter();
//		getter.getNextElement();
//		getter.processRemaining(System.out::println);
	}
}
