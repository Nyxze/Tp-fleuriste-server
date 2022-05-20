package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.Fleur;
import com.formation.jpa.bean.Plante;
import com.formation.jpa.dal.PlanteDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;
import com.formation.jpa.util.Validator;

public class PlanteManager {

	private PlanteDao dao;

	public PlanteManager() {
		dao = DaoFactory.getPlanteDao();
	}

	public List<Plante> listePlantes() {
		return dao.findAll();
	}

	public Plante trouverPlante(int id) {
		return dao.findById(id);
	}

	public void ajoutPlante(Plante p) throws Exception {
		System.out.println(p.toString());
		if (!Validator.isValidString(p.getName())) {
			System.out.println(p.toString());
			throw new BeanException("Le Plante doit posséder un nom");

		}
		if (!Validator.isValidPrice(p.getPrice())) {
			System.out.println(p.toString());
			throw new BeanException("Prix incorrect");

		}
		if (!Validator.isValidStock(p.getStock())) {
			System.out.println(p.toString());
			throw new BeanException("Stock incorrect");

		}

		dao.add(p);

	}

	public void modifierPlante(Plante p) throws Exception {
		if (p.getName() != null && !p.getName().trim().equals(""))
			dao.update(p);
		else
			throw new BeanException("Le Plante doit posséder un libellé");
	}

	public void supprimerPlante(Plante p) throws Exception {
		dao.delete(p);
	}

	public void supprimerPlante(int id) throws Exception {
		System.out.println(id);
		Plante p = dao.findById(id);
		System.out.println(p.toString());
		dao.delete(p);
	}

	public List<Plante> trier(String type) {
		List<Plante> liste = null;
		switch (type) {
		default:
			liste = dao.findAll();
		}
		return liste;
	}

	public double trouverPrixMax() {
		return dao.getPriceMax();

	}

	public double trouverPrixMin() {

		return dao.getPriceMin();
	}

	public List<Plante> trouverParQuerryParams(String search, double min, double max) {

		return dao.findByQuerryParams(search, min, max);

	}

}
