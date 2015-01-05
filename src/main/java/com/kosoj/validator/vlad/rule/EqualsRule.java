package com.kosoj.validator.vlad.rule;

import java.util.Set;

import com.kosoj.validator.support.ValidationUtils;

import org.sapia.validator.ValidationContext;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 3.7.12
 * Time: 8:17
 */
public class EqualsRule extends AbstractRule {

    private Set<String> values;
    private boolean negativeRule;


    // ACCESSORS
    public void setValue(final String value) {
        this.values = split(value);
        addParam("values", this.values);
    }

    public void setNegativeRule(final boolean negativeRule) {
        this.negativeRule = negativeRule;
    }


    // IMPLEMENTATION
    @Override
    public void validate(final ValidationContext ctx) {
        Assert.notEmpty(values, "Predefined values cannot be null. Vlad validation of attribute=" + attribute + " has failed");

        final Object value = getValue(ctx);
        if (!ValidationUtils.isEmpty(value) && (negativeRule == values.contains(ValidationUtils.getString(value)))) {
            ctx.getStatus().error(this);
        }
    }

}
