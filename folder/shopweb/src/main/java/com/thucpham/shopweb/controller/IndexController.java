package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Account;
import com.thucpham.shopweb.entity.Cart;
import com.thucpham.shopweb.entity.Category;
import com.thucpham.shopweb.service.CategoryService;
import com.thucpham.shopweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes({"cart", "name"})
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String index(ModelMap modelMap, HttpSession httpSession) {
		Iterable<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		
		Pageable pageable = PageRequest.of(0, 4);
		
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		
		
		//List<ProductPromotion> list = queryService.findProductBestSell(pageable);
		modelMap.addAttribute("listProductDriedVegetarian", productService.findByCategoryId(1, pageable));
		modelMap.addAttribute("listProductColdVegetarian", productService.findByCategoryId(2, pageable));
		modelMap.addAttribute("listProductPasteuriez", productService.findByCategoryId(3, pageable));
		modelMap.addAttribute("listProductSpiceVegetarian", productService.findByCategoryId(4, pageable));
		
		modelMap.addAttribute("sizeCart", listCart.size());
		modelMap.addAttribute("listProductBestSell", listProductBestSell);
		
		if (httpSession.getAttribute("account") != null) {
			Account account = (Account) httpSession.getAttribute("account");
			modelMap.addAttribute("account", account);
		}
		return "index";
	}
	
	private List<Cart> listCart = new ArrayList<Cart>();
	
	@PostMapping("/addcart")
	@ResponseBody
	public String addCart(@RequestParam int id, @RequestParam int quantity, HttpSession httpSession) {
		ProductDto productDto = productService.findById(id);
		Cart cart = new Cart(); 
		cart.setId(id);
		cart.setName(productDto.getName());
		cart.setPrice(productDto.getPrice());
		cart.setPriceStr(productDto.getPriceStr());
		cart.setQuantity(quantity);
		
		
		if (httpSession.getAttribute("cart") == null) {
			cart.setTotalPrice(cart.getPrice() * cart.getQuantity());
			
			DecimalFormat df = new DecimalFormat("###,###");
			String format = df.format(cart.getTotalPrice());
			cart.setTotalPriceStr(format.replace(",", "."));
			listCart.add(cart);
		} else {
			listCart = (List<Cart>) httpSession.getAttribute("cart");
			int idCheck = check(id, listCart);
			if (idCheck == -1) {
				cart.setTotalPrice(cart.getPrice() * cart.getQuantity());
				
				DecimalFormat df = new DecimalFormat("###,###");
				String format = df.format(cart.getTotalPrice());
				cart.setTotalPriceStr(format.replace(",", "."));
				
				listCart.add(cart);
			} else {
				
				int quantityNew = listCart.get(idCheck).getQuantity() + quantity;
				Cart cart2 = listCart.get(idCheck);
				cart2.setQuantity(quantityNew);
				
				cart2.setTotalPrice(cart2.getPrice() * cart2.getQuantity());
				
				DecimalFormat df = new DecimalFormat("###,###");
				String format = df.format(cart2.getTotalPrice());
				cart2.setTotalPriceStr(format.replace(",", "."));
			}
		}
		
		int size = listCart.size();
		httpSession.setAttribute("cart", listCart);
		return size+"";
		
	}
	
	@PostMapping("/searchform")
	public String search(@RequestParam String name, ModelMap modelMap, HttpSession httpSession) {
		Pageable pageable = PageRequest.of(0, 12);
		List<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("listProductBestSell", listProductBestSell);
		modelMap.addAttribute("listProduct", productService.searchProduct(name, pageable));
		String nameSearch = (String) httpSession.getAttribute("name");
		httpSession.setAttribute("name", nameSearch);
		modelMap.addAttribute("sizeCart", listCart.size());
		return "search";
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
