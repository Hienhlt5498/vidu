package com.thucpham.shopweb.controller;

import com.thucpham.shopweb.common.WriteFile;
import com.thucpham.shopweb.dto.ProductDto;
import com.thucpham.shopweb.entity.Account;
import com.thucpham.shopweb.entity.Cart;
import com.thucpham.shopweb.service.AccountService;
import com.thucpham.shopweb.service.CategoryService;
import com.thucpham.shopweb.service.ProductService;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
public class AccountController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private WriteFile writeFile;

	@GetMapping("/register")
	public String register(ModelMap modelMap, HttpSession httpSession) {
		modelMap.addAttribute("listCategory", categoryService.findAll());
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("listProductBestSell",listProductBestSell);
		if (httpSession.getAttribute("cart") != null) //kiểm tra giá trị session khác null ko
		{
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("sizeCart", listCart.size());
		} else {
			modelMap.addAttribute("sizeCart", 0);
		}
		return "register";
	}
	
	@GetMapping("/login")
	public String login(ModelMap modelMap, HttpSession httpSession) {
		modelMap.addAttribute("listCategory", categoryService.findAll());
		List<ProductDto> listProductBestSell = productService.findProductSellPaging();
		modelMap.addAttribute("listProductBestSell",listProductBestSell);
		if (httpSession.getAttribute("cart") != null) {
			List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
			modelMap.addAttribute("sizeCart", listCart.size());
		} else {
			modelMap.addAttribute("sizeCart", 0);
		}
		return "login";
	}
	
	@GetMapping("/profile")
	public String profile(ModelMap modelMap, HttpSession httpSession) {
		if (httpSession.getAttribute("account") != null) {
			Account account = (Account) httpSession.getAttribute("account"); //lấy giá trị session
			modelMap.addAttribute("account", account);
			modelMap.addAttribute("listCategory", categoryService.findAll());
			List<ProductDto> listProductBestSell = productService.findProductSellPaging();
			modelMap.addAttribute("listProductBestSell",listProductBestSell);
			if (httpSession.getAttribute("cart") != null) {
				List<Cart> listCart = (List<Cart>) httpSession.getAttribute("cart");
				modelMap.addAttribute("sizeCart", listCart.size());
			} else {
				modelMap.addAttribute("sizeCart", 0);
			}
		}
		return "profile";
	}
	
	@PostMapping("/createuser")
	public String createUser(@RequestParam String name, @RequestParam String userName, @RequestParam String address, @RequestParam String phone, @RequestParam String email, @RequestParam String password, ModelMap modelMap) {
		Account account = new Account();
		account.setName(name);
		account.setUserName(userName);
		account.setAddress(address);
		account.setPhone(phone);
		account.setEmail(email);
		account.setUserType(3);
		account.setAvatar("/images/avatar_default.jpg");
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		account.setPassword(hash);
		Account accountCheck = accountService.login(userName);
		if (accountCheck == null) {
			Account account2 = accountService.save(account);
			if (account2 != null) {
				return "login";
			} else {
				modelMap.addAttribute("message", "Tạo tài khoản không thành công");
				return "register";
			}
		} else {
			modelMap.addAttribute("message", "Tên đăng nhập đã tồn tại");
			return "register";
		}
		
	}
	
	@PostMapping("/loginuser")
	public String login(@RequestParam String userName, @RequestParam String password, ModelMap modelMap, HttpSession httpSession) {
		Account account = accountService.login(userName);
		String message = "";
		if (account != null) {
			boolean check = BCrypt.checkpw(password, account.getPassword());
			if (check) {
				if (httpSession.getAttribute("account") == null) {
					httpSession.setAttribute("account", account);
				}
				
				return "redirect:/";
			} else {
				message = "Tài khoản không tồn tại";
				modelMap.addAttribute("message", message);
				return "login";
			}
		} else {
			message = "Tài khoản không tồn tại";
			modelMap.addAttribute("message", message);
			return "login";
		}
	}
	
	@PostMapping("/updateinfo")
	@ResponseBody
	public String updateInfo(@RequestParam(defaultValue="") String username, @RequestParam(defaultValue="") String address, @RequestParam(defaultValue="") String phone, @RequestParam(defaultValue="") String email, @RequestParam(defaultValue="") String name, @RequestParam String image, @RequestParam String imageName, HttpSession httpSession) {
		if (httpSession.getAttribute("account") != null) {
			Account account = (Account) httpSession.getAttribute("account");
			
			if (!username.equals("")) {
				account.setUserName(username);
			}
			
			if (!address.equals("")) {
				account.setAddress(address);
			}
			
			if (!phone.equals("")) {
				account.setPhone(phone);
			}
			
			if (!email.equals("")) {
				account.setEmail(email);
			}
			
			if (!name.equals("")) {
				account.setName(name);
			}
			
			if (!image.equals("") && !imageName.equals("")) {
				String base64 = image.split(",")[1];
				//imageName = imageName.replace("\\", "/").split("/")[2];
				
				byte[] bytes = Base64.getDecoder().decode(base64);
				
				account.setAvatar("/images/"+imageName);
				writeFile.writeBase64ToFileAvatar(bytes, "/images/" + imageName);
			}
			
			accountService.save(account);

		}
		
		return "Cập nhật thành công";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		
		Account account = (Account) req.getSession().getAttribute("account");
		if (account != null) {
			System.out.println(new Date().getTime());
			account.setLastLogin(new Date().getTime());
			accountService.save(account);
			account = null;
			req.getSession().setAttribute("account", account);
			return "login";
		} else {
			return "login";
		}
	}
}
