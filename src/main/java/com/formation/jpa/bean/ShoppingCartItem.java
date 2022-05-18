package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public  class ShoppingCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;

	@NotNull
	private int quantity;

	public ShoppingCartItem() {
		System.out.println("Default constructor");

	}

	public ShoppingCartItem(int id, Product product, @NotNull int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	@Transient
	public Double getLinePrice() {
		return getProduct().getPrice() * getQuantity();
	}

	public int getId() {
		System.out.println("getId");
		return id;
	}

	public void setId(int id) {
		System.out.println("setId");
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		System.out.println("setProduct");
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		System.out.println("setQuantity");
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}

}
