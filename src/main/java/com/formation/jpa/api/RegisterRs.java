package com.formation.jpa.api;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.formation.jpa.bean.User;
import com.formation.jpa.bll.UserManager;
import com.formation.jpa.util.dto.UserDto;

@Path("/user")
@Singleton
public class RegisterRs {

	private UserManager userManager = new UserManager();

	@Context
	private HttpServletRequest httpRequest;


	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(User u) {
		try {
			userManager.ajoutUser(u);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(u.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}

		return u.getUsername();

	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String connectUser(UserDto u) {
		try {
			
			if(httpRequest.getSession().getAttribute("user")== null  && userManager.connecterUser(u)) {
				User user = userManager.trouverUserParNom(u.getUsername());
				httpRequest.getSession().setAttribute("user",user );
			}
			System.out.println(httpRequest.getSession().getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
		
		return  ((User) httpRequest.getSession().getAttribute("user")).getRole().getName();
	}
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String testLogin() {
		User u = (User) httpRequest.getSession().getAttribute("user");
		System.out.println(u);
		return u.getUsername();
		
	}
	

	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public void deconnexion() {
		httpRequest.getSession().setAttribute("user", null);
		httpRequest.getSession().invalidate();
	}
	

}
