package com.unq.estip.pada.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Service
@Path("/login")
public class LoginRest {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response saveOrUpdateClient(@FormParam("username") String user, @FormParam("password") String password ) {
		
		System.out.println(user + " " + password);
		
//		clientService.save(id, name, lastName, telephone, cellphone, address);
		
		if( Math.random() < 0.5){
			System.out.println("ok!");
			return Response.ok().header("Access-Control-Allow-Origin", "*").build();
		}else{
			System.out.println("not ok ");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
		
	}
	

}
