/**
 * 
 */
package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * Class that represents one student with his name, grade and jmbag.
 */
public class StudentRecord {

	/**
	 * jmbag
	 */
	private String jmbag;
	
	/**
	 * Student's last name
	 */
	private String lastName;
	
	/**
	 * Student's first name
	 */
	private String firstName;
	
	/**
	 * Student's final grade
	 */
	private String finalGrade;	
	
	/**
	 * constructor
	 * @param jmbag
	 * @param lastName
	 * @param firstName
	 * @param finalGrade
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, String finalGrade) {
		super();
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	/**
	 * @param jmbag the jmbag to set
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param finalGrade the finalGrade to set
	 */
	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 * @return the jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the finalGrade
	 */
	public String getFinalGrade() {
		return finalGrade;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
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
		if (!(obj instanceof StudentRecord))
			return false;
		StudentRecord other = (StudentRecord) obj;
		return Objects.equals(jmbag, other.jmbag);
	}
	
	
}
