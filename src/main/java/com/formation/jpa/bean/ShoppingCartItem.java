package com.formation.jpa.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
public class ShoppingCartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Product product;
	
	

	@NotNull
	private int quantity;
	
	public ShoppingCartItem(int id, Product product, @NotNull int quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}


	public ShoppingCartItem() {
		
		
	}
	
	 @Transient
	 public Double getLinePrice() {
	      return getProduct().getPrice()*getQuantity();
	    }

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "CartLine [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}
	

}
