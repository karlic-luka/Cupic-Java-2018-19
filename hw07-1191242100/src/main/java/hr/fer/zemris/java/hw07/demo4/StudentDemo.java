package hr.fer.zemris.java.hw07.demo4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Demo razred koji predstavlja rad s bazom podataka te u odgovarajucem
 * formatu ispisuje zadane zadatke.
 * @author Luka
 *
 */
public class StudentDemo {

	/**
	 * 
	 * @param args ne koristi se
	 */
	public static void main(String[] args) {
		
		List<String> lines = null;
        try {
            lines = Files.readAllLines(
                    Paths.get("studenti.txt"),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            System.out.println("Nisam mogao pročitati iz datoteke!");
            System.exit(1);
        }
        
        List<StudentRecord> records = convert(lines);
//        for(StudentRecord record : records) {
//        	System.out.println(record);
//        }

        //-------------------------------------------------------------
        ispisiMarginu(1);
        System.out.println("Broj studenata koji imaju više od 25 bodova iz svih ispita zajedno");
        System.out.println(vratiBodovaViseOd25(records));
        
        ispisiMarginu(2);
        System.out.println("Broj studenata koji su kolegij položili s 5.");
        System.out.println(vratiBrojOdlikasa(records));
        
        ispisiMarginu(3);
        List<StudentRecord> listaOdlikasa = vratiListuOdlikasa(records);
        System.out.println("Studenti koji su kolegij položili s 5.");
        listaOdlikasa.forEach(System.out::println);
        
        ispisiMarginu(4);
        List<StudentRecord> odlikasiSortirano = vratiSortiranuListuOdlikasa(records);
        System.out.println("Studenti koji su kolegij položili s 5 sortirani po bodovima silazno.");
        odlikasiSortirano.forEach(System.out::println);
        
//        System.out.println();
//        for(StudentRecord student: odlikasiSortirano) {
//        	System.out.format(student.getIme() + "--> %f%n", student.getUkupanBrojBodova());
//        }
        
        ispisiMarginu(5);
        List<String> nepolozeniJMBAGovi = vratiPopisNepolozenih(records);
        System.out.println("Popis studenata koji nisu polozili kolegij: ");
        nepolozeniJMBAGovi.forEach(System.out::println);
        
        ispisiMarginu(6);
        Map<Integer, List<StudentRecord>> mapaPoOcjenama = razvrstajStudentePoOcjenama(records);
        Set<Entry<Integer, List<StudentRecord>>> kljucevi = mapaPoOcjenama.entrySet();
        
        for(Entry<Integer, List<StudentRecord>> entry : kljucevi) {
        	System.out.println("Popis studenata s ocjenom: " + entry.getKey());
        	entry.getValue().forEach(System.out::println);
        	System.out.println();
        }
        
        ispisiMarginu(7);
        Map<Integer, Integer> mapaPoOcjenama2 = vratiBrojStudenataPoOcjenama(records);
        Set<Entry<Integer, Integer>> kljucevi2 = mapaPoOcjenama2.entrySet();
        for(Entry<Integer, Integer> entry : kljucevi2) {
        	System.out.println("Broj studenata s ocjenom " + entry.getKey() + " je " + entry.getValue());
    
        }
        
        ispisiMarginu(8);
        Map<Boolean, List<StudentRecord>> prolazNeprolaz = razvrstajProlazPad(records);
        Set<Entry<Boolean, List<StudentRecord>>> prosliNeprosli = prolazNeprolaz.entrySet();
        
        for(Entry<Boolean, List<StudentRecord>> entry : prosliNeprosli) {
        	if(entry.getKey()) {
        		System.out.println("Studenti koji su položili kolegij:");
        		entry.getValue().forEach(System.out::println);
        		System.out.println();
        	} else {
        		System.out.println("Studenti koji su pali kolegij:");
        		entry.getValue().forEach(System.out::println);
        		System.out.println();
        	}
        }
        //----------------------------------------------------------------------------------
        
	}

	/**
	 * Pomoćna metoda koja ubacuje pojedinog studenta u listu.
	 * @param lines lista linija - svaka linija predstavlja jednog studenta
	 * @return
	 */
	public static List<StudentRecord> convert(List<String> lines) {
		
		List<StudentRecord> records = new ArrayList<>();
		for(String line : lines) {
			records.add(new StudentRecord(line));
		}
		return records;
	}
	
	/**
	 * @param records lista studenata
	 * @return broj studenata koji su ostvarili vise od 25 bodova na ispitima
	 */
	public static long vratiBodovaViseOd25(List<StudentRecord> records) {
		return records.stream().filter(student -> student.getUkupanBrojBodova() > 25).count();
	}
	
	/**
	 * @param records lista studenata
	 * @return broj studenata koji su ostvarili ocjenu 5
	 */
	public static long vratiBrojOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(student -> student.getOcjena() == 5).count();
	}
	
	/**
	 * @param records lista studenata
	 * @return lista studenata koji su ostvarili ocjenu 5
	 */
	public static List<StudentRecord> vratiListuOdlikasa(List<StudentRecord> records) {
		return records.stream().filter(student -> student.getOcjena() == 5).collect(Collectors.toList());
	}
	
	/**
	 * @param records lista studenata
	 * @return sortirana lista studenata koji su ostvarili ocjenu 5
	 */
	public static List<StudentRecord> vratiSortiranuListuOdlikasa(List<StudentRecord> records) {
		
		return records.stream().filter(student -> student.getOcjena() == 5).
				sorted((s1, s2) -> Double.compare(s2.getUkupanBrojBodova(), s1.getUkupanBrojBodova())).collect(Collectors.toList());
	}
	
	/**
	 * @param records lista studenata
	 * @return lista studenata koji nisu polozili predmet
	 */
	public static List<String> vratiPopisNepolozenih(List<StudentRecord> records) {
		
		return records.stream().filter(student -> student.getOcjena() == 1).
				map(student -> student.getJmbag()).sorted().collect(Collectors.toList());
	}

	/**
	 * @param records lista studenata
	 * @return mapa studenata, pri cemu kljuceve predstavljaju ocjene, a vrijednosti su liste studenata
	 * s odgovarajucom ocjenom
	 */
	public static Map<Integer, List<StudentRecord>> razvrstajStudentePoOcjenama(List<StudentRecord> records) {
	
		return records.stream().collect(Collectors.groupingBy(StudentRecord::getOcjena));
	}
	
	/**
	 * @param records lista studenata
	 * @return mapa studenata, pri cemu kljuceve predstavljaju ocjene, a vrijednost je broj studenata
	 * s odgovarajucom ocjenom
	 */
	public static Map<Integer, Integer> vratiBrojStudenataPoOcjenama(List<StudentRecord> records) {
	
		return records.stream().collect(Collectors.toMap(StudentRecord::getOcjena, prvotna -> 1, 
				(stara, nova) -> stara + nova));
	}
	
	/**
	 * @param records lista studenata
	 * @return mapa studenata, pri cemu kljucevi predstavljaju prolaz/pad, a vrijednost je lista
	 * studenata s odgovarajucim kljucem
	 */
	public static Map<Boolean, List<StudentRecord>> razvrstajProlazPad(List<StudentRecord> records) {
	
		return records.stream().collect(Collectors.partitioningBy(student -> student.getOcjena() > 1));
	}
	
	/**
	 * Pomoćna metoda za ispis.
	 * @param brojZadatka redni broj zadatka
	 */
	public static void ispisiMarginu(int brojZadatka) {
		
		if(brojZadatka != 1) {
			System.out.println();
		}
		System.out.println("Zadatak " + brojZadatka);
		System.out.println("==========");
	}
	
}

