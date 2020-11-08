package com.thucpham.shopweb.service.impl;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Product;
import com.thucpham.shopweb.entity.ProductBestSeller;
import com.thucpham.shopweb.repository.ProductRepository;
import com.thucpham.shopweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDto> findAll() {
		Iterable<Product> iterable = productRepository.findAll();
		List<ProductDto> listProductDto = new ArrayList<ProductDto>();
		for (Product product : iterable) {
			ProductDto productDto = convertProduct(product);
			listProductDto.add(productDto);
		}
		return listProductDto;
	}

	@Override
	public ProductDto findById(int id) {
		return convertProduct(productRepository.getOne(id));
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<ProductDto> findProductPaging(Pageable pageable) {
		List<Product> iterable = productRepository.findProductPaging(pageable);
		List<ProductDto> listProductDto = new ArrayList<ProductDto>();
		for (Product product : iterable) {
			ProductDto productDto = convertProduct(product);
			listProductDto.add(productDto);
		}
		return listProductDto;
	}

	@Override
	public List<ProductDto> findByCategoryId(int categoryId, Pageable pageable) {
		Iterable<Product> iterable = productRepository.findByCategoryId(categoryId, pageable);
		List<ProductDto> listProductDto = new ArrayList<ProductDto>();
		for (Product product : iterable) {
			ProductDto productDto = convertProduct(product);
			listProductDto.add(productDto);
		}
		return listProductDto;
	}
	
	@Override
	public List<ProductDto> findByCategoryId(int categoryId) {
		Iterable<Product> iterable = productRepository.findByCategoryId(categoryId);
		List<ProductDto> listProductDto = new ArrayList<ProductDto>();
		for (Product product : iterable) {
			ProductDto productDto = convertProduct(product);
			listProductDto.add(productDto);
		}
		return listProductDto;
	}
	
	@Override
	public List<ProductDto> findProductSellPaging() {
		List<ProductBestSeller> listProductBestSeller = productRepository.findProductSellPaging();
		
		List<ProductBestSeller> sortedList = listProductBestSeller.stream()
				.sorted(Comparator.comparingLong(ProductBestSeller::getSum)
				.reversed())
				.collect(Collectors.toList());
		
		List<ProductDto> listProductBestSell = new ArrayList<ProductDto>();
		
		for (int i = 0; i < 4; i++) {
			ProductBestSeller productBestSeller = sortedList.get(i);
			listProductBestSell.add(convertProduct(productBestSeller.getId()));
		}
		
		
		return listProductBestSell;
	}
	
	@Override
	public List<ProductDto> searchProduct(String name, Pageable pageable) {
		List<Product> listProduct = productRepository.searchProduct(name, pageable);
		List<ProductDto> listProductDto = new ArrayList<ProductDto>();
		for (Product product : listProduct) {
			ProductDto productDto = convertProduct(product);
			listProductDto.add(productDto);
		}
		return listProductDto;
	}
	
	private ProductDto convertProduct(Product product) {
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setPrice(product.getPrice());
		
		DecimalFormat df = new DecimalFormat("###,###");
		String format = df.format(product.getPrice());
		productDto.setPriceStr(format.replace(",", "."));
		productDto.setQuantity(product.getQuantity());
		productDto.setTime(product.getTime());
		productDto.setImage(product.getImage());
		productDto.setCategory(product.getCategory());
		
		return productDto;
	}

	@Override
	public Product findByProductId(int id) {
		return productRepository.findById(id);
	}

}
