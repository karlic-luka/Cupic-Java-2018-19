/**
 * 
 */
package searching.slagalica;

/**
 * @author Luka
 *
 */
public class KonfiguracijaSlagalice {

	private int[] polje;

	private static final int PRAZNO_POLJE = 0;
	/**
	 * @param polja
	 */
	public KonfiguracijaSlagalice(int[] polje) {
		super();
		this.polje = polje;
	}

	/**
	 * @return the polja
	 */
	public int[] getPolja() {
		return polje;
	}
	
	public int indexOfSpace() {
		int i;
		for(i = 0; i < 9; i++) {
			if(polje[i] == 0) {
				break;
			}
		}
		return i;
	}
	
	public int[] changeSpace(int index) {
		int[] novoPolje = new int[9];
		int indeksPraznogPolja = this.indexOfSpace();
		for(int i = 0; i < 9; i++) {
			if(i == index) {
				novoPolje[i] = PRAZNO_POLJE;
			} else if(i == indeksPraznogPolja) {
				novoPolje[i] = polje[index];
			} else {
				novoPolje[i] = polje[i];
			}
		}
		return novoPolje;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9; i++) {
			if(polje[i] == 0) {
				sb.append("* ");
			} else {
				sb.append(polje[i]).append(" ");
			}
			if(i != 8 && (i + 1) % 3 == 0) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
}
