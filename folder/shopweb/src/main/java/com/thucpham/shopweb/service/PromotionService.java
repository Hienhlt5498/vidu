package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.ProductPromotion;

import java.sql.Date;
import java.util.List;

public interface PromotionService {

	List<ProductPromotion> findProductPromotion(Date date);
	
	List<ProductPromotion> findProductByCategory();
}
