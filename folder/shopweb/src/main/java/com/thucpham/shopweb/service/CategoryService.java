package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.Category;

import java.util.List;

public interface CategoryService {
	List<Category> findAll();
	Category findById(int id);
	Category save(Category category);
	void delete(Category category);
}
