/**
 * 
 */
package searching.slagalica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import searching.algorithms.Transition;

/**
 * @author Luka
 *
 */
public class Slagalica implements Supplier<KonfiguracijaSlagalice>, Function<KonfiguracijaSlagalice, 
	List<Transition<KonfiguracijaSlagalice>>>, Predicate<KonfiguracijaSlagalice> {
	
	private static final double PRIJELAZ = 1;

	private KonfiguracijaSlagalice referenca;
	
	/**
	 * @param referenca
	 */
	public Slagalica(KonfiguracijaSlagalice referenca) {
		super();
		this.referenca = referenca;
	}

	@Override
	public boolean test(KonfiguracijaSlagalice t) {
		ArrayList<Integer> rjesenje = new ArrayList<>();
		for(int i = 1; i < 9; i++) {
			rjesenje.add(i);
		}
		rjesenje.add(0);
		int[] polje = t.getPolja();
		ArrayList<Integer> mojePolje = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			mojePolje.add(polje[i]);
		}
		
		return rjesenje.equals(mojePolje);
	}

	@Override
	public List<Transition<KonfiguracijaSlagalice>> apply(KonfiguracijaSlagalice t) {
		
		List<Transition<KonfiguracijaSlagalice>> susjedi = new LinkedList<>();
		int indeksPraznogPolja = t.indexOfSpace();
		
		if(indeksPraznogPolja != 2 && indeksPraznogPolja != 5 && indeksPraznogPolja != 8) {
			susjedi.add(new Transition<KonfiguracijaSlagalice>(new KonfiguracijaSlagalice(t.changeSpace(indeksPraznogPolja + 1)), PRIJELAZ));
		}
		if(indeksPraznogPolja != 0 && indeksPraznogPolja != 3 && indeksPraznogPolja != 6) {
			susjedi.add(new Transition<KonfiguracijaSlagalice>(new KonfiguracijaSlagalice(t.changeSpace(indeksPraznogPolja - 1)), PRIJELAZ));
		}
		if(indeksPraznogPolja < 6) {
			susjedi.add(new Transition<KonfiguracijaSlagalice>(new KonfiguracijaSlagalice(t.changeSpace(indeksPraznogPolja + 3)), PRIJELAZ));
		}
		if(indeksPraznogPolja > 2) {
			susjedi.add(new Transition<KonfiguracijaSlagalice>(new KonfiguracijaSlagalice(t.changeSpace(indeksPraznogPolja - 3)), PRIJELAZ));
		}
		
		return susjedi;
	}
	
	@Override
	public KonfiguracijaSlagalice get() {
		
		return referenca;
	}
	
}
