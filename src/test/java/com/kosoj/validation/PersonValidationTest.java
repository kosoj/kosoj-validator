package com.kosoj.validation;


import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;
import java.util.Date;

import com.kosoj.entity.Contact;
import com.kosoj.entity.Gender;
import com.kosoj.entity.Person;
import com.kosoj.entity.PersonalName;
import org.joda.time.DateTime;
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
public class PersonValidationTest {

	@Autowired
	protected IEntityValidator validator;

	@Test
	public void validate_Person_Ok() {
		ValidationResult<Person> result = validator.validate(getPerson());
		assertTrue(result.isOk());
	}

	@Test
	public void validate_Person_Code() {
		final Person entity = getPerson();

		// mandatory
		entity.setCode(null);
		ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER001"));

		// male
		entity.setCode("XXX");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER004"));

		// female
		entity.setGender(Gender.FEMALE);
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER005"));

		entity.setCode("00F3214");
		result = validator.validate(entity);
		assertTrue(result.isOk());
	}


	@Test
	public void validate_Person_Gender() {
		final Person entity = getPerson();

		entity.setGender(null);
		final ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER002"));
	}

	@Test
	public void validate_Person_Name() {
		final Person entity = getPerson();

		// mandatory
		entity.setName(null);
		ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER003"));
	}

	@Test
	public void validate_Person_Status() {
		final Person entity = getPerson();

		// mandatory
		entity.setStatus(null);
		ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("ENT001"));

		// regex
		entity.setStatus("X");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("ENT002"));

		entity.setStatus("O");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("ENT002"));
	}

	@Test
	public void validate_Person_Birth() {
		final Person entity = getPerson();

		// mandatory
		entity.setBirth(null);
		ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER006"));

		// age
		entity.setBirth(new Date());
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER007"));
	}

	@Test
	public void validate_Person_Contacts() {
		final Person entity = getPerson();

		// mandatory
		entity.setContacts(null);
		final ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("PER008"));
	}


	// TOOLS
	protected Person getPerson() {
		final Person entity = new Person();

		entity.setCode("98M0005");
		entity.setGender(Gender.MALE);
		entity.setName(new PersonalName("Peru", "Boby"));
		entity.setBirth(new DateTime(1990, 4, 1, 0, 0, 0).toDate());
		entity.setStatus("A");
		entity.setContacts(Arrays.asList(new Contact("OTHER", "bruno.ferrari", "Test")));

		return entity;
	}

}
