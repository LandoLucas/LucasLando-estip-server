package com.unq.estip.pada.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.model.Product;
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

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void save(String id, String name, Double price, Double quantity, Unit unit) {
		Product p = Utilities.isVariableSet(id) ? productDAO.findById(Integer.parseInt(id)) : new Product();
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

}
