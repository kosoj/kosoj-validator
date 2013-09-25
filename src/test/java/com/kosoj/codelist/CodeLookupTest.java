package com.kosoj.codelist;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 10:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/kosoj-validator-context.xml")
public class CodeLookupTest {

	private final static String STATUS = "STATUS";

	@Autowired
	private CodeLookup codeLookup;


	@Test
	public void check_CodeList_Status() {
		assertThat(codeLookup.exists(STATUS), is(true));

		final CodeList codelist = codeLookup.getCodelist(STATUS);
		assertThat(codelist, notNullValue());
		assertThat(codelist.size(), is(3));
	}

}
