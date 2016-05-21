package com.unq.estip.pada.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Sale;
import com.unq.estip.pada.service.SaleService;

@Service
@Path("/sales")
public class SaleRest {

	private SaleService saleService;

	public SaleService getSaleService() {
		return saleService;
	}

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	
	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveNewSale(@FormParam("id") Integer id, 
			@FormParam("clientID") Integer clientId, @FormParam("productsID") Map<Integer , Double> productsID, 
			@FormParam("price") String price, @FormParam("date") String date) {
		
		Double priceD = ConversionUtilities.parseDouble(price);
		DateTime dateTime = new DateTime(date);
		
		saleService.save(id, clientId, productsID, priceD, dateTime);

		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllSales() {
    	List<Sale> sales = this.saleService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(sales).build();
    }
	
}
