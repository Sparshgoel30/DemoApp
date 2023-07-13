package com.api.exittest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.exittest.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, String> {

	// Search products by product code, product name, or product brand
	List<Products> findByProdCodeOrProdNameContainingOrProdBrandContaining(String prodCode, String prodName, String prodBrand);

	// Get a product by its product code
	Products getProductByProdCode(String prodCode);


}
