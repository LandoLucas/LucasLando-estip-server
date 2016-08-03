package com.unq.estip.pada.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "SaleProduct")
@Proxy(lazy = true)
public class SaleProduct {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Product product;
	
	@Column
	private int quantity;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public SaleProduct(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public SaleProduct() {
	}

}
