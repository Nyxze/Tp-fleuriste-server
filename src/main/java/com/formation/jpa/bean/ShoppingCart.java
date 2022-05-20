package com.formation.jpa.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(targetEntity = ShoppingCartItem.class, cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy = "shoppingCart")
	private List<ShoppingCartItem> listCartItem;

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", listCartItem=" + listCartItem + "]";
	}

	public ShoppingCart() {
	}

	public ShoppingCart(int id, List<ShoppingCartItem> listCartLine) {
		super();
		this.id = id;
		this.listCartItem = listCartLine;
	}

	@Transient
	public Double getTotalCartPrice() {
		double sum = 0D;
		List<ShoppingCartItem> lineProduct = getListCartItem();
		for (ShoppingCartItem cartItem : lineProduct) {
			sum += cartItem.getLinePrice();
		}
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.listCartItem.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ShoppingCartItem> getListCartItem() {
		return listCartItem;
	}

	public void setListCartItem(List<ShoppingCartItem> listCartLine) {
		this.listCartItem = listCartLine;
	}

}
