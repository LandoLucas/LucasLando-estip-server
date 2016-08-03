package com.unq.estip.pada.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.unq.estip.pada.model.Client;

@ContextConfiguration(locations = {"classpath:spring-base-context.xml"})
public class ClientServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@Test
	public void findAll(){
		List<Client> clients = clientService.findAll();
		assertEquals( 0 , clients.size());
	}
	
	@Test
	public void saveAndRemove(){
		clientService.save(null, "FirstName", "LastName", "1234", "123", "address");
		
		List<Client> clients = clientService.findAll();
		assertEquals( 1 , clients.size());
		
		clientService.removeClient("FirstName", "LastName");
		
		clients = clientService.findAll();
		assertEquals( 0 , clients.size());
	}
	

	@Test
	public void update(){
		clientService.save(null, "FirstName", "LastName", "1234", "123", "address");
		
		List<Client> clients = clientService.findAll();
		Client c = clients.get(0);
		
		clientService.save(c.getId().toString(), "NewName", "LastName", "1234", "123", "address");
		
		clients = clientService.findAll();
		assertEquals( 1 , clients.size());
		assertEquals( "NewName" , clients.get(0).getFirstName());
		
		clientService.removeClient("NewName", "LastName");
	}
	
	
	
}
