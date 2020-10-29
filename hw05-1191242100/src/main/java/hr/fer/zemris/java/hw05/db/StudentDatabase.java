/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Class that represents database of students. It contains internal list of
 * student records and returns with O(1) complexity record with jmbag as a key.
 * It filter with 0(n) complexity.
 *  
 */
public class StudentDatabase {
	
	/**
	 * Internal storage of student records
	 */
	private ArrayList<StudentRecord> studentRecordsList;
	
	/**
	 * Map with key=jmbag and value=student record
	 */
	private HashMap<String, StudentRecord> studentRecordsMap;

	/**
	 * Constructor 
	 * @param rows each row represents one student with his records
	 * @throws IllegalArgumentException if row does not have valid arguments 
	 * or student already exists.
	 */
	public StudentDatabase(List<String> rows) {
		Objects.requireNonNull(rows);
		studentRecordsList = new ArrayList<>(rows.size());
        studentRecordsMap = new HashMap<>(rows.size());
        
		for(String row : rows) {
			
			String[] data = row.split("\t");
			if (data.length != 4) {
                throw new IllegalArgumentException("There is invalid number of arguments.");
            }
			
			if(Integer.parseInt(data[3]) < 1 || Integer.parseInt(data[3]) > 5) {
				throw new IllegalArgumentException("Grades are from 1 to 5");
			}
			
			if(studentRecordsMap.containsKey(data[0])) {
				throw new IllegalArgumentException("That student already exists");
			}
			
			StudentRecord student = new StudentRecord(data[0], data[1], data[2], data[3]);
			studentRecordsList.add(student);
			studentRecordsMap.put(data[0], student);
		}
	}
	
	/**
	 * Returns record of student with given jmbag
	 * @param jmbag 
	 * @return record of student with given jmbag
	 */
	public StudentRecord forJMBAG(String jmbag) {
		 
		return studentRecordsMap.get(jmbag);
	}
	
	/**
	 *  loops through all student records in its internal list; it calls 
	 *  accepts method on given filter-object with current record; each record for which accepts returns true is
	 *  added to temporary list and this list is then returned by the filter method
	 * @param filter 
	 * @return list of added students
	 */
	public List<StudentRecord> filter(IFilter filter) {
		
		List<StudentRecord> temporaryList = new ArrayList<>();
		for(StudentRecord student : studentRecordsList) {
			if(filter.accepts(student)) {
				temporaryList.add(student);
			}
		}
		return temporaryList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(studentRecordsList, studentRecordsMap);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentDatabase))
			return false;
		StudentDatabase other = (StudentDatabase) obj;
		return Objects.equals(studentRecordsList, other.studentRecordsList)
				&& Objects.equals(studentRecordsMap, other.studentRecordsMap);
	}
}
