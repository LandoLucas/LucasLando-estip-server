package com.unq.estip.pada.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.unq.estip.pada.errors.ErrorCodes;
import com.unq.estip.pada.model.Store;
import com.unq.estip.pada.service.StoreService;

@Service
@Path("/stores")
public class StoresRest {

	private StoreService storeService;

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveStore(@FormParam("id") String id, @FormParam("name") String name, @FormParam("address") String address,
			@FormParam("telephone") String telephone, @FormParam("cellphone") String cellphone) {
		
		storeService.save(id, name, address, telephone, cellphone);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllStores() {
    	List<Store> stores = this.storeService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(stores).build();
    }
	
	@POST
	@Path("/remove")
	@Consumes("application/x-www-form-urlencoded")
	public Response removeStore(@FormParam("id") String id) {
		try{
			storeService.removeStore(Integer.valueOf(id));
			return Response.ok().header("Access-Control-Allow-Origin", "*").build();
		}catch(DataIntegrityViolationException e){
			return Response.serverError().header("Access-Control-Allow-Origin", "*").entity(ErrorCodes.STORE_IN_USE).build();
		}
	}
	
}
