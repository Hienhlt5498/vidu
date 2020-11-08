package com.thucpham.shopweb.entity;

public class Cart {
	private int id;
	private String name;
	private int quantity;
	private double price;
	private String priceStr;
	private double totalPrice;
	private String totalPriceStr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPriceStr() {
		return priceStr;
	}
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTotalPriceStr() {
		return totalPriceStr;
	}
	public void setTotalPriceStr(String totalPriceStr) {
		this.totalPriceStr = totalPriceStr;
	}
	
	
}
