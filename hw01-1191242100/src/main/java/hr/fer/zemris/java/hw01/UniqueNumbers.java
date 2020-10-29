package hr.fer.zemris.java.hw01;

import java.util.Scanner;

import hr.fer.zemris.java.hw01.UniqueNumbers.TreeNode;

/**
 * Program koji stvara binarno stablo traženja sastavljeno
 * od cijelih brojeva te naposljetku ispisuje čvorove 
 * sortirane uzlazno i silazno.
 * Ispisuje poruku za neprikladan unos te završava unosom riječi "kraj"
 * @author Luka Karlić
 *
 */
public class UniqueNumbers {
	
	/**
	 *Pomoćna struktura koja predstavlja jedan čvor binarnog stabla
	 *s cjelobrojnim vrijednostima.
	 */
	public static class TreeNode {
		TreeNode left;
		TreeNode right;
		int value;
	}
	
	/**
	 * Metoda koja se poziva prilikom izvođenja programa.
	 * Argumenti su objasnjeni u nastavku.
	 * 
	 * @param args Argumenti komandne linije koji se, međutim, ne koriste.
	 */
	public static void main(String[] args) {
		TreeNode korijen = null;
		Scanner unos = new Scanner(System.in);
		while(true) {
			System.out.print("Unesite broj > ");
			
			if(unos.hasNextInt()) {
				int cvor = unos.nextInt();
				if(containsValue(korijen, cvor)) {
					System.out.print("Broj već postoji. Preskačem\n");
					continue;
				}
				korijen = addNode(korijen, cvor);
				System.out.print("Dodano. \n");
			} else {
				String ulaz = unos.next();
				if(ulaz.equals("kraj")) {
					unos.close();
					break;
				}
				System.out.format("'%s' se ne može protumačiti kao cijeli broj!%n", ulaz);
			}
		}
		System.out.format("Ispis od najmanjeg: ");
		uzlazniSort(korijen);
		System.out.format("%nIspis od najvećeg: ");
		silazniSort(korijen);
		
	}
	
	/**
	 * Metoda koja dodaje u stablo novi cvor ako 
	 * vec nije dio stabla.
	 * 
	 * @param trenutni Korijen trenutnog podstabla.
	 * @param cvor Vrijednost cvora koji zelimo ubaciti
	 * @return Novi korijen stabla ukoliko je bilo prazno ili
	 * samo korijen trenutnog podstabla nakon ubacivanja novog cvora
	 */
	public static TreeNode addNode(TreeNode trenutni, int cvor) {
		if(trenutni == null) {
			TreeNode novi = new TreeNode();
			novi.value = cvor;
			return novi;
		}
		if(cvor > trenutni.value) {
			trenutni.right = addNode(trenutni.right, cvor);
		} else if(cvor < trenutni.value) {
			trenutni.left = addNode(trenutni.left, cvor);
		}
		return trenutni;
	}
	
	/**
	 * Metoda koja provjerava nalazi li se zadani <code>cvor</code>
	 * u podstablu stabla s korijenom <code>trenutni</code>
	 * 
	 * @param trenutni Korijen podstabla 
	 * @param cvor Vrijednost cvora ciju prisutnost zelimo ispitati
	 * @return vraca <code>true</code> ukoliko <code>cvor</code>
	 * pripada podstablu <code>trenutni</code>
	 */
	public static boolean containsValue(TreeNode trenutni, int cvor) {
		if(trenutni == null) {
			return false;
		}
		if(trenutni.value == cvor) {
			return true;
		}
		if(cvor > trenutni.value) {
			return containsValue(trenutni.right, cvor);
		}
		return containsValue(trenutni.left, cvor);
	}
	
	/**
	 * Metoda koja izracunava ukupan broj cvorova u podstablu
	 * <code>trenutni</code>
	 * 
	 * @param trenutni Korijen podstabla
	 * @return Ukupan broj cvorova podstabla <code>trenutni</code>
	 */
	public static int treeSize(TreeNode trenutni) {
		if(trenutni == null) {
			return 0;
		}
		return 1 + treeSize(trenutni.left) + treeSize(trenutni.right);
	}
	
	/**
	 * Imitacija inorder obilaska u cilju sortiranja stabla.
	 * 
	 * @param trenutni Korijen podstabla od kojeg krecemo
	 * u obilazak.
	 */
	private static void uzlazniSort(TreeNode trenutni) {
		if(trenutni == null) {
			return;
		}
		uzlazniSort(trenutni.left);
		System.out.format("%s ", trenutni.value);
		uzlazniSort(trenutni.right);
	}
	
	/**
	 * Prerada inorder obilaska kako bi se postigla silazna
	 * sortiranost.
	 * 
	 * @param trenutni Korijen podstabla od kojeg 
	 * krecemo u obilazak.
	 */
	private static void silazniSort(TreeNode trenutni) {
		if(trenutni == null) {
			return;
		}
		silazniSort(trenutni.right);
		System.out.format("%s ", trenutni.value);
		silazniSort(trenutni.left);
	}
}
