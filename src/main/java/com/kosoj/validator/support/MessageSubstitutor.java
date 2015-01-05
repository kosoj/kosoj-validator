package com.kosoj.validator.support;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;


/**
 * User: machain
 * Date: 12.9.12
 * Time: 15:42
 */
public final class MessageSubstitutor {
    // STATIC
    public static final String NULL = "<null>";
    public static final String VALUE = "value";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String PATTERN = "pattern";

    private Map<String, Object> param;


    // IMPLEMENTATION
    public void addParam(final String key, final Object value) {
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        param.put(key, value == null ? NULL : value);
    }

    public String getMessage(final String message) {
        if (message != null) {
            return StrSubstitutor.replace(message, param);
        }

        return message;
    }

}
