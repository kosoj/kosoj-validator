package com.kosoj;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 10:44
 */
public final class ControlCenter implements ApplicationContextAware {

	private static ApplicationContext ctx;


	// CONSTRUCTORS
	private ControlCenter() {
		// SINGLETON
	}


	// IMPLEMENTATION
	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}


	// TOOLS
	public static <T> T getBean(final Class<T> clazz) {
		return ctx.getBean(clazz);
	}

	public static Object getBean(final String name) {
		return ctx.getBean(name);
	}

}
