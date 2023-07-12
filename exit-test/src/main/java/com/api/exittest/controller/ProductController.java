package com.api.exittest.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.exittest.model.Products;
import com.api.exittest.model.Reviews;
import com.api.exittest.repo.ProductRepo;

@RestController
@RequestMapping(path = "/api/")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductRepo productRepository;

	/**
	 * Retrieve all products
	 * 
	 * @return List of all products
	 */
	@GetMapping("/getproducts")
	public List<Products> getAllProducts() {
		System.out.println("Get Products Clicked");
		return productRepository.findAll();
	}

	/**
	 * Add a new product.
	 * 
	 * @param newProduct The new product to be added
	 * @return The newly added product
	 */
	@PostMapping("/postproducts")
	public Products addNewProduct(@RequestBody Products newProduct) {
		System.out.println("New Product Added");
		return productRepository.save(newProduct);
	}

	/**
	 * Retrieve a product by its product code.
	 * 
	 * @param prodCode The product code
	 * @return The product with the specified product code
	 */
	@RequestMapping("/product/{prodCode}")
	public Optional<Products> getProduct(@PathVariable("prodCode") String prodCode) {
		return productRepository.findById(prodCode);
	}

	/**
	 * Search products based on a keyword.
	 * 
	 * @param keyword : The keyword to search for in product code, name, and brand
	 * @return List of products matching the keyword
	 */
	@GetMapping("/productSearch/{keyword}")
	public List<Products> searchProducts(@PathVariable("keyword") String keyword) {
		System.out.println("Search Product Clicked");
		return productRepository.findByProdCodeOrProdNameContainingOrProdBrandContaining(keyword, keyword, keyword);
	}

	/**
	 * Calculate the average rating for a product.
	 * 
	 * @param prodCode The product code
	 * @return The average rating of the product, formatted with one decimal place
	 */
	@GetMapping("/product/{prodCode}/rating")
	public String getProductAverageRating(@PathVariable("prodCode") String prodCode) {
		System.out.println("Calculate Product Average Rating");

		// Retrieve the product with the specified product code
		Optional<Products> optionalProduct = productRepository.findById(prodCode);
		if (optionalProduct.isPresent()) {
			Products product = optionalProduct.get();
			List<Reviews> reviews = product.getReviews();

			// Calculate the average rating if there are reviews available for the product
			if (!reviews.isEmpty()) {
				int totalRating = 0;

				// Iterate over each review and sum up the ratings
				for (Reviews review : reviews) {
					totalRating += review.getRating();
				}

				// Calculate the average rating by dividing the total rating by the number of
				// reviews
				double averageRating = (double) totalRating / reviews.size();

				// Format the average rating with two decimal places
				DecimalFormat df = new DecimalFormat("#.#");
				String formattedRating = df.format(averageRating);

				return formattedRating;
			}
		}
		// If product or reviews not found, return 0.0 indicating no rating available
		return "0.0";
	}

	/**
	 * Get the total count of products.
	 * 
	 * @return The total number of products
	 */
	@GetMapping("/getTotalProductCount")
	public int getTotalProductCount() {
		System.out.println("Get Total Product Count");
		return productRepository.findAll().size();
	}

}
