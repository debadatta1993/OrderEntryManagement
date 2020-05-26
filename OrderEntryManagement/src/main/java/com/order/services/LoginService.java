package com.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.LoginDAO;
import com.order.entity.User;

@Service
public class LoginService {

	@Autowired
	LoginDAO loginDAO;

	public String validateUser(User user) {
		String resultString = "";
		String username = user.getUsername();
		String password = user.getPassword();
		long result = loginDAO.checkUser(username);
		if (result <= 0) {
			resultString = "Invalid username";
			return resultString;
		} else {
			long checkPassword = loginDAO.checkPassword(username, password);
			if (checkPassword <= 0) {
				resultString = "Invalid login";
				return resultString;
			}
		}
		return "Successfully validated the User";
	}
}
