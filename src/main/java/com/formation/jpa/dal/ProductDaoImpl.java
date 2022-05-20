package com.formation.jpa.dal;

import javax.persistence.EntityManager;

import com.formation.jpa.bean.Product;

import com.formation.jpa.util.DAOUtil;
import com.formation.jpa.util.dto.ProductData;

public class ProductDaoImpl implements ProductDao {

	public Product findById(ProductData id) {
		EntityManager em = DAOUtil.getEntityManager();
		Product p = em.find(Product.class, id.getId());
		System.out.println(p.toString());
		em.close();
		return p;
	}

}
