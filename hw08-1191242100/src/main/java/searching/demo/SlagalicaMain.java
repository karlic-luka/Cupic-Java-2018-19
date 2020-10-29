package searching.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import searching.algorithms.Node;
import searching.algorithms.SearchUtil;
import searching.slagalica.KonfiguracijaSlagalice;
import searching.slagalica.Slagalica;
import searching.slagalica.gui.SlagalicaViewer;
/**
 * @author Luka
 *
 */
public class SlagalicaMain {

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Netočan broj argumenata.");
			System.exit(1);
		} else {
			if(!provjeriUnos(args[0])) {
				System.out.println("Invalid input.");
			}
			int[] polje = new int[9];
			char[] unos = args[0].toCharArray();
			for(int i = 0; i < 9; i++) {
				polje[i] = Integer.parseInt("" + unos[i]);
			}
			Slagalica slagalica = new Slagalica(
					new KonfiguracijaSlagalice(polje)
					);
			Node<KonfiguracijaSlagalice> rješenje =
					SearchUtil.bfs(slagalica, slagalica, slagalica);
			if(rješenje==null) {
				System.out.println("Nisam uspio pronaći rješenje.");
				} else {
				System.out.println(
				"Imam rješenje. Broj poteza je: " + rješenje.getCost()
				);
				SlagalicaViewer.display(rješenje);
				List<KonfiguracijaSlagalice> lista = new ArrayList<>();
				Node<KonfiguracijaSlagalice> trenutni = rješenje;
				while(trenutni != null) {
				lista.add(trenutni.getState());
				trenutni = trenutni.getParent();
				}
				Collections.reverse(lista);
				lista.stream().forEach(k -> {
				System.out.println(k);
				System.out.println();
				});
				}
		
		}

	}

	private static boolean provjeriUnos(String string) {
		
		char[] unos = string.toCharArray();
		int suma = 0;
		Set<Integer> provjera = new HashSet<>();
		try {
			for(int i = 0; i < 9; i++) {
				int broj = Integer.parseInt("" + unos[i]);
				suma  += broj;
				provjera.add(broj);
			}
		} catch(NumberFormatException e) {
			return false;
		}
		if(provjera.size() != 9 || suma != 36) {
			return false;
		}
		return true;
	}
}
