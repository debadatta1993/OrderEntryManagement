package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.User;
import com.order.exception.UserNotFoundException;
import com.order.services.LoginService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public String authenticateUser(@RequestBody User user) {
		String result = "";
		String username = user.getUsername();
		String password = user.getPassword();
		if ((username == null || username == "") || (password == null || password == "")) {
			throw new UserNotFoundException("Username/password can not be null:Kindly Enter Valid login Details");
		}
		result = loginService.validateUser(user);
		return result;
	}
}
