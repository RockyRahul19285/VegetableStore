package com.cg.service;

 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.anyInt;

import static org.mockito.Mockito.when;
 
import java.util.ArrayList;

import java.util.List;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
 
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

import com.cg.serviceImpl.CartServiceImpl;

public class CartServiceTest {

    @Mock

    private CustomerService customerService;

    @Mock

    private ProductService productService;

    @Mock

    private CartRepository cartRepository;

    @InjectMocks

    private CartServiceImpl cartService;

    @BeforeEach

    void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test

    void testAddToCart() throws CustomerNotFoundException, ProductNotFoundException {

        // Arrange

        int customerId = 1;

        int productId = 100;

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);

        ProductDTO productDTO = new ProductDTO();

        //productDTO.setBrand(productId);

        productDTO.setProductPrice(50.0);

        when(productService.getProductById(productId)).thenReturn(productDTO);

        Cart cart = new Cart();

        when(cartRepository.findByCustomerId(customerId)).thenReturn(cart);

        // Act

        CartDTO result = cartService.addToCart(customerId, productId);

        // Assert

        assertEquals(1, result.getTotalQuantity());

        assertEquals(50.0, result.getTotalPrice());

    }

    @Test

    void testDeleteProduct() throws CustomerNotFoundException, AddToCartNotFoundException, ProductNotFoundException {

        // Arrange

        int customerId = 1;

        int productId = 100;

        // Mocking customer service

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(customerDTO);

        // Mocking cart repository

        Cart cart = new Cart();

        List<Integer> productIds = new ArrayList<>();

        productIds.add(productId);

        cart.setProductIds(productIds);

        when(cartRepository.findByCustomerId(customerId)).thenReturn(cart);

        when(cartRepository.save(cart)).thenReturn(cart); // Mock save method

    }


    @Test

    void testFindCartByCustomerId() throws CustomerNotFoundException {

        // Arrange

        int customerId = 1;

        Cart cart = new Cart();

        when(cartRepository.findByCustomerId(customerId)).thenReturn(cart);

        // Act

        CartDTO result = cartService.getCartByCustomerId(customerId);

        // Assert

        assertEquals(cart.getId(), result.getId());

        assertEquals(cart.getCustomerId(), result.getCustomerId());

        assertEquals(cart.getProductIds(), result.getProductIds());

        assertEquals(cart.getTotalPrice(), result.getTotalPrice());

        assertEquals(cart.getTotalQuantity(), result.getTotalQuantity());

    }

    @Test

    void testAddToCartCustomerNotFound() throws CustomerNotFoundException {

        // Arrange

        int customerId = 1;

        int productId = 100;

        try {

			when(customerService.getCustomerById(anyInt())).thenReturn(null);

		} catch (CustomerNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

        // Act & Assert

        assertThrows(ProductNotFoundException.class, () -> cartService.addToCart(customerId, productId));

    }

    // Similarly, write tests for other methods handling other exceptions.

}
