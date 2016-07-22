package com.unq.estip.pada.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.rest.dto.ProductsDTO;
import com.unq.estip.pada.rest.dto.SaleDTO;
import com.unq.estip.pada.service.ClientService;
import com.unq.estip.pada.service.ProductService;
import com.unq.estip.pada.service.SaleService;
import com.unq.estip.pada.service.StoreService;

public class DatabaseInitializer {

	private ProductService productService;
	private ClientService clientService;
	private SaleService saleService;
	private StoreService storeService;
	
	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public SaleService getSaleService() {
		return saleService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@PostConstruct
	public void populateDatabase(){
		System.out.println("Populating database");
		
		this.storeService.save(null, "Mercado Juan", "Calle falsa 123", "555-5555", "15-5265-5625");
		this.storeService.save(null, "Tienda Pepe", "", "", "");
		this.storeService.save(null, "Supermercado Coto", "Av 9 de Julio 111", "444-4444", "");
		this.storeService.save(null, "Supermercado Dia %", "", "", "15-1111-1111");
		
		this.productService.save(null, "Brownies", 25d, 1d, Unit.unidad);
		this.productService.save(null, "Lemon Pie mediana", 180d, 1d, Unit.unidad);
		this.productService.save(null, "Cheesecake", 180d, 1d, Unit.unidad);
		this.productService.save(null, "Empanadas arabes", 120d, 12d, Unit.unidad);
		
		this.clientService.save(null, "Lalo", "Landa", "4111-1212", "15-6565-4545", "direccion 1");
		this.clientService.save(null, "Frank", "Grimes", "", "5411-1111-2222", "");
		this.clientService.save(null, "Otto", "", "4222-1212", "", "");
		this.clientService.save(null, "Lionel", "Hutz", "", "", "direccion 4 piso 3");
		
		System.out.println("Database populated");
	}
	
}
