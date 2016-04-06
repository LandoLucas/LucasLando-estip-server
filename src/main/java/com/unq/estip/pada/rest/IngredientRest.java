package com.unq.estip.pada.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.model.Ingredient;
import com.unq.estip.pada.service.IngredientService;

@Service
@Path("/ingredients")
public class IngredientRest {

	private IngredientService ingredientService;

	public IngredientService getIngredientService() {
		return ingredientService;
	}

	public void setIngredientService(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@POST
	@Path("/save")
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateIngredient(@FormParam("name") String name) {
		ingredientService.save(name);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
    @Path("/ingredient/{id}")
    @Produces("application/json")
    public Response getIngredient(@PathParam("id") String id) {
    	Ingredient i = ingredientService.findById(Integer.parseInt(id));

    	if(i == null) return Response.status(Response.Status.NOT_FOUND).build();
    	
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(i).build();
    }

	
	@GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllIngredients() {
    	List<Ingredient> ingredients = this.ingredientService.findAll();
    	return Response.ok().header("Access-Control-Allow-Origin", "*").entity(ingredients).build();
    }
	
	
}
