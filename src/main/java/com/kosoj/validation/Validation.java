package com.kosoj.validation;

import java.util.Map;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 10:42
 */
public class Validation {

	private String code;
	private ValidationType type;
	private String attribut;
	private String Message;
	private Map<String, String> properties;


	// ACCESSORS
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public ValidationType getType() {
		return type;
	}

	public void setType(final ValidationType type) {
		this.type = type;
	}

	public String getAttribut() {
		return attribut;
	}

	public void setAttribut(final String attribut) {
		this.attribut = attribut;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(final String message) {
		Message = message;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, String> properties) {
		this.properties = properties;
	}

}
