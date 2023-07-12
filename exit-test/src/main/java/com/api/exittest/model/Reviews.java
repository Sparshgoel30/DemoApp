package com.api.exittest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Reviews {

	// Define the review ID column as the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long reviewId;

	@Column(name = "review_heading")
	private String reviewHead;

	@Column(name = "review_description")
	private String reviewDesc;

	@Column(name = "rating")
	private double rating;

	// Establish a many-to-one relationship with Products
	@ManyToOne
	@JoinColumn(name = "product_code", referencedColumnName = "product_code")
	@JsonBackReference // To handle serialization of Products
	private Products product;

	// Parameterized constructor
	public Reviews(Long reviewId, String reviewHead, String reviewDesc, double rating, Products product) {
		super();
		this.reviewId = reviewId;
		this.reviewHead = reviewHead;
		this.reviewDesc = reviewDesc;
		this.rating = rating;
		this.product = product;
	}

	// Default constructor
	public String getReviewHead() {
		return reviewHead;
	}

	// Getter and setter methods
	public void setReviewHead(String reviewHead) {
		this.reviewHead = reviewHead;
	}

	public Reviews() {
		super();
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewDesc() {
		return reviewDesc;
	}

	public void setReviewDesc(String reviewDesc) {
		this.reviewDesc = reviewDesc;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", reviewDesc=" + reviewDesc + ", rating=" + rating + "]";
	}

}
