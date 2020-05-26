package com.order.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.OrderDAO;
import com.order.entity.Order;
import com.order.entity.OrderProduct;
import com.order.entity.Product;

@Service
public class OrderService {
	@Autowired
	private OrderDAO orderDAO;

	public Product getItem(int productID) {
		return orderDAO.getProduct(productID);
	}

	public List<Order> getOrder(String startDate, String endDate, int userId) {
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Order> orders = orderDAO.getOrder(start, end, userId);
		return orders;
	}

	public void addItem(List<OrderProduct> orderProducts) {
		if (!orderProducts.isEmpty()) {
			for (OrderProduct reqOrder : orderProducts) {
				int id = reqOrder.getOrderId();
				String pname = reqOrder.getProductName();
				List<OrderProduct> orderList=orderDAO.getOrderDetails(id, pname);
				// query from db if it not eq 1 then update or insert
				if (orderList.size() != 0) {
					reqOrder.setQuantity(orderList.get(0).getQuantity()+reqOrder.getQuantity());
					orderDAO.updateItem(reqOrder);
				} else {
					orderDAO.addItem(reqOrder);
				}
			}
		}
	}
}
