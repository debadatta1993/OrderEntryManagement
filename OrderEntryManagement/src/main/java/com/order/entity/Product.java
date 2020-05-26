package com.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="products")
public class Product {
    @Id
	private int productID;
  	private String productName;
	private double price;
	private String productDetails;
	private boolean available;

	public Product() {
		super();
	}

	public Product(int productID, String productName, double price, String productDetails, boolean available) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.productDetails = productDetails;
		this.available = available;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", price=" + price
				+ ", productDetails=" + productDetails + ", available=" + available + "]";
	}

}
