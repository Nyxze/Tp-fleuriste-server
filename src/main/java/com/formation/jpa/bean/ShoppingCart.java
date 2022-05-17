package com.formation.jpa.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(targetEntity = ShoppingCartItem.class, cascade = CascadeType.ALL)
	private List<ShoppingCartItem> listCartLine;

	public ShoppingCart() {
	}

	public ShoppingCart(int id, List<ShoppingCartItem> listCartLine) {
		super();
		this.id = id;
		this.listCartLine = listCartLine;
	}

	@Transient
	public Double getTotalCartPrice() {
		double sum = 0D;
		List<ShoppingCartItem> lineProduct = getListCartLine();
		for (ShoppingCartItem cartItem : lineProduct) {
			sum += cartItem.getLinePrice();
		}
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.listCartLine.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ShoppingCartItem> getListCartLine() {
		return listCartLine;
	}

	public void setListCartLine(List<ShoppingCartItem> listCartLine) {
		this.listCartLine = listCartLine;
	}

}
