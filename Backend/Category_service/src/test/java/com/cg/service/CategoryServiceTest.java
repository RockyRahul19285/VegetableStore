package com.cg.service;

 
 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
 
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
 
import com.cg.dto.CategoryDTO;

import com.cg.entity.Category;

import com.cg.repository.CategoryRepository;

import com.cg.serviceImpl.CategoryServiceImpl;
 
class CategoryServiceTest {
 
   @Mock

   private CategoryRepository categoryRepository;
 
   @InjectMocks

   private CategoryServiceImpl categoryService;
 
   @BeforeEach

   void setUp() {

       MockitoAnnotations.initMocks(this);

   }
 


   @Test

   void testUpdateCategory() {

       // Arrange

       int categoryId = 1;

       CategoryDTO categoryDTO = new CategoryDTO();

       categoryDTO.setCategoryName("Updated Category");
 
       Category category = new Category();

       category.setCategoryId(categoryId);

       category.setCategoryName("Original Category");
 
       when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
 
       // Act

       String result = categoryService.updateCategory(categoryId, categoryDTO);
 
       // Assert

       assertEquals("Updated Successfully", result);

       assertEquals(categoryDTO.getCategoryName(), category.getCategoryName());

   }
 
   @Test

   void testUpdateCategoryNotFound() {

       // Arrange

       int categoryId = 1;

       CategoryDTO categoryDTO = new CategoryDTO();

       categoryDTO.setCategoryName("Updated Category");
 
       when(categoryRepository.findById(categoryId)).thenReturn(null);
 
       // Act & Assert

   }
 
   @Test

   void testAddCategory() {

       // Arrange

       Category category = new Category();

       category.setCategoryId(1);

       category.setCategoryName("Test Category");
 
       when(categoryRepository.save(category)).thenReturn(category);
 
       // Act

       //CategoryDTO result = categoryService.removeCategory(category);
 
       // Assert

//       assertEquals(category.getCategoryId(), result.getCategoryId());

//       assertEquals(category.getCategoryName(), result.getCategoryName());

   }
 
   @Test

   void testRemoveCategory() {

       // Arrange

       int categoryId = 1;
 
       // Act

       String result = categoryService.removeCategory(categoryId);
 
       // Assert

      // assertEquals("Deleted Successfully", result);

   }
 
   @Test

   void testSearchCategoryByName() {

       // Arrange

       String categoryName = "Test Category";

       Category category = new Category();

       category.setCategoryId(1);

       category.setCategoryName(categoryName);
 
       when(categoryRepository.findByCategoryName(categoryName)).thenReturn(category);
 
       // Act

       CategoryDTO result = categoryService.searchCategoryByName(categoryName);
 
       // Assert

       assertEquals(category.getCategoryId(), result.getCategoryId());

       assertEquals(category.getCategoryName(), result.getCategoryName());

   }
 
   @Test

   void testSearchCategoryById() {

       // Arrange

       int categoryId = 1;

       Category category = new Category();

       category.setCategoryId(categoryId);

       category.setCategoryName("Test Category");
 
       when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
 
       // Act

       CategoryDTO result = categoryService.searchCategoryById(categoryId);
 
       // Assert

       assertEquals(category.getCategoryId(), result.getCategoryId());

       assertEquals(category.getCategoryName(), result.getCategoryName());

   }
 
  
 
   @Test

   void testFindAllCategories() {

       // Arrange

       List<Category> categories = new ArrayList<>();

       Category category1 = new Category();

       category1.setCategoryId(1);

       category1.setCategoryName("Category 1");

       Category category2 = new Category();

       category2.setCategoryId(2);

       category2.setCategoryName("Category 2");

       categories.add(category1);

       categories.add(category2);
 
       when(categoryRepository.findAll()).thenReturn(categories);
 
       // Act

       List<CategoryDTO> result = categoryService.findAllCategories();
 
       // Assert

       assertEquals(2, result.size());

       assertEquals(category1.getCategoryId(), result.get(0).getCategoryId());

       assertEquals(category1.getCategoryName(), result.get(0).getCategoryName());

       assertEquals(category2.getCategoryId(), result.get(1).getCategoryId());

       assertEquals(category2.getCategoryName(), result.get(1).getCategoryName());

   }

}
