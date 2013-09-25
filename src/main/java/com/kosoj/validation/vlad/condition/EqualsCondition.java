package com.kosoj.validation.vlad.condition;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.kosoj.validation.support.ValidationUtils;
import org.sapia.validator.ValidationContext;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 20.9.13
 * Time: 12:41
 */
public class EqualsCondition extends AbstractRuleCondition {
	// STATIC
	public static final String DELIMITER = "\\|";

	protected Set<String> values;


	// ACCESSORS
	public void setValue(final String value) {
		Assert.hasText(value);

		final String[] parts = value.split(DELIMITER);
		values = new HashSet<String>(Arrays.asList(parts));
	}


	// IMPLEMENTATION
	@Override
	public boolean canValidate(final ValidationContext ctx) {
		final Object value = getValue(ctx);

		if (value != null && values != null) {
			return values.contains(ValidationUtils.getString(value));
		}

		return false;
	}

}
