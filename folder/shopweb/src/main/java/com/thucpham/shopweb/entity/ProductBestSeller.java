package com.thucpham.shopweb.entity;

public class ProductBestSeller {

	private Product id;
	private Long sum;

	public ProductBestSeller(Product id, Long sum) {
		super();
		this.id = id;
		this.sum = sum;
	}
	public Product getId() {
		return id;
	}
	public void setId(Product id) {
		this.id = id;
	}
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	
	
}
