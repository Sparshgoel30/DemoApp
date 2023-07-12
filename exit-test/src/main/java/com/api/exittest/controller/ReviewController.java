package com.api.exittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.exittest.model.Products;
import com.api.exittest.model.Reviews;
import com.api.exittest.repo.ProductRepo;
import com.api.exittest.repo.ReviewsRepo;

@RestController
@RequestMapping(path = "/api/")
@CrossOrigin(origins = "*")
public class ReviewController {

	@Autowired
	private ReviewsRepo reviewRepository;

	@Autowired
	private ProductRepo productService;

	/**
	 * Add a new review to the product code
	 * 
	 * @param prodCode
	 * @param newReview
	 * @return
	 */
	@PostMapping("/addReview")
	public Reviews addReview(@RequestParam("prodCode") String prodCode, @RequestBody Reviews newReview) {
		// Print message indicating the addition of a review for a specific product code
		System.out.println("Adding a review for product code: " + prodCode);

		// Retrieve the product from the database using the prodCode
		Products product = productService.getProductByProdCode(prodCode);
		if (product == null) {
			// Throw an exception if the product does not exist
			throw new IllegalArgumentException("Product with code " + prodCode + " does not exist.");
		}

		// Set the product for the review
		newReview.setProduct(product);

		// Save the new review
		return reviewRepository.save(newReview);
	}

	/**
	 * get all reviews by product code
	 * 
	 * @param prodCode
	 * @return
	 */
	@GetMapping("/getReviewsByProductCode/{prodCode}")
	public List<Reviews> getAllReviewsByProductCode(@PathVariable("prodCode") String prodCode) {
		// Print message indicating the retrieval of all reviews for a specific product
		// code
		System.out.println("Get All Reviews for Product Code: " + prodCode);

		// Retrieve the product from the database using the prodCode
		Products product = productService.getProductByProdCode(prodCode);
		if (product == null) {
			// Throw an exception if the product does not exist
			throw new IllegalArgumentException("Product with code " + prodCode + " does not exist.");
		}

		// Retrieve all reviews for the product
		return reviewRepository.findByProduct(product);
	}

	/**
	 * get all reviews by product code
	 * 
	 * @param prodCode
	 * @return all reviews for the product
	 */
	@GetMapping("/getReviewsCountByProductCode/{prodCode}")
	public long getReviewsCountByProductCode(@PathVariable("prodCode") String prodCode) {
		// Print message indicating the retrieval of the reviews count for a specific
		// product code
		System.out.println("Get All Reviews for Product Code: " + prodCode);

		// Retrieve the product from the database using the prodCode
		Products product = productService.getProductByProdCode(prodCode);

		if (product == null) {
			// Throw an exception if the product does not exist
			throw new IllegalArgumentException("Product with code " + prodCode + " does not exist.");
		}

		// Retrieve all reviews for the product
		return reviewRepository.countByProduct(product);
	}

	/**
	 * get total number of reviews
	 * 
	 * @return count
	 */
	@GetMapping("/getTotalReviewsCount")
	public int getTotalReviewsCount() {
		// Print message indicating the retrieval of the total reviews count
		System.out.println("Get Total Reviews Count");
		// Retrieve all reviews and return the count
		return reviewRepository.findAll().size();
	}

}
