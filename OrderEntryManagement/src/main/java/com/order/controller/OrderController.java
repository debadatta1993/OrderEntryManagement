package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Order;
import com.order.entity.OrderProduct;
import com.order.entity.Product;
import com.order.entity.User;
import com.order.exception.UserNotFoundException;
import com.order.services.LoginService;
import com.order.services.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private LoginService loginService;

	@GetMapping("/items/{productID}")
	public ResponseEntity getProduct(@PathVariable("productID") int productID, @RequestHeader String username,
			@RequestHeader String password) {
		if ((username == null || username == "") || (password == null || password == "")) {
			throw new UserNotFoundException("Username/password can not be null:Kindly Enter Valid login Details");
		}
		String valid = loginService.validateUser(new User(username, password));
		if (valid.equals("Successfully validated the User")) {
			Product product = orderService.getItem(productID);

			if (product == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.ok(valid);
		}

	}

	@GetMapping("/orders/{start}/{end}/{userId}")
	public ResponseEntity getOrder(@PathVariable("start") String start, @PathVariable("end") String end,
			@PathVariable("userId") int userId, @RequestHeader String username, @RequestHeader String password) {

		if ((username == null || username == "") || (password == null || password == "")) {
			throw new UserNotFoundException("Username/password can not be null:Kindly Enter Valid login Details");
		}
		String valid = loginService.validateUser(new User(username, password));
		if (valid.equals("Successfully validated the User")) {
			List<Order> orders = orderService.getOrder(start, end, userId);
			return ResponseEntity.ok(orders);
		} else {
			return ResponseEntity.ok(valid);
		}
	}

	@PostMapping("/items/add")
	public ResponseEntity addItem(@RequestBody List<OrderProduct> orderProducts, @RequestHeader String username,
			@RequestHeader String password) {

		if ((username == null || username == "") || (password == null || password == "")) {
			throw new UserNotFoundException("Username/password can not be null:Kindly Enter Valid login Details");
		}
		String valid = loginService.validateUser(new User(username, password));
		if (valid.equals("Successfully validated the User")) {
			orderService.addItem(orderProducts);
			return ResponseEntity.ok("Item added sucessfully");
		} else {
			return ResponseEntity.ok(valid);
		}

	}

}
