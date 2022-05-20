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

import com.formation.jpa.bean.Fleur;
import com.formation.jpa.bean.Plante;
import com.formation.jpa.bll.PlanteManager;

@Path("/plantes")
@Singleton
public class PlanteRs {

	private PlanteManager planteManager;

	public PlanteRs() {
		planteManager = new PlanteManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Plante> getSaison() {
		return planteManager.listePlantes();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Plante getOnePlante(@PathParam("id") int id ) {
		return planteManager.trouverPlante(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postPlante(Plante p) {
		try {
			System.out.println("Ajout de " + p);
			planteManager.ajoutPlante(p);
		} catch (Exception e) {
			System.out.println(p.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removePlante(@PathParam("id") int id) {
		try {
			planteManager.supprimerPlante(id);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void putPlante(Plante s, @PathParam("id") int id) {
		try {
			s.setId(id);
			planteManager.modifierPlante(s);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
	
	@GET
	@Path("price/max")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMaxPrice() {
		return planteManager.trouverPrixMax();
	}

	@GET
	@Path("price/min")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMinPrice() {
		return planteManager.trouverPrixMin();
	}
	
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Plante> getPlantesByQuerryParams(@Context UriInfo info) {
		String search = info.getQueryParameters().getFirst("search");
		String min = info.getQueryParameters().getFirst("minPrice");
		String max = info.getQueryParameters().getFirst("maxPrice");
		System.out.println(max);
		System.out.println(min);
		return planteManager.trouverParQuerryParams(
				search,
				Double.parseDouble(min), 
				Double.parseDouble(max)
				);

	}
}
