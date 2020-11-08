package com.thucpham.shopweb.repository;

import com.thucpham.shopweb.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByUserName(String userName);
	
	Account findByEmail(String email);
	
	Account findByOtp(String otp);
	
	List<Account> findByUserType(int userType);
	
	Account findById(int id);
	
	void delete(Account account);
}
