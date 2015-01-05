package com.kosoj.validator.dummy.codelist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 9:13
 */
public class CodeList extends CodeItem {

    private final Map<String, CodeItem> items = new LinkedHashMap<String, CodeItem>();


    // CONSTRUCTOR
    public CodeList() {
    }

    public CodeList(final String code) {
        super(code);
    }

    public CodeList(final String code, final String description) {
        super(code, description);
    }

    public CodeList(final String code, final String description, final Collection<CodeItem> codeItems) {
        super(code, description);
        setItems(codeItems);
    }


    // ACCESSORS
    public void setItems(final Collection<CodeItem> codeItems) {
        if (!CollectionUtils.isEmpty(codeItems)) {
            for (final CodeItem item : codeItems) {
                addItem(item);
            }
        }
    }

    public Collection<CodeItem> getItems() {
        return ListUtils.unmodifiableList(new ArrayList<CodeItem>(items.values()));
    }

    public void addItem(final CodeItem item) {
        Assert.notNull(item);
        Assert.hasText(item.getCode());

        items.put(item.getCode(), item);
    }

    public CodeItem removeItem(final String code) {
        Assert.hasText(code);

        return items.remove(code);
    }

    public CodeItem getItem(final String code) {
        if (items != null && code != null) {
            return items.get(code);
        }

        return null;
    }

    public boolean exists(final String code) {
        return getItem(code) != null;
    }

    public int reset() {
        final int size = size();
        items.clear();
        return size;
    }

    public int size() {
        return items.size();
    }

}
