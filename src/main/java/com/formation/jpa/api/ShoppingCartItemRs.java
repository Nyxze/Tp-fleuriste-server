package com.formation.jpa.api;

import java.util.List;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.bean.User;
import com.formation.jpa.bll.ShoppingCartItemManager;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.dto.ProductData;

@Path("/cart")
@Singleton
public class ShoppingCartItemRs {

	private ShoppingCartItemManager shoppingCartItemManager;
	
	@Context
	private HttpServletRequest httpRequest;

	public ShoppingCartItemRs() {
		shoppingCartItemManager = new ShoppingCartItemManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingCartItem> getCartItems() throws BeanException  {
		int shoppingCartId = 0;
		try {
			shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Problème lors de la récupération du user de la session");
		}

		return shoppingCartItemManager.listCartItems(shoppingCartId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postCartItem(ProductData productData) {
		int shoppingCartId = 0;
		try {
			shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();
			System.out.println("SC ID:" + shoppingCartId);
			shoppingCartItemManager.creerShoppingCartItem(productData,shoppingCartId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeCartItem( @PathParam("id") int id) {
		int shoppingCartId = 0;
		try {
			shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();
			shoppingCartItemManager.supprimerShoppingCartItemById(id,shoppingCartId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putCartItem(ShoppingCartItem s, @PathParam("id") int id) {
		try {
			s.setId(id);
			shoppingCartItemManager.modifierShoppingCartItem(s);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

}
