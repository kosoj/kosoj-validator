package com.kosoj.validator.support;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;

/**
 * User: machain
 * Date: 6.9.13
 * Time: 9:39
 */
public final class ValidationUtils {

    // CONSTRUCTOR
    private ValidationUtils() {
    }


    // TOOLS
    public static boolean isEmpty(final Object value) {
        final boolean isEmpty;
        if (value == null) {
            isEmpty = true;
        } else if (value instanceof Object[] && ((Object[]) value).length == 0) {
            // array
            isEmpty = true;
        } else if (value instanceof Collection<?> && ((Collection<?>) value).size() == 0) {
            // collection
            isEmpty = true;
        } else if (value instanceof Map<?, ?> && ((Map<?, ?>) value).size() == 0) {
            // map
            isEmpty = true;
        } else if (value instanceof String && !StringUtils.hasText((String) value)) {
            // string
            isEmpty = true;
        } else {
            isEmpty = false;
        }

        return isEmpty;
    }

    public static String getString(final Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof String) {
            return (String) value;
        }

        if (value instanceof Enum) {
            return ((Enum) value).name();
        }

        return value.toString();
    }

    public static Date getDate(final Object value) {
        final Date result;
        if (value == null) {
            result = null;
        } else if (value instanceof Date) {
            result = (Date) value;
        } else if (value instanceof DateTime) {
            final DateTime dateTime = (DateTime) value;
            result = new Date(dateTime.getMillis());
        } else if (value instanceof Calendar) {
            final Calendar calendar = (Calendar) value;
            result = calendar.getTime();
        } else if (value instanceof XMLGregorianCalendar) {
            final XMLGregorianCalendar calendar = (XMLGregorianCalendar) value;
            result = calendar.toGregorianCalendar().getTime();
        } else {
            throw new IllegalArgumentException("Unsupported type of date: " + value.getClass().getName());
        }
        return result;
    }

}
