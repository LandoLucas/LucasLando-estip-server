package com.unq.estip.pada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Client;
import com.unq.estip.pada.model.Sale;
import com.unq.estip.pada.model.SaleProduct;
import com.unq.estip.pada.model.SaleState;
import com.unq.estip.pada.persistence.ClientDAO;
import com.unq.estip.pada.persistence.ProductDAO;
import com.unq.estip.pada.persistence.SaleDAO;

@Transactional
public class SaleService {

	private SaleDAO saleDAO;
	private ClientDAO clientDAO;
	private ProductDAO productDAO;
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public SaleDAO getSaleDAO() {
		return saleDAO;
	}

	public void setSaleDAO(SaleDAO saleDAO) {
		this.saleDAO = saleDAO;
	}
	
	public List<Sale> findAll() {
		return this.saleDAO.findAll();
	}
	
	public void save(Integer id, Integer clientId, Map<Integer, Double> productsID, Double price, DateTime date) {
		
		Client client = clientDAO.findById(clientId);
		
		List<SaleProduct> saleProducts = new ArrayList<SaleProduct>();
		for( Integer i : productsID.keySet()){
			saleProducts.add( new SaleProduct( productDAO.findById(i) , productsID.get(i)));
		}
		
		Sale sale = new Sale(id, client, saleProducts, price, date, SaleState.Pedido);
		this.saleDAO.save(sale);
	}
	
}
