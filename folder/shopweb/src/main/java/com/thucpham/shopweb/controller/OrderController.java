package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.*;
import com.thucpham.shopweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

@RestController
public class OrderController {

	@Autowired
	private CategoryService  categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceDetailService invoiceDetailService;
	
	String message = "";
	
	@GetMapping("/order")
	public String order(ModelMap modelMap, HttpSession httpSession) {
		List<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("listProductBestSell", listProductBestSell);
		
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("listCart", listCart);
			double totalPrice = 0;
			for (Cart cart : listCart) {
				totalPrice = totalPrice + cart.getQuantity() * cart.getPrice();
			}
			DecimalFormat df = new DecimalFormat("###,###");
			String totalPriceStr = df.format(totalPrice).replace(",", ".");
			modelMap.addAttribute("sizeCart", listCart.size());
			modelMap.addAttribute("totalPrice", totalPriceStr);
			CartRequest cartRequest = new CartRequest(); // support submit list
			cartRequest.setListCart(listCart);
			modelMap.addAttribute("cartRequest", cartRequest);
		} else {
			modelMap.addAttribute("sizeCart", 0);
		}
		
		if (!message.equals("")) {
			modelMap.addAttribute("message", message);
		}
		message = "";
		if (httpSession.getAttribute("account") != null) {
			Account account = (Account) httpSession.getAttribute("account");
			modelMap.addAttribute("account", account);
			
			Customer customer = new Customer();
			customer.setName(account.getName());
			customer.setEmail(account.getEmail());
			customer.setPhone(account.getPhone());
			customer.setAddress(account.getAddress());
			
			modelMap.addAttribute("customer", customer);
		} else {
			modelMap.addAttribute("customer", new Customer());
		}
		
		return "order";
	}

	@PostMapping("/addorder")
	public String addOrder(@ModelAttribute("customer") Customer customer, @RequestParam String note, ModelMap modelMap, HttpSession httpSession) {
		List<Cart> listCart = null;
		double totalPrice = 0;
		if (httpSession.getAttribute("cart") != null) {
			listCart = (List<Cart>) httpSession.getAttribute("cart");
			
			if (listCart.size() > 0) {
				for (Cart cart : listCart) {
					totalPrice = totalPrice + cart.getQuantity() * cart.getPrice();
				}
				DecimalFormat df = new DecimalFormat("###,###");
				String totalPriceStr = df.format(totalPrice).replace(",", ".");
				
				Customer customerResponse = customerService.save(customer);
				Invoice invoice = new Invoice();
				invoice.setTotal(totalPrice);
				invoice.setDate(new Date(new java.util.Date().getTime()));
				invoice.setCustomer(customerResponse);
				invoice.setNote(note);
				Invoice invoiceResponse =  invoiceService.save(invoice);
				for (Cart cart : listCart) {
					Product product = productService.findById(cart.getId());
					InvoiceDetail invoiceDetail = new InvoiceDetail();
					invoiceDetail.setInvoice(invoiceResponse);
					invoiceDetail.setProduct(product);
					invoiceDetail.setQuantity(cart.getQuantity());
					
					invoiceDetailService.save(invoiceDetail);
				}
				
				listCart.removeAll(listCart);
				message = "Đặt hàng thành công";
			} else {
				message = "Vui lòng thêm giỏ hàng";
			}
		}
		return "redirect:/order";
	}
}
