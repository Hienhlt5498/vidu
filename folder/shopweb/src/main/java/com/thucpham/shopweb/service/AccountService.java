package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.Account;

import java.util.List;

public interface AccountService {



	Account save(Account account);
	
	Account login(String userName);
	
	Account findByEmail(String email);
	
	Account findByOtp(String otp);
	
	List<Account> findByUserType(int userType);
	
	Account findById(int id);
	
	void delete(Account account);
}
