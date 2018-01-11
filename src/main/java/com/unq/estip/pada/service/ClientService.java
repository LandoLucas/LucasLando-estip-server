package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Client;
import com.unq.estip.pada.persistence.ClientDAO;

@Transactional
public class ClientService {

	private ClientDAO clientDAO;

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void save(String id, String name, String lastName, String telephone, String cellphone, String address) {
		if(id != null && !id.isEmpty()){
			Client c = clientDAO.findById(Integer.parseInt(id));
			c.setFirstName(name);
			c.setLastName(lastName);
			c.setTelephone(telephone);
			c.setCellphone(cellphone);
			c.setAddress(address);
			this.clientDAO.save(c);
		}else{
			this.clientDAO.save(new Client(name, lastName, telephone, cellphone, address));
		}
	}
	public List<Client> findAll() {
		return this.clientDAO.findAll();
	}

	public void removeClient(String name, String lastName) {
		List<Client> allClients = this.clientDAO.findAll();
		for(Client c : allClients){
			if(c.getFirstName().equals(name) && c.getLastName().equals(lastName)) {
			    c.setDeleted(true);
			    this.clientDAO.save(c); break; 				
			}
		}
	}

}
