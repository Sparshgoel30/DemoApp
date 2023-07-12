package com.api.exittest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.exittest.model.Products;
import com.api.exittest.model.Reviews;

@Repository
public interface ReviewsRepo extends JpaRepository<Reviews, String>{

	// Find reviews by product
	List<Reviews> findByProduct(Products product);

	// Count the number of reviews for a specific product
	long countByProduct(Products product);

}
