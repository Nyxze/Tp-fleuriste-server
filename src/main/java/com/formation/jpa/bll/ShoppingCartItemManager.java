package com.formation.jpa.bll;

import java.util.List;


import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.dal.ShoppingCartItemDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;
import com.formation.jpa.util.dto.ProductData;

public class ShoppingCartItemManager {

	private ShoppingCartItemDao dao;

	public ShoppingCartItemManager() {
		dao = DaoFactory.getShoppingCartItemDao();
	}

	public List<ShoppingCartItem> listCartItems() {
		return dao.findAllGroupBy();
	}
	public ShoppingCartItem trouverCartItem(int id ) {
		return dao.findById(id);
	}

	public void creerShoppingCartItem(ProductData productData) throws Exception {
		
		if (productData.getQuantity() >0 && productData.getId()>0)
			dao.createByProductData(productData);
		else
			throw new BeanException("Le pannier doit contenir un produit");
	}

	public void modifierShoppingCartItem(ShoppingCartItem s) throws Exception {

		if (s.getProduct() != null)
			dao.update(s);

		else
			throw new BeanException("Le style doit posséder un libellé");
	}

	public void supprimerShoppingCartItem(ShoppingCartItem s) throws Exception {
		dao.delete(s);
	}

	public void supprimerShoppingCartItemById(int id) throws Exception {
		System.out.println(id);
		ShoppingCartItem s = dao.findById(id);
		System.out.println(s.toString());
		dao.delete(s);
	}


	public List<ShoppingCartItem> trier(String type) {
		List<ShoppingCartItem> liste = null;
		switch (type) {
		default:
			liste = dao.findAll();
		}
		return liste;
	}

}
