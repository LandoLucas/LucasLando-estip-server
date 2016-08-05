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
import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.service.ProductService;
import com.unq.estip.pada.utils.Utilities;

/**
 * REST service for Products
 * @author Lucas
 */
@Service
@Path("/products")
public class ProductRest {

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateProduct(@FormParam("id") String id, @FormParam("name") String name, @FormParam("price") String price,
			@FormParam("quantity") String quantity, @FormParam("unit") String unit) {
		
		Double quantityDouble = Utilities.parseDouble(quantity);
		Unit unitEnum = Unit.getEnum(unit);
		
		productService.save(id, name , Double.parseDouble(price), quantityDouble, unitEnum);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllProducts() {
    	List<Product> ps = this.productService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(ps).build();
    }
	
	@POST
	@Path("/remove")
	@Consumes("application/x-www-form-urlencoded")
	public Response removeProduct(@FormParam("name") String name, @FormParam("quantity") String quantity) {
		try{
			productService.removeProduct(name, Utilities.parseDouble(quantity));	
			return Response.ok().header("Access-Control-Allow-Origin", "*").build();
		}catch(DataIntegrityViolationException e){
			return Response.serverError().header("Access-Control-Allow-Origin", "*").entity(ErrorCodes.PRODUCT_IN_USE).build();
		}
	}

	@GET
    @Path("/units")
    @Produces("application/json")
    public Response getAllUnits() {
		Unit[] values = Unit.class.getEnumConstants();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(values).build();
    }
	
}