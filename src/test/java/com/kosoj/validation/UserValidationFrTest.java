package com.kosoj.validation;


import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import com.kosoj.entity.User;
import org.springframework.test.context.ContextConfiguration;

/**
 * User: machain
 * Date: 20.9.13
 * Time: 11:26
 */
@ContextConfiguration(locations = "classpath:META-INF/test-kosoj-validator-context.xml")
public class UserValidationFrTest extends UserValidationTest {


	@Override
	public void validate_User_Name() {
		final User entity = getUser();

		entity.setName(null);
		final ValidationResult<User> result = validator.validate(entity);
		assertTrue(result.contains("USR501.FR"));
	}

	@Override
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
		assertTrue(result.contains("USR003.FR"));

		entity.setNumber(entity.getNumber().toLowerCase());
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("USR003.FR"));
	}

	@Override
	public User getUser() {
		final User entity = super.getUser();

		entity.setNumber("680005");

		return entity;
	}
}
