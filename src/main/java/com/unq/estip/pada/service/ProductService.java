package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.persistence.ProductDAO;

/**
 * Service class for Product management
 * @author Lucas
 */
public class ProductService {

	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Transactional
	public void save(String name, Double price, Double quantity, Unit unit) {
		Product product = new Product(name, price, quantity, unit);
		this.productDAO.save(product);
	}

	public List<Product> findAll() {
		return this.productDAO.findAll();
	}

	public void removeProduct(String name, Double quantity) {
		this.productDAO.removeProductByNameAndQuantity(name,quantity);
	}
	
}
