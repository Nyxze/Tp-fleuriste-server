package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Bouquet extends Product {
	
	@ManyToOne
	private Saison season;
	private String color;
	
	@ManyToOne
	private Style style;
	
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public  Bouquet() {
		super();
	}
	public Bouquet(int id, String name, float price, int stock, String infos, String urlImg, String color, Saison season, Style style) {
		super(id, name, price, stock, infos, urlImg);
		this.color = color; 
		this.season = season; 
		this.style = style;
		
	}
	public Bouquet(String name, float price, int stock, String infos, String urlImg,String color, Saison season, Style style) {
		super(name, price, stock, infos, urlImg);
		this.color = color; 
		this.season = season; 
		this.style = style;
	}
	
	@Override
	public String toString() {
		return "Bouquet [season=" + season + ", color=" + color + ", style=" + style + "]";
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
	
	
	
	

}
