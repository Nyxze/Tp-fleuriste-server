package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.Bouquet;

public interface BouquetDao {
	public void add(Bouquet s) throws Exception;
	public void delete(Bouquet s) throws Exception;
	public  void update(Bouquet s) throws Exception;
	public Bouquet findById(int id);
	public  List<Bouquet> findAll();

}
