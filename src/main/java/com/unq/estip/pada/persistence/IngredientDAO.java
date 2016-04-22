package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.Ingredient;

public class IngredientDAO extends HibernateGenericDAO<Ingredient> implements GenericRepository<Ingredient>{

	private static final long serialVersionUID = -7350513340300006288L;

	@Override
	protected Class<Ingredient> getDomainClass() {
		return Ingredient.class;
	}

	public void removeByName(String name) {
		this.getHibernateTemplate().bulkUpdate("delete from "+getDomainClass().getName()+" where name="+"'"+name+"'");
	}

}
