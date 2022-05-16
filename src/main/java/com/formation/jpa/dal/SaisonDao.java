package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.Saison;

public interface SaisonDao {
	public void add(Saison s) throws Exception;
	public void delete(Saison s) throws Exception;
	public  void update(Saison s) throws Exception;
	public Saison findById(int id);
	public  List<Saison> findAll();

}
