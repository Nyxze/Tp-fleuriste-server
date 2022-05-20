package com.formation.jpa.api;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.formation.jpa.bean.Product;
import com.formation.jpa.bean.ShoppingCart;
import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.bean.User;
import com.formation.jpa.bll.ProductManager;
import com.formation.jpa.bll.ShoppingCartItemManager;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.dto.ProductData;

@Path("/cart")
@Singleton
public class ShoppingCartItemRs {

	private ShoppingCartItemManager shoppingCartItemManager;
	private ProductManager productManager;

	@Context
	private HttpServletRequest httpRequest;

	public ShoppingCartItemRs() {
		shoppingCartItemManager = new ShoppingCartItemManager();
		productManager = new ProductManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ShoppingCartItem> getCartItems() throws BeanException {

		if (httpRequest.getSession().getAttribute("user") != null) {
			int shoppingCartId = 0;
			try {
				shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problème lors de la récupération du user de la session");
			}

			return shoppingCartItemManager.listCartItems(shoppingCartId);
		} else {
			ShoppingCart tempShoppingCart = new ShoppingCart();
			try {
				if (httpRequest.getSession().getAttribute("temp_cart") != null) {
					tempShoppingCart = ((ShoppingCart) httpRequest.getSession().getAttribute("temp_cart"));
					System.out.println(tempShoppingCart.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problème lors de la récupération du temp_cart de la session");
			}

			return tempShoppingCart.getListCartItem();

		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postCartItem(ProductData productData) {

		if (httpRequest.getSession().getAttribute("user") != null) {
			System.out.println("temp_cart == ??");
			int shoppingCartId = 0;
			try {
				shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();
				System.out.println("SC ID:" + shoppingCartId);
				shoppingCartItemManager.creerShoppingCartItem(productData, shoppingCartId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new WebApplicationException(Response.Status.CONFLICT);
			}

		} else {
			ShoppingCart tempShoppingCart = new ShoppingCart();

			try {

				if (httpRequest.getSession().getAttribute("temp_cart") == null) {
//
					System.out.println("temp_cart == null");
					httpRequest.getSession().setAttribute("temp_cart", tempShoppingCart);
					List<ShoppingCartItem> tempList = new ArrayList<>();
					tempList.add(createShoppingCartItemForSession(productData));
					tempShoppingCart.setListCartItem(tempList);
					System.out.println(httpRequest.getSession().getAttribute("temp_cart"));

				} else {
					tempShoppingCart = ((ShoppingCart) httpRequest.getSession().getAttribute("temp_cart"));
					List<ShoppingCartItem> cartItemList = tempShoppingCart.getListCartItem();
					if (cartItemList.size() == 0) {
						cartItemList.add(createShoppingCartItemForSession(productData));

					} else {
						System.out.println("NANI ??");
						cartItemList = updateQuantity(cartItemList, productData);
						cartItemList = addCartItem(cartItemList, productData);
						System.out.println(cartItemList.toString());
						tempShoppingCart.setListCartItem(cartItemList);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problème lors de la récupération du temp_cart de la session");
			}

		}

	}

	@DELETE
	@Path("/{id}")
	public void removeCartItem(@PathParam("id") int id) {

		if (httpRequest.getSession().getAttribute("user") != null) {
			int shoppingCartId = 0;
			try {
				shoppingCartId = ((User) httpRequest.getSession().getAttribute("user")).getShoppingCart().getId();
				shoppingCartItemManager.supprimerShoppingCartItemById(id, shoppingCartId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new WebApplicationException(Response.Status.CONFLICT);
			}
		} else {

			ShoppingCart tempShoppingCart = new ShoppingCart();
			try {
				if (httpRequest.getSession().getAttribute("temp_cart") != null) {
					tempShoppingCart = ((ShoppingCart) httpRequest.getSession().getAttribute("temp_cart"));
					List<ShoppingCartItem> tempList = tempShoppingCart.getListCartItem();
					tempList.removeIf(i -> i.getId() == id);
					tempShoppingCart.setListCartItem(tempList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Problème lors de la récupération du temp_cart de la session");
			}

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

	@GET
	@Path("/reset")
	public void resetCart() {
		if (httpRequest.getSession().getAttribute("temp_cart") != null) {
			httpRequest.getSession().setAttribute("temp_cart", null);
			httpRequest.getSession().invalidate();
		}

	}

	private ShoppingCartItem createShoppingCartItemForSession(ProductData productData) {
		ShoppingCartItem item = new ShoppingCartItem();
		item.setId(productData.getId());
		item.setQuantity(productData.getQuantity());
		item.setProduct(productManager.trouverProduit(productData));

		return item;
	}

	private List<ShoppingCartItem> updateQuantity(List<ShoppingCartItem> list, ProductData productData) {
		List<ShoppingCartItem> tempList = new ArrayList<ShoppingCartItem>();
		for (ShoppingCartItem shoppingCartItem : list) {
			if (shoppingCartItem.getId() == productData.getId()) {
				shoppingCartItem.setQuantity(productData.getQuantity());

			}
			tempList.add(shoppingCartItem);
		}
		System.out.println(tempList.toString());

		return tempList;

	}

	

	private List<ShoppingCartItem> addCartItem(List<ShoppingCartItem> list, ProductData productData) {

		ArrayList<Integer> OnlyOne = new ArrayList<Integer>();

		for (ShoppingCartItem scitem : list) {
			OnlyOne.add(Integer.valueOf(scitem.getId()));
		}
		boolean ImCHECK = OnlyOne.contains(Integer.valueOf(productData.getId()));
		if (!ImCHECK) {
			list.add(createShoppingCartItemForSession(productData));
		}

		System.out.println(list.toString());
		return list;

	}
}
