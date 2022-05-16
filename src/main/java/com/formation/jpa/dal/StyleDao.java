package com.formation.jpa.dal;

import java.util.List;

import com.formation.jpa.bean.Style;

public interface StyleDao {
	public void add(Style s) throws Exception;
	public void delete(Style s) throws Exception;
	public  void update(Style s) throws Exception;
	public Style findById(int id);
	public  List<Style> findAll();

}
