package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.Bouquet;
import com.formation.jpa.dal.BouquetDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;

public class BouquetManager {

	private BouquetDao dao;

	public BouquetManager() {
		dao = DaoFactory.getBouquetDao();
	}

	public List<Bouquet> listeBouquets() {
		return dao.findAll();
	}

	public Bouquet trouverBouquet(int id) {
		return dao.findById(id);
	}

	public void ajoutBouquet(Bouquet b) throws Exception {

		if (b.getName() != null && !b.getName().trim().equals(""))
			dao.add(b);
		else
			throw new BeanException("Le Bouquet doit posséder un nom");
	}

	public void modifierBouquet(Bouquet b) throws Exception {
		if (b.getName() != null && !b.getName().trim().equals(""))
			dao.update(b);
		else
			throw new BeanException("Le Bouquet doit posséder un nom");
	}

	public void supprimerBouquet(Bouquet b) throws Exception {
		dao.delete(b);
	}

	public void supprimerBouquet(int id) throws Exception {
		System.out.println(id);
		Bouquet b = dao.findById(id);
		System.out.println(b.toString());
		dao.delete(b);
	}

	public List<Bouquet> trier(String type) {
		List<Bouquet> liste = null;
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

	public List<Bouquet> trouverParQuerryParams(String search, double min, double max, int season, int style) {
		if (season == 0 && style == 0) {

			return dao.findByQuerryParams(search, min, max);

		}
		if (style == 0 && season != 0) {
			return dao.findByQuerryParams(search, min, max, season, "season");

		}

		if (style != 0 && season == 0) {

			return dao.findByQuerryParams(search, min, max, style, "style");
		}

		return dao.findByQuerryParams(search, min, max,season,style);

	}

}
