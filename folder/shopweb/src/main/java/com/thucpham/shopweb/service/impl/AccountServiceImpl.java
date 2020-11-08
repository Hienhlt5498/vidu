package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.entity.Account;
import com.thucpham.shopweb.repository.AccountRepository;
import com.thucpham.shopweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account login(String userName) {
		return accountRepository.findByUserName(userName);
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public Account findByOtp(String otp) {
		return accountRepository.findByOtp(otp);
	}

	@Override
	public List<Account> findByUserType(int userType) {
		return accountRepository.findByUserType(userType);
	}

	@Override
	public Account findById(int id) {
		return accountRepository.findById(id);
	}

	@Override
	public void delete(Account account) {
		accountRepository.delete(account);		
	}

}
