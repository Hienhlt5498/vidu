package com.thucpham.shopweb.repository;

import com.thucpham.shopweb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int id);
}
