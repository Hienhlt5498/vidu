package com.thucpham.shopweb.entity;

import java.text.DecimalFormat;

public class ProductPromotion {

	private Product product;
	private String name;
	private Double pricePromotion;
	private java.util.Date startDate;
	private java.util.Date endDate;
	private String priceStr;
	
	private String pricePromotionStr;
	
	 static DecimalFormat df = new DecimalFormat("###,###");
	
	public ProductPromotion(Product product, String name, Double pricePromotion, java.util.Date startDate, java.util.Date endDate) {
		this.product = product;
		this.name = name;
		this.pricePromotion = pricePromotion;
		this.startDate = startDate;
		this.endDate = endDate;
		
		if (pricePromotion != null) {
			String format = df.format(pricePromotion);
			this.pricePromotionStr = format.replace(",", ".");
		}
		
		String format2 = df.format(product.getPrice());
		this.priceStr = format2.replace(",", ".");
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPricePromotion() {
		return pricePromotion;
	}
	public void setPricePromotion(double pricePromotion) {
		this.pricePromotion = pricePromotion;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public void setPricePromotion(Double pricePromotion) {
		this.pricePromotion = pricePromotion;
	}
	public String getPricePromotionStr() {
		return pricePromotionStr;
	}
	public void setPricePromotionStr(String pricePromotionStr) {
		this.pricePromotionStr = pricePromotionStr;
	}
	public String getPriceStr() {
		return priceStr;
	}
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	
	
	
}
