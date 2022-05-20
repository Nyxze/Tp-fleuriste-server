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
import com.formation.jpa.bean.Fleur;
import com.formation.jpa.bll.FleurManager;


@Path("/fleurs")
@Singleton
public class FleurRs {

	private FleurManager fleurManager;

	public FleurRs() {
		fleurManager = new FleurManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fleur> getAllFleurs() {
		return fleurManager.listeFleurs();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Fleur getOneFleur(@PathParam("id") int id ) {
		return fleurManager.trouverFleur(id);
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postFleur(Fleur f) {
		try {
			fleurManager.ajoutFleur(f);
		} catch (Exception e) {
			System.out.println(f.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeFleur(@PathParam("id") int id) {
		try {
			fleurManager.supprimerFleur(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putFleur(Fleur f, @PathParam("id") int id) {
		try {
			f.setId(id);
			fleurManager.modifierFleur(f);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
	
	@GET
	@Path("price/max")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMaxPrice() {
		return fleurManager.trouverPrixMax();
	}

	@GET
	@Path("price/min")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMinPrice() {
		return fleurManager.trouverPrixMin();
	}
	
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fleur> getFleurByQuerryParams(@Context UriInfo info) {
		String search = info.getQueryParameters().getFirst("search");
		String min = info.getQueryParameters().getFirst("minPrice");
		String max = info.getQueryParameters().getFirst("maxPrice");
		String season = info.getQueryParameters().getFirst("season");
		return fleurManager.trouverParQuerryParams(
				search,
				Double.parseDouble(min), 
				Double.parseDouble(max),
				Integer.parseInt(season)
				);

	}

}
