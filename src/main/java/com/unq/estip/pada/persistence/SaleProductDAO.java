package com.unq.estip.pada.persistence;

import java.util.List;

import com.unq.estip.pada.model.SaleProduct;

public class SaleProductDAO  extends HibernateGenericDAO<SaleProduct> implements GenericRepository<SaleProduct>{

	private static final long serialVersionUID = 6971596930880588647L;

	@Override
	protected Class<SaleProduct> getDomainClass() {
		return SaleProduct.class;
	}

	public void save(List<SaleProduct> saleProducts) {
		for(SaleProduct sp : saleProducts){
			this.save(sp);
		}
	}

}
