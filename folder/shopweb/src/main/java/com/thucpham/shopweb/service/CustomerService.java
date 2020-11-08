package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.Customer;

public interface CustomerService {

	Customer save(Customer customer);
	
	Customer findById(int id);
}
