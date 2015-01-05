package com.kosoj.validator.vlad.condition;

import com.kosoj.validator.support.ValidationUtils;

import org.sapia.validator.ValidationContext;

/**
 * Similar to IfDefined. Implementation uses rule !ValidationUtils.isEmpty(value) instead of value != null
 *
 * User: machain
 * Date: 13.9.13
 * Time: 12:15
 */
public class NotEmptyCondition extends AbstractRuleCondition {

    // OVERRIDEN
    @Override
    public boolean canValidate(final ValidationContext ctx) {
        final Object value = getValue(ctx);
        return !ValidationUtils.isEmpty(value);
    }

}
