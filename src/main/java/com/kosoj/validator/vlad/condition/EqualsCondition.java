package com.kosoj.validator.vlad.condition;

import java.util.Set;

import com.kosoj.validator.support.ValidationUtils;

import org.sapia.validator.ValidationContext;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 20.9.13
 * Time: 12:41
 */
public class EqualsCondition extends AbstractRuleCondition {

    protected Set<String> values;


    // ACCESSORS
    public void setValue(final String value) {
        Assert.hasText(value);
        values = split(value);
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
