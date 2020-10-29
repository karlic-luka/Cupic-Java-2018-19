/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for searching database. Only two valid commands - query and exit.
 * Valid input: fieldName operator stringLiteral
 * @author Luka Karlić
 *
 */
public class StudentDB {

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(
                    Paths.get("database.txt"),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            System.out.println("Could not read from file!");
            System.exit(1);
        }

        StudentDatabase database = new StudentDatabase(lines);
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.trim().equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (!input.trim().startsWith("query")) {
                System.out.println("I do not recognize that command.");
                continue;
            }

            QueryParser parser;
            try {
            	//skip "query"
                parser = new QueryParser(input.trim().substring(5));
            } catch (RuntimeException ex) {
                System.out.println("Invalid input");
                continue;
            }
            List<StudentRecord> records;

            if(parser.isDirectQuery()) {
            	System.out.println("Using index for record retrieval.");
                records = new ArrayList<>();
                StudentRecord record = database.forJMBAG(parser.getQueriedJMBAG());
                if(record != null) {
                    records.add(record);
                }

            } else {
            	try {
            		records = database.filter(new QueryFilter(parser.getQuery()));
            	} catch(IllegalArgumentException e) {
            		//more than one wildcard
            		System.out.println(e.getMessage());
            		continue;
            	}
            }
            if (records.size() > 0) {
                System.out.println(formatOutput(records));
            }
            System.out.println("Records selected: " + records.size() + "\n");
        }
        scanner.close();
    }

    /**
     * Helper method used for formatting given input.
     * @param list of student records 
     * @return String output
     */
    private static String formatOutput(List<StudentRecord> records) {
        int jmbagLongest = 0;
        int lastNameLongest = 0;
        int firstNameLongest = 0;

        for (StudentRecord record : records) {
            if (record.getJmbag().length() > jmbagLongest) {
            	jmbagLongest = record.getJmbag().length();
            }
            if (record.getLastName().length() > lastNameLongest) {
            	lastNameLongest = record.getLastName().length();
            }
            if (record.getFirstName().length() > firstNameLongest) {
            	firstNameLongest = record.getFirstName().length();
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(appendBorder(jmbagLongest, lastNameLongest, firstNameLongest));
        builder.append("\n");

        for (StudentRecord record : records) {
        	builder.append("| ");
        	builder.append(record.getJmbag());
        	builder.append(" ".repeat(jmbagLongest - record.getJmbag().length()));
        	builder.append(" | ");
            builder.append(record.getLastName());
            builder.append(" ".repeat(lastNameLongest - record.getLastName().length()));
            builder.append(" | ");
            builder.append(record.getFirstName());
            builder.append(" ".repeat(firstNameLongest - record.getFirstName().length()));
            builder.append(" | ");
            builder.append(record.getFinalGrade());
            builder.append(" |\n");
        }
        builder.append(appendBorder(jmbagLongest, lastNameLongest, firstNameLongest));
        return builder.toString();
    }
    
    private static String appendBorder(int jmbagLongest, int lastNameLongest, int firstNameLongest) {
    	
    	StringBuilder builder = new StringBuilder();
    	builder.append("+");
    	builder.append("=".repeat(jmbagLongest + 2));
    	builder.append("+");
    	builder.append("=".repeat(lastNameLongest + 2));
    	builder.append("+");
    	builder.append("=".repeat(firstNameLongest + 2));
    	builder.append("+===+");
        return builder.toString();
    }
   
}