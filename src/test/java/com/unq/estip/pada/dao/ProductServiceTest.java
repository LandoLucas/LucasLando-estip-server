package com.unq.estip.pada.dao;

import static org.junit.Assert.assertSame;

import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.service.ProductService;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ProductServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ProductService service;
	
	@Test
	public void test(){
		Product prod = new Product("cake", Money.parse("ARS 120.4"));
		service.save(prod);
		
		Product prodFromDB = service.findById(prod.getId());
		
		assertSame(prod , prodFromDB);
	}
	
}
