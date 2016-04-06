package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.joda.money.Money;

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
	
	@Column(unique=true)
	private String name;
	
	@Column
	private Money price;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Money getPrice() {
		return price;
	}
	
	public void setPrice(Money price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product() {
		super();
	}

	public Product(String name, Money price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
	
	
}
