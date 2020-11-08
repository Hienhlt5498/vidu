package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.entity.InvoiceDetail;
import com.thucpham.shopweb.repository.InvoiceDetailRepository;
import com.thucpham.shopweb.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService{

	@Autowired
	private InvoiceDetailRepository invoiceDetailRepository;
	
	@Override
	public InvoiceDetail save(InvoiceDetail invoiceDetail) {
		return invoiceDetailRepository.save(invoiceDetail);
	}

}
