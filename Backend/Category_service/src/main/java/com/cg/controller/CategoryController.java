package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CategoryDTO;
import com.cg.serviceImpl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
//@CrossOrigin(origins = "*") // Frontend Connection
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@PostMapping("/addCategory")
	public CategoryDTO addNewProduct(@RequestBody CategoryDTO categoryDTO) {

		return categoryServiceImpl.addCategory(categoryDTO);
	}

	@PutMapping("/updateCategory/{no}")
	public String updateCategory(@PathVariable(value = "no") int no, @RequestBody CategoryDTO categoryDTO) {
		categoryServiceImpl.updateCategory(no, categoryDTO);
		return "Updated Successfully";
	}

	@DeleteMapping("/deleteCategory/{no}")
	public String deleteCategory(@PathVariable(value = "no") int no) {
		categoryServiceImpl.removeCategory(no);
		return " Deleted Successfully";
	}
	@GetMapping("/categoryUsingName/{name}")
	public CategoryDTO getCategoryByName(@PathVariable(value = "name") String name) {
		return categoryServiceImpl.searchCategoryByName(name);
	}

	@GetMapping("/categoryUsingId/{id}")
	public CategoryDTO getCategoryById(@PathVariable(value = "id") int id) {
		return categoryServiceImpl.searchCategoryById(id);
	}

	@GetMapping("/findAll")
	public List<CategoryDTO> getAllCategory() {
		return categoryServiceImpl.findAllCategories();
	}
}

