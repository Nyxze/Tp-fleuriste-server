package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Style {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	
	
	public Style() {
	}
	public Style(String libelle) {
		this.libelle = libelle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	@Override
	public String toString() {
		return "Style [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}
