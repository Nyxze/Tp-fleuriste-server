package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.ShoppingCartItem;
import com.formation.jpa.util.dto.ProductData;

public interface ShoppingCartItemDao {
	
	public void create(ShoppingCartItem s) throws Exception;
	public void delete(ShoppingCartItem s) throws Exception;
	public  void update(ShoppingCartItem s) throws Exception;
	public ShoppingCartItem findById(int id);
	public void createByProductData(ProductData pd) throws Exception;
	public  List<ShoppingCartItem> findAll();
	public List<ShoppingCartItem> findAllGroupBy();

}
