package com.formation.jpa.bll;

import java.util.List;


import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.bean.User;
import com.formation.jpa.dal.ShoppingCartItemDao;
import com.formation.jpa.exception.BeanException;
import com.formation.jpa.util.DaoFactory;
import com.formation.jpa.util.dto.ProductData;

public class ShoppingCartItemManager {

	private ShoppingCartItemDao dao;

	public ShoppingCartItemManager() {
		dao = DaoFactory.getShoppingCartItemDao();
	}

	public List<ShoppingCartItem> listCartItems( int shoppingCartId) throws BeanException {
		if(shoppingCartId == 0 ) {
			
			throw new BeanException("TODO: Anonymous");
		}
		return dao.findAllGroupBy(shoppingCartId);
	}
	public ShoppingCartItem trouverCartItem(int id, int shoppingCartId ) {
		return dao.findById(id, shoppingCartId);
	}

	public void creerShoppingCartItem(ProductData productData, int id) throws Exception {
		
		if (productData.getQuantity() >0 && productData.getId()>0)
			dao.createByProductData(productData,id);
		else
			throw new BeanException("Le pannier doit contenir un produit");
	}

	public void modifierShoppingCartItem(ShoppingCartItem s) throws Exception {

		if (s.getProduct() != null)
			dao.update(s);

		else
			throw new BeanException("Le style doit posséder un libellé");
	}


	public void supprimerShoppingCartItemById(int id, int shoppingCartId) throws Exception {
		ShoppingCartItem s = dao.findById(id, shoppingCartId);
		System.out.println(s.toString());
		dao.delete(s,shoppingCartId);
	}




}
