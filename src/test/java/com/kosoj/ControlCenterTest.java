package com.kosoj;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.kosoj.codelist.CodeLookup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: machain
 * Date: 24.9.13
 * Time: 10:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/kosoj-validator-context.xml")
public class ControlCenterTest {


	@Test
	public void find_CodeLookup_byClass() {
		final CodeLookup bean = ControlCenter.getBean(CodeLookup.class);
		assertThat(bean, notNullValue());
	}

	@Test
	public void find_CodeLookup_byName() {
		final CodeLookup bean = (CodeLookup) ControlCenter.getBean("codeLookup");
		assertThat(bean, notNullValue());
	}

}
