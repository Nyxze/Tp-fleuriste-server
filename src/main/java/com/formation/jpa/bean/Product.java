package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "JoinedProductAbstract")
public abstract class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private double price;

	private int stock;
	private String infos;
	private String urlImg;

	public Product() {

	}

	public Product(String name, double price, int stock, String infos, String urlImg) {

		this.name = name;
		this.price = price;
		this.stock = stock;
		this.infos = infos;
		this.urlImg = urlImg;
	}

	public Product(int id, String name, double price, int stock, String infos, String urlImg) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.infos = infos;
		this.urlImg = urlImg;
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

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", infos=" + infos
				+ ", urlImg=" + urlImg + "]";
	}

}
