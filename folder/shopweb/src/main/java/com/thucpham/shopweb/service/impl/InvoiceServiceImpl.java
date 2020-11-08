package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.entity.Invoice;
import com.thucpham.shopweb.entity.InvoiceManager;
import com.thucpham.shopweb.repository.InvoiceRepository;
import com.thucpham.shopweb.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<InvoiceManager> findAllInvoiceManager() {
		return invoiceRepository.findAllInvoiceManager();
	}

	@Override
	public List<InvoiceManager> findByIdInvoiceManager(int id) {
		return invoiceRepository.findInvoiceManagerById(id);
	}

	@Override
	public Invoice findById(int id) {
		return invoiceRepository.findById(id);
	}

}
