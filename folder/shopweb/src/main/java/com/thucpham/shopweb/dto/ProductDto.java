package com.thucpham.shopweb.dto;

import com.thucpham.shopweb.entity.Product;

public class ProductDto extends Product{

	private String priceStr;

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	
	
}
