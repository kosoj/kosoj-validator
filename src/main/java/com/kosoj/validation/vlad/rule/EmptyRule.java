package com.kosoj.validation.vlad.rule;

import com.kosoj.validation.support.ValidationUtils;
import org.sapia.validator.ValidationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 16:12
 */
public class EmptyRule extends AbstractRule {
	// STATIC
	private static final Logger LOG = LoggerFactory.getLogger(EmptyRule.class);

	protected boolean checkEmpty = true;


	// IMPLEMENTATION
	@Override
	public void validate(final ValidationContext ctx) {
		try {
			if (checkEmpty != isEmpty(ctx)) {
				ctx.getStatus().error(this);
			}
		} catch (final Exception e) {
			LOG.error("Unexpected excetion occured during evalutaion of Vlad validation rule - " + this.getClass().getSimpleName(), e);
			ctx.getStatus().error(this, e);
		}
	}

	protected boolean isEmpty(final ValidationContext ctx) {
		return ValidationUtils.isEmpty(getValue(ctx));
	}

}
