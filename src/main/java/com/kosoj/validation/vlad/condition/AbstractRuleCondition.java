package com.kosoj.validation.vlad.condition;

import com.kosoj.validation.vlad.rule.AbstractRule;
import org.sapia.util.xml.confix.ConfigurationException;
import org.sapia.util.xml.confix.ObjectHandlerIF;
import org.sapia.validator.Validatable;
import org.sapia.validator.ValidationContext;

/**
 * User: machain
 * Date: 20.9.13
 * Time: 12:33
 */
public abstract class AbstractRuleCondition extends AbstractRule implements ObjectHandlerIF {

	protected Validatable validatable;


	// ABSTRACT
	public abstract boolean canValidate(final ValidationContext ctx);


	// OVERRIDEN
	@Override
	public void handleObject(final String itemName, final Object object) throws ConfigurationException {
		if (validatable != null) {
			throw new IllegalArgumentException(qualifiedName() + " rule can only take one rule/ruleset");
		}

		if (!(object instanceof Validatable)) {
			System.out.println(qualifiedName() + " rule only takes a validatable object");
			throw new IllegalArgumentException(qualifiedName() + " rule only takes a validatable object");
		}

		validatable = (Validatable) object;
	}

	@Override
	public void validate(final ValidationContext ctx) {
		if (validatable != null && canValidate(ctx)) {
			validatable.validate(ctx);
		}
	}

}
