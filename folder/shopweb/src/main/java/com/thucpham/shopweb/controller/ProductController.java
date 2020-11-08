package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Cart;
import com.thucpham.shopweb.entity.Category;
import com.thucpham.shopweb.service.CategoryService;
import com.thucpham.shopweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String product(@RequestParam int id, ModelMap modelMap, HttpSession httpSession) {
		List<Category> listCategory = categoryService.findAll();
		modelMap.addAttribute("listCategory", listCategory);
		ProductDto productDto = productService.findById(id);
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("currentCategory", categoryService.findById(productDto.getCategory().getId()));
		modelMap.addAttribute("listProductBestSell", listProductBestSell);
		modelMap.addAttribute("listProduct", productService.findByCategoryId(productDto.getCategory().getId()));
		modelMap.addAttribute("product", productDto);
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("sizeCart", listCart.size());
		} else {
			modelMap.addAttribute("sizeCart", 0);
		}
		return "product";
	}
	
//	@PostMapping("/search")
//	public String search(@RequestParam String name, ModelMap modelMap) {
//		Pageable pageable = PageRequest.of(0, 12);
//		List<Category> listCategory = categoryService.findAll();
//		modelMap.addAttribute("listCategory", listCategory);
//		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
//		modelMap.addAttribute("listProductBestSell", listProductBestSell);
//		modelMap.addAttribute("listProduct", productService.searchProduct(name, pageable));
//		return "search";
//	}
}
