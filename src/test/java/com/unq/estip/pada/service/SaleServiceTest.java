package com.unq.estip.pada.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Sale;
import com.unq.estip.pada.model.SaleState;
import com.unq.estip.pada.rest.dto.ProductsDTO;
import com.unq.estip.pada.rest.dto.SaleDTO;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class SaleServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private SaleService saleService;

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}

	@Test
	public void findAll(){
		List<Sale> sales = saleService.findAll();
		assertEquals( 0 , sales.size());
	}
	
	@Test
	public void saveAndRemove(){
		List<ProductsDTO> products = new ArrayList<ProductsDTO>();
		products.add(new ProductsDTO(1, "prod", 120d, 1));
		SaleDTO saleDTO = new SaleDTO(null, 1, products, 120d, DateTime.now().toString(), DateTime.now().toString());
		
		saleService.save(saleDTO);
		
		
		List<Sale> sales = saleService.findAll();
		assertEquals( 1 , sales.size());
		
		saleService.removeSale(sales.get(0).getId());
		sales = saleService.findAll();
		assertEquals( 0 , sales.size());
	}
	
	@Test
	public void update(){
		List<ProductsDTO> products = new ArrayList<ProductsDTO>();
		products.add(new ProductsDTO(1, "prod", 120d, 1));
		SaleDTO saleDTO = new SaleDTO(null, 1, products, 120d, DateTime.now().toString(), DateTime.now().toString());
		
		saleService.save(saleDTO);
		
		List<Sale> sales = saleService.findAll();
		Sale s = sales.get(0);
		s.setPrice(240d);
		SaleDTO saleDTO2 = new SaleDTO(s.getId(), 1, products, 240d, DateTime.now().toString(), DateTime.now().toString());
		saleService.save(saleDTO2);
		
		sales = saleService.findAll();
		assertEquals( 1 , sales.size());
		assertEquals( 240d , sales.get(0).getPrice(), 0.0001);
	}

	@Test
	public void changeState(){
		List<ProductsDTO> products = new ArrayList<ProductsDTO>();
		products.add(new ProductsDTO(1, "prod", 120d, 1));
		SaleDTO saleDTO = new SaleDTO(null, 1, products, 120d, DateTime.now().toString(), DateTime.now().toString());
		
		saleService.save(saleDTO);
		
		List<Sale> sales = saleService.findAll();
		assertEquals( SaleState.Pedido , sales.get(0).getState());
		
		saleService.changeState(sales.get(0).getId(), SaleState.Listo);
		sales = saleService.findAll();
		assertEquals( SaleState.Listo , sales.get(0).getState());
	}
}
