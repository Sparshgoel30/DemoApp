package com.api.exittest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Products {

	// Define the primary key column
	@Id
	@Column(name = "product_code")
	private String prodCode;
	@Column(name = "product_name")
	private String prodName;
	@Column(name = "product_brand")
	private String prodBrand;
	// Establish a one-to-many relationship with Reviews
	@OneToMany(mappedBy = "product")
	@JsonManagedReference // To handle serialization of Reviews
	private List<Reviews> reviews;

	// constructor using fields or parameterized constructor
	public Products(String prodCode, String prodName, String prodBrand, List<Reviews> reviews) {
		super();
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.prodBrand = prodBrand;
		this.reviews = reviews;
	}

	// constructor without using fields or non-parameterized constructor
	public Products() {
		super();
	}

	// getter and setter methods
	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Products [prodCode=" + prodCode + ", prodName=" + prodName + ", prodBrand=" + prodBrand + "]";
	}

}
