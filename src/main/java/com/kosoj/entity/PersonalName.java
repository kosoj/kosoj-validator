package com.kosoj.entity;

/**
 * User: machain
 * Date: 18.9.13
 * Time: 11:42
 */
public class PersonalName {

	private String surname;
	private String firstname;


	// CONSTRUCTORS
	public PersonalName() {
	}

	public PersonalName(final String surname, final String firstname) {
		this();
		setSurname(surname);
		setFirstname(firstname);
	}


	// ACCESSORS
	public String getSurname() {
		return surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
}
