package com.kosoj.validator.dummy.codelist;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 9:55
 */
public interface CodeLookup {

    CodeList getCodelist(String listCode);

    CodeItem getCodeItem(String listCode, String itemCode);

    boolean exists(String listCode);

    boolean exists(String listCode, String itemCode);

}
