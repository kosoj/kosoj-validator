package com.kosoj.validation.vlad.rule;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 16:59
 */
public class NotEmptyRule extends EmptyRule {

	// CONSTRUCTOR
	public NotEmptyRule() {
		super();
		// sapia is not so spring friendly, this cannot be easy injected
		this.checkEmpty = false;
	}

}
