package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CartDTO;
import com.cg.exception.AddToCartNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.ProductNotFoundException;
import com.cg.serviceImpl.CartServiceImpl;


@RestController
@RequestMapping("/cart")
//@CrossOrigin(origins = "*")
public class CartController {

	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@PostMapping("/add/{customerId}/{productId}")
	public CartDTO addToCart(@PathVariable(value = "customerId") int customerId,
			@PathVariable(value = "productId") int productId) throws CustomerNotFoundException, ProductNotFoundException {
		return cartServiceImpl.addToCart(customerId, productId);
	}

	@DeleteMapping("/deleteProducts/{customerId}/{productId}")
	public String deleteProductCart(@PathVariable(value = "customerId") int customerId,
			@PathVariable(value = "productId") int productId) throws ProductNotFoundException, AddToCartNotFoundException {
		cartServiceImpl.deleteProduct(customerId, productId);
		return "deleted successfully";
	}
	@GetMapping("/getByCustomerId/{custId}")
	public CartDTO getCartByCustomerId(@PathVariable("custId") int custId) {
		return cartServiceImpl.getCartByCustomerId(custId);
	}
	@DeleteMapping("/delete/{cartId}")
	public String deleteCart(@PathVariable("cartId") int cartId) {
		return cartServiceImpl.deleteCart(cartId);
	}
}
