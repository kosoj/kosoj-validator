package com.kosoj.codelist;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 9:03
 */
public class CodeItem {

	private String code;
	private String descriprion;


	// CONSTRUCTORS
	public CodeItem() {
	}

	public CodeItem(final String code) {
		this();
		setCode(code);
	}

	public CodeItem(final String code, final String descriprion) {
		this(code);
		setDescriprion(descriprion);
	}


	// ACCESSORS
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescriprion() {
		return descriprion;
	}

	public void setDescriprion(final String descriprion) {
		this.descriprion = descriprion;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final CodeItem codeItem = (CodeItem) o;

		if (code != null ? !code.equals(codeItem.code) : codeItem.code != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return code != null ? code.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "CodeItem{" +
				"code='" + code + '\'' +
				'}';
	}

}
