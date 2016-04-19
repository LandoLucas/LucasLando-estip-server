package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Column
	private Double price;
	
	@Column
	private Double quantity;
	
	@Column
	private Unit unit;
	
	@Column
	private String brand;
	
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Ingredient(String name, Double price, Double quantity, String brand, Unit unit) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
		this.brand = brand;
	}

	@Enumerated(EnumType.STRING)
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	
	
	
}
