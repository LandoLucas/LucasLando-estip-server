package com.unq.estip.pada.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Purchase;
import com.unq.estip.pada.persistence.PurchaseDAO;
import com.unq.estip.pada.persistence.StoreDAO;
import com.unq.estip.pada.rest.dto.PurchaseDTO;

@Transactional
public class PurchaseService {

	private PurchaseDAO purchaseDAO;
	private StoreDAO storeDAO;
	
	public StoreDAO getStoreDAO() {
		return storeDAO;
	}

	public void setStoreDAO(StoreDAO storeDAO) {
		this.storeDAO = storeDAO;
	}

	public PurchaseDAO getPurchaseDAO() {
		return purchaseDAO;
	}

	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	
	public List<Purchase> getAll(){
		return purchaseDAO.findAll();
	}

	public void savePurchase(PurchaseDTO purchase) {
		Purchase p = (purchase.getId() != null) ? purchaseDAO.findById(purchase.getId()) : new Purchase();
		p.setStore(storeDAO.findById(purchase.getStore()));
		p.setPrice(purchase.getPrice());
		p.setDate(new DateTime(purchase.getDate()));

		purchaseDAO.save(p);
	}

	public void delete(Integer id) {
		purchaseDAO.deleteById(id);
	}
	
}
