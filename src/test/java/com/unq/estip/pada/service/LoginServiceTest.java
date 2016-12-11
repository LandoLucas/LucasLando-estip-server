package com.unq.estip.pada.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class LoginServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Test
	public void password(){
//		System.out.println( loginService.cryptWithMD5("fvalenzuela") );
	}
	
	
}
