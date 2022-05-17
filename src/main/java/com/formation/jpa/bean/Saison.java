package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Saison {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	public Saison() {
		
	}
	
	public Saison(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String nom) {
		this.name = nom;
	}

	@Override
	public String toString() {
		return "Saison [id=" + id + ", name=" + name + "]";
	}

	

}
