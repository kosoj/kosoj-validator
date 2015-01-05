package com.kosoj.validator.vlad.rule;

import java.util.Date;

import com.kosoj.validator.support.MessageSubstitutor;
import com.kosoj.validator.support.ValidationUtils;

import org.joda.time.DateTime;
import org.sapia.validator.ValidationContext;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 13:42
 */
public class DateAgeRule extends AbstractRule {
    // STATIC
    public static enum Type {
        DAY, MONTH, YEAR
    }

    // FIELDS
    private Type type = Type.YEAR;
    private int age;


    // ACCESSORS
    public void setType(final String type) {
        this.type = Type.valueOf(type);

    }

    public void setAge(final int age) {
        this.age = age;
        addParam("age", age);
    }


    // IMPLEMENTATION
    @Override
    public void validate(final ValidationContext ctx) {
        final Date date = getDate(getValue(ctx));

        if (date != null) {
            final DateTime dt = new DateTime(date);

            addParam(MessageSubstitutor.VALUE, dt.toString());
            addParam("type", type);
            addParam("age", age);
            final DateTime milestone = getMilestone();
            if (milestone.isBefore(dt)) {
                ctx.getStatus().error(this);
            }
        }
    }


    // INTERNAL
    protected Date getDate(final Object value) {
        return ValidationUtils.getDate(value);
    }

    private DateTime getMilestone() {
        final DateTime now = new DateTime();

        switch (type) {
            case YEAR:
                return now.minusYears(age);
            case MONTH:
                return now.minusMonths(age);
            case DAY:
                return now.minusDays(age);
            default:
                throw new IllegalArgumentException("Unsupported type of time unit: " + type);
        }
    }


}
