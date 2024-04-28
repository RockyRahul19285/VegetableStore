package com.cg.service;

import java.util.List;

import com.cg.dto.CategoryDTO;

public interface CategoryService {
	public CategoryDTO addCategory(CategoryDTO category);
	public String removeCategory(int categoryId);
	public String updateCategory(int categoryId,CategoryDTO categoryDTO);
	public CategoryDTO searchCategoryByName(String name);
	public CategoryDTO searchCategoryById(int id);
	public List<CategoryDTO> findAllCategories();
	
}
