package com.thucpham.shopweb.entity;

import java.text.DecimalFormat;

public class InvoiceManager {

	private int customerid;
	private String name;
	private String address;
	private String phone;
	private int invoiceid;
	private double total;
	private int quantity;
	private Product productid;
	private String productname;
	private Long count;
	private String totalStr;
	private int status;
	
	
	public InvoiceManager(int customerid, String name, String address, String phone, int invoiceid, double total,
			int quantity, Product productid, String productname, Long count, int status) {
		super();
		this.customerid = customerid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.invoiceid = invoiceid;
		this.total = total;
		this.quantity = quantity;
		this.productid = productid;
		this.productname = productname;
		this.count = count;
		this.status = status;
		
		DecimalFormat df = new DecimalFormat("###,###");
		String format = df.format(total);
		
		this.totalStr = format.replace(",", ".");
	}
	
	
	
	public InvoiceManager(int invoiceid, double total, int quantity, Product productid, String productname) {
		super();
		this.invoiceid = invoiceid;
		this.total = total;
		this.quantity = quantity;
		this.productid = productid;
		this.productname = productname;
	}



	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getInvoiceid() {
		return invoiceid;
	}
	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProductid() {
		return productid;
	}
	public void setProductid(Product productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getTotalStr() {
		return totalStr;
	}
	public void setTotalStr(String totalStr) {
		this.totalStr = totalStr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
