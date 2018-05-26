package com.unq.estip.pada.persistence;

import javax.annotation.PostConstruct;

import com.unq.estip.pada.service.ClientService;
import com.unq.estip.pada.service.ProductService;
import com.unq.estip.pada.service.SaleService;
import com.unq.estip.pada.service.StoreService;

public class DatabaseInitializer {

	private ProductService productService;
	private ClientService clientService;
	private SaleService saleService;
	private StoreService storeService;
	private LoginDAO loginDAO;
	
	public ProductService getProductService() {
		return productService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public SaleService getSaleService() {
		return saleService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@PostConstruct
	public void populateDatabase(){
		
	}
	
}
