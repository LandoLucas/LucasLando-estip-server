package com.unq.estip.pada.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "Sale")
@Proxy(lazy = true)
public class Sale {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private Client client;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SaleProduct> products;
	
	@Column
	private Double price;
	
	@Column @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;
	
	@Column
	private SaleState state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<SaleProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SaleProduct> products) {
		this.products = products;
	}

	public SaleState getState() {
		return state;
	}

	public void setState(SaleState state) {
		this.state = state;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Sale(Integer id, Client client, List<SaleProduct> products, Double price, DateTime date, SaleState state) {
		super();
		this.id = id;
		this.client = client;
		this.products = products;
		this.price = price;
		this.date = date;
		this.state = state;
	}

	public Sale() {
		super();
	}

}
