package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.Saison;
import com.formation.jpa.dal.SaisonDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;

public class SaisonManager {

	private SaisonDao dao;

	public SaisonManager() {
		dao = DaoFactory.getSaisonDao();
	}

	public List<Saison> listeSaisons() {
		return dao.findAll();
	}

	public Saison trouverSaison(int id) {
		return dao.findById(id);
	}

	public void ajoutSaison(Saison s) throws Exception {

		if (s.getName() != null && !s.getName().trim().equals(""))
			dao.add(s);
		else
			throw new BeanException("Le Saison doit posséder un libellé");
	}

	public void modifierSaison(Saison s) throws Exception {
		if (s.getName() != null && !s.getName().trim().equals(""))
			dao.update(s);
		else
			throw new BeanException("Le Saison doit posséder un libellé");
	}

	public void supprimerSaison(Saison s) throws Exception {
		dao.delete(s);
	}

	public void supprimerSaison(int id) throws Exception {
		System.out.println(id);
		Saison s = dao.findById(id);
		System.out.println(s.toString());
		dao.delete(s);
	}

	public List<Saison> trier(String type) {
		List<Saison> liste = null;
		switch (type) {
		default:
			liste = dao.findAll();
		}
		return liste;
	}

}
