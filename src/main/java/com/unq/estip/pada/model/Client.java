package com.unq.estip.pada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * Representation of a client
 * @author Lucas
 */
@Entity
@Table(name = "Client")
@Proxy(lazy=true)
public class Client {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String telephone;
	
	@Column
	private String cellphone;
	
	@Column
	private String address;

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private void setVars(String firstName, String lastName, String telephone, String cellphone, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.cellphone = cellphone;
		this.address = address;
	}

	public Client(String firstName, String lastName, String telephone, String cellphone, String address) {
		super();
		setVars(firstName, lastName, telephone, cellphone, address);
	}
	
	public Client(){}

}
