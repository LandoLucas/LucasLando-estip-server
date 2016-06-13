package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Purchase;

public class PurchaseDAO extends HibernateGenericDAO<Purchase> implements GenericRepository<Purchase>{

	private static final long serialVersionUID = -7346735387992793907L;

	@Override
	protected Class<Purchase> getDomainClass() {
		return Purchase.class;
	}

}
