package com.cg.service;

import com.cg.dto.CartDTO;
import com.cg.exception.AddToCartNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.ProductNotFoundException;

public interface CartService {
	public CartDTO addToCart(int custid,int productid) throws CustomerNotFoundException, ProductNotFoundException;

	public String deleteProduct(int custid, int prodid) throws AddToCartNotFoundException,ProductNotFoundException;
	
	public CartDTO getCartByCustomerId(int custid);
	
	public String deleteCart(int cartId);
}
