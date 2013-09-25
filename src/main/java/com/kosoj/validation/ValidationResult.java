package com.kosoj.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 10:21
 */
public class ValidationResult<E> {
	// STATIC
	private static final String NULL = "<null>";

	private E entity;
	private List<Validation> validations;


	// CONSTRUCTOR
	public ValidationResult(final E entity) {
		this.entity = entity;
	}


	// ACCESSORS
	public List<Validation> getValidations() {
		return new ArrayList<Validation>(validations);
	}

	public void addValidation(final Validation validation) {
		if (validations == null) {
			validations = new ArrayList<Validation>();
		}
		validations.add(validation);
	}

	public boolean isOk() {
		return CollectionUtils.isEmpty(validations);
	}

	public int size() {
		return (validations == null) ? 0 : validations.size();
	}


	public String getEntityName() {
		return (entity == null) ? NULL : entity.getClass().getSimpleName();
	}

	public boolean contains(final String code) {
		if (StringUtils.hasText(code) && !CollectionUtils.isEmpty(validations)) {
			for (final Validation validation : validations) {
				if (code.equalsIgnoreCase(validation.getCode())) {
					return true;
				}
			}
		}

		return false;
	}

}
