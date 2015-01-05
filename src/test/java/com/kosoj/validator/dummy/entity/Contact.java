package com.kosoj.validator.dummy.entity;

/**
 * User: machain
 * Date: 25.9.13
 * Time: 9:20
 */
public class Contact extends EntityRoot {

    private String type;
    private String value;
    private String note;


    // CONSTRUCTORS
    public Contact() {
    }

    public Contact(final String type, final String value) {
        this();
        setType(type);
        setValue(value);
    }

    public Contact(final String type, final String value, final String note) {
        this(type, value);
        setNote(note);
    }


    // ACCESSORS
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

}
