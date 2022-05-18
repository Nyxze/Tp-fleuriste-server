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

import com.formation.jpa.bean.Plante;
import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.bll.ShoppingCartItemManager;
import com.formation.jpa.util.dto.ProductData;


@Path("/cart")
@Singleton
public class ShoppingCartItemRs {

	private ShoppingCartItemManager shoppingCartItemManager;

	public ShoppingCartItemRs() {
		shoppingCartItemManager = new ShoppingCartItemManager();
	}
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingCartItem> getCartItems() {
		return shoppingCartItemManager.listCartItems();
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postCartItem(ProductData productData) {
		System.out.println(productData.toString());
		try {
			shoppingCartItemManager.creerShoppingCartItem(productData);
		} catch (Exception e) {
			e.printStackTrace();
		
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeCart(@PathParam("id") int id) {
		try {
			shoppingCartItemManager.supprimerShoppingCartItemById(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putCart(ShoppingCartItem s, @PathParam("id") int id) {
		try {
			s.setId(id);
			shoppingCartItemManager.modifierShoppingCartItem(s);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingCartItem getOneCartItem(@PathParam("id") int id ) {
		return shoppingCartItemManager.trouverCartItem(id);
	}
	
}
