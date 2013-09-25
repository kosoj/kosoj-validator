package com.kosoj.validation;


import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import com.kosoj.entity.PersonalName;
import com.kosoj.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 11:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/kosoj-validator-context.xml")
public class UserValidationTest {

	@Autowired
	protected IEntityValidator validator;

	@Test
	public void validate_User_Ok() {
		final User entity = getUser();

		final ValidationResult<User> result = validator.validate(entity);
		assertTrue(result.isOk());
	}

	@Test
	public void validate_User_Login() {
		final User entity = getUser();

		// mandatory
		entity.setLogin(null);
		ValidationResult<User> result = validator.validate(entity);
		assertTrue(result.contains("USR001"));

		entity.setLogin("");
		result = validator.validate(entity);
		assertTrue(result.contains("USR001"));

		// regex
		entity.setLogin("y");
		result = validator.validate(entity);
		assertTrue(result.isOk());

		entity.setLogin("Y");
		result = validator.validate(entity);
		assertTrue(result.contains("USR002"));

		entity.setLogin("1");
		result = validator.validate(entity);
		assertTrue(result.contains("USR002"));

		entity.setLogin("í");
		result = validator.validate(entity);
		assertTrue(result.contains("USR002"));
	}

	@Test
	public void validate_User_Name() {
		final User entity = getUser();

		// null
		entity.setName(null);
		final ValidationResult<User> result = validator.validate(entity);
		assertTrue(result.isOk());
	}

	@Test
	public void validate_User_Number() {
		final User entity = getUser();

		// null
		entity.setNumber(null);
		ValidationResult<User> result = validator.validate(entity);
		assertTrue(result.isOk());

		// regex
		entity.setNumber("X0");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("USR003"));

		entity.setNumber(entity.getNumber().toLowerCase());
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("USR003"));
	}


	// TOOLS
	public User getUser() {
		final User entity = new User();

		entity.setLogin("kosoj");
		entity.setName(new PersonalName("Machain", "Milan"));
		entity.setNumber("KJ0001");

		return entity;
	}

}
