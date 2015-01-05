package com.kosoj.validator;

/**
 * Created by Milan.Machain on 16.12.2014.
 */
public class ValidationProperty {

    private String key;
    private String value;


    // CONSTRUCTOR
    public ValidationProperty() {
    }

    public ValidationProperty(final String key, final String value) {
        this.key = key;
        this.value = value;
    }


    // ACCESSORS
    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
