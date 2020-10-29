package hr.fer.zemris.java.hw05.db.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import hr.fer.zemris.java.hw05.db.ComparisonOperators;
import hr.fer.zemris.java.hw05.db.ConditionalExpression;
import hr.fer.zemris.java.hw05.db.FieldValueGetters;
import hr.fer.zemris.java.hw05.db.StudentDatabase;
import hr.fer.zemris.java.hw05.db.StudentRecord;

/**
 * Demo class for testing StudentDatabase class
 * @author Luka
 *
 */
public class StudentDatabaseDemo {

	/**
	 * @param args not used
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		List<String> lines = Files.readAllLines(Paths.get("database.txt"), StandardCharsets.UTF_8);
//        StudentDatabase dataBase = new StudentDatabase(lines);
//        dataBase.forJMBAG("0000000001");
		
		ConditionalExpression expr = new ConditionalExpression(
				 FieldValueGetters.LAST_NAME,
				 "Bos*",
				 ComparisonOperators.LIKE
		);
		StudentRecord record = new StudentRecord("1191242100", "Karlic", "Luka", "5");
		boolean recordSatisfies = expr.getComparisonOperator().satisfied(
				expr.getFieldGetter().get(record), // returns lastName from given record
				 expr.getStringLiteral() // returns "Bos*"
		);
//		System.out.println("AB*".split("\\*")[0].endsWith("*AB".split("\\*")[0]));
        
    
	}
	
	

}
