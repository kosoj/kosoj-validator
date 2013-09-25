package com.kosoj.validation;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

import com.kosoj.entity.Person;
import org.springframework.test.context.ContextConfiguration;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 11:41
 */
@ContextConfiguration(locations = "classpath:META-INF/test-kosoj-validator-context.xml")
public class PersonValidationFrTest extends PersonValidationTest {


	@Override
	public void validate_Person_Status() {
		final Person entity = getPerson();

		// mandatory
		entity.setStatus(null);
		ValidationResult<Person> result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("ENT001"));

		// codelist
		entity.setStatus("X");
		result = validator.validate(entity);
		assertEquals(1, result.size());
		assertTrue(result.contains("ENT002.FR"));

		entity.setStatus("O");
		result = validator.validate(entity);
		assertTrue(result.isOk());
	}

}
