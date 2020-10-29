package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/** 
 * Program prima preko komandne linije ili tipkovnice
 * dva realna broja, a zatim izračunava površinu
 * te opseg pravokutnika zadanih proporcija. Ispisuje 
 * poruku za neprikladan unos.
 * @author Luka Karlić
 */
public class Rectangle {

	/**
	 * Metoda koja se poziva prilikom pokretanja programa.
	 * Argumenti su objasnjeni u nastavku.
	 * 
	 * @param args Argumenti komandne linije.
	 */
  	public static void main(String[] args) {
  		double sirina = 0;
  		double visina = 0;
		if(args.length == 2) {
			try {
				sirina = Double.parseDouble(args[0]);
				visina = Double.parseDouble(args[1]);
				if(sirina < 0 || visina < 0) {
					throw new IllegalArgumentException("Visina ili sirina ne smiju biti negativni brojevi.");
				}
				System.out.format("Pravokutnik širine %s i visine %s ima površinu %s te opseg %s."
						, sirina, visina, površinaPravokutnika(sirina, visina), opsegPravokutnika(sirina, visina));
			} catch(NumberFormatException ex) {
				System.out.println("Barem jedan od argumenata se ne može protumačiti kao broj!");
				System.exit(0);
			}
		} else if(args.length == 0) {
			sirina = unesiParametar(true);
			visina = unesiParametar(false); //boolean false mi oznacava da je korisnik unio sirinu pa sada trebam visinu
			if(sirina < 0 || visina < 0) {
				throw new IllegalArgumentException("Visina i sirina ne smiju biti negativni brojevi.");
			}
			System.out.format("Pravokutnik širine %s i visine %s ima površinu %s te opseg %s."
					, sirina, visina, površinaPravokutnika(sirina, visina), opsegPravokutnika(sirina, visina));
		} else {
			System.out.println("Niste unijeli prikladan broj argumenata.");
			System.exit(0);
			}
	}

  	/**
  	 * Metoda koja računa opseg pravokutnika zadanih parametara.
  	 * @param sirina Širina pravokutnika
  	 * @param visina Visina pravokutnika
  	 * @return Opseg
  	 */
	private static double opsegPravokutnika(double sirina, double visina) {
		return 2 * (sirina + visina);
	}
	
	/**
  	 * Metoda koja računa površinu pravokutnika zadanih parametara.
  	 * @param sirina Širina pravokutnika
  	 * @param visina Visina pravokutnika
  	 * @return Površina
  	 */
	private static double površinaPravokutnika(double sirina, double visina) {
		return sirina * visina;
	}
	
	/**
	 * Metoda koja služi kao format upisa za veličinu pravokutnika
	 * Ne prihvaća realne brojeve zapisane decimalnim zarezom.
	 * 
	 * @param zastavica pokazatelj želimo li da korisnik unese
	 * širinu ili visinu.
	 * Postavljena na <code>true</code> ukoliko želimo visinu,
	 * inače <code>false</code>
	 * @return vrijednost unesene širine ili visine
	 */
	private static double unesiParametar(boolean zastavica) {
		Scanner ulaz = new Scanner(System.in);
		while(true) {
			if(zastavica == true) { //dogovorno je zastavica true ako korisnik jos nije unio visinu
				System.out.println("Unesite širinu > ");
			} else {
				System.out.println("Unesite visinu > ");
			}
			
			if(ulaz.hasNextDouble()) {
				double parametar = ulaz.nextDouble();
				return parametar;
			} else {
				System.out.format("'%s' se ne može protumačiti kao broj.%n",ulaz.next());
			}
		}
	}
}
