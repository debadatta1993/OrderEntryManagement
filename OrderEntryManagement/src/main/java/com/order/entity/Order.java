package com.order.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Orders")
public class Order {
	@Id
	private String orderId;
	@Column
	private Date orderDate;
	@Column
	private double price;
	@Column
	private int userId;
	@Transient
	private List<OrderProduct> products;

	public Order() {
		super();
	}

	public Order(String orderId, Date orderDate, double price, int userId, List<OrderProduct> products) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.price = price;
		this.userId = userId;
		this.products = products;
	}

	public List<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
