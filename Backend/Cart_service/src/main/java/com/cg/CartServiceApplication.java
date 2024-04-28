package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cg.client", "com.cg.serviceImpl","com.cg.controller"}) // Add the package containing CustomerService and CartServiceImpl
@EnableFeignClients(basePackages = {"com.cg.client"}) // Add the package containing Feign clients
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

}
