package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Cart;
import com.thucpham.shopweb.entity.CartRequest;
import com.thucpham.shopweb.entity.Category;
import com.thucpham.shopweb.service.CategoryService;
import com.thucpham.shopweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes("cart")
public class CartController {

	@Autowired
	private CategoryService  categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/cart")
	public String cart(ModelMap modelMap, HttpSession httpSession) {
		List<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("listProductBestSell", listProductBestSell);
//		if (listCar3.size() > 0) {
//
//			httpSession.setAttribute("cart", listCar3);
//		}
//		
//		listCar3 = new ArrayList<Cart>();

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
		
		return "cart";
	}
	
	@PostMapping("/updatecart")
	public String updateCart(@ModelAttribute("cartRequest") CartRequest listCart, ModelMap modelMap, HttpSession httpSession, HttpServletRequest request) {
		List<Cart> listCart2 = listCart.getListCart();
		List<Cart> listCar3 = new ArrayList<Cart>();
		for (Cart cart : listCart2) {
			cart.setTotalPrice(cart.getQuantity() * cart.getPrice());
			
			DecimalFormat df = new DecimalFormat("###,###");
			String format = df.format(cart.getTotalPrice());
			cart.setTotalPriceStr(format.replace(",", "."));
			
			List<Cart> listCartSession = (List<Cart>) request.getSession().getAttribute("cart");
			if (listCartSession == null) {
				listCartSession = new ArrayList<Cart>();
				request.getSession().setAttribute("cart", listCartSession);
				listCartSession.add(cart);
			}
			int idCheck = check(cart.getId(), listCartSession);
			if (idCheck != -1) {
				listCartSession.get(idCheck).setQuantity(cart.getQuantity());;
				listCartSession.get(idCheck).setTotalPrice(cart.getTotalPrice());
				listCartSession.get(idCheck).setTotalPriceStr(cart.getTotalPriceStr());	
			}
			
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/deleteCart")
	public String delete(@RequestParam int id, HttpSession httpSession) {
		List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
		int idCheck = check(id, listCart);
		if (idCheck != -1) {
			listCart.remove(idCheck);
		}
		return "redirect:/cart";
	}
	
	private int check(int id, List<Cart> listCart) {
		
		for (int i = 0; i < listCart.size(); i++) {
			if (listCart.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
