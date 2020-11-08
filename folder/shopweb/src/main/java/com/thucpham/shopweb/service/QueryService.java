package com.thucpham.shopweb.service;

import com.thucpham.shopweb.entity.ProductPromotion;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryService {

	List<ProductPromotion> findProductBestSell(Pageable pageable);
}
