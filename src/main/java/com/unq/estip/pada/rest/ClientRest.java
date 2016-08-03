package com.unq.estip.pada.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Client;
import com.unq.estip.pada.service.ClientService;

@Service
@Path("/clients")
public class ClientRest {

	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateClient(@FormParam("id") String id, @FormParam("firstName") String name, 
			@FormParam("lastName") String lastName, @FormParam("telephone") String telephone,
			@FormParam("cellphone") String cellphone, @FormParam("address") String address ) {
		
		clientService.save(id, name, lastName, telephone, cellphone, address);
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/remove")
	@Consumes("application/x-www-form-urlencoded")
	public Response removeClient(@FormParam("firstName") String name, @FormParam("lastName") String lastName) {
		clientService.removeClient(name, lastName);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAll() {
    	List<Client> clients = this.clientService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(clients).build();
    }

}
