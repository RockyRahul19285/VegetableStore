package com.cg.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.dto.CategoryDTO;

@FeignClient(name="CategoryService",url = "http://localhost:8092" )
public interface CategoryService {
	
	@GetMapping("/category/categoryUsingId/{id}")
	public CategoryDTO searchCategoryById(@PathVariable("id") int id);
}
