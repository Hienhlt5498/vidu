package com.thucpham.shopweb.repository;

import com.thucpham.shopweb.entity.Invoice;
import com.thucpham.shopweb.entity.InvoiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	@Query("select new com.thucpham.shopweb.entity.InvoiceManager(cus.id as customerid, cus.name, cus.address, cus.phone, iv.id as invoiceid, iv.total, ivd.quantity, ivd.product as productid, p.name as productname, count(iv.id) as count, iv.status)"
			+" from Customer cus"
			+" inner join Invoice iv on cus.id = iv.customer "
			+" inner join InvoiceDetail ivd on ivd.invoice = iv.id"
			+" inner join Product p on p.id = ivd.product"
			+" group by iv.id")
	List<InvoiceManager> findAllInvoiceManager();
	
	@Query("select new com.thucpham.shopweb.entity.InvoiceManager(iv.id as invoiceid, iv.total, ivd.quantity, ivd.product as productid, p.name as productname)"
			+" from Customer cus"
			+" inner join Invoice iv on cus.id = iv.customer "
			+" inner join InvoiceDetail ivd on ivd.invoice = iv.id"
			+" inner join Product p on p.id = ivd.product"
			+" where iv.id = :id")
	List<InvoiceManager> findInvoiceManagerById(@Param("id") int id);
	
	Invoice findById(int id);
}
