package com.kosoj.entity;

/**
 * User: machain
 * Date: 15.7.13
 * Time: 11:22
 */
public class User extends EntityRoot {

	private String login;
	private PersonalName name;
	private String number;    // employee no.


	// CONSTRUCTOR
	public User() {
	}

	public User(final String login) {
		this();
		setLogin(login);
	}

	public User(final String code, final String login) {
		this(code);
		setLogin(login);
	}


	// ACCESSORS
	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public PersonalName getName() {
		return name;
	}

	public void setName(final PersonalName name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	// OVERRIDEN
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		final User user = (User) o;

		if (login != null ? !login.equals(user.login) : user.login != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (login != null ? login.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				", login='" + login + '\'' +
				'}';
	}

}
