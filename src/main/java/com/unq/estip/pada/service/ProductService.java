package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Product;
import com.unq.estip.pada.model.Purchase;
import com.unq.estip.pada.model.Unit;
import com.unq.estip.pada.persistence.ProductDAO;
import com.unq.estip.pada.utils.Utilities;

/**
 * Service class for Product management
 * @author Lucas
 */
@Transactional
public class ProductService {

	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void save(Integer id, String name, Double price, Double quantity, Unit unit) {
		Product p = Utilities.isVariableSet(id) ? productDAO.findById(id) : new Product();
		p.setName(name);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setUnit(unit);

		this.productDAO.save(p);
	}

	public List<Product> findAll() {
		return this.productDAO.findAll();
	}

	
	public void removeProduct(String name, Double quantity) {
		this.productDAO.removeProductByNameAndQuantity(name,quantity);
	}

	public Product findById(int i) {
		return this.productDAO.findById(i);
	}
	
}
