/**
 * 
 */
package hr.fer.zemris.java.hw07.demo4;


/**
 * Razred reprezentira jednog studenta s pripadajućim podacima iz određenog kolegija.
 * Podaci o kolegiju su: broj bodova na međuispitu, završnom ispitu, laboratorijskim vježbama
 * te ocjena iz dotičnog kolegija.
 */
public class StudentRecord {

	/**
	 * Jmbag studenta
	 */
	private String jmbag;
	
	/**
	 * Prezime studenta
	 */
	private String prezime;
	
	/**
	 * Ime studenta
	 */
	private String ime;
	
	/**
	 * Broj bodova na međuispitu
	 */
	private double brojBodovaNaMedjuispitu;
	
	/**
	 * Broj bodova na zavrsnom ispitu
	 */
	private double brojBodovaNaZavrsnomIspitu;
	
	/**
	 * Broj bodova na laboratorijskim vjezbama
	 */
	private double brojBodovaNaLabosima;
	
	/**
	 * Ocjena studenta
	 */
	private int ocjena;
	
	/**
	 * Konstruktor.
	 */
	public StudentRecord(String line) {
		String[] data = line.split("\t");
		jmbag = data[0];
		prezime = data[1];
		ime = data[2];
		brojBodovaNaMedjuispitu = Double.parseDouble(data[3]);
		brojBodovaNaZavrsnomIspitu = Double.parseDouble(data[4]);
		brojBodovaNaLabosima = Double.parseDouble(data[5]);
		ocjena = Integer.parseInt(data[6]);
	}

	/**
	 * @return jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * @return prezime
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * @return ime
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * @return brojBodovaNaMedjuispitu
	 */
	public double getBrojBodovaNaMedjuispitu() {
		return brojBodovaNaMedjuispitu;
	}

	/**
	 * @return brojBodovaNaZavrsnomIspitu
	 */
	public double getBrojBodovaNaZavrsnomIspitu() {
		return brojBodovaNaZavrsnomIspitu;
	}

	/**
	 * @return brojBodovaNaLabosima
	 */
	public double getBrojBodovaNaLabosima() {
		return brojBodovaNaLabosima;
	}

	/**
	 * @return ocjena
	 */
	public int getOcjena() {
		return ocjena;
	}
	
	/**
	 * @return ukupan sumiran broj bodova iz svih ispita
	 */
	public double getUkupanBrojBodova() {
		return brojBodovaNaLabosima + brojBodovaNaMedjuispitu + brojBodovaNaZavrsnomIspitu;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return jmbag + "\t" + prezime + "\t" + ime + "\t" + brojBodovaNaMedjuispitu + "\t" + brojBodovaNaZavrsnomIspitu + "\t" 
				+ brojBodovaNaLabosima + "\t" + ocjena;
	}
	
}
