package com.unq.estip.pada.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Sale;
import com.unq.estip.pada.model.SaleState;
import com.unq.estip.pada.rest.dto.SaleDTO;
import com.unq.estip.pada.service.SaleService;

@Service
@Path("/sales")
public class SaleRest {

	private SaleService saleService;

	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveNewSale(SaleDTO sale) {
		saleService.save(sale);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSale(SaleDTO sale) {
		saleService.save(sale);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllSales() {
    	List<Sale> sales = this.saleService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(sales).build();
    }
	
	@POST
	@Path("/remove")
	@Consumes("application/x-www-form-urlencoded")
	public Response removeSale(@FormParam("id") String id) {
		saleService.removeSale(Integer.valueOf(id));
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/inPreparation")
	@Consumes("application/x-www-form-urlencoded")
	public Response inPreparation(@FormParam("id") String id) {
		saleService.changeState(Integer.valueOf(id) , SaleState.Preparando);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/prepared")
	@Consumes("application/x-www-form-urlencoded")
	public Response prepared(@FormParam("id") String id) {
		saleService.changeState(Integer.valueOf(id) , SaleState.Listo);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/delivered")
	@Consumes("application/x-www-form-urlencoded")
	public Response delivered(@FormParam("id") String id) {
		saleService.changeState(Integer.valueOf(id) , SaleState.Entregado);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@POST
	@Path("/requested")
	@Consumes("application/x-www-form-urlencoded")
	public Response requested(@FormParam("id") String id) {
		saleService.changeState(Integer.valueOf(id) , SaleState.Pedido);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	
}
