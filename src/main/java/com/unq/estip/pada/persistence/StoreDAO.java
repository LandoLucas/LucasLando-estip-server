package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Store;

public class StoreDAO extends HibernateGenericDAO<Store> implements GenericRepository<Store>{

	private static final long serialVersionUID = -3256418454078994856L;

	@Override
	protected Class<Store> getDomainClass() {
		return Store.class;
	}

}
