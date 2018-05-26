package com.unq.estip.pada.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Purchase;
import com.unq.estip.pada.rest.dto.PurchaseDTO;
import com.unq.estip.pada.service.PurchaseService;

@Service
@Path("/purchase")
public class PurchaseRest {

	private PurchaseService purchaseService;

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@GET
    @Path("/grouped")
    @Produces("application/json")
    public Response getAllGroupedByYear() {
		Map<Integer, List<Purchase>> m = this.purchaseService.findAllGroupedByYear();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(m).build();
    }
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAll() {
    	List<Purchase> ps = this.purchaseService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(ps).build();
    }
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(PurchaseDTO purchase) {
		purchaseService.savePurchase(purchase);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	
	@POST
	@Path("/delete")
	@Consumes("application/x-www-form-urlencoded")
	public Response delete(@FormParam("id") String id) {
		purchaseService.remove(Integer.valueOf(id));
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
}
