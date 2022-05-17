package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.ShoppingCart;

public interface ShoppingCartDao {
	
	public void create(ShoppingCart s) throws Exception;
	public void delete(ShoppingCart s) throws Exception;
	public  void update(ShoppingCart s) throws Exception;
	public ShoppingCart findById(int id);
	public  List<ShoppingCart> findAll();

}
