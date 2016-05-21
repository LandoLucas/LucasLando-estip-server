package com.unq.estip.pada.service;

import org.springframework.transaction.annotation.Transactional;

import com.unq.estip.pada.persistence.SaleProductDAO;

@Transactional
public class SaleProductService {

	private SaleProductDAO saleProductDAO;

	public SaleProductDAO getSaleProductDAO() {
		return saleProductDAO;
	}

	public void setSaleProductDAO(SaleProductDAO saleProductDAO) {
		this.saleProductDAO = saleProductDAO;
	}
	
}
