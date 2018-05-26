package com.unq.estip.pada.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Purchase;
import com.unq.estip.pada.persistence.PurchaseDAO;
import com.unq.estip.pada.persistence.StoreDAO;
import com.unq.estip.pada.rest.dto.PurchaseDTO;
import com.unq.estip.pada.utils.Utilities;

@Transactional
public class PurchaseService {

	private PurchaseDAO purchaseDAO;
	private StoreDAO storeDAO;
	
	public void setStoreDAO(StoreDAO storeDAO) {
		this.storeDAO = storeDAO;
	}

	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	
	public Map<Integer, List<Purchase>> findAllGroupedByYear(){
		Map<Integer, List<Purchase>> m = new HashMap<Integer, List<Purchase>>();
		List<Purchase> purchases = purchaseDAO.findAll();
		
		for(Purchase p : purchases) {
			if(m.containsKey(p.getDate().getYear())) {
				m.get(p.getDate().getYear()).add(p);
			}else {
				List<Purchase> ps = new ArrayList<Purchase>();
				ps.add(p);
				m.put(p.getDate().getYear(), ps);
			}
		}
		
		return m;
	}
	
	public List<Purchase> findAll(){
		return purchaseDAO.findAll();
	}

	public void savePurchase(PurchaseDTO purchase) {
		Purchase p = Utilities.isVariableSet(purchase.getId()) ? purchaseDAO.findById(purchase.getId()) : new Purchase();
		p.setStore(storeDAO.findById(purchase.getStore()));
		p.setPrice(purchase.getPrice());
		p.setDate(new DateTime(purchase.getDate()));

		purchaseDAO.save(p);
	}

	public void remove(Integer id) {
	    Purchase p = purchaseDAO.findById(id);
	    List<Purchase> pp = purchaseDAO.findAll();
	    p.setDeleted(true);
		purchaseDAO.update(p);
	}
	
}
