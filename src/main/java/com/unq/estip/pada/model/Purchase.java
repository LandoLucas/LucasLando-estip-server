package com.unq.estip.pada.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "Purchase")
@Proxy(lazy=false)
public class Purchase {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private Store store;
	
	@Column
	private Double price;
	
	@Column @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}


	public Purchase() {
		super();
	}
	
}
