package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JoinedPlante")
public class Plante  extends Product{

	public Plante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plante(int id, String name, float price, int stock, String infos, String urlImg) {
		super(id, name, price, stock, infos, urlImg);
		// TODO Auto-generated constructor stub
	}

	public Plante(String name, float price, int stock, String infos, String urlImg) {
		super(name, price, stock, infos, urlImg);
		// TODO Auto-generated constructor stub
	}
	

}
