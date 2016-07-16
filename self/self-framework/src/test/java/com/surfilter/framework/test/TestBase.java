package com.surfilter.framework.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/smcs-config-context.xml",
						"classpath:ctrl/smcs-ctrl-config.xml",
						"classpath:auth/smcs-auth-config.xml"  })

public abstract class TestBase {

	@Test
	public void test() {
		System.out.println("mapping ok!");
	}

}
