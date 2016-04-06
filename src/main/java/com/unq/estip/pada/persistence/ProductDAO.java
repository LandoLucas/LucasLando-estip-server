package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Product;

/**
 * Data access object for Product entity
 * @author Lucas
 *
 */
public class ProductDAO extends HibernateGenericDAO<Product> implements GenericRepository<Product>{

	private static final long serialVersionUID = -1843885662184992359L;

	@Override
	protected Class<Product> getDomainClass() {
		return Product.class;
	}

	
}
