package com.kosoj.validator.dummy.entity;

import java.util.Date;
import java.util.List;

/**
 * User: machain
 * Date: 15.7.13
 * Time: 11:43
 */
public class Person extends AbstractEntity {

    private String code;
    private PersonalName name;
    private Date birth;
    private Gender gender;
    private List<Contact> contacts;

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public PersonalName getName() {
        return name;
    }

    public void setName(final PersonalName name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(final Date birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(final List<Contact> contacts) {
        this.contacts = contacts;
    }
}
