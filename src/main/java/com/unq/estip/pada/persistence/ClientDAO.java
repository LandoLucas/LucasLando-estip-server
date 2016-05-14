package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Client;

public class ClientDAO extends HibernateGenericDAO<Client> implements GenericRepository<Client>{

	private static final long serialVersionUID = -7346735387992793907L;

	@Override
	protected Class<Client> getDomainClass() {
		return Client.class;
	}

}
