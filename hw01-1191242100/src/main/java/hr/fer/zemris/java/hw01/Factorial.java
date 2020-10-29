package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Program koji ispisuje faktorijele brojeva iz raspona 3-20
 *  te ostavlja poruku za neprikladan unos. Završava unosom riječi "kraj".
 * @author Luka Karlić
 */
public class Factorial {
	
	/**
	 * Metoda od koje kreće izvođenje programa.
	 * 
	 * @param args Argumenti zadani preko naredbenog retka, međutim ne koristi se.
	 */
	public static void main(String[] args) {
		while(true) {
			System.out.format("Unesite broj > ");
			Scanner sc = new Scanner(System.in);
		
			if(sc.hasNextInt()) {
				int broj = sc.nextInt();
				
				if(broj >= 3 && broj <= 20) {
					System.out.format("%s! = %s%n", broj, izracunajFaktorijele(broj));
					continue;
				} else {
					System.out.format("'%s' nije u rasponu!%n", broj);
					continue;
				}
			}
			if(sc.hasNext()) {
				String unos = sc.next();
				if(unos.equals("kraj")) {
					System.out.println("Doviđenja!");
					sc.close();
					break;
				} else {
					System.out.format("'%s' nije broj!%n", unos);
					continue;
				}
			} else {
				System.out.print("Niste ništa unijeli.");
				sc.close();
				break;
			}
		}
	}

	/**
	 * Pomoćna metoda koja računa faktorijele danog argumenta.
	 * 
	 * @param broj argument za koji želimo izračunati faktorijele
	 * @throws IllegalArgumentException ako se kao argument preda
	 * vrijednost manja od 0 ili veća od 20
	 * @return iznos faktorijela za zadani parametar
	 */
	public static long izracunajFaktorijele(int broj) {
		long rezultat = 1;
		if(broj < 0) {
			throw new IllegalArgumentException("Faktorijeli nisu definirani za dani broj.");
		}
		if(broj > 20) {
			throw new IllegalArgumentException("Rezultat je prevelik da bi se mogao ispisati.");
		}
		
		for(int i = 1; i <= broj; i++) {
			rezultat *= i;
		}
		return rezultat;
	} 
}