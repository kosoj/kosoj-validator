package com.kosoj.validation;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import com.kosoj.entity.Contact;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: machain
 * Date: 25.9.13
 * Time: 10:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/kosoj-validator-context.xml")
public class ContactValidationTest {

	@Autowired
	private IEntityValidator validator;

	// TEST
	@Test
	public void validate_Contact_Ok() {
		final ValidationResult<Contact> result = validator.validate(getContact());
		assertTrue(result.isOk());
	}

	@Test
	public void validate_Contact_Type() {
		final Contact entity = getContact();

		// mandatory
		entity.setType(null);
		ValidationResult<Contact> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT001"));

		// codelist
		entity.setType("XXX");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT002"));
	}

	@Test
	public void validate_Contact_Value() {
		final Contact entity = getContact();

		// mandatory
		entity.setValue(null);
		ValidationResult<Contact> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT003"));

		// length
		entity.setValue(StringUtils.repeat("x", 50));
		result = validator.validate(entity);
		assertTrue(result.isOk());

		entity.setValue(StringUtils.repeat("x", 51));
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT005"));
	}

	@Test
	public void validate_Contact_Value_Phone() {
		final Contact entity = new Contact("PHONE", "420555555");

		// ok
		ValidationResult<Contact> result = validator.validate(entity);
		assertTrue(result.isOk());

		// invalid first digit
		entity.setValue("042555555");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT006"));

		// invalid length
		entity.setValue("420555555122");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT006"));
	}

	@Test
	public void validate_Contact_Value_Email() {
		final Contact entity = new Contact("EMAIL", "bobby@peru.net");

		// ok
		ValidationResult<Contact> result = validator.validate(entity);
		assertTrue(result.isOk());

		// invalid
		entity.setValue("bobby@perunet");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT007"));

		entity.setValue("bobbyperu.net");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT007"));

		entity.setValue("bobbyperune.t");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT007"));
	}

	@Test
	public void validate_Contact_Note() {
		final Contact entity = getContact();

		// null
		entity.setNote(null);
		ValidationResult<Contact> result = validator.validate(entity);
		assertTrue(result.isOk());

		// length
		entity.setNote(StringUtils.repeat("x", 80));
		result = validator.validate(entity);
		assertTrue(result.isOk());

		entity.setNote(StringUtils.repeat("x", 81));
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("CNT004"));
	}


	// TOOLS
	public Contact getContact() {
		return new Contact("OTHER", "bruno.ferrari", "Skype");
	}

}
