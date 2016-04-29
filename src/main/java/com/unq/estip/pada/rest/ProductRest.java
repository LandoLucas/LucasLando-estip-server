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

import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.service.ProductService;

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

	public ProductService getProductService() {
		return productService;
	}


	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateProduct(@FormParam("name") String name, @FormParam("price") String price,
			@FormParam("quantity") String quantity, @FormParam("unit") String unit) {
		
		Double quantityDouble = ConversionUtilities.parseDouble(quantity);
		Unit unitEnum = Unit.getEnum(unit);
		
		productService.save(name , Double.parseDouble(price), quantityDouble, unitEnum);
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
		productService.removeProduct(name, ConversionUtilities.parseDouble(quantity));
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}


}