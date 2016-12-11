package com.unq.estip.pada.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.unq.estip.pada.errors.LoginException;
import com.unq.estip.pada.service.LoginService;

@Service
@Path("/login")
public class LoginRest {

	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response login(@FormParam("username") String user, @FormParam("password") String password ) {
		try{
			loginService.login(user, password);
			return Response.ok().header("Access-Control-Allow-Origin", "*").build();
		}catch(LoginException e){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
}
