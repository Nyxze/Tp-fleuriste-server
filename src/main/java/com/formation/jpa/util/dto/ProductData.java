package com.formation.jpa.util.dto;

public class ProductData {
	private int id;
	private int quantity;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductData [id=" + id + ", quantity=" + quantity + "]";
	}
	

}
