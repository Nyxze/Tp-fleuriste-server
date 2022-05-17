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
import com.formation.jpa.bll.BouquetManager;


@Path("/bouquets")
@Singleton
public class BouquetRs {

	private BouquetManager BouquetManager;

	public BouquetRs() {
		BouquetManager = new BouquetManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bouquet> getAllBouquets() {
		return BouquetManager.listeBouquets();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Bouquet getOneBouquet(@PathParam("id") int id ) {
		return BouquetManager.trouverBouquet(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postBouquet(Bouquet b) {
		try {
			BouquetManager.ajoutBouquet(b);
		} catch (Exception e) {
			System.out.println(b.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeBouquet(@PathParam("id") int id) {
		try {
			BouquetManager.supprimerBouquet(id);
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
			BouquetManager.modifierBouquet(b);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
	
	
}