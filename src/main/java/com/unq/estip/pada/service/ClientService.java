package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Client;
import com.unq.estip.pada.persistence.ClientDAO;

@Transactional
public class ClientService {

	private ClientDAO clientDAO;

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void save(String id, String name, String lastName, String telephone, String cellphone, String address) {
		if(id != null && !id.isEmpty()){
			this.clientDAO.save(new Client(Integer.parseInt(id), name, lastName, telephone, cellphone, address));
		}else{
			this.clientDAO.save(new Client(name, lastName, telephone, cellphone, address));
		}
	}
	public List<Client> findAll() {
		return this.clientDAO.findAll();
	}

	public void removeClient(String name, String lastName) {
		List<Client> example = this.clientDAO.findByExample(new Client(name, lastName, null, null, null));
		this.clientDAO.delete(example.get(0));
	}

}
