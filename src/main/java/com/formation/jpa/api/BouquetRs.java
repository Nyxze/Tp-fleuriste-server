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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.bll.BouquetManager;
import com.formation.jpa.bll.ShoppingCartItemManager;

@Path("/bouquets")
@Singleton
public class BouquetRs {

	private BouquetManager bouquetManager;

	public BouquetRs() {
		bouquetManager = new BouquetManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bouquet> getAllBouquets() {
		return bouquetManager.listeBouquets();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bouquet getOneBouquet(@PathParam("id") int id) {
		return bouquetManager.trouverBouquet(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postBouquet(Bouquet b) {
		try {
			bouquetManager.ajoutBouquet(b);
		} catch (Exception e) {
			System.out.println(b.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeBouquet(@PathParam("id") int id) {
		try {
			bouquetManager.supprimerBouquet(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putBouquet(Bouquet b, @PathParam("id") int id) {
		try {
			b.setId(id);
			bouquetManager.modifierBouquet(b);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@GET
	@Path("price/max")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMaxPrice() {
		return bouquetManager.trouverPrixMax();
	}

	@GET
	@Path("price/min")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMinPrice() {
		return bouquetManager.trouverPrixMin();
	}

	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bouquet> getBouquetsByQuerryParams(@Context UriInfo info) {
		String search = info.getQueryParameters().getFirst("search");
		String min = info.getQueryParameters().getFirst("minPrice");
		String max = info.getQueryParameters().getFirst("maxPrice");
		String season = info.getQueryParameters().getFirst("season");
		String style = info.getQueryParameters().getFirst("style");
		System.out.println(style);
		System.out.println(season);
		return bouquetManager.trouverParQuerryParams(
				search,
				Double.parseDouble(min), 
				Double.parseDouble(max),
				Integer.parseInt(season),
				Integer.parseInt(style)
				);

	}

}
