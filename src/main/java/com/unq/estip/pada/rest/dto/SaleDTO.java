package com.unq.estip.pada.rest.dto;

import java.util.List;

public class SaleDTO {

	private Integer id;
	private int clientID;
	private List<ProductsDTO> products;
	private Double price;
	private String date;
	private String time;
	private String comment;

	

	public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<ProductsDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsDTO> products) {
		this.products = products;
	}


	public SaleDTO(Integer id, int clientID, List<ProductsDTO> products, Double price, String date, String time,
            String comment) {
        super();
        this.id = id;
        this.clientID = clientID;
        this.products = products;
        this.price = price;
        this.date = date;
        this.time = time;
        this.comment = comment;
    }

    public SaleDTO(){}
	
}
