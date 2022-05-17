package com.formation.jpa.api;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.bean.ShoppingCart;
import com.formation.jpa.bll.ShoppingCartManager;


@Path("/cart")
@Singleton
public class ShoppingCartRs {

	private ShoppingCartManager shoppingCartManager;

	public ShoppingCartRs() {
		shoppingCartManager = new ShoppingCartManager();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingCart> getAllCart() {
		return shoppingCartManager.listerCart();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingCart getOneCart(@PathParam("id") int id ) {
		return shoppingCartManager.trouverCart(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postCart(ShoppingCart b) {
		try {
			shoppingCartManager.creerCart(b);
		} catch (Exception e) {
			System.out.println(b.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeCart(@PathParam("id") int id) {
		try {
			shoppingCartManager.supprimerCart(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putCart(ShoppingCart s, @PathParam("id") int id) {
		try {
			s.setId(id);
			shoppingCartManager.modifierCart(s);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
	
	
}
