package com.cg.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.client.CustomerService;
import com.cg.client.ProductService;
import com.cg.dto.CartDTO;
import com.cg.dto.CustomerDTO;
import com.cg.dto.ProductDTO;
import com.cg.entity.Cart;
import com.cg.exception.AddToCartNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.ProductNotFoundException;
import com.cg.repository.CartRepository;
import com.cg.service.CartService;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;

	@Override
	public CartDTO addToCart(int customerId, int productId) throws CustomerNotFoundException, ProductNotFoundException {
		
		CustomerDTO customer = customerService.getCustomerById(customerId);

		ProductDTO product = productService.getProductById(productId);
		
		if(product==null) {
			throw new ProductNotFoundException("Invalid product ID");
		}
		
		Cart cart= cartRepository.findByCustomerId(customerId);
		if(cart==null) {
			cart= new Cart();
			if(customer==null) {
				throw new CustomerNotFoundException("Invalid customer ID");
			}
			cart.setCustomerId(customerId);
		}
		cart.getProductIds().add(productId);
		cart.setTotalQuantity(cart.getTotalQuantity()+1);
		cart.setTotalPrice(product.getProductPrice()+cart.getTotalPrice());

		cartRepository.save(cart);
		
		CartDTO cartDTO= new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setCustomerId(cart.getCustomerId());
		cartDTO.setProductIds(cart.getProductIds());
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setTotalQuantity(cart.getTotalQuantity());

		return cartDTO;

	}
	@Override
	public String deleteProduct(int custid, int prodid) throws ProductNotFoundException, AddToCartNotFoundException {

		ProductDTO product = productService.getProductById(prodid);
		
		Cart cart= cartRepository.findByCustomerId(custid);
		if(cart==null) {
			throw new AddToCartNotFoundException("Invalid customer Id");
		}
		if(product==null) {
			throw new ProductNotFoundException("Invalid product ID");
		}
		
		for(Integer prod:cart.getProductIds()) {
			if( prod==prodid) {
				cart.setTotalQuantity(cart.getTotalQuantity()-1);
				cart.setTotalPrice(cart.getTotalPrice()-product.getProductPrice());
				cart.getProductIds().remove(prod);
				break;
			}
		}
		
		if(cart.getTotalQuantity()==0) {
			cartRepository.delete(cart);
		}else {
			cartRepository.save(cart);
		}

			return "deleted Successfully";	
	}

	@Override
	public CartDTO getCartByCustomerId(int custid) {
		Cart cart= cartRepository.findByCustomerId(custid);
		CartDTO cartDTO= new CartDTO();
		cartDTO.setId(cart.getId());
		cartDTO.setCustomerId(cart.getCustomerId());
		cartDTO.setProductIds(cart.getProductIds());
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setTotalQuantity(cart.getTotalQuantity());
		return cartDTO;
	}

	@Override
	public String deleteCart(int cartId) {
		cartRepository.deleteById(cartId);
		return "Cart deleted.";
	}
}
