package com.kosoj.validator.vlad.rule;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.kosoj.validator.support.MessageSubstitutor;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.sapia.validator.Rule;
import org.sapia.validator.ValidationContext;

/**
 * User: machain
 * Date: 12.9.12
 * Time: 15:50
 */
public abstract class AbstractRule extends Rule {
    // STATIC
    public static final String DELIMITER = "\\|";

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


    // INTERNAL
    protected <T> T getValue(final ValidationContext ctx) {
        final T value = getValue(ctx, qualifiedName(), attribute);

        addParam(MessageSubstitutor.VALUE, value == null ? MessageSubstitutor.NULL : value);

        return value;
    }

    // STATIC
    protected static Set<String> split(final String value) {
        if (StringUtils.isNoneBlank(value)) {
            final String[] parts = value.split(DELIMITER);
            return new HashSet<String>(Arrays.asList(parts));
        }

        return Collections.emptySet();
    }


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
