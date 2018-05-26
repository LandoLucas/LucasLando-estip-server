package com.unq.estip.pada.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Purchase;
import com.unq.estip.pada.rest.dto.PurchaseDTO;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class PurchaseServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@Test
	public void findAll(){
		Map<Integer, List<Purchase>>purchases = purchaseService.findAllGroupedByYear();
		assertEquals( 0 , purchases.size());
	}
	
	@Test
	public void saveAndRemove(){
		PurchaseDTO purchase = new PurchaseDTO();
		purchase.setPrice(10d);
		purchase.setDate(DateTime.now().toString());
		purchase.setStore(1);
		
		purchaseService.savePurchase(purchase);
		
		Map<Integer, List<Purchase>> purchases = purchaseService.findAllGroupedByYear();
		assertEquals( 1 , purchases.size());
		
		purchaseService.remove(purchases.get(DateTime.now().getYear()).get(0).getId());
		purchases = purchaseService.findAllGroupedByYear();
		assertEquals( 0 , purchases.size());
	}
	
	@Test
	public void update(){
		PurchaseDTO purchase = new PurchaseDTO();
		purchase.setPrice(10d);
		purchase.setDate(DateTime.now().toString());
		purchase.setStore(1);
		
		purchaseService.savePurchase(purchase);
		
		Map<Integer, List<Purchase>> purchases = purchaseService.findAllGroupedByYear();
        assertEquals( 1 , purchases.size());
        
		purchase.setId(purchaseService.findAllGroupedByYear().get(DateTime.now().getYear()).get(0).getId());
		purchase.setPrice(11d);
		
		purchaseService.savePurchase(purchase);
		
		purchases = purchaseService.findAllGroupedByYear();
        assertEquals( 1 , purchases.size());
		assertEquals( 11d , purchases.get(DateTime.now().getYear()).get(0).getPrice(), 0.0001);
	}
	
}
