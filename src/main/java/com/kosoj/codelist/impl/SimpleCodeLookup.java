package com.kosoj.codelist.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kosoj.codelist.CodeItem;
import com.kosoj.codelist.CodeList;
import com.kosoj.codelist.CodeLookup;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 9:02
 */
@Component
public class SimpleCodeLookup implements CodeLookup {

	private final Map<String, CodeList> codeLists = new LinkedHashMap<String, CodeList>();


	// ACCESSORS
	public void setCodelists(final Collection<CodeList> codeLists) {
		if (!CollectionUtils.isEmpty(codeLists)) {
			for (final CodeList codeList : codeLists) {
				addCodelist(codeList);
			}
		}
	}

	public void addCodelist(final CodeList item) {
		Assert.notNull(item);
		Assert.hasText(item.getCode());

		codeLists.put(item.getCode(), item);
	}


	// IMPLEMENTATION
	public CodeList getCodelist(final String code) {
		Assert.hasText(code);

		return codeLists.get(code);
	}

	public CodeItem getCodeItem(final String listCode, final String itemCode) {
		final CodeList codeList = getCodelist(listCode);

		if (codeList != null) {
			return codeList.getItem(itemCode);
		}

		return null;
	}

	public boolean exists(final String listCode) {
		return getCodelist(listCode) != null;
	}

	public boolean exists(final String listCode, final String itemCode) {
		return getCodeItem(listCode, itemCode) != null;
	}

}
