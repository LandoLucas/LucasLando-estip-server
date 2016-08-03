package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Store;
import com.unq.estip.pada.persistence.StoreDAO;

@Transactional
public class StoreService {

	private StoreDAO storeDAO;

	public void setStoreDAO(StoreDAO storeDAO) {
		this.storeDAO = storeDAO;
	}
	
	public void save(String id, String name, String address, String telephone, String cellphone) {
		if(id != null && !id.isEmpty()){
			Store s = storeDAO.findById(Integer.parseInt(id));
			s.setName(name);
			s.setTelephone(telephone);
			s.setCellphone(cellphone);
			s.setAddress(address);
			this.storeDAO.save(s);
		}else{
			this.storeDAO.save(new Store(name, telephone, cellphone, address));
		}
	}
	
	public List<Store> findAll() {
		return this.storeDAO.findAll();
	}

	public void removeStore(int id) {
		this.storeDAO.deleteById(id);
	}

}
