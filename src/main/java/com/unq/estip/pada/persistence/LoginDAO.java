package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.User;

public class LoginDAO extends HibernateGenericDAO<User> implements GenericRepository<User>{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7332166006065430509L;

	@Override
	protected Class<User> getDomainClass() {
		return User.class;
	}


}
