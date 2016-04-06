package com.unq.estip.pada.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Product;
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



	@GET
    @Path("/product/{id}")
    @Produces("application/json")
    public Response getProduct(@PathParam("id") String id) {
    	Product p = productService.findById(Integer.parseInt(id));

    	if(p == null) return Response.status(Response.Status.NOT_FOUND).build();
    	
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(p).build();
    }


	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateProduct(@FormParam("name") String name, @FormParam("price") String price) {
		productService.save(name , price);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	


}