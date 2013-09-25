package com.kosoj.validation.vlad.rule;

import com.kosoj.ControlCenter;
import com.kosoj.codelist.CodeLookup;
import org.apache.commons.lang.StringUtils;
import org.sapia.validator.ValidationContext;
import org.springframework.util.Assert;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 11:15
 */
public class CodeItemRule extends AbstractRule {

	private String codelist;


	// ACCESSORS
	public void setCodelist(final String codelist) {
		Assert.hasText(codelist);
		this.codelist = codelist;
	}


	// IMPLEMENTATION
	@Override
	public void validate(final ValidationContext ctx) {
		final String value = getValue(ctx);
		addParam("codelist", codelist);

		if (StringUtils.isNotBlank(value) && !getLookup().exists(codelist, value)) {
			ctx.getStatus().error(this);
		}
	}


	// TOOLS
	private static CodeLookup getLookup() {
		final CodeLookup lookup = ControlCenter.getBean(CodeLookup.class);

		Assert.notNull(lookup);

		return lookup;
	}

}
