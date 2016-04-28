package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * Representation of a product
 * @author Lucas
 *
 */
@Entity
@Table(name = "Product")
@Proxy(lazy=true)
public class Product {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private Double price;
	
	@Column
	private Double quantity;

	@Column
	private Unit unit;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product() {
		super();
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	
	
	public Product(String name, Double price, Double quantity, Unit unit) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	
}
