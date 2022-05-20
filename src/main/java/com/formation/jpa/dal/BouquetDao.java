package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.Bouquet;

public interface BouquetDao {
	public void add(Bouquet s) throws Exception;
	public void delete(Bouquet s) throws Exception;
	public  void update(Bouquet s) throws Exception;
	public Bouquet findById(int id);
	public  List<Bouquet> findAll();
	public double getPriceMax();
	public double getPriceMin();
	public List<Bouquet> findByQuerryParams(String search,double min,double max);
	public List<Bouquet> findByQuerryParams(String search,double min,double max,int season,int style);
	public List<Bouquet> findByQuerryParams(String search,double min,double max,int season, String params);



}
