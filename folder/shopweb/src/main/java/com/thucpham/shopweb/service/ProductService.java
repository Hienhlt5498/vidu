package com.thucpham.shopweb.service;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

	List<ProductDto> findAll();
	
	ProductDto findById(int id);
	
	Product findByProductId(int id);
	
	void save(Product product);
	
	void delete(Product product);
	
	List<ProductDto> findProductPaging(Pageable pageable);
	
	List<ProductDto> findByCategoryId(int categoryId, Pageable pageable);
	
	List<ProductDto> findByCategoryId(int categoryId);
	
	List<ProductDto> findProductSellPaging();
	
	List<ProductDto> searchProduct(String name, Pageable pageable);
}
