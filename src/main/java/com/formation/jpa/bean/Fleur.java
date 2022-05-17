package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JoinedFleur")
public class Fleur extends Product {
	
	@ManyToOne
	private Saison season;
	private String color;
	
	public  Fleur() {
		super();
	}
	public Fleur(int id, String name, float price, int stock, String infos, String urlImg, String color, Saison season) {
		super(id, name, price, stock, infos, urlImg);
		this.color = color; 
		this.season = season; 
		
	}
	public Fleur(String name, float price, int stock, String infos, String urlImg,String color, Saison season) {
		super(name, price, stock, infos, urlImg);
		this.color = color; 
		this.season = season; 
	}
	
	public Saison getSeason() {
		return season;
	}
	public void setSeason(Saison season) {
		this.season = season;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Fleur [season=" + season + ", color=" + color + "]";
	}
	
	
	
	

}
