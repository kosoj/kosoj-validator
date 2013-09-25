package com.kosoj.validation.vlad;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.kosoj.validation.IEntityValidator;
import com.kosoj.validation.Validation;
import com.kosoj.validation.ValidationResult;
import com.kosoj.validation.ValidationType;
import org.sapia.validator.Status;
import org.sapia.validator.ValidationErr;
import org.sapia.validator.Vlad;
import org.sapia.validator.config.ConfigException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * User: machain
 * Date: 23.8.13
 * Time: 15:57
 */
public class VladValidator implements IEntityValidator {

	private static final Logger LOG = LoggerFactory.getLogger(VladValidator.class);
	private static final String DELIMITER = ";";

	private final Vlad vlad;
	private final Locale locale;


	// CONSTRUCTOR
	public VladValidator() {
		this(Locale.getDefault());
	}

	public VladValidator(final Locale locale) {
		this.vlad = new Vlad();
		this.locale = locale;
	}


	// ACCESSORS
	public void setResource(final String resource) throws IOException, ConfigException {
		Assert.hasText(resource);
		vlad.load(resource);
	}

	public void setResources(final List<String> resources) throws IOException, ConfigException {
		Assert.notEmpty(resources);
		for (final String resource : resources) {
			setResource(resource);
		}
	}


	// IMPLEMENTATION
	@Override
	public <E> ValidationResult<E> validate(final E entity) {
		return validate(entity, getDefaultRuleSet(entity));
	}

	@Override
	public <E> ValidationResult<E> validate(final E entity, final String rule) {
		Assert.hasText(rule);

		final ValidationResult<E> result = new ValidationResult<E>(entity);
		if (entity == null) {
			return result;
		}

		// invoke sapia vlad validation
		final Status status = vlad.validate(rule, entity, locale);

		if (status.isError()) {
			// no generics, no fun
			@SuppressWarnings("unchecked")
			final List<ValidationErr> errors = (List<ValidationErr>) status.getErrors();
			for (final ValidationErr error : errors) {
				if (error.isThrowable()) {
					LOG.warn("VladValidator.validate - entity=" + result.getEntityName(), error.getThrowable());
				} else {
					result.addValidation(getValidation(error));
				}
			}
		}

		return result;
	}

	// PRIVATE
	private Validation getValidation(final ValidationErr error) {
		final Validation validation = new Validation();

		validation.setCode(error.getId());
		// message is expected in this format: validation;attribute;message
		if (StringUtils.hasText(error.getMsg())) {
			final String[] parts = error.getMsg().split(DELIMITER);
			if (parts.length == 3) {
				validation.setType(ValidationType.valueOf(parts[0]));
				validation.setAttribut(parts[1]);
				validation.setMessage(parts[2]);
			} else {
				LOG.warn("VladValidator.getValidation - unexpected number of message parts (3): " + parts);
			}
		}

		return validation;
	}

	private String getDefaultRuleSet(final Object entity) {
		return "check" + entity.getClass().getSimpleName();
	}

}
