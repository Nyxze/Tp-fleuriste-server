package com.formation.jpa.dal;

import java.util.List;


import com.formation.jpa.bean.Plante;

public interface PlanteDao {
	public void add(Plante p) throws Exception;
	public void delete(Plante p) throws Exception;
	public  void update(Plante p) throws Exception;
	public Plante findById(int id);
	public  List<Plante> findAll();
	public double getPriceMax();
	public double getPriceMin();
	public List<Plante> findByQuerryParams(String search,double min,double max);

}
