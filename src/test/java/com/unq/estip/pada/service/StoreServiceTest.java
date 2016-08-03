package com.unq.estip.pada.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Store;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class StoreServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private StoreService storeService;

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@Test
	public void find(){
		List<Store> stores = storeService.findAll();
		assertEquals(0, stores.size());
	}
	
	@Test
	public void saveAndRemove(){
		storeService.save(null, "name", "a", "123", "456");
		
		List<Store> stores = storeService.findAll();
		assertEquals(1, stores.size());
		
		storeService.removeStore(stores.get(0).getId());
		stores = storeService.findAll();
		assertEquals(0, stores.size());
	}
	
	@Test
	public void update(){
		storeService.save(null, "name", "a", "123", "456");
		List<Store> stores = storeService.findAll();
		
		storeService.save(stores.get(0).getId().toString(), "name2", "b", "111", "222");

		stores = storeService.findAll();
		assertEquals(1, stores.size());
		Store s = stores.get(0);
		assertEquals("name2" , s.getName());
		assertEquals("b" , s.getAddress());
		assertEquals("111" , s.getTelephone());
		assertEquals("222" , s.getCellphone());
		
		storeService.removeStore(stores.get(0).getId());
		stores = storeService.findAll();
		assertEquals(0, stores.size());
	}
	
}
