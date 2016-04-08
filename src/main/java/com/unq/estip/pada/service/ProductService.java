package com.unq.estip.pada.service;

import java.util.List;

import org.joda.money.Money;
import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Product;
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

	public void save(Product product) {
		this.productDAO.save(product);
	}

	public Product findById(Integer id) {
		return this.productDAO.findById(id);
	}

	@Transactional
	public void save(String name, String price) {
		Money money = Money.parse("ARS " + price);
		Product product = new Product();
		product.setName(name);
		product.setPrice(money);
		this.save(product);
	}

	public List<Product> findAll() {
		return this.productDAO.findAll();
	}
	
}
