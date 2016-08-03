package com.unq.estip.pada.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.model.Unit;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ProductServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ProductService productService;

	public void setStoreService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void find(){
		List<Product> products = productService.findAll();
		assertEquals(0, products.size());
	}
	
	@Test
	public void saveAndRemove(){
		productService.save(null, "p", 10d, 1d, Unit.cg);
		
		List<Product> products = productService.findAll();
		assertEquals(1, products.size());
		
		productService.removeProduct("p", 1d);
		
		products = productService.findAll();
		assertEquals(0, products.size());
	}
	
	@Test
	public void update(){
		productService.save(null, "p", 10d, 1d, Unit.cg);
		
		List<Product> products = productService.findAll();
		productService.save(products.get(0).getId().toString(), "p2", 11d, 2d, Unit.cm);
		
		products = productService.findAll();
		assertEquals(1, products.size());
		Product p = products.get(0);
		assertEquals("p2", p.getName());
		assertEquals(11d, p.getPrice() , 0.00001);
		assertEquals(2d, p.getQuantity() , 0.00001);
		assertEquals(Unit.cm, p.getUnit());
	}
	
}