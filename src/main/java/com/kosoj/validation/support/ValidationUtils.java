package com.kosoj.validation.support;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * User: machain
 * Date: 6.9.13
 * Time: 9:39
 */
public final class ValidationUtils {

	// CONSTRUCTOR
	private ValidationUtils() {
	}


	// TOOLS
	public static boolean isEmpty(final Object value) {
		final boolean isEmpty;
		if (value == null) {
			isEmpty = true;
		} else if (value instanceof Object[] && ((Object[]) value).length == 0) {
			// array
			isEmpty = true;
		} else if (value instanceof Collection<?> && ((Collection<?>) value).size() == 0) {
			// collection
			isEmpty = true;
		} else if (value instanceof Map<?, ?> && ((Map<?, ?>) value).size() == 0) {
			// map
			isEmpty = true;
		} else if (value instanceof String && !StringUtils.hasText((String) value)) {
			// string
			isEmpty = true;
		} else {
			isEmpty = false;
		}

		return isEmpty;
	}

	public static String getString(final Object value) {
		if (value == null) {
			return null;
		}

		if (value instanceof String) {
			return (String) value;
		}

		if (value instanceof Enum) {
			return ((Enum) value).name();
		}

		return value.toString();
	}


}
