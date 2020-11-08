package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.entity.Customer;
import com.thucpham.shopweb.repository.CustomerRepository;
import com.thucpham.shopweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(int id) {
		return customerRepository.findById(id);
	}

}
