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

import com.formation.jpa.bean.Fleur;
import com.formation.jpa.bll.FleurManager;


@Path("/fleurs")
@Singleton
public class FleurRs {

	private FleurManager FleurManager;

	public FleurRs() {
		FleurManager = new FleurManager();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fleur> getSaison() {
		return FleurManager.listeFleurs();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postFleur(Fleur f) {
		try {
			System.out.println("Ajout de " + f);
			FleurManager.ajoutFleur(f);
		} catch (Exception e) {
			System.out.println(f.toString());
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}

	@DELETE
	@Path("/{id}")
	public void removeFleur(@PathParam("id") int id) {
		try {
			FleurManager.supprimerFleur(id);
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
			FleurManager.modifierFleur(f);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
	}
}
