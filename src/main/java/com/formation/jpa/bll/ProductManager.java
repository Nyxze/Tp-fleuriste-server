package com.formation.jpa.bll;


import com.formation.jpa.bean.Product;

import com.formation.jpa.dal.ProductDao;

import com.formation.jpa.util.DaoFactory;
import com.formation.jpa.util.dto.ProductData;

public class ProductManager {

	private ProductDao dao;

	public ProductManager() {
		dao = DaoFactory.getProductDao();
	}

	public Product trouverProduit(ProductData id) {
		return dao.findById(id);
	}

}
