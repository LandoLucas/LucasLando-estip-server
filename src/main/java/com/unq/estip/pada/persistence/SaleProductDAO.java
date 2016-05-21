package com.unq.estip.pada.persistence;

import com.unq.estip.pada.model.SaleProduct;

public class SaleProductDAO  extends HibernateGenericDAO<SaleProduct> implements GenericRepository<SaleProduct>{

	private static final long serialVersionUID = 6971596930880588647L;

	@Override
	protected Class<SaleProduct> getDomainClass() {
		return SaleProduct.class;
	}

}
