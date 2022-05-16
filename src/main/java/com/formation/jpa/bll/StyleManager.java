package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.Style;
import com.formation.jpa.dal.StyleDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;


public class StyleManager {

	private StyleDao dao;
	
	public StyleManager(){
		dao = DaoFactory.getStyleDao();
	}
	
	public List<Style> listeStyles(){
		return dao.findAll();
	}
	
	
	public Style trouverStyle(int id){
		return dao.findById(id);
	}

	public void ajoutStyle(Style s) throws Exception{
		
		if (s.getLibelle() != null && !s.getLibelle().trim().equals(""))
			dao.add(s);
		else
			throw new BeanException("Le style doit posséder un libellé");
	}
	
	public void modifierStyle(Style s) throws Exception{
		if (s.getLibelle() != null && !s.getLibelle().trim().equals(""))
			dao.update(s);
		else
			throw new BeanException("Le style doit posséder un libellé");
	}
	
	public void supprimerStyle(Style s) throws Exception{
		dao.delete(s);
	}
	
	public void supprimerStyle(int id) throws Exception{
		System.out.println(id);
		Style s = dao.findById(id);
		System.out.println(s.toString());
		dao.delete(s);
	}
	
	public List<Style> trier(String type){
		List<Style> liste = null;
		switch (type){
		default : liste = dao.findAll();
		}
		return liste;
	}
	
}
