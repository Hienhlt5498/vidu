package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.entity.Category;
import com.thucpham.shopweb.repository.CategoryRepository;
import com.thucpham.shopweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		Iterable<Category> listCategory = categoryRepository.findAll();
		List<Category> listCategoryResponse = new ArrayList<Category>();
		for (Category category : listCategory) {
			listCategoryResponse.add(category);
		}
		return listCategoryResponse;
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
	
}
