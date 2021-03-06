package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Cart;
import com.thucpham.shopweb.entity.Category;
import com.thucpham.shopweb.service.CategoryService;
import com.thucpham.shopweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@SessionAttributes("cart")
public class CategoryController {
	
	@Autowired
	private CategoryService  categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/category")
	public String category(@RequestParam int id, ModelMap modelMap, @RequestParam(defaultValue="1") int page, HttpSession httpSession) {
		List<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		modelMap.addAttribute("currentCategory", categoryService.findById(id));
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		List<ProductDto> listProduct = productService.findByCategoryId(id);
		double size = listProduct.size();
		
		double paging = Math.ceil(size / 12);
		int start = 0;
		
		if (page > 0) {
			start = (page -1);
		}
		
		Pageable pageable = PageRequest.of(start, 12);
		modelMap.addAttribute("listProduct", productService.findByCategoryId(id, pageable));
		modelMap.addAttribute("listProductBestSell",listProductBestSell);
		modelMap.addAttribute("paging", paging);
		modelMap.addAttribute("page", page);
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("sizeCart", listCart.size());
		} else {
			modelMap.addAttribute("sizeCart", 0);
		}
		return "category";
	}
}
