package com.kosoj.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;


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
        if (CollectionUtils.isEmpty(validations)) {
            return Collections.emptyList();
        }
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

    public boolean containsId(final String code) {
        if (StringUtils.isNotBlank(code) && !CollectionUtils.isEmpty(validations)) {
            for (final Validation validation : validations) {
                if (code.equalsIgnoreCase(validation.getId())) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<String> getMessages() {
        if (CollectionUtils.isEmpty(validations)) {
            return Collections.emptyList();
        }

        final List<String> result = new ArrayList<String>();

        for (final Validation validation : validations) {
            result.add(validation.getMessage());
        }

        result.removeAll(Collections.singleton(null));

        return result;
    }

    public String getMessagesAsText() {
        final List<String> messages = getMessages();

        if (!CollectionUtils.isEmpty(messages)) {
            return StringUtils.join(messages, System.getProperty("line.separator"));
        }

        return null;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("entity", entity)
                .append("status", isOk())
                .append("validations", validations)
                .toString();
    }
}
