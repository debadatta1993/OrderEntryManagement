package com.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({ "com.order.controller", "com.order.entity", "com.order.dao", "com.order.services",
		"com.order.configuration","com.order.exception"})

public class OrderEntryManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrderEntryManagementApplication.class, args);
	}

}
