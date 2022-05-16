package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.Fleur;
import com.formation.jpa.dal.FleurDao;
import com.formation.jpa.dal.SaisonDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;


public class FleurManager {

	private FleurDao dao;
	
	public FleurManager(){
		dao = DaoFactory.getFleurDao();
	}
	
	public List<Fleur> listeFleurs(){
		return dao.findAll();
	}
	
	
	public Fleur trouverFleur(int id){
		return dao.findById(id);
	}

	public void ajoutFleur(Fleur f) throws Exception{
		
		if (f.getName() != null && !f.getName().trim().equals(""))
			dao.add(f);
		else
			throw new BeanException("Le Fleur doit posséder un libellé");
	}
	
	public void modifierFleur(Fleur f) throws Exception{
		if (f.getName() != null && !f.getName().trim().equals(""))
			dao.update(f);
		else
			throw new BeanException("Le Fleur doit posséder un libellé");
	}
	
	public void supprimerFleur(Fleur f) throws Exception{
		dao.delete(f);
	}
	
	public void supprimerFleur(int id) throws Exception{
		System.out.println(id);
		Fleur f = dao.findById(id);
		System.out.println(f.toString());
		dao.delete(f);
	}
	
	public List<Fleur> trier(String type){
		List<Fleur> liste = null;
		switch (type){
		default : liste = dao.findAll();
		}
		return liste;
	}
	
}
