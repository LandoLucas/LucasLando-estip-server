package com.unq.estip.pada.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Client;
import com.unq.estip.pada.model.Sale;
import com.unq.estip.pada.model.SaleProduct;
import com.unq.estip.pada.model.SaleState;
import com.unq.estip.pada.persistence.ClientDAO;
import com.unq.estip.pada.persistence.ProductDAO;
import com.unq.estip.pada.persistence.SaleDAO;
import com.unq.estip.pada.persistence.SaleProductDAO;
import com.unq.estip.pada.rest.dto.ProductsDTO;
import com.unq.estip.pada.rest.dto.SaleDTO;

@Transactional
public class SaleService {

	private SaleDAO saleDAO;
	private ClientDAO clientDAO;
	private ProductDAO productDAO;
	private SaleProductDAO saleProductDAO;
	
	public void setSaleProductDAO(SaleProductDAO saleProductDAO) {
		this.saleProductDAO = saleProductDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void setSaleDAO(SaleDAO saleDAO) {
		this.saleDAO = saleDAO;
	}
	
	public List<Sale> findAll() {
		return this.saleDAO.findAll();
	}
	
	public void save(SaleDTO saleDTO) {
		Client client = clientDAO.findById(saleDTO.getClientID());
		
		List<SaleProduct> saleProducts = new ArrayList<SaleProduct>();
		
		for( ProductsDTO p : saleDTO.getProducts()){
			saleProducts.add( new SaleProduct( productDAO.findById(p.getId()) , p.getQuantity()));
		}
	
		DateTime date = new DateTime(saleDTO.getDate());
		DateTime time = new DateTime(saleDTO.getTime());
		
		date = date.withHourOfDay(time.getHourOfDay());
		date = date.withMinuteOfHour(time.getMinuteOfHour());
		
		Sale sale;
		if(saleDTO.getId() == null){
			sale = new Sale(client, saleProducts, saleDTO.getPrice(), date, SaleState.Pedido, saleDTO.getComment());
		}else{
			sale = saleDAO.findById(saleDTO.getId());
			sale.setClient(client);
			sale.setProducts(saleProducts);
			sale.setPrice(saleDTO.getPrice());
			sale.setDate(date);
			sale.setComment(saleDTO.getComment());
		}
		
		this.saleProductDAO.save(saleProducts);
		this.saleDAO.save(sale);
	}

	public void removeSale(Integer id) {
	    Sale s = saleDAO.findById(id);
	    s.setDeleted(true);
		this.saleDAO.save(s);
	}

	public void changeState(Integer id, SaleState state) {
		Sale s = this.saleDAO.findById(id);
		s.setState(state);
		this.saleDAO.update( s );
	}

	
}
