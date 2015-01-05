package com.kosoj.validator.vlad.rule;

import java.util.Calendar;
import java.util.Date;

import com.kosoj.validator.support.MessageSubstitutor;
import com.kosoj.validator.support.ValidationUtils;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.sapia.validator.ValidationContext;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 13:42
 */
public class DateIntervalRule extends AbstractRule {
    // STATIC
    public static enum Type {
        DAY, MONTH, YEAR
    }

    // FIELDS
    private Type type = Type.YEAR;
    private Integer lowerBoundary;
    private Integer upperBoundary;


    // ACCESSORS
    public void setType(final String type) {
        this.type = Type.valueOf(type);
    }

    public void setLowerBoundary(final int lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
        addParam("lowerBoundary", lowerBoundary);
    }

    public void setUpperBoundary(final int upperBoundary) {
        this.upperBoundary = upperBoundary;
        addParam("upperBoundary", lowerBoundary);
    }

    // IMPLEMENTATION
    @Override
    public void validate(final ValidationContext ctx) {
        final Date value = ValidationUtils.getDate(getValue(ctx));

        if (value != null) {
            final DateTime date = new DateTime(value);

            addParam(MessageSubstitutor.VALUE, date.toString());
            addParam("type", type);

            // lower
            if (lowerBoundary != null) {
                final DateTime lower = getDateLimit(false);
                if (date.isBefore(lower)) {
                    ctx.getStatus().error(this);
                }
            }

            // upper
            if (upperBoundary != null) {
                final DateTime upper = getDateLimit(true);
                if (date.isAfter(upper)) {
                    ctx.getStatus().error(this);
                }
            }
        }
    }


    // TOOLS
    private DateTime getDateLimit(final boolean upper) {
        final Date date = DateUtils.truncate(new Date(), Calendar.DATE);
        final DateTime now = new DateTime(date);

        switch (type) {
            case YEAR:
                return upper ? now.plusYears(upperBoundary) : now.minusYears(lowerBoundary);
            case MONTH:
                return upper ? now.plusMonths(upperBoundary) : now.minusMonths(lowerBoundary);
            case DAY:
                return upper ? now.plusDays(upperBoundary) : now.minusDays(lowerBoundary);
            default:
                throw new IllegalArgumentException("Unsupported type of time unit: " + type);
        }
    }


}
