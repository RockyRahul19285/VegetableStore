package com.cg.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.ProductDTO;
import com.cg.exception.ProductNotFoundException;

@FeignClient(name="ProductService",url = "http://localhost:8096" )
public interface ProductService {
	@GetMapping("/product/getById/{id}")
	public ProductDTO getProductById(@PathVariable(value = "id") int id) throws ProductNotFoundException ;

}
