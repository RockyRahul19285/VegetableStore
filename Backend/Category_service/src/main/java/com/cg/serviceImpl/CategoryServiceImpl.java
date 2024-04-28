package com.cg.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.CategoryDTO;
import com.cg.entity.Category;
import com.cg.exception.CategoryNotFoundException;
import com.cg.repository.CategoryRepository;
import com.cg.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		
		category.setCategoryName(categoryDTO.getCategoryName());
		categoryRepository.save(category);
		category.setCategoryId(category.getCategoryId());
		return categoryDTO;

	}

	@Override
	public String updateCategory(int categoryId, CategoryDTO categoryDTO) {
		try {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());
		category.setCategoryName(categoryDTO.getCategoryName());

		categoryRepository.save(category);
		
		}
		catch(CategoryNotFoundException e)
		{
			System.out.println(e);
			return "Category not updated";
		}
		return "Updated Successfully";

	}

	@Override
	public String removeCategory(int categoryId) {

		try {
			categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException());
			categoryRepository.deleteById(categoryId);
		}catch(CategoryNotFoundException e){
			System.out.println(e);
			return "Invalid id";
		}
		
		return "Deleted Successfully";
	}

	@Override
	public CategoryDTO searchCategoryByName(String name) {

		Category category = categoryRepository.findByCategoryName(name);

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;
	}

	@Override
	public CategoryDTO searchCategoryById(int id) {
		Category category = categoryRepository.findById(id).get();

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;

	}
	@Override
	public List<CategoryDTO> findAllCategories() {
		List<Category> categories = categoryRepository.findAll();

		List<CategoryDTO> categoryDTOs=new ArrayList<CategoryDTO>();
		for(Category category:categories)
		{
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTOs.add(categoryDTO);
		
		}

		return categoryDTOs;

	}

}
