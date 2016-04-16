package com.unq.estip.pada.dao;

import static org.junit.Assert.assertSame;

import org.joda.money.Money;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Ingredient;
import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.persistence.IngredientDAO;
import com.unq.estip.pada.persistence.ProductDAO;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ProductDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private IngredientDAO ingDao;
	
	
	@Test
	public void test(){
		Product prod = new Product();
		prod.setName("cake");
		prod.setPrice( Money.parse("ARS 120.5") );

		dao.save(prod);
		Product prodFromDB = dao.findById(prod.getId());
		
		assertSame(prod , prodFromDB);
	}
	
}
