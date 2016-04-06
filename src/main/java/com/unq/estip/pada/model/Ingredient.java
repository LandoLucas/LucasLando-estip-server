package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "Ingredient")
@Proxy(lazy=true)
public class Ingredient {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ingredient() {
		super();
	}

	public Ingredient(String name) {
		super();
		this.name = name;
	}

}
