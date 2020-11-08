package com.thucpham.shopweb.repository;

import com.thucpham.shopweb.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	Category findById(int id);
}
