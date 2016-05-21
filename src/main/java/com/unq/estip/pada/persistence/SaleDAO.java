package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Sale;

public class SaleDAO extends HibernateGenericDAO<Sale> implements GenericRepository<Sale>{

	private static final long serialVersionUID = 4904113116712972782L;

	@Override
	protected Class<Sale> getDomainClass() {
		return Sale.class;
	}

}
