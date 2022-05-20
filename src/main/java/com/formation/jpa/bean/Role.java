package com.formation.jpa.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	private String name;

	public Role(String name) {
		super();
		this.name = name;
	}

	public Role() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + "]";
	}

}
