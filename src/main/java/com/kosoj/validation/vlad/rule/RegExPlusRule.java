package com.kosoj.validation.vlad.rule;

import java.util.Locale;

import com.kosoj.validation.support.MessageSubstitutor;
import org.sapia.validator.rules.RegEx;

/**
 * User: machain
 * Date: 13.9.13
 * Time: 11:49
 */
public class RegExPlusRule extends RegEx {

	protected MessageSubstitutor messageSubstitutor = new MessageSubstitutor();


	// CONSTRUCTOR
	public RegExPlusRule() {
		throwExceptionOnNull(false);
	}


	// OVERIDDEN
	@Override
	public void setPattern(final String pattern) {
		super.setPattern(pattern);
		messageSubstitutor.addParam(MessageSubstitutor.PATTERN, pattern);
	}

	@Override
	protected boolean doValidate(final Object value) {
		messageSubstitutor.addParam(MessageSubstitutor.VALUE, value);

		if (value != null) {
			return super.doValidate(value);
		}

		return true;
	}

	@Override
	public String getErrorMessageFor(final Locale loc) {
		return messageSubstitutor.getMessage(super.getErrorMessageFor(loc));
	}

}
