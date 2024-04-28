package com.cg.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.CartDTO;

@FeignClient(name="CartService",url = "http://localhost:8091" )
public interface CartService {

	@GetMapping("/cart/getByCustomerId/{custId}")
	public CartDTO getCartByCustomerId(@PathVariable("custId") int custId);
	
	@DeleteMapping("/cart/delete/{cartId}")
	public String deleteCart(@PathVariable("cartId") int cartId);
}
