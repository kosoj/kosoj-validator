package com.kosoj.validation.vlad.rule;

import java.util.Locale;

import com.kosoj.validation.support.MessageSubstitutor;
import org.apache.commons.beanutils.PropertyUtils;
import org.sapia.validator.Rule;
import org.sapia.validator.ValidationContext;

/**
 * User: machain
 * Date: 12.9.12
 * Time: 15:50
 */
public abstract class AbstractRule extends Rule {

	protected String attribute;
	protected MessageSubstitutor messageSubstitutor = new MessageSubstitutor();


	// ACCESSORS
	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}

	public void addParam(final String key, final Object value) {
		messageSubstitutor.addParam(key, value);
	}


	// IMPLEMENTATION
	@Override
	public String getErrorMessageFor(final Locale loc) {
		return messageSubstitutor.getMessage(super.getErrorMessageFor(loc));
	}


	// TOOLS
	protected <T> T getValue(final ValidationContext ctx) {
		final T value = getValue(ctx, qualifiedName(), attribute);

		addParam(MessageSubstitutor.VALUE, value == null ? MessageSubstitutor.NULL : value);

		return value;
	}


	// STATIC
	@SuppressWarnings("unchecked")
	public static <T> T getValue(final ValidationContext ctx, final String qualifiedName, final String attribute) {
		final Object parent = ctx.get();
		if (parent != null) {
			final Object value;
			if (attribute == null) {
				value = parent;
			} else {
				try {
					value = PropertyUtils.getProperty(parent, attribute);
				} catch (final Exception e) {
					throw new IllegalArgumentException("Cannot extract value for validation: attribute=" + attribute + ", qualifiedName=" + qualifiedName, e);
				}
			}
			return (T) value;
		}
		return null;
	}

}
