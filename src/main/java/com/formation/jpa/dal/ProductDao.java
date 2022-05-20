package com.formation.jpa.dal;



import com.formation.jpa.bean.Product;
import com.formation.jpa.util.dto.ProductData;


public interface ProductDao {
	public Product findById(ProductData pd);


}
