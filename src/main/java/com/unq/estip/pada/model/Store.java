package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "Store")
@Proxy(lazy=true)
public class Store {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String telephone;
	
	@Column
	private String cellphone;
	
	@Column
	private String address;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Store(Integer id, String name, String telephone, String cellphone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.address = address;
	}

	public Store() {}

	public Store(String name, String telephone, String cellphone, String address) {
		super();
		this.name = name;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.address = address;
	}

	
}
