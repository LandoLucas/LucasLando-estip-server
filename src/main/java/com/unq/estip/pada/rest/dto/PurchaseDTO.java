package com.unq.estip.pada.rest.dto;


public class PurchaseDTO {
	private Integer id;
	private int store;
	private String date;
	private Double price;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	public PurchaseDTO(Integer id, int store, String date, Double price) {
//		super();
//		this.id = id;
//		this.store = store;
//		this.date = date;
//		this.price = price;
//	}

	public PurchaseDTO() {
		super();
	}
	

}
