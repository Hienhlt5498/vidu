package com.thucpham.shopweb.repository;

import com.thucpham.shopweb.entity.Product;
import com.thucpham.shopweb.entity.ProductBestSeller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findById(int id);
	
	List<Product> findByCategoryId(int categoryId, Pageable pageable);
	
	List<Product> findByCategoryId(int categoryId);
	
	@Query("select p from Product p order by time desc")
	List<Product> findProductPaging(Pageable pageable);
	
	@Query("select new com.thucpham.shopweb.repositoryentity.ProductBestSeller(idl.product as id,sum(idl.quantity) as sum)"
			+" from InvoiceDetail idl"
			+" group by idl.product")
	List<ProductBestSeller> findProductSellPaging();
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%')")
	List<Product> searchProduct(@Param("name") String name, Pageable pageable);
}
