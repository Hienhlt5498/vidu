package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.Invoice;
import com.thucpham.shopweb.entity.InvoiceManager;

import java.util.List;

public interface InvoiceService {

	Invoice save(Invoice invoice);
	
	List<InvoiceManager> findAllInvoiceManager();
	
	List<InvoiceManager> findByIdInvoiceManager(int id);
	
	Invoice findById(int id);
}
