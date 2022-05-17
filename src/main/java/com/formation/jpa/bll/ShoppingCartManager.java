package com.formation.jpa.bll;

import java.util.List;

import com.formation.jpa.bean.ShoppingCart;
import com.formation.jpa.bean.Style;
import com.formation.jpa.bean.Product;
import com.formation.jpa.dal.ShoppingCartDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;


public class ShoppingCartManager {

	private ShoppingCartDao dao;
	
	public ShoppingCartManager(){
		dao = DaoFactory.getShoppingCartDao();
	}
	
	
	public List<ShoppingCart> listerCart(){
		return dao.findAll();
	}
	
	
	public ShoppingCart trouverCart(int id){
		return dao.findById(id);
	}

	public void creerCart(ShoppingCart s) throws Exception{
		
		if (s.getListCartLine() != null && s.getListCartLine().size()>0)
			dao.create(null);
		else
			throw new BeanException("Le pannier doit contenir un produit");
	}
	
	public void modifierCart(ShoppingCart s) throws Exception{
		if (s.getListCartLine() != null && s.getListCartLine().size()>0)
			dao.update(s);
		else
			throw new BeanException("Le style doit posséder un libellé");
	}
	
	public void supprimerCart(ShoppingCart s) throws Exception{
		dao.delete(s);
	}
	
	public void supprimerCart(int id) throws Exception{
		System.out.println(id);
		ShoppingCart s = dao.findById(id);
		System.out.println(s.toString());
		dao.delete(s);
	}
	
	public List<ShoppingCart> trier(String type){
		List<ShoppingCart> liste = null;
		switch (type){
		default : liste = dao.findAll();
		}
		return liste;
	}
	
	
	
	
}
